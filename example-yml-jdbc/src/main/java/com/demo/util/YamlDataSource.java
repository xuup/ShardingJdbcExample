package com.demo.util;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class YamlDataSource {

    static final String yamlPath = "/Users/xupeng/Documents/workSpace/ShardingJdbcExample/example-yml-jdbc/src/main/resources/shardingConfig.yaml";

    public static DataSource getDataSource() throws IOException, SQLException {
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File(yamlPath));
        return dataSource;
    }
}
