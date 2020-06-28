package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.Admin;
import com.jxau.yzm.mytravelproject.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@CrossOrigin
public class InformationController {

    @Autowired
    AdminService adminService;
    private static final Logger log = LoggerFactory.getLogger(InformationController.class);
    Result result = null;

    //来到个人修改信息页面
    @GetMapping("/admin/information/{username}")
    public String toModify(@PathVariable("username") String username, Model model){
        Admin admin = adminService.findadminByName(username);
        log.debug("admin " + admin);
        model.addAttribute("admin",admin);
        return "/admin/information";
    }

    @PutMapping(value = "/admin/information")
    public String modifyInformation(Admin admin){
        Admin admin1 = adminService.modifyAdmin(admin);
        if (admin1 !=null){
            log.info("admin = "+admin1);
            log.info("修改成功");
        }
        return "/admin/dashboard";
    }

}
