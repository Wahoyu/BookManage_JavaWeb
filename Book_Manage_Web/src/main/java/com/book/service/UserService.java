package com.book.service;

import javax.servlet.http.HttpSession;

/**
 * @author Wahoyu
 * @date 2022/7/3
 */
public interface UserService {
    boolean auth(String username, String password, HttpSession session);
}
