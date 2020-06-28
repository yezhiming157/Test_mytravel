package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.HotelOrderDao;
import com.jxau.yzm.mytravelproject.pojo.HotelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: mytravelproject
 * @ClassName HotelOrderService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 22:07
 * @Version 1.0
 **/
@Service
public class HotelOrderService {

    @Autowired
    HotelOrderDao hotelOrderDao;

    public List<HotelOrder> findAllOrderByHotel(Page<HotelOrder> pages, String name) {
        int count = hotelOrderDao.getCountByName("%" + name + "%");
        pages.setTotalSize(count);
        List<HotelOrder> orderLists = hotelOrderDao.findAllOrderByHotel("%"+name+"%", (pages.getCurrentPage() - 1) * pages.getPageSize(), pages.getPageSize());
        return  orderLists;
    }

    public List<HotelOrder> findAllOrder(Page<HotelOrder> pages) {
        int count = (int) hotelOrderDao.count();
        pages.setTotalSize(count);
        List<HotelOrder> orderLists = hotelOrderDao.findAllOrder((pages.getCurrentPage() - 1) * pages.getPageSize(), pages.getPageSize());
        return orderLists;
    }

    public void deleteOrder(String ordercode) {
        hotelOrderDao.deleteById(ordercode);
    }

    public HotelOrder findOrderByCode(String ordercode) {
        Optional<HotelOrder> orders = hotelOrderDao.findById(ordercode);
        HotelOrder order = orders.get();
        return order;
    }
}
