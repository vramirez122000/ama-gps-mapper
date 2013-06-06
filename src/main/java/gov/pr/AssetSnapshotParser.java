package gov.pr;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.net.URL;

/**
 * User: victor
 */
public class AssetSnapshotParser {

    private AssetSnapshotParseListener listener;

    public AssetSnapshotParser(AssetSnapshotParseListener listener) {
        this.listener = listener;
    }

    public void parse(URL url) throws IOException {

        JsonFactory factory = new JsonFactory();
        // configure, if necessary:
        factory.enable(JsonParser.Feature.ALLOW_COMMENTS);
        JsonParser parser = factory.createParser(url);

        // Sanity check: verify that we got "Json Object":
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Expected data to start with an Object");
        }
        listener.parseBegin();

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            // Let's move to value
            parser.nextToken();
            if (fieldName.equals("timeStamp")) {
                LocalDateTime timeStamp = new LocalDateTime(parser.getLongValue());
                //check not stale;
            } else if (fieldName.equals("assetSnapshots")) {

                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    AssetSnapshot snapshot = read(parser);
                    listener.assetSnapshotParsed(snapshot);
                }

            } else { // ignore, or signal error?
                System.out.printf("Unrecognized field %s\n", fieldName);
            }
        }
        listener.parseEnd();
        parser.close();
    }

    private static AssetSnapshot read(JsonParser parser) throws IOException {
        AssetSnapshot result = new AssetSnapshot();
        // Iterate over object fields:
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            // Let's move to value
            parser.nextToken();
            if (fieldName.equals("assetId")) {
                result.setAssetId((parser.getIntValue()));
            } else if (fieldName.equals("status")) {
                result.setStatusCode(parser.getValueAsInt(0));
            } else if (fieldName.equals("trail")) {

                // Sanity check: verify that we got "Json Object":
                while (parser.nextToken() != JsonToken.END_ARRAY) {

                    LatLng latLng = new LatLng();
                    while (parser.nextToken() != JsonToken.END_OBJECT) {
                        String latLngFieldName = parser.getCurrentName();
                        // Let's move to value
                        parser.nextToken();
                        if (latLngFieldName.equals("lat")) {
                            latLng.setLat((parser.getDoubleValue()));
                        } else if (latLngFieldName.equals("lng")) {
                            latLng.setLng((parser.getDoubleValue()));
                        } else { // ignore, or signal error?
                            System.out.printf("Unrecognized field assetSnapshot.latLng.%s\n", latLngFieldName);
                        }
                    }
                    result.getTrail().add(latLng);
                }
            } else { // ignore, or signal error?
                System.out.printf("Unrecognized field assetSnapshot.%s\n", fieldName);
            }
        }

        return result;
    }
}