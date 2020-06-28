package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.ScenicOrderDao;
import com.jxau.yzm.mytravelproject.pojo.ScenicOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: mytravelproject
 * @ClassName ScenicOrderService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 17:44
 * @Version 1.0
 **/
@Service
public class ScenicOrderService {

    @Autowired
    ScenicOrderDao scenicOrderDao;

    public List<ScenicOrder> findAllOrderByScenic(Page<ScenicOrder> pages, String scenicName) {
        int count = scenicOrderDao.getCountByName("%" + scenicName + "%");
        pages.setTotalSize(count);
        List<ScenicOrder> scenicOrder = scenicOrderDao.getOrderByName("%" + scenicName + "%",(pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
        return scenicOrder;
    }

    public List<ScenicOrder> findAllOrder(Page<ScenicOrder> pages) {
        int count = (int) scenicOrderDao.count();
        //设置总记录数
        pages.setTotalSize(count);
        return scenicOrderDao.findAllOrder((pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
    }

    public ScenicOrder findOrderByCode(String ordercode) {
        Optional<ScenicOrder> orders = scenicOrderDao.findById(ordercode);
        ScenicOrder order = orders.get();
        return order;
    }

    public void deleteOrder(String ordercode) {
        scenicOrderDao.deleteById(ordercode);
    }
}
