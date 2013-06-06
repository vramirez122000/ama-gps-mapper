package gov.pr;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Query;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.text.WKTParser;
import org.geotools.referencing.CRS;
import org.opengis.filter.Filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: victor
 * Date: 6/6/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static final String serverUrl = "http://gps.pr.gov/amagps/assetSnapshots.json";
    public static final String outFie = "web/assets.json";

    public static void main(String[] args) throws Exception {


        final URL url = new URL(serverUrl);
        Timer  timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    AssetSnapshotParser parser = new AssetSnapshotParser(
                            new AssetSnapshotParseListenerAssetRouteUpdater(),
                            new AssetSnapshotParseListenerJsonWriter(new BufferedWriter(new FileWriter(outFie))));
                    parser.parse(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
        while(true) {
            Thread.sleep(5000);
        }

    }
}
