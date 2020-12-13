package com.xup.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MyComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm {

    Logger logger = LoggerFactory.getLogger(MyComplexShardingAlgorithm.class);

    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        logger.info("complexKeysShardingValue:{}", complexKeysShardingValue);
        // 得到每个分片健对应的值
        Collection<Integer> cstatuValues = this.getShardingValue(complexKeysShardingValue, "cstatus");
        Collection<Integer> userIdValues = this.getShardingValue(complexKeysShardingValue, "user_id");

        List<String> shardingSuffix = new ArrayList<>();
        // 对两个分片健同时取模的方式分库
        for (Integer userId : userIdValues) {
            for (Integer status : cstatuValues) {
                String suffix = (userId + status) % 2 + "";
                for (Object databaseName : collection) {
                    if (databaseName.toString().endsWith(suffix)) {
                        shardingSuffix.add(databaseName.toString());
                    }
                }
            }
        }

        return shardingSuffix;
    }

    private Collection<Integer> getShardingValue(ComplexKeysShardingValue<Integer> shardingValues, final String key) {
        Collection<Integer> valueSet = new ArrayList<>();
        Map<String, Collection<Integer>> columnNameAndShardingValuesMap = shardingValues.getColumnNameAndShardingValuesMap();
        if (columnNameAndShardingValuesMap.containsKey(key)) {
            valueSet.addAll(columnNameAndShardingValuesMap.get(key));
        }
        return valueSet;
    }
}
