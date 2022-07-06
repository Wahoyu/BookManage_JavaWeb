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
    //获取借阅列表
    @Override
    public List<Borrow> getBorrorList() {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            //按照mapper查询借阅信息
            return mapper.getBorrowList();
        }
    }
    //还书
    @Override
    public void returnBook(String id) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            //按照mapper删除借阅信息
            mapper.deleteBorrow(id);
        }
    }
    //获取还能借的书
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
    //获取全部学生信息
    @Override
    public List<Student> getStudentList() {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            //得到全部的学生信息
            return mapper.getStudentList();
        }
    }
    //添加借阅信息
    @Override
    public void addBorrow(int sid, int bid) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            mapper.addBorrow(sid, bid);
        }
    }
    //获取全部书籍（要看借阅状态的）
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
    //删除书籍
    @Override
    public void deleteBook(int bid) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }
    //添加书籍
    @Override
    public void addBook(String title, String desc, double price) {
        //创建当前数据库连接对象
        try(SqlSession sqlsession = MybatisUtil.getSession()){
            BookMapper mapper = sqlsession.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }
}
