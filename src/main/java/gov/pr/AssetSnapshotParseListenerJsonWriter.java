package gov.pr;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: victor
 * Date: 6/6/13
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssetSnapshotParseListenerJsonWriter implements AssetSnapshotParseListener {

    private Writer out;
    private ObjectMapper objectMapper = new ObjectMapper();
    private boolean first = true;

    public AssetSnapshotParseListenerJsonWriter(Writer out) {
        if(out == null) {
            throw new IllegalArgumentException("cannot be null");
        }
        this.out = out;
    }

    @Override
    public void parseBegin() {
        try {
            StringWriter jsonOut = new StringWriter(20);
            objectMapper.writeValue(jsonOut, new Date());
            out.append("{\"timeStamp\":")
                    .append(jsonOut.toString())
                    .append(",\"assetSnapshots\":[");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void assetSnapshotParsed(AssetSnapshot assetSnapshot) {

        AssetRoute assetRoute = Globals.assetRoutes.get(assetSnapshot.getAssetId());
        if(assetRoute != null) {
            assetSnapshot.setRoute(assetRoute.getLastKnownRoute());
        }

        Asset asset = Globals.assets.get(assetSnapshot.getAssetId());
        if(asset != null) {
            assetSnapshot.setAssetDescription(asset.getDescription());
        }

        try {
            if (first) {
                first = false;
            } else {
                out.append(",");
            }
            String jsonOut = objectMapper.writer().writeValueAsString(assetSnapshot);
            out.append(jsonOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void parseEnd() {
        System.out.println("parse end");
        try {
            out.append("]}");
            out.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
