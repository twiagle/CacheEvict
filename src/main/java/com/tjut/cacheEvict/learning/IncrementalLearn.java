package com.tjut.cacheEvict.learning;

import com.tjut.cacheEvict.sample.AbstractSampleLib;
import com.tjut.cacheEvict.sample.ILSampleLib;

/**
 * @author tb
 * @date 7/1/20-9:53 PM
 */
public class IncrementalLearn {

    ILSampleLib ilSampleLib;

    public IncrementalLearn(ILSampleLib ilSampleLib) {
        this.ilSampleLib = ilSampleLib;
    }

    public static int evict(int[] evictCandidates){
        int evictedSize = 0;
        int[] evictedObj = getEvictedObjsByCandidates(evictCandidates);


        ILSampleLib.addSubscribedObj(evictedObj);//subscribe evicted obj
        return evictedSize;
    }

    private static int[] getEvictedObjsByCandidates(int[] evictCandidates) {
        int[] evictedObj = null;


        return evictedObj;
    }

    public static void generateFirstBaseClassifier(AbstractSampleLib sampleLib){

    }
    public static void selfUpdate(){

    }
}
