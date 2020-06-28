package com.jxau.yzm.mytravelproject.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: mytravelproject
 * @ClassName HotelOrder
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 22:01
 * @Version 1.0
 **/
@Entity
@Table(name = "hotel_orders")
public class HotelOrder {

    @Id
    @Column(name = "ordercode")
    private String code;
    @Column(name = "hotelid")
    private int hotelId;
    @Column(name = "userid")
    private int userId;
    private int number;
    private double paymoney;
    private int status;
    private String paytime;
    private String phone;
    @Column(name = "username")
    private String userName;
    @Column(name = "hotelname")
    private String hotelName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(double paymoney) {
        this.paymoney = paymoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @Override
    public String toString() {
        return "HotelOrder{" +
                "code='" + code + '\'' +
                ", hotelId=" + hotelId +
                ", userId=" + userId +
                ", number=" + number +
                ", paymoney=" + paymoney +
                ", status=" + status +
                ", paytime='" + paytime + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                '}';
    }
}
