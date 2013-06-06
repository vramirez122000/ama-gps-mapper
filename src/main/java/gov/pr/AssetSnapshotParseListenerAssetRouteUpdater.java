package gov.pr;

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
import java.util.List;

/**
 * User: victor
 */
public class AssetSnapshotParseListenerAssetRouteUpdater implements AssetSnapshotParseListener {

    private SimpleFeatureSource featureSource;

    public AssetSnapshotParseListenerAssetRouteUpdater() throws IOException {
        File file = new File("geofence/geofence.shp");
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();

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

       /* try {

            String filter;
            if(assetRoute.getPossibleRoutes().isEmpty()) {
                filter = String.format("CONTAINS(PATH, %s", toWKT(assetSnapshot.getTrail()));
            } else {
                StringBuilder builder = new StringBuilder();
                for (String possibleRoute : assetRoute.getPossibleRoutes()) {
                    possible
                }
                filter = String.format("")
            }


            SimpleFeatureCollection features = featureSource.getFeatures(CQL.toFilter(filter));

            assetRoute.getPossibleRoutes().clear();
            SimpleFeatureIterator iterator = features.features();
            while(iterator.hasNext()) {
                SimpleFeature currGeofence = iterator.next();
                if (!GeofenceType.ROUTE.equals(currGeofence.getAttribute("TYPE"))) {
                    return;
                }
                assetRoute.getPossibleRoutes().add((String) currGeofence.getAttribute("DESCRIPTIO"));
            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/


        /*List<Geofence> containingGeofences;
        if(v.getPossibleRoutes().isEmpty()) {
            containingGeofences = modelDao.getContainingGeofences(assetSnapshot.getTrail());
        } else {
            Set<Integer> possibleRoutes = v.getPossibleRoutesAsSet();
            containingGeofences = modelDao.getContainingGeofences(assetSnapshot.getTrail(), possibleRoutes.toArray(new Integer[possibleRoutes.size()]));
        }

        v.clearPossibleRoutes();
        for (Geofence containingGeofence : containingGeofences) {
            if(containingGeofence.getType() != Geofence.Type.ROUTE) {
                v.clearPossibleRoutes();
                modelDao.updateAssetRoute(v);
                return;
            }
            v.getPossibleRoutes().put(containingGeofence.getId(), true);
        }
        ImmutableSet<Integer> newPossibleRoutes = v.getPossibleRoutesAsSet();

        if(newPossibleRoutes.size() == 1) {
            v.setLastKnownRouteId(newPossibleRoutes.iterator().next());
        } else if(!newPossibleRoutes.contains(v.getLastKnownRouteId())) {
            v.setLastKnownRouteId(null);
        }
        modelDao.updateAssetRoute(v);*/
    }

    @Override
    public void parseEnd() {

    }

    private static String toWKT(List<LatLng> trail) {
        StringBuilder stringBuilder = new StringBuilder("LINESTRING(");
        for (LatLng latLng : trail) {
            stringBuilder.append(latLng.getLng()).append(" ").append(latLng.getLat()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
