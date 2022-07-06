package com.book.service;

import com.book.eneity.Book;
import com.book.eneity.Borrow;
import com.book.eneity.Student;

import java.util.List;
import java.util.Map;

/**
 * @author Wahoyu
 * @date 2022/7/4
 */
public interface BookService {
    //查看借阅关系
    List<Borrow> getBorrorList();
    //还书
    void returnBook(String id);
    //查看过滤版本的书（borrow）
    List<Book> getActiveBookList();
    //查看借书的学生
    List<Student> getStudentList();
    //借书
    void addBorrow(int sid,int bid);
    //查看全部书籍以及状况
    Map<Book,Boolean> getBookList();
    //删除书籍
    void deleteBook(int bid);
    //添加书籍
    void addBook(String title,String desc,double price);
}
