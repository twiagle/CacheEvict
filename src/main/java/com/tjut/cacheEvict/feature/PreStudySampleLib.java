package com.tjut.cacheEvict.feature;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.config.Request;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author tb
 * @date 7/3/20-1:07 PM
 * listen for all objects
 * only put each obj once into this lab to avoid popular bias
 */
public class PreStudySampleLib extends AbstractSampleLib {
    public PreStudySampleLib(){}

    @Override
    public List<TrainingSample> generateSamples(Request req){
        //first update featureLib

        List<TrainingSample> sample = new ArrayList<>();
        //expire window
        List<Integer> expiredObject = FeatureLib.getInstance().getExpiredObject(req);
        if(expiredObject != null && expiredObject.size()>0){
            for (Integer objID : expiredObject) {
                Feature feature = FeatureLib.getInstance().getFeature(objID);
                TrainingSample ts = new TrainingSample(objID, Config.getInstance().getBeladyBoundry());
                sample.add(ts);
            }
        }

        //add each obj only once
        int objID = req.getObjID();
        if(lib.containsKey(objID)){
        }else{//get sample in the window
            Feature feature = FeatureLib.getInstance().getFeature(req.getObjID());
            TrainingSample ts = new TrainingSample(objID,req.getReqTimeStamp());
            sample.add(ts);
            lib.put(req.getObjID(), ts);
        }
        //
        FeatureLib.getInstance().updateFeatureLib(req);
        return sample;
    }
}
