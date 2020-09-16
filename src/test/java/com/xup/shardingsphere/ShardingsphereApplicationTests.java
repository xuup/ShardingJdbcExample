package com.xup.shardingsphere;

import com.xup.shardingsphere.Mapper.CourseMapper;
import com.xup.shardingsphere.Mapper.UdictMapper;
import com.xup.shardingsphere.Mapper.UserMapper;
import com.xup.shardingsphere.entity.Course;
import com.xup.shardingsphere.entity.Udict;
import com.xup.shardingsphere.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingsphereApplicationTests {


    @Autowired
    private CourseMapper courseMapper;


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UdictMapper udictMapper;

    @Test
    void contextLoads() {

    }


    //===========测试水平分库============
    @Test
    public void addCourseDb(){
        courseMapper.intsert("name1", 21L, "OK");
    }


    //============测试水平分表===========

    @Test
    public void addCourse(){

        for (int i=0;i<5;i++){
            courseMapper.intsert("name3", 12L, "OK");
        }
    }

    //============测试垂直分库分表=========
    @Test
    public void addUserDb(){
        User user = new User();
        user.setUserName("lucy");
        user.setUserStatus("ok");
        userMapper.insertUser(user);
    }



    //============测试公共表==========
    @Test
    public void addDict(){
        Udict udict = new Udict();
        udict.setUstatus("ok");
        udict.setUvalue("启用");
        udictMapper.insertUdict(udict);
    }


    @Test
    public void deleteDict(){
        Udict udict = new Udict();
        udict.setDictid(498079649054588929L);
        udictMapper.deleteUdict(udict);
    }

}
