package com.mymusic.testshiro.interceptor;

import com.mymusic.jpatest.common.util.PasswordUtil;
import com.mymusic.testshiro.consts.SessionConst;
import com.mymusic.testshiro.entity.UserInfo;
import com.mymusic.testshiro.sevice.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理自动登录
 * 没有生效呢？？？？？
 */
@Slf4j
@Component
public class RememberAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("是否认证");
        if (subject.isAuthenticated()) {
            System.out.println("已认证");
            log.info("已认证");
            return true;
        }
        Session session = subject.getSession(true);
        if (session.getAttribute(SessionConst.USER_SESSION_KEY) != null) {
            return true;
        }

        if(!subject.isRemembered()) {
            log.warn("未设置“记住我”,跳转到登录页...");
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        try {
            String username = subject.getPrincipal().toString();
            UserInfo user = userInfoService.findByUsername(username);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), PasswordUtil.getEncryptPassword(user.getPassword(), user.getUsername()), true);
            subject.login(token);

            session.setAttribute(SessionConst.USER_SESSION_KEY, user);
            log.info("[{}] - 已自动登录", user.getUsername());
        } catch (Exception e) {
            log.error("自动登录失败", e);
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
