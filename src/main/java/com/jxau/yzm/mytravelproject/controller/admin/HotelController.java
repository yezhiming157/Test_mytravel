package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.Hotel;
import com.jxau.yzm.mytravelproject.pojo.Scenic;
import com.jxau.yzm.mytravelproject.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName HotelController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-12 15:20
 * @Version 1.0
 **/

@Controller
@CrossOrigin
public class    HotelController {

    Result result = null ;
    @Autowired
    HotelService hotelService;
    private static final Logger log = LoggerFactory.getLogger(HotelController.class);
    Page<Hotel> pages = null;
    List<Hotel> allHotels =null;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping(value = "/admin/hotels")
    public String toHotel(){
        return "/admin/manage_hotel";
    }

    //酒店添加
    @GetMapping("/admin/hotel")
    public String addScenic(Model model){
        Scenic hotel = null;
        model.addAttribute("hotel",hotel);
        return "/admin/editHotel";
    }


    @GetMapping("/admin/hotel/{id}")
    public String toModifyHotel(@PathVariable("id") Integer id,Model model){
        Hotel hotel = hotelService.findHotelById(id);
        model.addAttribute("hotel",hotel);
        log.debug(hotel.toString());
        return "/admin/editHotel";
    }

    @PutMapping("/admin/hotel")
    @ResponseBody
    public Result modifyHotel(Hotel hotel){
        result = new Result();
        Hotel hotel1 = hotelService.saveHotel(hotel);
        if(hotel1 !=null){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
            result.setMessage("修改失败！！");
        }
        return result;
    }




    @PostMapping("/admin/hotel")
    @ResponseBody
    public Result addHotel(Hotel hotel){
        result = new Result();
        Hotel hotel1 = hotelService.saveHotel(hotel);
        if(hotel1 !=null){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
            result.setMessage("添加失败！！");
        }
        return result;
    }

    @DeleteMapping(value = "/admin/hotel")
    @ResponseBody
    public Result deleteHotel(@RequestParam("id") Integer id){
        result = new Result();
        try {
            hotelService.deleteHotelById(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！");
        }
        return result;
    }


    @PostMapping(value = "/admin/toHotelIndex")
    @ResponseBody
    public Result hotelList(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                            Integer pageSize,
                            @RequestParam(value = "queryText", required = false) String queryText){
        result = new Result();
        pages = new Page<>() ;
        //设置当前页数
        pages.setCurrentPage(currentPage);
        //设置每页显示记录数
        pages.setPageSize(pageSize);
        log.debug("queryText="+queryText);
        if (queryText != null && !queryText.equals("")){
            allHotels =  hotelService.findAllHotelByName(pages,queryText);
        }else {
            allHotels = hotelService.findAllHotel(pages);
        }
        //设置列表数据
        pages.setDatas(allHotels);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回

    }

}
