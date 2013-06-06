package gov.pr;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.GeometryBuilder;

import java.io.File;
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
    public static Map<Integer, Asset> assets = Collections.synchronizedMap(new HashMap<Integer, Asset>());

    static {
        assets.put(856, new Asset(856, "2002-01"));
        assets.put(883, new Asset(883, "2002-02"));
        assets.put(819, new Asset(819, "2002-03"));
        assets.put(818, new Asset(818, "2002-04"));
        assets.put(884, new Asset(884, "2002-05"));
        assets.put(824, new Asset(824, "2002-06"));
        assets.put(817, new Asset(817, "2002-07"));
        assets.put(820, new Asset(820, "2002-08"));
        assets.put(928, new Asset(928, "2002-09"));
        assets.put(825, new Asset(825, "2002-10"));
        assets.put(843, new Asset(843, "2004-01"));
        assets.put(864, new Asset(864, "2004-02"));
        assets.put(839, new Asset(839, "2004-03"));
        assets.put(838, new Asset(838, "2004-04"));
        assets.put(893, new Asset(893, "2004-05"));
        assets.put(822, new Asset(822, "2004-06"));
        assets.put(941, new Asset(941, "2004-07"));
        assets.put(887, new Asset(887, "2004-08"));
        assets.put(851, new Asset(851, "2004-09"));
        assets.put(863, new Asset(863, "2004-10"));
        assets.put(879, new Asset(879, "2004-11"));
        assets.put(844, new Asset(844, "2004-12"));
        assets.put(858, new Asset(858, "2004-13"));
        assets.put(829, new Asset(829, "2004-14"));
        assets.put(841, new Asset(841, "2004-15"));
        assets.put(826, new Asset(826, "2004-16"));
        assets.put(892, new Asset(892, "2004-17"));
        assets.put(852, new Asset(852, "2004-18"));
        assets.put(827, new Asset(827, "2004-19"));
        assets.put(949, new Asset(949, "2004-20"));
        assets.put(840, new Asset(840, "2004-21"));
        assets.put(847, new Asset(847, "2004-22"));
        assets.put(834, new Asset(834, "2004-23"));
        assets.put(865, new Asset(865, "2004-24"));
        assets.put(962, new Asset(962, "2004-25"));
        assets.put(830, new Asset(830, "2004-26"));
        assets.put(842, new Asset(842, "2004-27"));
        assets.put(837, new Asset(837, "2004-28"));
        assets.put(872, new Asset(872, "2004-29"));
        assets.put(869, new Asset(869, "2004-30"));
        assets.put(861, new Asset(861, "2005-01"));
        assets.put(876, new Asset(876, "2005-02"));
        assets.put(853, new Asset(853, "2005-03"));
        assets.put(828, new Asset(828, "2005-05"));
        assets.put(929, new Asset(929, "2005-06"));
        assets.put(867, new Asset(867, "2005-07"));
        assets.put(868, new Asset(868, "2005-08"));
        assets.put(860, new Asset(860, "2005-09"));
        assets.put(886, new Asset(886, "2005-11"));
        assets.put(943, new Asset(943, "2005-12"));
        assets.put(875, new Asset(875, "2005-13"));
        assets.put(926, new Asset(926, "2005-14"));
        assets.put(890, new Asset(890, "2005-15"));
        assets.put(896, new Asset(896, "2005-17"));
        assets.put(859, new Asset(859, "2005-18"));
        assets.put(854, new Asset(854, "2005-19"));
        assets.put(939, new Asset(939, "2005-20"));
        assets.put(889, new Asset(889, "2005-21"));
        assets.put(880, new Asset(880, "2005-22"));
        assets.put(882, new Asset(882, "2005-23"));
        assets.put(832, new Asset(832, "2005-24"));
        assets.put(874, new Asset(874, "2005-25"));
        assets.put(878, new Asset(878, "2005-26"));
        assets.put(927, new Asset(927, "2005-27"));
        assets.put(954, new Asset(954, "2005-28"));
        assets.put(836, new Asset(836, "2005-29"));
        assets.put(835, new Asset(835, "2005-30"));
        assets.put(877, new Asset(877, "2005-31"));
        assets.put(948, new Asset(948, "2005-32"));
        assets.put(873, new Asset(873, "2005-33"));
        assets.put(846, new Asset(846, "2005-34"));
        assets.put(831, new Asset(831, "2007-01"));
        assets.put(960, new Asset(960, "2007-02"));
        assets.put(934, new Asset(934, "2007-03"));
        assets.put(850, new Asset(850, "2007-04"));
        assets.put(932, new Asset(932, "2007-05"));
        assets.put(845, new Asset(845, "2007-06"));
        assets.put(862, new Asset(862, "2007-07"));
        assets.put(931, new Asset(931, "2007-08"));
        assets.put(933, new Asset(933, "2007-09"));
        assets.put(888, new Asset(888, "2007-10"));
        assets.put(848, new Asset(848, "2007-11"));
        assets.put(881, new Asset(881, "2007-12"));
        assets.put(866, new Asset(866, "2007-13"));
        assets.put(891, new Asset(891, "2007-14"));
        assets.put(823, new Asset(823, "2007-15"));
        assets.put(894, new Asset(894, "2007-16"));
        assets.put(821, new Asset(821, "2007-17"));
        assets.put(870, new Asset(870, "2007-18"));
        assets.put(930, new Asset(930, "2007-19"));
        assets.put(855, new Asset(855, "2007-20"));
        assets.put(942, new Asset(942, "2007-21"));
        assets.put(895, new Asset(895, "2007-22"));
        assets.put(959, new Asset(959, "2007-23"));
        assets.put(964, new Asset(964, "2007-24"));
        assets.put(849, new Asset(849, "2007-25"));
        assets.put(871, new Asset(871, "2007-26"));
        assets.put(857, new Asset(857, "2007-27"));
        assets.put(885, new Asset(885, "2007-28"));
        assets.put(833, new Asset(833, "2007-29"));
        assets.put(940, new Asset(940, "2007-30"));
        assets.put(1007, new Asset(1007, "2010-01"));
        assets.put(996, new Asset(996, "2010-02"));
        assets.put(1032, new Asset(1032, "2010-03"));
        assets.put(1020, new Asset(1020, "2010-04"));
        assets.put(1024, new Asset(1024, "2010-05"));
        assets.put(1016, new Asset(1016, "2010-06"));
        assets.put(1017, new Asset(1017, "2010-07"));
        assets.put(1010, new Asset(1010, "2010-08"));
        assets.put(995, new Asset(995, "2010-09"));
        assets.put(1025, new Asset(1025, "2010-10"));
        assets.put(997, new Asset(997, "2010-11"));
        assets.put(1001, new Asset(1001, "2010-12"));
        assets.put(993, new Asset(993, "2010-13"));
        assets.put(994, new Asset(994, "2010-14"));
        assets.put(1005, new Asset(1005, "2010-15"));
        assets.put(1019, new Asset(1019, "2010-16"));
        assets.put(1002, new Asset(1002, "2010-17"));
        assets.put(1003, new Asset(1003, "2010-18"));
        assets.put(1027, new Asset(1027, "2010-19"));
        assets.put(1012, new Asset(1012, "2010-20"));
        assets.put(1030, new Asset(1030, "2010-21"));
        assets.put(1006, new Asset(1006, "2010-22"));
        assets.put(1028, new Asset(1028, "2010-23"));
        assets.put(1021, new Asset(1021, "2010-24"));
        assets.put(1009, new Asset(1009, "2010-25"));
        assets.put(1015, new Asset(1015, "2010-26"));
        assets.put(1004, new Asset(1004, "2010-27"));
        assets.put(999, new Asset(999, "2010-28"));
        assets.put(1029, new Asset(1029, "2010-29"));
        assets.put(1008, new Asset(1008, "2010-30"));
        assets.put(1018, new Asset(1018, "2010-31"));
        assets.put(998, new Asset(998, "2010-32"));
        assets.put(1014, new Asset(1014, "2010-33"));
        assets.put(1026, new Asset(1026, "2010-34"));
        assets.put(1011, new Asset(1011, "2010-35"));
        assets.put(1013, new Asset(1013, "2010-36"));
        assets.put(1022, new Asset(1022, "2010-37"));
        assets.put(1000, new Asset(1000, "2010-38"));
        assets.put(1031, new Asset(1031, "2010-39"));
        assets.put(1023, new Asset(1023, "2010-40"));

    }


}
