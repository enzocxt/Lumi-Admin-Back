package com.tao.lumiadmin.controller;

import com.tao.lumiadmin.pojo.User;
// import com.tao.lumiadmin.entity.User;
import com.tao.lumiadmin.result.Result;
import com.tao.lumiadmin.result.ResultFactory;
import com.tao.lumiadmin.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
// import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

// import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    // controller 里写这么多逻辑是不合理的，要尽量封装到 service 中去
    @Autowired
    UserService userService;

    @PostMapping("api/login")
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        // 对 html 标签进行转义，防止 XSS 攻击
        username = HtmlUtils.htmlEscape(username);
        
        /* 旧的实现方式
        String orgPassword = requestUser.getPassword();
        User user = userService.getByName(username);
        if (null == user)
            return ResultFactory.buildFailResult("用户不存在");
        
        int times = 2;
        String encodedPassword = new SimpleHash("md5", orgPassword, user.getSalt(), times).toString();
        if (encodedPassword.equals(user.getPassword())) {
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessResult(user);
        } else {
            return ResultFactory.buildFailResult("账号密码错误");
        // }
        */

        Subject subject = SecurityUtils.getSubject();
        // subject.getSession().setTimeout(10000);
        // 根据 用户名和原始密码 构建 Token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            User user = userService.findByUsername(username);
            if (!user.isEnabled()) {
                return ResultFactory.buildFailResult("该用户已被禁用");
            }
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(usernamePasswordToken);
        } catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("账号密码错误");
        } catch (UnknownAccountException e) {
            return ResultFactory.buildFailResult("账号不存在");
        }
    }

    @GetMapping("/login")
    public Result login() {
        String message = "非法登录";
        return ResultFactory.buildSuccessResult(message);
    }

    @PostMapping("api/register")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成 salt，默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 和 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.addOrUpdate(user);

        return ResultFactory.buildSuccessResult(user);
    }

    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();  // DelegatingSubject.logout()
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }

    @GetMapping("api/authentication")
    public String authentication() {
        // 暂时不做任何事
        return "身份认证成功";
    }
}

