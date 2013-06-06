package gov.pr;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: victor
 * Date: 6/6/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssetRoute {

    private Integer assetId;
    private String lastKnownRoute;
    private Set<String> possibleRoutes = new HashSet<>();

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getLastKnownRoute() {
        return lastKnownRoute;
    }

    public void setLastKnownRoute(String lastKnownRoute) {
        this.lastKnownRoute = lastKnownRoute;
    }

    public Set<String> getPossibleRoutes() {
        return possibleRoutes;
    }

    public void setPossibleRoutes(Set<String> possibleRoutes) {
        if(possibleRoutes == null) throw new IllegalArgumentException("cannot be null");
        this.possibleRoutes = possibleRoutes;
    }
}
