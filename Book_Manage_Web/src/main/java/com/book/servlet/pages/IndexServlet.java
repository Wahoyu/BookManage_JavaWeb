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
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    //获取借阅信息
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
        //内容修改
        //获取username放在页面右上角
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname",user.getNickname());
        //借阅信息列表整个返回到前端
        context.setVariable("borrow_list",service.getBorrorList());
        //完善主页
        context.setVariable("book_count",service.getBookList().size());
        context.setVariable("student_count",service.getStudentList().size());
        context.setVariable("book_count",service.getBookList().size());
        //提交修改
        ThymeleafUtil.process("index.html",context,resp.getWriter());
    }
}
