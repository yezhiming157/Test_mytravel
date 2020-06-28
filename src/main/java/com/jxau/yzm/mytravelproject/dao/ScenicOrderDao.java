package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.ScenicOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScenicOrderDao extends JpaRepository<ScenicOrder,String>, JpaSpecificationExecutor<ScenicOrder> {

    @Query(value = "SELECT count(*) from scenic_orders where username like ?1",nativeQuery = true)
    int getCountByName(String name);

    @Query(value = "select * from scenic_orders where scenicname like ?1 limit ?2,?3",nativeQuery = true)
    List<ScenicOrder> getOrderByName(String name, int indexPage, int pageSize);

    @Query(value = "select * from scenic_orders limit ?1,?2",nativeQuery = true)
    List<ScenicOrder> findAllOrder(int indexPage, int pageSize);
}
