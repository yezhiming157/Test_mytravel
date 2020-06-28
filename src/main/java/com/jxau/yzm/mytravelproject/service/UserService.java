package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.UserDao;
import com.jxau.yzm.mytravelproject.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName UserService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-09 22:02
 * @Version 1.0
 **/
@Service
@Transactional
public class UserService {

    @Autowired
    UserDao userDao;
    //DESC加密解密 密钥
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /*
     *    进行分页操作  传入页面参数page
     */
    public List<User> getUserList(Page<User> page){
        int count = (int) userDao.count();
        //设置总记录数
        page.setTotalSize(count);
        List<User> userList = userDao.findUsers((page.getCurrentPage()-1)*page.getPageSize(), page.getPageSize());
        return userList;
    }

    public User findUserById(Integer id) {
        User user = userDao.findUserById(id);
        return user;
    }

    //使用DES加密
    public User modifyUser(User user) {
 /*     String password = user.getPassword();
        try {
            String pwd = DesUtil.encrypt(password, key);
            user.setPassword(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return userDao.save(user);
    }

    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

    public List<User> getUserByName(String name,Page<User> page) {
        List<User> userList = userDao.getUserLikeName("%" + name + "%");
        Integer countByName = userDao.getCountByName("%" + name + "%");
        if (countByName == 0){
            page.setTotalSize(1);
        }else {
            page.setTotalSize(countByName);
        }
        return userList;
    }

    public int findByName(String loginacct) {
        List<User> userByName = userDao.findUserByName(loginacct);
        log.debug(userByName.toString());
        if (userByName !=null && userByName.size() != 0){
            return 1;
        }else{
            return 0;
        }
    }

    public User loginUser(String username, String password) {
        User user = userDao.findUser(username,password);
        try {
/*            String pwd = DesUtil.decrypt(password,key);
            log.debug("username="+username+", password="+pwd);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
