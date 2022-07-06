package com.book.servlet.auth;

import com.book.dao.UserMapper;
import com.book.eneity.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import org.apache.ibatis.session.SqlSession;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wahoyu
 * @date 2022/7/2
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //Cookie检验
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            String username = null;
            String password = null;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if(cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            if(username != null && password != null){
                if(service.auth(username, password, req.getSession())){
                    resp.sendRedirect("index");
                    return;
                }

            }
        }
        //创建一个上下文对象
        Context context = new Context();
        //携带参数给if进行赋值
        if (req.getSession().getAttribute("login-failure") != null) {
            context.setVariable("failure", true);
            req.getSession().removeAttribute("login-failure");
        }
        //如果你已经登陆，却还在请求login，直接给你重定向到index主页里去
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }
        //把对应的内容提交给html
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取得表单中的用户信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        //采用Service中的逻辑语句进行比对
        // 如果true则重定向到index
        if (service.auth(username, password, req.getSession())) {
            //记住我+doGet
            if (remember != null) {

                Cookie cookie_username = new Cookie("username", username);
                cookie_username.setMaxAge(60 * 60 * 24 * 7);
                Cookie cookie_password = new Cookie("password", password);
                cookie_password.setMaxAge(60 * 60 * 24 * 7);
                //给回响的Cookie加上数据
                resp.addCookie(cookie_username);
                resp.addCookie(cookie_password);
            }
            resp.sendRedirect("index");
        }
        //如果false则滚去重新进行登陆页面
        else {
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req, resp);
        }

    }
}
