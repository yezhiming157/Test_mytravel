package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.OrderDao;
import com.jxau.yzm.mytravelproject.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @program: mytravelproject
 * @ClassName BookingService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-19 18:47
 * @Version 1.0
 **/

@Service
@Transactional
public class BookingService {

    @Autowired
    OrderDao orderDao;

    public Orders saveOrder(Orders order) {
        Orders order1 = orderDao.save(order);
        return order1;
    }

    public List<Orders> findAllOrderByName(Page<Orders> pages, String name) {
        int count = orderDao.getCountByName("%" + name + "%");
        pages.setTotalSize(count);
        List<Orders> orderLists = orderDao.findAllOrderByName("%" + name + "%", (pages.getCurrentPage() - 1) * pages.getPageSize(), pages.getPageSize());
        return  orderLists;
    }


    public List<Orders> findAllOrder(Page<Orders> pages) {
        int count = (int) orderDao.count();
        pages.setTotalSize(count);
        List<Orders> orderLists = orderDao.findAllOrder((pages.getCurrentPage() - 1) * pages.getPageSize(), pages.getPageSize());
        return orderLists;
    }


    public void deleteOrderbyId(Integer id) {
        orderDao.deleteById(id);
    }

    public Orders getOrderById(Integer id) {
        Optional<Orders> order = orderDao.findById(id);
        Orders orders = order.get();
        return orders;
    }

    public List<Orders> findMyOrderByName(Page<Orders> pages, String username) {
        int count = orderDao.getMyCountByName(username);
        pages.setTotalSize(count);
        List<Orders> orderLists = orderDao.findMyOrderByName(username, (pages.getCurrentPage() - 1) * pages.getPageSize(), pages.getPageSize());
        return  orderLists;
    }
}
