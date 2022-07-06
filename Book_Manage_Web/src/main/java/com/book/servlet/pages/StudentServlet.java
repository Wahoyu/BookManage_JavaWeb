package com.book.servlet.pages;

import com.book.eneity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wahoyu
 * @date 2022/7/4
 */
@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    BookService service;
    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //创建一个上下文对象
        Context context = new Context();
        //获取username放在页面右上角
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname",user.getNickname());
        //学生列表获取
        context.setVariable("student_list",service.getStudentList());
        //提交修改
        ThymeleafUtil.process("students.html",context,resp.getWriter());
    }
}
