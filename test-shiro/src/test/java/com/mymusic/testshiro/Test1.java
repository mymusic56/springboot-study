package com.mymusic.testshiro;

import com.mymusic.testshiro.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test1 {
    @Test
    public void testLoginAndLogout() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 从SecurityUtils中创建一个subject  (org.apache.shiro.subject.Subject) 即主体
        Subject subject = SecurityUtils.getSubject();

        // 在认证提交前准备token(令牌)   这里的账号和密码 将来是由用户输入进去的
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        //执行认证提交
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        // 是否认证通过 (org.apache.shiro.authc.Authenticator) 即认证器
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + isAuthenticated);
        if (isAuthenticated) {
            System.out.println("subject：" + subject);
            System.out.println("用户：" + subject.getPrincipal());
            System.out.println("getPrincipal().getClass(): " + subject.getPrincipal().getClass());
        }

        //退出操作
        subject.logout();

        // 是否认证通过
        isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + isAuthenticated);
    }

    @Test
    public void testGenerateHashedCredentials() {
        String salt = ShiroUtils.generateSalt();
        String password = ShiroUtils.generatePwdEncrypt("123456", salt);
        System.out.println("salt：" + salt);
        System.out.println("password：" + password);
        log.info("========================");
    }
}
