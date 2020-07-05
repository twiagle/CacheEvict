package com.tjut.cacheEvict.cache;

import com.tjut.cacheEvict.config.Config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tb
 * @date 7/5/20-4:04 PM
 */
public class TmpLRUCache<K,V> extends LinkedHashMap<K,V> {

    public int getCapacity() {
        return capacity;
    }

    //定义缓存的容量
    private final int capacity;
    //带参数的构造器
    public TmpLRUCache(int capacity){
        //调用LinkedHashMap的构造器，传入以下参数
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    //实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest){
        System.out.println(eldest.getKey() + "=" + eldest.getValue());
        return size()>capacity;
    }
}
