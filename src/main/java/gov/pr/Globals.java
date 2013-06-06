package gov.pr;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: victor
 * Date: 6/6/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class Globals {

    public static Map<Integer, AssetRoute> assetRoutes = Collections.synchronizedMap(new HashMap<Integer, AssetRoute>());
}
