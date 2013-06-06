package gov.pr;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.text.cql2.CQL;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: victor
 */
public class AssetSnapshotParseListenerAssetRouteUpdater implements AssetSnapshotParseListener {

    private SimpleFeatureSource featureSource;

    public AssetSnapshotParseListenerAssetRouteUpdater() throws IOException {
        File file = new File("geofence/geofence.shp");
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        this.featureSource = store.getFeatureSource();
    }

    @Override
    public void parseBegin() {

    }

    @Override
    public void assetSnapshotParsed(AssetSnapshot assetSnapshot) {
        if (!Globals.assetRoutes.containsKey(assetSnapshot.getAssetId())) {
            AssetRoute v = new AssetRoute();
            v.setAssetId(assetSnapshot.getAssetId());
            Globals.assetRoutes.put(v.getAssetId(), v);
        }

        AssetRoute assetRoute = Globals.assetRoutes.get(assetSnapshot.getAssetId());

        try {

            /*String filter;
            if (assetRoute.getPossibleRoutes().isEmpty()) {
                filter = String.format("CONTAINS(PATH, %s)", toWKT(assetSnapshot.getTrail()));
            } else {
                StringBuilder builder = new StringBuilder("( TYPE <> 'ROUTE' OR ");
                for (String possibleRoute : assetRoute.getPossibleRoutes()) {
                    builder.append("DESCRIPTIO = '" + possibleRoute + "' OR");
                }
                builder.append(") AND ");
                filter = builder.append(String.format(" CONTAINS(PATH, %s)", toWKT(assetSnapshot.getTrail()))).toString();
            }
            System.out.println(filter);*/

            WKTReader reader = new WKTReader();
            Geometry trail = reader.read(toWKT(assetSnapshot.getTrail()));

            SimpleFeatureCollection features = featureSource.getFeatures();

            assetRoute.getPossibleRoutes().clear();
            SimpleFeatureIterator iterator = features.features();

            Set<String> newPossibleRoutes = new HashSet<>();
            while (iterator.hasNext()) {
                SimpleFeature currGeofence = iterator.next();
                Geometry defaultGeometry = (Geometry) currGeofence.getDefaultGeometry();
                if(defaultGeometry.contains(trail)) {
                    if (!GeofenceType.ROUTE.name().equals(currGeofence.getAttribute("TYPE"))) {
                        assetRoute.getPossibleRoutes().clear();
                        return;
                    }
                    if(assetRoute.getPossibleRoutes().isEmpty()) {
                        newPossibleRoutes.add((String) currGeofence.getAttribute("DESCRIPTIO"));
                    } else if(assetRoute.getPossibleRoutes().contains(currGeofence.getAttribute("DESCRIPTIO"))) {
                        newPossibleRoutes.add((String) currGeofence.getAttribute("DESCRIPTIO"));
                    }

                }
            }

            assetRoute.setPossibleRoutes(newPossibleRoutes);
            if(assetRoute.getPossibleRoutes().size() == 1) {
                assetRoute.setLastKnownRoute(assetRoute.getPossibleRoutes().iterator().next());
            } else if(!assetRoute.getPossibleRoutes().contains(assetRoute.getLastKnownRoute())) {
                assetRoute.setLastKnownRoute(null);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void parseEnd() {

    }

    private static String toWKT(List<LatLng> trail) {

        if(trail.size() == 1) {
            return String.format("POINT(%s %s)",trail.get(0).getLng(), trail.get(0).getLat());
        }

        StringBuilder stringBuilder = new StringBuilder("LINESTRING(");
        for (LatLng latLng : trail) {
            stringBuilder.append(latLng.getLng()).append(" ").append(latLng.getLat()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
