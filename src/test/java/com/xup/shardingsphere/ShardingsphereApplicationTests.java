package com.xup.shardingsphere;

import com.xup.shardingsphere.Mapper.CourseMapper;
import com.xup.shardingsphere.entity.Course;
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

    @Test
    void contextLoads() {

    }


    @Test
    public void addCourse(){

        for (int i=0;i<5;i++){
            courseMapper.intsert("name1", 11L, "OK");
        }


    }

}
