package com.book.servlet.manage;

import com.book.service.BookService;
import com.book.service.StudentService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    //获取借阅信息
    StudentService service;
    @Override
    public void init() throws ServletException {
        service = new StudentServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //创建一个上下文对象
        Context context = new Context();
        //提交修改
        ThymeleafUtil.process("add-student.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置输入的编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //取得前端表单
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        int grade = Integer.parseInt((req.getParameter("grade")));
        //System.out.println(title+desc+price);
        service.addStudent(name,sex,grade);
        resp.sendRedirect("students");
    }
}
