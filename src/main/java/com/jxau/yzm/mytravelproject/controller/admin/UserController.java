package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.User;
import com.jxau.yzm.mytravelproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName UserController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-09 21:39
 * @Version 1.0
 **/

@Controller
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    Result result = null ;
    //设置每页记录数
    Integer size = 10;

    /*
    *   进入用户管理首页
    */
    @GetMapping("/admin/user")
    public String getUserList(Model model){
        Page<User> page = new Page<>();
        //设置当前页数
        page.setCurrentPage(1);
        //设置每页显示记录数
        page.setPageSize(size);
        List<User> userLists= userService.getUserList(page);
        page.setDatas(userLists);
        model.addAttribute("pages",page);
        log.info(userLists.toString());
        return "/admin/manage_user";
    }

    /*
    * 分页   传入当前页号
    * */
    @GetMapping("/admin/userpage/{i}")
    public String getUserPage(@PathVariable("i") Integer i, Model model){
        Page<User> page = new Page<>();
        page.setPageSize(size);
        if(i>=1){
            page.setCurrentPage(i);
        }else{
            page.setCurrentPage(1);
        }
        List<User> userLists= userService.getUserList(page);
        log.info(userLists.toString());
        page.setDatas(userLists);
        model.addAttribute("pages",page);
        return "/admin/manage_user";
    }

    @GetMapping("/admin/username/{name}")
    public String selectUserByName(@PathVariable("name") String name,Model model){
        List<User> userList = null;
        Page<User> page = new Page<>();
        //设置当前页数
        page.setCurrentPage(1);
        //设置每页显示记录数
        page.setPageSize(size);
/*        if (name == null){
            userList= userService.getUserList(page);
        }else {
            userList = userService.getUserByName(name,page);
        }*/
        userList = userService.getUserByName(name,page);
        page.setDatas(userList);
        log.debug(page.toString());
        model.addAttribute("pages",page);
        return "/admin/manage_user";
    }

    /*
          传入用户ID 查询用户信息后去往用户修改页面
     */
    @GetMapping("/admin/user/{id}")
    public String getUser(@PathVariable("id") Integer id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        log.info(user.toString());
        return "/admin/editUser";
    }

    @PutMapping("/admin/user")
    @ResponseBody
    public Result doModify(User user){
        result = new Result();
        User temp = userService.modifyUser(user);
        if (temp !=null){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }

    @DeleteMapping("/admin/user")
    @ResponseBody
    public Result doDelete(@RequestParam("id") Integer id){
        result = new Result();
        try {
            userService.deleteUser(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！");
        }
        return  result;
    }
}
