package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.Message;
import com.jxau.yzm.mytravelproject.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName MessageController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-16 20:25
 * @Version 1.0
 **/
@Controller
@CrossOrigin
public class MessageController  {

    @Autowired
    MessageService messageService ;
    private static final Logger log = LoggerFactory.getLogger(HotelOrderController.class);
    Page<Message> pages = null;
    List<Message> messages = null;

    @GetMapping("/admin/messages")
    public String toMessageIndex(){
        return "/admin/manage_message";
    }

    @DeleteMapping("/admin/message")
    @ResponseBody
    public Result deleteMessage(@RequestParam(value = "id") Integer id){
        Result result = new Result();
        try {
            messageService.deleteMessage(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！！");
        }
        return result;
    }

    @PostMapping("/user/message")
    public String addMessage(@RequestParam(value = "message") String data, HttpSession session){
        if(session.getAttribute("USERNAME_USER") == null){
            return "/user/login";
        }else{
        String username =  session.getAttribute("USERNAME_USER").toString();
        Message message = new Message();
        message.setMessage(data);
        message.setUsername(username);
        log.debug(message.toString());
        messageService.addMessage(message);
        return "/user/booking";
        }
    }

    /*
     *  查询出所有留言并分页
     */
    @PostMapping("/admin/toMessageIndex")
    @ResponseBody
    public Result getMessages(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                              Integer pageSize,
                              @RequestParam(value = "queryText", required = false) String queryText){
        Result result = new Result() ;
        pages = new Page<>() ;
        //设置当前页数
        pages.setCurrentPage(currentPage);
        //设置每页显示记录数
        pages.setPageSize(pageSize);
        log.debug("queryText="+queryText);
        if (queryText != null && !queryText.equals("")){
            messages =  messageService.findAllMessagesByName(pages,queryText);
        }else {
            messages = messageService.findAllMessages(pages);
            log.debug("message"+messages.toString());
        }
        //设置列表数据
        pages.setDatas(messages);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }



}
