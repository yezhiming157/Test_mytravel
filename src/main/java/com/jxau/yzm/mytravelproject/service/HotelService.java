package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.HotelDao;
import com.jxau.yzm.mytravelproject.pojo.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName HotelService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-12 15:21
 * @Version 1.0
 **/

@Service
@Transactional
public class HotelService {

    @Autowired
    HotelDao hotelDao;


    public List<Hotel> findAllHotel(Page<Hotel> pages) {
        int count = (int) hotelDao.count();
        //设置总记录数
        pages.setTotalSize(count);
        return hotelDao.findAllHotel((pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
    }

    public List<Hotel> findAllHotelByName(Page<Hotel> pages, String name) {
        Integer count = hotelDao.getCountByName("%" + name + "%");
        pages.setTotalSize(count);
        List<Hotel> hotelList = hotelDao.getHotelByName("%" + name + "%",(pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
        return hotelList;
    }

    public void deleteHotelById(Integer id) {
        hotelDao.deleteById(id);
    }

    public Hotel findHotelById(Integer id) {
        Hotel hotelById = hotelDao.findHotelById(id);
        return hotelById;
    }

    //传入hotel.id 则进行修改，不传入id则插入
    public Hotel saveHotel(Hotel hotel) {
        return hotelDao.save(hotel);
    }

    public List<Hotel> findAllType() {
        List<Hotel> allType = hotelDao.findAll();
        for (int i = 0; i < allType.size()-1; i++) {
            for (int j = allType.size()-1; j > i; j--) {
                if (allType.get(j).getType().equals(allType.get(i).getType())) {
                    allType.remove(j);
                }
            }
        }
        System.out.println(allType.toString());
        return allType;
    }

    public  List<Hotel> findAllHotel() {
        List<Hotel> all = hotelDao.findAll();
        for (int i = 0; i < all.size()-1; i++) {
            for (int j = all.size()-1; j > i; j--) {
                if (all.get(j).getName().equals(all.get(i).getName())) {
                    all.remove(j);
                }
            }
        }
        System.out.println(all.toString());
        return all;
    }

    public Double getHotelMoney(String name,String type) {
        Hotel hotel = hotelDao.getMoney(name, type);
        double price =(double) hotel.getPrice();
        return price;
    }

}
