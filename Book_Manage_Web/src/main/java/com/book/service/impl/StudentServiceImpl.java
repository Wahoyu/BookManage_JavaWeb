package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.StudentMapper;
import com.book.service.StudentService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.annotation.WebServlet;

public class StudentServiceImpl implements StudentService {
    @Override
    public void addStudent(String name, String sex, int grade) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            StudentMapper mapper = sqlsession.getMapper(StudentMapper.class);
            mapper.addStudent(name, sex, grade);
        }
    }

    @Override
    public void deleteStudent(int sid) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            StudentMapper mapper = sqlsession.getMapper(StudentMapper.class);
            mapper.deleteStudent(sid);
        }
    }
}
