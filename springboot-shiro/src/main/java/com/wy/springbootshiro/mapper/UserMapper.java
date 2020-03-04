package com.wy.springbootshiro.mapper;

import com.wy.springbootshiro.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserMapper {

    @Select("select * from user where name=#{name}")
    @Results(id = "userMap",value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER,id = true),
            @Result(column = "name", property ="name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR)
    })
    //@ResultType(user)    ?
    public User findByUsername(String name);


    @Select("select * from user where id=#{id}")
    public User findById(Integer id);
}
