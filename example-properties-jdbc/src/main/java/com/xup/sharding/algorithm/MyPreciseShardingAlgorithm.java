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

        logger.info("------algorithm------");
        logger.info(collection.size() + "");
        logger.info("分片键:{}", preciseShardingValue);

        return null;
    }
}
