package com.pqv.app.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.pqv.app.bean.User;



@Mapper
@Repository
public interface UserMapper {
    
    
    @Select("select * from user where account = #{name}")
    User sLogin(String name);
    
    
   
}
