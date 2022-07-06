package com.book.service.impl;

import com.book.eneity.User;
import com.book.dao.UserMapper;
import com.book.service.UserService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

/**
 * @author Wahoyu
 * @date 2022/7/3
 */
public class UserServiceImpl implements UserService {
    //实现判断登录用户的接口方法
    @Override
    public boolean auth(String username, String password, HttpSession session) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            //通过UserMapper创建登录人员的Mapper
            UserMapper mapper = sqlsession.getMapper(UserMapper.class);
            //如果按照对应的的查找规则 进行对象查找
            User user = mapper.getUser(username,password);
            //如果没有这个user说明用户名或者密码不正确
            if(user == null){
                return false;
            }else{
                //成功则设置user的属性为查到的实体类的信息
                session.setAttribute("user",user);
                return true;
            }
        }
    }
}
