package com.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseMapper {

    @Insert("insert into t_course (course, cstatus, user_id) values (#{course}, #{status}, #{userId})")
    public int insertCourse(@Param("course") String course, @Param("status") int status, @Param("userId") int userId);

}
