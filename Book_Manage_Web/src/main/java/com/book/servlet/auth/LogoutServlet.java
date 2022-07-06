package com.book.servlet.auth;

import com.book.eneity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wahoyu
 * @date 2022/7/4
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //清除session
        User user = (User) req.getSession().getAttribute("user");
        req.getSession().removeAttribute("user");
        //清除Cookie
        Cookie cookie_username = new Cookie("username", "username");
        cookie_username.setMaxAge(0);
        Cookie cookie_password = new Cookie("password", "password");
        cookie_password.setMaxAge(0);
        //给回响的Cookie加上数据
        resp.addCookie(cookie_username);
        resp.addCookie(cookie_password);
        //重定向
        resp.sendRedirect("login");

    }
}
