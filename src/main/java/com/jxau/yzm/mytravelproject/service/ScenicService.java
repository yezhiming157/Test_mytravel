package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.ScenicDao;
import com.jxau.yzm.mytravelproject.pojo.Scenic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName ScenicService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-11 17:00
 * @Version 1.0
 **/
@Service
@Transactional
public class ScenicService {

    @Autowired
    ScenicDao scenicDao ;

    public List<Scenic> findAllScenic(Page<Scenic> pages) {
        int count = (int) scenicDao.count();
        //设置总记录数
        pages.setTotalSize(count);
       return scenicDao.findAllScenic((pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
    }

    public List<Scenic> findAllScenicByName(Page<Scenic> pages, String name) {
        Integer count = scenicDao.getCountByName("%" + name + "%");
        pages.setTotalSize(count);
        List<Scenic> scenicList = scenicDao.getUserByName("%" + name + "%",(pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
        return scenicList;
    }

    public void deleteScenicById(Integer id) {
         scenicDao.deleteById(id);
    }

    public Scenic findScenic(Integer id) {
        Scenic scenic = scenicDao.findScenic(id);
        return scenic;
    }

    public Scenic modifyScenic(Scenic scenic) {
        Scenic s = scenicDao.save(scenic);
        return s;
    }

    public Scenic saveScenic(Scenic scenic) {
        return scenicDao.save(scenic);
    }

    public List<Scenic> findAllName() {
        List<Scenic> allNames = scenicDao.findAllName();
        for (int i = 0; i < allNames.size()-1; i++) {
            for (int j = allNames.size()-1; j > i; j--) {
                if (allNames.get(j).getName() == allNames.get(i).getName()) {
                    allNames.remove(j);
                }
            }
        }
        return allNames;
    }

    public Double getScenicMoney(String name) {
        Scenic scenic = scenicDao.getMoney(name);
        double price = (double) scenic.getPrice();
        return price;
    }
}
