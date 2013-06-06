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
        SimpleFeatureIterator iterator = null;
        try {

            WKTReader reader = new WKTReader();
            Geometry trail = reader.read(toWKT(assetSnapshot.getTrail()));

            SimpleFeatureCollection features = featureSource.getFeatures();
            iterator = features.features();
            Set<String> newPossibleRoutes = new HashSet<>();
            while (iterator.hasNext()) {
                SimpleFeature currGeofence = iterator.next();
                Geometry defaultGeometry = (Geometry) currGeofence.getDefaultGeometry();
                if (defaultGeometry.contains(trail)) {
                    if (!GeofenceType.ROUTE.name().equals(currGeofence.getAttribute("TYPE"))) {
                        assetRoute.getPossibleRoutes().clear();
                        return;
                    }
                    String route = (String) currGeofence.getAttribute("DESCRIPTIO");
                    if (assetRoute.getPossibleRoutes().isEmpty()) {
                        newPossibleRoutes.add(route);
                    } else if (assetRoute.getPossibleRoutes().contains(route)) {
                        newPossibleRoutes.add(route);
                    }
                }
            }

            assetRoute.setPossibleRoutes(newPossibleRoutes);
            if (assetRoute.getPossibleRoutes().size() == 1) {
                assetRoute.setLastKnownRoute(assetRoute.getPossibleRoutes().iterator().next());
            } else if (!assetRoute.getPossibleRoutes().contains(assetRoute.getLastKnownRoute())) {
                assetRoute.setLastKnownRoute(null);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (iterator != null) {
                iterator.close();
            }
        }
    }

    @Override
    public void parseEnd() {

    }

    private static String toWKT(List<LatLng> trail) {

        if (trail.size() == 1) {
            return String.format("POINT(%s %s)", trail.get(0).getLng(), trail.get(0).getLat());
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
