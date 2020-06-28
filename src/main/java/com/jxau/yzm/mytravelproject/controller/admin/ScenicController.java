package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.Scenic;
import com.jxau.yzm.mytravelproject.service.ScenicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName ScenicController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-11 15:23
 * @Version 1.0
 **/

@Controller
@CrossOrigin
public class ScenicController {

    @Autowired
    ScenicService scenicService;
    private static final Logger log = LoggerFactory.getLogger(ScenicController.class);
    Page<Scenic> pages = null;
    List<Scenic> allScenic =null;

    //来到景点管理页面
    @GetMapping("/admin/scenics")
    public String toScenic(){
        return "/admin/manage_scenic";
    }

    //景点添加
    @GetMapping("/admin/scenic")
    public String addScenic(Model model){
        Scenic scenic = null;
        model.addAttribute("scenic",scenic);
        return "/admin/editScenic";
    }

    @PostMapping("/admin/scenic")
    @ResponseBody
    public Result saveScenic(Scenic scenic){
        Result result = new Result();
        Scenic s = scenicService.saveScenic(scenic);
        if (s!=null){
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
            result.setMessage("添加景点失败！！");
        }
        return result;
    }

    //景点修改页面
    @GetMapping("/admin/scenic/{id}")
    public String toModifyScenic(@PathVariable("id") Integer id, Model model){
        Scenic scenic = scenicService.findScenic(id);
        model.addAttribute("scenic",scenic);
        return "/admin/editScenic";
    }

    @PutMapping("/admin/scenic")
    @ResponseBody
    public Result modifyScenic(Scenic scenic){
        log.debug(scenic.toString());
        Result result = new Result();
       Scenic s = scenicService.modifyScenic(scenic);
        if (s!=null){
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
            result.setMessage("修改信息失败！！");
        }
        return result;
    }

    /*
     *  删除景点信息
     */
    @DeleteMapping("/admin/scenic")
    @ResponseBody
    public Result deleteScenic(@RequestParam("id") Integer id){
        Result result = new Result();
        try {
            scenicService.deleteScenicById(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！");
        }
        return result;
    }


    /*
    *     查询风景列表
    *   传入的queryText为查询条件
    * */
    @PostMapping("/admin/toScenicIndex")
    @ResponseBody
    public Result toScenicIndex(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                                Integer pageSize,
                                @RequestParam(value = "queryText", required = false) String queryText) {
        Result result = new Result();
        pages = new Page<>() ;
        //设置当前页数
        pages.setCurrentPage(currentPage);
        //设置每页显示记录数
        pages.setPageSize(pageSize);
        log.debug("queryText="+queryText);
        if (queryText != null && !queryText.equals("")){
           allScenic =  scenicService.findAllScenicByName(pages,queryText);
        }else {
            allScenic = scenicService.findAllScenic(pages);
            log.debug("allscenic"+allScenic.toString());
        }
        //设置列表数据
        pages.setDatas(allScenic);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }



}
