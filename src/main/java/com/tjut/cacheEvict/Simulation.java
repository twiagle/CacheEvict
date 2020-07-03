package com.tjut.cacheEvict;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.feature.PreStudySampleLib;

import java.util.HashMap;

/**
 * @author tb
 * @date 6/29/20-3:44 PM
 */
//cd jar's directory : java -cp  CacheEvict-1.0-SNAPSHOT.jar com.tjut.cacheEvict.Simulation ../test.properties
public class Simulation {
    public static void main(String[] args) {
        init(args[0]);
        PreStudySampleLib preStudySampleLib = new PreStudySampleLib();
        preStudySampleLib.generateSamples(req);



    }
    static void init(String propertyFile){
        Config config = Config.getInstance(propertyFile);// this should be first
        System.out.println(config.getTrainDataFile());
    }
}
