package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface GalleryDao extends JpaRepository<Gallery,Integer>,JpaSpecificationExecutor<Gallery>{

    @Query(value = "select * from gallery limit ?1,?2 ",nativeQuery = true)
    List<Gallery> findAlgallerys(int i, int pageSize);
}
