package com.jxau.yzm.mytravelproject.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: mytravelproject
 * @ClassName ScenicOrder
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 17:30
 * @Version 1.0
 **/
@Entity
@Table(name = "scenic_orders")
public class ScenicOrder {

    @Id
    @Column(name = "ordercode")
    private String code;
    @Column(name = "scenicid")
    private int scenicId;
    @Column(name = "userid")
    private int userId;
    private int number;
    private double payment;
    private int status;
    private String paytime;
    private String phone;
    @Column(name = "username")
    private String userName;
    @Column(name = "scenicname")
    private String scenicnName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getScenicId() {
        return scenicId;
    }

    public void setScenicId(int scenicId) {
        this.scenicId = scenicId;
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

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
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

    public String getScenicnName() {
        return scenicnName;
    }

    public void setScenicnName(String scenicnName) {
        this.scenicnName = scenicnName;
    }

    @Override
    public String toString() {
        return "ScenicOrder{" +
                "code='" + code + '\'' +
                ", scenicId=" + scenicId +
                ", userId=" + userId +
                ", number=" + number +
                ", payment=" + payment +
                ", status='" + status + '\'' +
                ", paytime='" + paytime + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", scenicnName='" + scenicnName + '\'' +
                '}';
    }
}
