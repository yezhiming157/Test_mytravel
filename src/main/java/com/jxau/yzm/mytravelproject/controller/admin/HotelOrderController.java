package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.HotelOrder;
import com.jxau.yzm.mytravelproject.service.HotelOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @program: mytravelproject
 * @ClassName HotelOrderController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 22:10
 * @Version 1.0
 **/
@Controller
@CrossOrigin
public class HotelOrderController {

    @Autowired
    HotelOrderService hotelOrderService ;
    private static final Logger log = LoggerFactory.getLogger(HotelOrderController.class);
    Page<HotelOrder> pages = null;
    List<HotelOrder> orders = null;

    //前往酒店订单首页
    @GetMapping("/admin/hotelOrders")
    public String toHotelOrder(){
        return "/admin/order_hotel";
    }

    @DeleteMapping(value = "/admin/hotelOrder")
    @ResponseBody
    public Result deleteOrder(@RequestParam(value = "ordercode")String ordercode){
        Result result = new Result();
        try {
            hotelOrderService.deleteOrder(ordercode);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！");
        }
        return result;
    }

    @GetMapping(value = "/admin/hotelOrder/{ordercode}")
    public String selectOrder(@PathVariable(value = "ordercode") String ordercode, Model model){
        HotelOrder hotelOrder = hotelOrderService.findOrderByCode(ordercode);
        model.addAttribute("hotelOrder",hotelOrder);
        return "/admin/orderHotelInfo";
    }

    @PostMapping(value = "/admin/toHotelOrders")
    @ResponseBody
    public Result hotelOrderList(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                                    Integer pageSize,
                                 @RequestParam(value = "queryText", required = false) String queryText){
        Result result = new Result() ;
        pages = new Page<>() ;
        //设置当前页数
        pages.setCurrentPage(currentPage);
        //设置每页显示记录数
        pages.setPageSize(pageSize);
        log.debug("queryText="+queryText);
        if (queryText != null && !queryText.equals("")){
            orders =  hotelOrderService.findAllOrderByHotel(pages,queryText);
        }else {
            orders = hotelOrderService.findAllOrder(pages);
            log.debug("hotelOrders"+orders.toString());
        }
        //设置列表数据
        pages.setDatas(orders);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }

}
