package com.book.dao;

import com.book.eneity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Wahoyu
 * @date 2022/7/3
 */
public interface UserMapper {

    @Select("select * from admin where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username,@Param("password") String password);

}
