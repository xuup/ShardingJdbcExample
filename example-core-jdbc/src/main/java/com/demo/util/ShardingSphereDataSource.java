package com.demo.util;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingSphereDataSource {


    /**
     * 配置数据库，数据表生成数据源并返回
     * @return
     */
    public static DataSource getShardingDataSource(){

        Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

        //配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/edu_db_1?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");

        dataSourceMap.put("ds0", dataSource1);

        //配置第二个数据源
        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/edu_db_2?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");

        dataSourceMap.put("ds1", dataSource2);



        //配置表course规则
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("t_course","ds$->{0..1}.course_${1..2}");

        //配置分库策略
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id","ds${user_id % 2}"));

        //配置分片策略 包含分片键和分片算法
        tableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("cid","course_${cid % 2 + 1}"));
        tableRuleConfiguration.setKeyGeneratorConfig(getKeyGeneratorConfiguration());

        //配置分片规则
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);

        //获取数据源对象
        DataSource dataSource = null;
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;

    }


    /**
     * 雪花算法生成主键
     * @return
     */
    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration(){
        KeyGeneratorConfiguration key = new KeyGeneratorConfiguration("SNOWFLAKE", "cid");
        return key;
    }


}
