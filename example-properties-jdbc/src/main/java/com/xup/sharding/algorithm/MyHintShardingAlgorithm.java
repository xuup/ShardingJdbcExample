package com.xup.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class MyHintShardingAlgorithm implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {

        Set<String> set = new LinkedHashSet<>();
        for (Object databaseName:collection){
            for(Object hintvalue:hintShardingValue.getValues()){
                if (databaseName.toString().endsWith(hintvalue + "")){
                    set.add(databaseName.toString());
                }
            }
        }

        return set;
    }
}
