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
    @Override
    public boolean auth(String username, String password, HttpSession session) {
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            UserMapper mapper = sqlsession.getMapper(UserMapper.class);
            User user = mapper.getUser(username,password);
            if(user == null){
                return false;
            }else{
                session.setAttribute("user",user);
                return true;
            }
        }
    }
}
