package gov.pr;

import java.util.List;
import java.util.Set;

/**
 * User: victor
 */
public class AssetSnapshotParseListenerAssetRouteUpdater implements AssetSnapshotParseListener {

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

        AssetRoute v = Globals.assetRoutes.get(assetSnapshot.getAssetId());
        /*List<Geofence> containingGeofences;
        if(v.getPossibleRouteIds().isEmpty()) {
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
            v.getPossibleRouteIds().put(containingGeofence.getId(), true);
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
}
