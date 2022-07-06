package com.book.dao;

import com.book.eneity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Wahoyu
 * @date 2022/7/3
 */
public interface UserMapper {
    //用户输入的数据通过前端表单，到Servlet的doPost，到达Service（使用Mapper定义的映射规则）返回一个true或者false
    //如果验证成功Servlet显示下一个页面
    //如果失败，会带着失败属性跳转到doGet请求，然后通过前端的if判断显示登陆失败。

    @Select("select * from admin where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username,@Param("password") String password);

}
