package com.xup.shardingsphere.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
public interface CourseMapper{

    @Insert("insert into course (cname, user_id, cstatus) values (#{cname}, #{userId}, #{cstatus})")
    public void intsert(@Param("cname")String cname, @Param("userId")Long userId, @Param("cstatus")String status);

}
