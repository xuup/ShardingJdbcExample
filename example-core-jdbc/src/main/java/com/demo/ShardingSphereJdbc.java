package com.demo;

import com.demo.util.ShardingSphereDataSource;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ShardingSphereJdbc {

    //数据源
    private static final DataSource dataSource = ShardingSphereDataSource.getShardingDataSource();

    /**
     * 查询
     * 根据cid 进行查询，当cid 为偶数，在表 course_1中查询，当cid为奇数，在表course_2中查询
     *
     * 在没有指定数据库的前提下，会在两个库中进行查询，返回两个库中的查询结果
     */
    @Test
    public void shardingSelect() throws SQLException {

        String sql = "select * from t_course where cid = ? ";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,2);
        ResultSet rs =  preparedStatement.executeQuery();
        while (rs.next()){
            System.out.print(rs.getObject(1) + " " + rs.getString(2) + "\n");
        }
        rs.close();
        preparedStatement.close();
        connection.close();
    }


    /**
     * 新增数据
     * 如果user_id 字段值为偶数，则在ds0这个库中新增，否则在ds1库中新增
     *
     * 如果不包含user_id字段，即不会路由至指定的数据库，会在两个库中都新增数据
     * @throws SQLException
     */
    @Test
    public void shardingInsert() throws SQLException {
        String sql = "insert into t_course (course, cstatus, user_id) values (?, ?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(int i = 5; i < 10; i++){
            preparedStatement.setString(1, "course-" + i);
            preparedStatement.setInt(2,1);
            preparedStatement.setInt(3, i);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        connection.close();

    }





}
