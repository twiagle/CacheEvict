package com.tjut.cacheEvict.feature;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.config.Request;

/**
 * @author tb
 * @date 7/3/20-1:15 PM
 */
public class TrainingSample {
    private int[] trainingSample;

    //SampleLib !contains, and put an unlabeled training sample into lib, the feature is done but waiting for label
    //invoked when reused or expired
    public TrainingSample(Integer objID, long curTimeStamp){
        Feature feature = FeatureLib.getInstance().getFeature(objID);
        trainingSample =  new int[feature.getTotalFeatureNum() + 1];
        System.arraycopy(feature.getFeatures(),0,trainingSample,0,feature.getTotalFeatureNum());
        trainingSample[feature.getTotalFeatureNum() - 1] = (int) (curTimeStamp - feature.getLastTimeStamp());
    }

    public int[] getTrainingSample() {
        return trainingSample;
    }
}
