package com.tjut.cacheEvict.feature;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.config.Request;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author tb
 * @date 7/3/20-1:36 PM
 * maintain all the feature during the window
 * only maintain feature, ignore label
 */
public class FeatureLib {
    //maintain ID -> feature
    private final LinkedHashMap<Integer, Feature> lib = new LinkedHashMap<>(2<<15);
    private static FeatureLib featureLib = new FeatureLib();
    private FeatureLib(){};
    public static FeatureLib getInstance(){
        return featureLib;
    }

    //must be invoked for each request, after SampleLib filter
    public void updateFeatureLib(Request request) {
        int objID = request.getObjID();
        if (lib.containsKey(objID)) {
            lib.get(objID).updateFeature(request);
        }else {
            Feature feature = new Feature();
            feature.updateFeature(request);
            lib.put(objID, feature);
        }
        //remove obj out of window
        for (Integer expireObjID: getExpiredObject(request)) {
            lib.remove(expireObjID);
        }
    }

    public List<Integer> getExpiredObject(Request request) {
        int objID = request.getObjID();
        List<Integer> expiredObj = new ArrayList<>();

        while(true){
            Iterator<Map.Entry<Integer, Feature>> iterator = lib.entrySet().iterator();
            if(iterator.hasNext()){
                Map.Entry<Integer, Feature> entry = iterator.next();
                Integer expireObjID  = entry.getKey();
                Feature feature = entry.getValue();
                if(feature.getLastTimeStamp() <= request.getReqTimeStamp() - Config.getInstance().getBeladyBoundry()){
                    expiredObj.add(expireObjID);
                }else{
                    break;
                }
            }
        }
        return expiredObj;
    }

//    public <K, V> Map.Entry<K, V> getHeadByReflection(LinkedHashMap<K, V> map)
//            throws NoSuchFieldException, IllegalAccessException {
//        Field head = map.getClass().getDeclaredField("head");
//        head.setAccessible(true);
//        return (Map.Entry<K, V>) head.get(map);
//    }
//    public <K, V> Map.Entry<K, V> getHead(LinkedHashMap<K, V> map) {
//        return map.entrySet().iterator().next();
//    }

    public Feature getFeature(Integer objID){
        return lib.get(objID);
    }
}
