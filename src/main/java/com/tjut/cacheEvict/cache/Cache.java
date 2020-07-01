package com.tjut.cacheEvict.cache;

import java.util.HashMap;
import java.util.Random;

/**
 * @author tb
 * @date 7/1/20-1:35 PM
 */
public class Cache {
    //singleton
    private static Cache cache = new Cache();
    //id, size
    private final static int SAMPLE = 64;
    private final static long MAX_CACHE_SIZE = 111;
    private long usedCacheSize = 0;//usedCacheSize
    //overall single cache map
    private final HashMap<Integer, Integer> map = new HashMap<>(65536);

    private Cache(){};
    public static Cache getInstance(){
        return cache;
    }
    //randomly get 64 keys from keySet
    public Integer[] getSampledKeys(){
        Random generator = new Random();
        Integer[] keySet = (Integer[])map.keySet().toArray();
        Integer[] sampledKeys = new Integer[SAMPLE];
        for (int i = 0; i < 64; i++) {
            sampledKeys[i] = keySet[generator.nextInt(keySet.length)];
        }
        return sampledKeys;
    }
    public boolean contains(Integer key){
        return map.containsKey(key);
    }
    public int getObjectSize(Integer key){
        return map.get(key);
    }
    public void remove(Integer key){
        map.remove(key);
    }
    public void remove(Integer[] keys){
        for (Integer key : keys) {
            remove(key);
        }
    }

    public void put(Integer key, Integer size){
        map.put(key, size);
    }

}
