package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageDao extends JpaRepository<Message,Integer>, JpaSpecificationExecutor<Message> {


    @Query(value = "select count(1) from message where username like ?1",nativeQuery = true)
    int getCountByName(String name);

    @Query(value = "select * from message where username like ?1 limit ?2,?3",nativeQuery = true)
    List<Message> findAllMessageByName(String name, int indexPage, int pageSize);

    @Query(value = "select * from message limit ?1,?2",nativeQuery = true)
    List<Message> findAllMessage(int indexPage, int pageSize);
}
