package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.HotelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelOrderDao extends JpaRepository<HotelOrder,String>, JpaSpecificationExecutor<HotelOrder> {

    @Query(value = "select * from hotel_orders where hotelname like ?1 limit ?2,?3",nativeQuery = true)
    public List<HotelOrder> findAllOrderByHotel(String name, int IndexPage, int pageSize);

    @Query(value = "select * from hotel_orders  limit ?1,?2",nativeQuery = true)
    public List<HotelOrder> findAllOrder(int IndexPage, int pageSize);

    @Query(value = "select count(1) from hotel_orders where hotelname like ?1",nativeQuery = true)
    int getCountByName(String name);
}
