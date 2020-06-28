package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.MessageDao;
import com.jxau.yzm.mytravelproject.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName MessageService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-16 20:32
 * @Version 1.0
 **/
@Service
public class MessageService {

    @Autowired
    MessageDao  messageDao;

    public List<Message> findAllMessages(Page<Message> pages) {
        int count = (int) messageDao.count();
        pages.setTotalSize(count);
        List<Message> messageList = messageDao.findAllMessage((pages.getCurrentPage() - 1) * pages.getPageSize(), pages.getPageSize());
        return messageList;
    }

    public List<Message> findAllMessagesByName(Page<Message> pages, String name) {
        int count = messageDao.getCountByName("%" + name + "%");
        pages.setTotalSize(count);
        List<Message> messageList = messageDao.findAllMessageByName("%"+name+"%", (pages.getCurrentPage() - 1) * pages.getPageSize(), pages.getPageSize());
        return  messageList;
    }

    public void deleteMessage(Integer id) {
        messageDao.deleteById(id);
    }

    public List<Message> findAllMessage(){
        List<Message> messageList = messageDao.findAll();
        return messageList;
    }

    public Message addMessage(Message message) {
        Message m = messageDao.save(message);
        return m;
    }
}
