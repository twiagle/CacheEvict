package com.tjut.cacheEvict.feature;

import java.util.HashMap;

/**
 * @author tb
 * @date 7/1/20-2:00 PM
 */
public class Feature {
    private static Feature feature = new Feature();
    //id, size
    private final int SAMPLE = 64;
    private long cacheSize = 0;
    //overall single cache map
    private final HashMap<Integer, Integer> map = new HashMap<>(65536);

    private Feature(){};
    public static Feature getInstance(){
        return feature;
    }
}
