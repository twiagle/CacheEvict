package com.tjut.cacheEvict;

import com.tjut.cacheEvict.config.Config;

import java.util.HashMap;

/**
 * @author tb
 * @date 6/29/20-3:44 PM
 */
//cd jar's directory : java -cp  CacheEvict-1.0-SNAPSHOT.jar com.tjut.cacheEvict.Simulation ../test.properties
public class Simulation {
    public static void main(String[] args) {
        Config config = Config.getInstance(args[0]);
        System.out.println(config.getTrainDataFile());
    }
}
