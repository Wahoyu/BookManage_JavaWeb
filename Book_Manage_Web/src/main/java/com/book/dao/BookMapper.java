package com.book.dao;

import com.book.eneity.Book;
import com.book.eneity.Borrow;
import com.book.eneity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wahoyu
 * @date 2022/7/4
 */
public interface BookMapper {
    //获取借阅列表
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "bid",property = "book_id"),
            @Result(column = "title",property = "book_name"),
            @Result(column = "time",property = "time"),
            @Result(column = "name",property = "student_name"),
            @Result(column = "sid",property = "student_id")
    })
    @Select("select * from borrow,student,book where borrow.bid = book.bid and borrow.sid = student.sid")
    List<Borrow> getBorrowList();

    //删除借阅信息
    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(String id);

    //获取全部图书
    @Select("select * from book")
    List<Book> getBookList();

    //获取全部学生
    @Select("select * from student")
    List<Student> getStudentList();

    //添加借阅信息
    @Insert("insert into borrow(sid,bid,time) values(#{sid},#{bid},NOW())")
    void addBorrow(@Param("sid")int sid,@Param("bid")int bid);

    //删除书籍
    @Delete("delete from book where bid = #{bid}")
    void deleteBook(int bid);

    //添加书籍
    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price})")
    void addBook(@Param("title")String title,@Param("desc") String desc,@Param("price") double price);
}
