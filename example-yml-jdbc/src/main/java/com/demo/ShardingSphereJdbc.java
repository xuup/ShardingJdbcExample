package com.demo;

import com.demo.util.YamlDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShardingSphereJdbc {


    @Test
    public void shardingJdbcSelect(){
        try {
            DataSource dataSource = YamlDataSource.getDataSource();

            String sql = "select * from t_course where cid = ?";
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


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shardingInsert() throws IOException, SQLException {
        DataSource dataSource = YamlDataSource.getDataSource();
        Connection connection = dataSource.getConnection();

        String sql = "insert into t_course (course, cstatus, user_id) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0;i < 5;i++){
            preparedStatement.setString(1, "yaml-course-" + i);
            preparedStatement.setInt(2, 2);
            preparedStatement.setInt(3,i+10);

            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        connection.close();
    }
}
