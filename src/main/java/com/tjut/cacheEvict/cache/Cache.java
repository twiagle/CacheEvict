package com.tjut.cacheEvict.cache;

import com.tjut.cacheEvict.config.Config;
import com.tjut.cacheEvict.learning.IncrmntLearn;

import java.util.HashMap;
import java.util.Random;

/**
 * @author tb
 * @date 7/1/20-1:35 PM
 * cache size should smaller than FeatureLib, so if sampled objs cannot find in FeatureLib,just remove
 */
public class Cache {
    //singleton
    private static Cache cache;
    //id, size
    private int sampleNum;
    private long maxCacheSize;
    private long usedCacheSize;//usedCacheSize
    //overall single cache map
    private HashMap<Integer, Integer> map;

    private Cache(){};
    public static Cache getInstance(){
        if(cache == null){
            cache = new Cache();
            cache.maxCacheSize = Config.getInstance().getMaxCacheSize();
            cache.sampleNum = Config.getInstance().getSampleNum();
            cache.usedCacheSize = 0;
            cache.map = new HashMap<>(65536);
        }
        return cache;
    }
    //randomly get 64 keys from keySet
    public Integer[] getSampledKeys(){
        Random generator = new Random();
        Integer[] keySet = (Integer[])map.keySet().toArray();
        Integer[] sampledKeys = new Integer[cache.sampleNum];
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
        usedCacheSize += size;
        while(usedCacheSize > maxCacheSize){
            int evictedSize = IncrmntLearn.evict(getSampledKeys());
            usedCacheSize -= evictedSize;
        }
    }

}
