package com.xup.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class MyRangeShardingAlgorithm implements RangeShardingAlgorithm {

    Logger logger = LoggerFactory.getLogger(MyRangeShardingAlgorithm.class);

    @Override
    public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {
        int lowPoint = (Integer) rangeShardingValue.getValueRange().lowerEndpoint();
        int highPoint = (Integer) rangeShardingValue.getValueRange().upperEndpoint();
        logger.info("lowPoint:{},highPoint:{}", lowPoint, highPoint);
        Set<String> set = new LinkedHashSet<String>();
        for(int i=lowPoint;i<=highPoint;i++){
            for (Object databasename: collection){
                if(databasename.toString().endsWith(i % collection.size() + "")){
                    set.add(databasename.toString());
                }
            }
        }
        return set;
    }
}
