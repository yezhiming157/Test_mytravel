package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.Admin;
import com.jxau.yzm.mytravelproject.service.AdminService;
import com.jxau.yzm.mytravelproject.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    @Autowired
    AdminService adminService;
    private static final Logger log = LoggerFactory.getLogger(AdminLoginController.class);
    Result result = null;

    @GetMapping(value = "/admin/reg/checkAdmin")
    @ResponseBody
    public Result checkAdmin(@RequestParam("loginacct" ) String loginacct){
        result = new Result();
        int num = adminService.findByName(loginacct);
        //如果num > 0 说明数据库中已经存在了该用户
        if (num > 0){
            result.setSuccess(false);
        }else{
            result.setSuccess(true);
        }
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/admin/reg/doreg")
    public  Result registerAdmin(Admin admin){
        result = new Result();
        String password = MD5Util.digest(admin.getPassword());
        admin.setPassword(MD5Util.digest(password));
       int count = adminService.findByName(admin.getName());
       int num = 0;
        if (count == 0){
            num = adminService.regAdmin(admin);
        }
        // num>0 添加admin 成功 。
        if (num > 0){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }


    @ResponseBody
    @PostMapping(value = "/admin/login")
    public Result login(@RequestParam("loginacct") String loginacct,
                        @RequestParam("userpswd") String userpswd,
                        HttpSession session ){
        result = new Result();
        log.debug("username = " + loginacct);
        log.debug("password = " + userpswd);
        Admin admin = adminService.loginAdmin(loginacct, userpswd);
        if (admin != null){
            result.setSuccess(true);
            session.setAttribute("USERNAME_ADMIN",loginacct);
            log.debug(result.toString());
        }else{
            result.setMessage("账号或密码错误！");
            log.debug(result.toString());
            result.setSuccess(false);
        }
      return result;
    }


    @GetMapping(value = "/admin/logout")
    public String logout(HttpSession session){
        //Object username_admin = session.getAttribute("USERNAME_ADMIN");
        session.removeAttribute("USERNAME_ADMIN");
        return "redirect:/admin ";
    }
}
