package gov.pr;

/**
 * Created with IntelliJ IDEA.
 * User: victor
 * Date: 6/6/13
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AssetSnapshotParseListener {

    void parseBegin();

    void assetSnapshotParsed(AssetSnapshot assetSnapshot);

    void parseEnd();

}
