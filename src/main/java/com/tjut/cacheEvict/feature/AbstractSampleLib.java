package com.tjut.cacheEvict.feature;

import com.tjut.cacheEvict.config.Request;

import java.util.*;

/**
 * @author tb
 * @date 7/3/20-1:00 PM
 */
public abstract class AbstractSampleLib {
    LinkedHashMap<Integer,TrainingSample> lib = new LinkedHashMap<>();
    abstract List<TrainingSample> generateSamples(Request req);
}
