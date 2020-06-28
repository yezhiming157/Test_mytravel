package com.jxau.yzm.mytravelproject.controller.user;

import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.User;
import com.jxau.yzm.mytravelproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: mytravelproject
 * @ClassName UserLoginController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-23 13:12
 * @Version 1.0
 **/
@CrossOrigin
@Controller
public class UserLoginController {
    @Autowired
    UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);
    Result result = null;

    @GetMapping(value = "/user/reg/checkUser")
    @ResponseBody
    public Result checkAdmin(@RequestParam("loginacct" ) String loginacct){
        result = new Result();
        int num = userService.findByName(loginacct);
        //如果num > 0 说明数据库中已经存在了该用户
        if (num > 0){
            result.setSuccess(false);
        }else{
            result.setSuccess(true);
        }
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/user/reg/doreg")
    public  Result registerUser(User user){
        result = new Result();
        User user1 = userService.modifyUser(user);
        // num>0 添加admin 成功 。
        if (user1 != null){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }


    @ResponseBody
    @PostMapping(value = "/user/login")
    public Result login(@RequestParam("loginacct") String loginacct,
                        @RequestParam("userpswd") String userpswd,
                        HttpSession session ){
        result = new Result();
        log.debug("username = " + loginacct);
        log.debug("password = " + userpswd);
        User user= userService.loginUser(loginacct, userpswd);
        if (user != null){
            if (user.getStatus() == 1){
                result.setSuccess(true);
                session.setAttribute("USERNAME_USER",loginacct);
                log.debug(result.toString());
            }else{
                result.setMessage("账号还未通过管理员授权！！");
                log.debug(result.toString());
                result.setSuccess(false);
            }
        }else{
            result.setMessage("账号或密码错误！");
            log.debug(result.toString());
            result.setSuccess(false);
        }
        return result;
    }


    @GetMapping(value = "/user/logout")
    public String logout(HttpSession session){
        //Object username_admin = session.getAttribute("USERNAME_ADMIN");
        session.removeAttribute("USERNAME_USER");
        return "redirect:/index ";
    }
}
