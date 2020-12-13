import com.xup.sharding.ShardingJdbcApplication;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.connection.ShardingConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest(classes = ShardingJdbcApplication.class)
@RunWith(SpringRunner.class)
public class ShardingJdbcApplicationTest {

    Logger logger = LoggerFactory.getLogger(ShardingJdbcApplicationTest.class);


    @Resource
    private DataSource dataSource;

    @Test
    public void test(){
        logger.info("datasource {}", dataSource);
    }

    //1.标准分片策略配置
    // 对应 StandardShardingStrategy。
    // 提供对 SQ L语句中的 =, >, <, >=, <=, IN 和 BETWEEN AND 的分片操作支持
    @Test
    public void testStandardSharding() throws SQLException {
        String sql = "select * from course where user_id = 12";
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            logger.info(resultSet.getObject(1)+"");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void testRangeSharding() throws SQLException {
        String sql = "select * from course where user_id between 1 and 10 ";
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            logger.info(resultSet.getObject(1)+"");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void testComplexSharding() throws SQLException {
        String sql = "select * from course where user_id = 12 and cstatus = 1 ";
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            logger.info(resultSet.getObject(1)+"");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testHintSharding() throws SQLException {

        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        //hintManager.setDatabaseShardingValue(3);
        hintManager.addDatabaseShardingValue("course",1);
        hintManager.addTableShardingValue("course",1);

        //hintManager.setMasterRouteOnly();

        String sql = "select * from course";
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            logger.info(resultSet.getObject(1)+"");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

}
