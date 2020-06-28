package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDao extends JpaRepository<Orders,Integer>, JpaSpecificationExecutor<Orders> {


    @Modifying
    @Query(value = "insert into orders(code,scenic,hotel,type,name,phone,email,startdate,enddate,adult,children,paymoney) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)",nativeQuery = true)
    void saveOrder(String code, String scenic, String hotel, String type, String name, String phone, String email, String startdate, String enddate, int adult, int children, double paymoney);

    @Query(value = "select count(1) from orders where scenic like ?1",nativeQuery = true)
    int getCountByName(String scenic);

    @Query(value = "select * from orders where scenic like ?1 limit ?2,?3",nativeQuery = true)
    List<Orders> findAllOrderByName(String scenic, int pageIndex, int pageSize);

    @Query(value = "select * from orders  limit ?1,?2",nativeQuery = true)
    List<Orders> findAllOrder(int pageIndex, int pageSize);

    @Query(value = "select count(1) from orders where name = ?1",nativeQuery = true)
    int getMyCountByName(String name);

    @Query(value = "select * from orders where name = ?1 limit ?2,?3",nativeQuery = true)
    List<Orders> findMyOrderByName(String name, int pageIndex, int pageSize);
}
