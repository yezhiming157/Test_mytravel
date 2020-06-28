package com.jxau.yzm.mytravelproject.controller.user;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.Orders;
import com.jxau.yzm.mytravelproject.service.BookingService;
import com.jxau.yzm.mytravelproject.service.HotelOrderService;
import com.jxau.yzm.mytravelproject.service.HotelService;
import com.jxau.yzm.mytravelproject.service.ScenicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @program: mytravelproject
 * @ClassName BookingController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-19 18:31
 * @Version 1.0
 **/
@CrossOrigin
@Controller
public class BookingController {


    @Autowired
    HotelService hotelService;
    @Autowired
    BookingService bookingService;
    @Autowired
    ScenicService scenicService;
    @Autowired
    HotelOrderService hotelOrderService;
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    Page<Orders> pages = null;
    List<Orders> orders = null;


    @GetMapping("/admin/toOrderIndex")
    public String toOrderIndex(){
        return "/admin/manage_orders";
    }


    @DeleteMapping("/admin/order")
    @ResponseBody
    public Result deleteOrder(@RequestParam(value = "id") Integer id){
        Result result = new Result();
        try {
            bookingService.deleteOrderbyId(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！");
        }
        return result;
    }

    @GetMapping("/admin/order/{id}")
    public String editOrder(@PathVariable(value = "id") Integer id, Model model){
        Orders order = bookingService.getOrderById(id);
        model.addAttribute("orders",order);
        return "/admin/orderInfo";
    }

    @PostMapping("/admin/orders")
    @ResponseBody
    public Result getOrders(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                            Integer pageSize,
                            @RequestParam(value = "queryText", required = false) String queryText){
        Result result = new Result();
        pages = new Page<>() ;
        //设置当前页数
        pages.setCurrentPage(currentPage);
        //设置每页显示记录数
        pages.setPageSize(pageSize);
        log.debug("queryText="+queryText);
        if (queryText != null && !queryText.equals("")){
            orders = bookingService.findAllOrderByName(pages, queryText);
        }else {
            orders = bookingService.findAllOrder(pages);
            log.debug("Orders"+orders.toString());
        }
        //设置列表数据
        pages.setDatas(orders);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }

    @PutMapping("/user/booking")
    @ResponseBody
    public Result booking(Orders order, HttpSession session){
        Result result = new Result();
        if(session.getAttribute("USERNAME_USER") == null){
            result.setSuccess(false);
            result.setMessage("请先登录再进行下单操作！！");
        } else{
                String orderCode = UUID.randomUUID().toString();
                try {
                    Double hotelMoney = hotelService.getHotelMoney(order.getHotel(), order.getType());
                    Double ScenicMoney = scenicService.getScenicMoney(order.getScenic());
                    Double money = hotelMoney + order.getAdult()*ScenicMoney;
                    order.setPaymoney(money);
                    order.setCode(orderCode);
                    log.debug(order.toString());
                    Orders order1 = bookingService.saveOrder(order);
                    if (order1!=null){
                        result.setSuccess(true);
                    }else{
                        result.setSuccess(false);
                        result.setMessage("预定失败！！");
                    }
                } catch (Exception e) {
                    result.setSuccess(false);
                    result.setMessage("预定失败！！");
                }
             }
        return result;
    }


    @PostMapping("/user/myBookings")
    @ResponseBody
    public Result getOrders(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                            Integer pageSize,HttpSession session) {
        Result result = new Result();
        pages = new Page<>() ;
        //设置当前页数
        pages.setCurrentPage(currentPage);
        //设置每页显示记录数
        pages.setPageSize(pageSize);
        if(session.getAttribute("USERNAME_USER") == null){
            result.setSuccess(false);
            result.setMessage("请先登录再查看！！");
        }else{
            String username = session.getAttribute("USERNAME_USER").toString();
            orders = bookingService.findMyOrderByName(pages,username);
            log.debug("Orders"+orders.toString());
            pages.setDatas(orders);
            result.setData(pages);
            result.setSuccess(true);
        }
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }

}
