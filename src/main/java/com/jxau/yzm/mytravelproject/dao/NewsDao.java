package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsDao extends JpaRepository<News,Integer>, JpaSpecificationExecutor<News> {

    @Query(value = "select count(*) from news where name like ?1",nativeQuery = true)
    int getCountByName(String name);

    @Query(value = "select * from news where name like ?1 limit ?2,?3",nativeQuery = true)
    List<News> getNewsByName(String name, int IndexPage, int pageSize);

    @Query(value = "select * from news  limit ?1,?2",nativeQuery = true)
    List<News> findAllNews(int IndexPage, int pageSize);

}
