package ru.itis.witchCrutch.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieActions {

    public static void deleteCookies(HttpServletRequest req, HttpServletResponse resp, String... name) {
        Cookie[] cookies = req.getCookies();

        for (String cookieName : name) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
    }
}
