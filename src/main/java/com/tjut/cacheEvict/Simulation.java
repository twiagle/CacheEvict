package com.tjut.cacheEvict;

import com.tjut.cacheEvict.cache.Cache;
import com.tjut.cacheEvict.cache.TmpLRUCache;
import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.config.Request;
import com.tjut.cacheEvict.feature.FeatureLib;
import com.tjut.cacheEvict.sample.ILSampleLib;
import com.tjut.cacheEvict.sample.PreStudySampleLib;
import com.tjut.cacheEvict.learning.IncrementalLearn;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author tb
 * @date 6/29/20-3:44 PM
 */
//cd jar's directory : java -cp  CacheEvict-1.0-SNAPSHOT.jar com.tjut.cacheEvict.Simulation ../test.properties
public class Simulation {
    static long timeStamp = 0;
    static TmpLRUCache<Integer,Integer> tmpLRU;
    static Config config;
    static Cache cache;

    public static void main(String[] args) {
        init(args[0]);

        PreStudySampleLib preStudySampleLib = new PreStudySampleLib();
        try (BufferedReader reader = new BufferedReader(new FileReader(config.getTrainDataFile()))) {
            String line;
            //warming up features
            while(timeStamp < 1000000
            && (line = reader.readLine()) != null){
                timeStamp++;
                /*sparse request*/
                final Request req = parseRequest(line);
                /*updateFeature*/
                FeatureLib.getInstance().updateFeatureLib(req);
            }

            //generate first classifier
            while(preStudySampleLib.getLabeledFeatureLib().size() < config.getTrainingInterval()
            && (line = reader.readLine()) != null){
                timeStamp++;
                /*sparse request*/
                final Request req = parseRequest(line);
                /*AOP listening*/
                preStudySampleLib.generateSamples(req);
                /*updateFeature*/
                FeatureLib.getInstance().updateFeatureLib(req);
                /* put in cache*/
                tmpLRU.put(req.getObjID(), req.getSize());// k - v ,here size simulate v
            }
            IncrementalLearn.generateFirstBaseClassifier(preStudySampleLib);
            preStudySampleLib = null;

            //start prediction and model self-update
            cache = Cache.ConfigInstance(tmpLRU);
            tmpLRU.clear();
            int totalReq = 0;
            int hit = 0;

            //cache
            while ((line = reader.readLine()) != null) {
                timeStamp++;
                totalReq++;
                /*sparse request*/
                final Request req = parseRequest(line);
                /*AOP listening*/
                ILSampleLib.generateSamples(req);
                /*updateFeature*/
                FeatureLib.getInstance().updateFeatureLib(req);
                /* put in cache*/
                if (cache.contains(req.getObjID())) {
                    hit++;
                }else{
                    cache.put(req.getObjID(), req.getSize());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void init(String propertyFile){
        config = Config.ConfigInstance(propertyFile);// this should be first
        int capacity = (int)Math.ceil(Config.getInstance().getMaxCacheSize()/ 0.75f) + 1;
        tmpLRU = new TmpLRUCache<>(capacity);

        System.out.println(config.getTrainDataFile());
    }

    static Request parseRequest(String line) {
        timeStamp++;
        Request req = new Request();
        String[] trace = line.split(" ");
        req.setReqTimeStamp(timeStamp);
        req.setObjID(Integer.parseInt(trace[1]));
        req.setSize(Integer.parseInt(trace[2]));
        req.setType(Integer.parseInt(trace[3]));
        return req;
    }
}
