package com.xup.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm {

    Logger logger = LoggerFactory.getLogger(MyPreciseShardingAlgorithm.class);

    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {


        for(Object databaseName: collection){
            if (databaseName.toString().endsWith((Integer)preciseShardingValue.getValue()%2 + "")){
                logger.info("路由至ds{}", (Integer)preciseShardingValue.getValue()%2);
                return databaseName.toString();
            }
        }

        throw new IllegalArgumentException();
    }
}
