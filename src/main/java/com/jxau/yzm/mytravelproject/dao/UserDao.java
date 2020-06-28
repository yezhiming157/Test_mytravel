package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {

    @Query(value = "select * from user where id = ?1",nativeQuery = true)
    User findUserById(Integer id);

    @Query(value ="select * from user limit ?1,?2",nativeQuery = true )
    List<User> findUsers(Integer CurrentPage,Integer PageSize);

    @Query(value = "Select * from user where name like ?1",nativeQuery = true)
    List<User>  getUserLikeName(String name);

    @Query(value = "select count(*) from user where name like ?1",nativeQuery = true)
    Integer getCountByName(String name);

    @Query(value = "select * from user where name = ?1",nativeQuery = true)
    List<User>  findUserByName(String name);

    @Query(value = "select * from user where name = ?1 and password = ?2",nativeQuery = true)
    User findUser(String username, String pwd);
}
