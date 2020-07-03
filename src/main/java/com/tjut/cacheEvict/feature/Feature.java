package com.tjut.cacheEvict.feature;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.config.Request;

/**
 * @author tb
 * @date 7/1/20-2:00 PM
 *
 * @date 7/2/30-4:38 PM
 * Feature stores
 */
public class Feature {
    private int objID;
    private int size;
    private int type;
    private CycQueue cycQueue;

    private long lastTimeStamp;
    private int deltaNum;
    private int totalFeatureNum;

    public void updateFeature(Request req){
        if(cycQueue == null){
            objID = req.getObjID();
            size = req.getSize();
            type = req.getType();
            deltaNum =  Config.getInstance().getFeatureNum();
            totalFeatureNum = deltaNum + 2;
            cycQueue = new CycQueue(deltaNum);
        }

        cycQueue.enQueue((int) (req.getReqTimeStamp() - lastTimeStamp));
        lastTimeStamp = req.getReqTimeStamp();
    }
    public int[] getFeatures(){
        int[] features = new int[totalFeatureNum];// delta size type
        System.arraycopy(cycQueue.getDeltas(),0, features, 0, deltaNum);
        features[totalFeatureNum-2] = size;
        features[totalFeatureNum-1] = type;
        return features;
    }

    public int getTotalFeatureNum() {
        return totalFeatureNum;
    }

    public int getObjID() {
        return objID;
    }

    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    public CycQueue getCycQueue() {
        return cycQueue;
    }

    public long getLastTimeStamp() {
        return lastTimeStamp;
    }

    public int getDeltaNum() {
        return deltaNum;
    }
}
