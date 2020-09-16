package com.demo.service;

import com.demo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseMapper mapper;

    public int insertCourse(String course, int status, int userId){
       return mapper.insertCourse(course, status, userId);
    }

}
