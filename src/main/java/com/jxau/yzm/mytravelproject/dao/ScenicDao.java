package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.Scenic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName ScenicDao
 * @description:
 * @author: yezhiming
 * @create: 2020-03-11 16:54
 * @Version 1.0
 **/

public interface ScenicDao extends JpaRepository<Scenic,Integer>, JpaSpecificationExecutor<Scenic> {

    @Query(value = "select * from Scenic limit ?1,?2 ",nativeQuery = true)
    List<Scenic> findAllScenic(int startIndex, int pageSize);

    @Query(value = "select * from Scenic where name like ?1 limit ?2,?3",nativeQuery = true)
    List<Scenic> getUserByName(String name, int startIndex, int pageSize);

    @Query(value = "select count(*) from Scenic where name like ?1",nativeQuery = true)
    Integer getCountByName(String s);

    @Query(value = "select * from Scenic  where id = ?1",nativeQuery = true)
    Scenic findScenic(Integer id);

    @Query(value = "select * from Scenic",nativeQuery = true)
    List<Scenic> findAllName();

    @Query(value = "select * from Scenic where name = ?1",nativeQuery = true)
    Scenic getMoney(String name);
}
