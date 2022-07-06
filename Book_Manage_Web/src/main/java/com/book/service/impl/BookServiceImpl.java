package com.book.service.impl;

import com.book.eneity.Book;
import com.book.dao.BookMapper;
import com.book.eneity.Borrow;
import com.book.eneity.Student;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wahoyu
 * @date 2022/7/4
 */
public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> getBorrorList() {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            //按照mapper查询借阅信息
            return mapper.getBorrowList();
        }
    }

    @Override
    public void returnBook(String id) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            //按照mapper删除借阅信息
            mapper.deleteBorrow(id);
        }
    }

    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> set = new HashSet();
        this.getBorrorList().forEach(borrow -> set.add(borrow.getBook_id()));
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            //进行已经借出的不显示
            return mapper.getBookList()
                        .stream()
                        .filter(book -> !set.contains(book.getBid()))
                        .collect(Collectors.toList());
        }
    }

    @Override
    public List<Student> getStudentList() {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            //得到全部的学生信息
            return mapper.getStudentList();
        }
    }

    @Override
    public void addBorrow(int sid, int bid) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            mapper.addBorrow(sid, bid);
        }
    }

    @Override
    public Map<Book, Boolean> getBookList() {
        Set<Integer> set = new HashSet();
        this.getBorrorList().forEach(borrow -> set.add(borrow.getBook_id()));
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            Map<Book,Boolean> map = new LinkedHashMap<>();
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            mapper.getBookList().forEach(book ->map.put(book, set.contains(book.getBid())));
            return map;
        }
    }

    @Override
    public void deleteBook(int bid) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }

    @Override
    public void addBook(String title, String desc, double price) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }
}
