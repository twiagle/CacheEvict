package com.tjut.cacheEvict.learning;

import com.tjut.cacheEvict.sample.AbstractSampleLib;
import com.tjut.cacheEvict.sample.ILSampleLib;
import moa.classifiers.meta.LearnNSE;

/**
 * @author tb
 * @date 7/1/20-9:53 PM
 */
public class IncrementalLearn {

    LearnNSE learnNSE;
    static IncrementalLearn incrementalLearn = new IncrementalLearn();

    public static IncrementalLearn getInstance() {
        return incrementalLearn;
    }

    private IncrementalLearn() {
        learnNSE = new LearnNSE();
        learnNSE.resetLearningImpl();
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
