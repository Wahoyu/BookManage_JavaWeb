package com.book.dao;

import com.book.eneity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wahoyu
 * @date 2022/7/4
 */
public interface StudentMapper {
    @Select("select * from student")
    List<Student> getStudentList();

    @Insert("insert into student(name,sex,grade) values(#{name},#{sex},#{grade})")
    void addStudent(@Param("name")String name, @Param("sex") String sex, @Param("grade") int grade);

    @Delete("delete from student where sid = #{sid}")
    void deleteStudent(int sid);
}
