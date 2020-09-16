package com.demo.controller;

import com.demo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseController {

    private  static  final Logger logger = LoggerFactory.getLogger(CourseController.class);


    @Autowired
    private CourseService courseService;


    public void insertCourse(String course, int status, int userId){
        logger.info("输入参数: course={}, status={}, userId={}", course, status, userId);
        int cid = courseService.insertCourse(course, status, userId);
        logger.info("添加course成功，主键为: {}", cid);
    }
}
