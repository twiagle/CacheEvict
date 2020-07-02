package com.tjut.cacheEvict.feature;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.config.Request;
import sun.dc.pr.PRError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tb
 * @date 7/1/20-2:00 PM
 *
 * @date 7/2/30-4:38 PM
 * Feature stores
 */
public class Feature {
    private int size;
    private int type;
    private CycQueue cycQueue;

    private long lastTimeStamp;
    private int deltaNum;

    public void updateFeature(Request req){
        if(cycQueue == null){
            size = req.getSize();
            type = req.getType();
            deltaNum =  Config.getInstance().getFeatureNum();
            cycQueue = new CycQueue(deltaNum);
        }

        cycQueue.enQueue((int) (req.getReq() - lastTimeStamp));
        lastTimeStamp = req.getReq();
    }
    public int[] getFeatures(){
        int totalFeatureNum = deltaNum + 2;
        int[] features = new int[totalFeatureNum];// delta size type
        System.arraycopy(cycQueue.getDeltas(),0, features, 0, deltaNum);
        features[totalFeatureNum-2] = size;
        features[totalFeatureNum-1] = type;
        return features;
    }


}
