package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.dao.AdminDao;
import com.jxau.yzm.mytravelproject.pojo.Admin;
import com.jxau.yzm.mytravelproject.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    //通过username查询账号是否存在
    public int findByName(String username) {
        Admin admin = adminDao.findByName(username);
        if (admin != null){
            log.info("查询到的用户："+ admin.toString());
            return 1;
        }
        return 0;
    }



    public  int regAdmin(Admin admin){
        Admin save = adminDao.save(admin);
        if (save != null){
            log.info("添加成功！");
            return 1;
        }
        return 0;
    }

    public Admin loginAdmin(String username,String password){
        Admin admin = adminDao.findAdmin(username, MD5Util.digest(MD5Util.digest(password)));
        return admin;
    }

    public Admin findadminByName(String username) {
        Admin admin = adminDao.findByName(username);
        return admin;
    }

    public Admin modifyAdmin(Admin admin) {
        return adminDao.save(admin);
    }
}
