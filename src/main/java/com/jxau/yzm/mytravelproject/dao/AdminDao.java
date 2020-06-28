package com.jxau.yzm.mytravelproject.dao;

import com.jxau.yzm.mytravelproject.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

//继承JPARepository
public interface AdminDao extends JpaRepository<Admin,Integer>, JpaSpecificationExecutor<Admin> {

    /**
     * 查名字
     * @param name
     * @return
     */
    @Query(value = "select * from Admin where name = ?1 ",nativeQuery = true)
    public Admin findByName(String name);


    @Query(value = "select * from Admin  where name = ?1 and password =?2",nativeQuery = true)
    public Admin findAdmin(String  name,String password);

}
