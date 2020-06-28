package com.jxau.yzm.mytravelproject.pojo;

import javax.persistence.*;

/**
 * @program: mytravelproject
 * @ClassName News
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 15:46
 * @Version 1.0
 **/

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int id;
    private String information;
    private String date;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", information='" + information + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
