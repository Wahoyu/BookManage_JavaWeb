package com.book.servlet.manage;

import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;

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
@WebServlet("/return-book")
public class ReturnServlet extends HttpServlet {
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
        //归还的是什么记录
        String id = req.getParameter("id");
        //执行归还行为
        service.returnBook(id);
        //归还之后重定向index
        resp.sendRedirect("index");

    }
}
