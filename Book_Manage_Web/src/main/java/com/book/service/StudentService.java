package com.book.service;

public interface StudentService {
    //添加学生
    void addStudent(String name,String sex,int grade);
    //删除学生
    void deleteStudent(int sid);
}
