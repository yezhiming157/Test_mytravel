package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelDao extends JpaRepository<Hotel,Integer>, JpaSpecificationExecutor<Hotel> {

    @Query(value = "select * from Hotel limit ?1,?2 ",nativeQuery = true)
    List<Hotel> findAllHotel(int startIndex, int pageSize);

    @Query(value = "select count(*) from Hotel where name like ?1",nativeQuery = true)
    Integer getCountByName(String name);

    @Query(value = "select * from Hotel where name like ?1 limit ?2,?3",nativeQuery = true)
    List<Hotel> getHotelByName(String name, int startIndex, int pageSize);

    @Query(value = "select * from Hotel where id = ?1",nativeQuery = true)
    Hotel findHotelById(Integer id);

    @Query(value = "select * from Hotel",nativeQuery = true)
    List<Hotel> findAll();

    @Query(value = "select * from Hotel where name = ?1 and type =?2",nativeQuery = true)
    Hotel getMoney(String name,String type);
}
