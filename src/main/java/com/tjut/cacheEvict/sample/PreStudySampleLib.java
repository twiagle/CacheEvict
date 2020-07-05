package com.tjut.cacheEvict.sample;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.config.Request;
import com.tjut.cacheEvict.feature.FeatureLib;

import java.util.*;

/**
 * @author tb
 * @date 7/3/20-1:07 PM
 * listen for all objects
 * only put each obj once into this lab to avoid popular bias
 */
public class PreStudySampleLib extends AbstractSampleLib {
    public PreStudySampleLib(){}

    public void generateSamples(Request req){
        //expire window
        List<Integer> expiredObject = FeatureLib.getInstance().getExpiredObject(req);
        if(expiredObject != null && expiredObject.size()>0){
            for (Integer objID : expiredObject) {
                TrainingSample ts = new TrainingSample(objID, Config.getInstance().getBeladyBoundry());
                labeledFeatureLib.put(objID, ts);//expired obj
            }
        }

        //add each obj only once
        int objID = req.getObjID();
        if(labeledFeatureLib.containsKey(objID)){
            TrainingSample ts = new TrainingSample(objID,req.getReqTimeStamp());
            labeledFeatureLib.put(req.getObjID(), ts);//mark as generated
        }
    }
}
