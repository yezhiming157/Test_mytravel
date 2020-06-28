package com.jxau.yzm.mytravelproject.pojo;

import javax.persistence.*;

/**
 * @program: mytravelproject
 * @ClassName Message
 * @description:
 * @author: yezhiming
 * @create: 2020-03-16 20:08
 * @Version 1.0
 **/
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int id;
    private String message;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
