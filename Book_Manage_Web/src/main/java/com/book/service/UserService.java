package com.book.service;

import javax.servlet.http.HttpSession;

/**
 * @author Wahoyu
 * @date 2022/7/3
 */
public interface UserService {
    // 创建判断用户是否正确的接口方法
    boolean auth(String username, String password, HttpSession session);
}
