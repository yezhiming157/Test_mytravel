package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.ScenicOrder;
import com.jxau.yzm.mytravelproject.service.ScenicOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName ScenicOrderController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 17:45
 * @Version 1.0
 **/
@CrossOrigin
@Controller
public class ScenicOrderController {

    @Autowired
    ScenicOrderService scenicOrderService ;
    private static final Logger log = LoggerFactory.getLogger(ScenicOrderController.class);
    Page<ScenicOrder> pages = null;
    List<ScenicOrder> orders =null;


    //前往景区订单首页
    @GetMapping(value = "/admin/toScenicOrder")
    public String toScenicOrder(){
        return "/admin/order_scenic";
    }

    @GetMapping(value = "/admin/scenicOrder/{ordercode}")
    public String selectOrder(@PathVariable(value = "ordercode") String ordercode, Model model){
        ScenicOrder scenicOrder = scenicOrderService.findOrderByCode(ordercode);
        model.addAttribute("scenicOrder",scenicOrder);
        return "/admin/orderScenicInfo";
    }

    @DeleteMapping(value = "/admin/scenicOrder")
    @ResponseBody
    public Result deleteOrder(@RequestParam(value = "ordercode")String ordercode){
        Result result = new Result();
        try {
            scenicOrderService.deleteOrder(ordercode);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！");
        }
        return result;
    }

    @PostMapping(value = "/admin/scenicOrders")
    @ResponseBody
    public Result scenicOrderList(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
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
            orders =  scenicOrderService.findAllOrderByScenic(pages,queryText);
        }else {
            orders = scenicOrderService.findAllOrder(pages);
            log.debug("allscenic"+orders.toString());
        }
        //设置列表数据
        pages.setDatas(orders);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }
}
