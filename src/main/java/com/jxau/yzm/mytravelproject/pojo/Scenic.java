package com.jxau.yzm.mytravelproject.pojo;

import javax.persistence.*;

/**
 * @program: mytravelproject
 * @ClassName Secnic
 * @description:
 * @author: yezhiming
 * @create: 2020-03-11 16:55
 * @Version 1.0
 **/
@Entity
@Table(name="scenic")
public class Scenic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int id;
    private String name;
    private String img;
    private int price;
    private String comment;
    private int stock;
    private int start;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Scenic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", stock=" + stock +
                ", start=" + start +
                ", country='" + country + '\'' +
                '}';
    }
}
