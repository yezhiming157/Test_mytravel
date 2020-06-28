package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.News;
import com.jxau.yzm.mytravelproject.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName NewsController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 15:51
 * @Version 1.0
 **/
@Controller
@CrossOrigin
public class NewsController {

    @Autowired
    NewsService newsService;
    private static final Logger log = LoggerFactory.getLogger(NewsController.class);
    Page<News> pages = null;
    List<News> allNews =null;

    @GetMapping(value = "/admin/newss")
    public String toNewsIndex(){
        return "/admin/manage_news";
    }
    //新闻添加
    @GetMapping("/admin/news")
    public String addNews(Model model){
        News news = null;
        model.addAttribute("news",news);
        return "/admin/editNews";
    }
    //新闻修改页面
    @GetMapping("/admin/news/{id}")
    public String toModifyNews(@PathVariable("id") Integer id, Model model){
        News news = newsService.findNews(id);
        model.addAttribute("news",news);
        return "/admin/editNews";
    }

    @PutMapping("/admin/news")
    @ResponseBody
    public Result modifyNews(News news){
        Result result = new Result();
        String time = this.getTime();
        news.setDate(time);
        log.debug("time="+time);
        News s = newsService.modifyNews(news);
        if (s!=null){
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
            result.setMessage("修改信息失败！！");
        }
        return result;
    }

    @PostMapping("/admin/news")
    @ResponseBody
    public Result saveNews(News news){
        Result result = new Result();
        String time = this.getTime();
        news.setDate(time);
        log.debug("time="+time);
        News s = newsService.modifyNews(news);
        if (s!=null){
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
            result.setMessage("添加新闻失败！！");
        }
        return result;
    }

    @DeleteMapping(value = "/admin/news")
    @ResponseBody
    public Result deleteNews(@RequestParam("id") Integer id){
        Result result = new Result();
        try {
            newsService.deleteNewsById(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！！");
        }
        return result;
    }

    @PostMapping(value = "/admin/toNewsIndex")
    @ResponseBody
    public Result getnewsList(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
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
            allNews =  newsService.findAllNewsByName(pages,queryText);
        }else {
            allNews = newsService.findAllNews(pages);
            log.debug("newsService"+newsService.toString());
        }
        //设置列表数据
        pages.setDatas(allNews);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }

    //获取当前日期和时间
    public String getTime(){
        Date now = new Date();
        DateFormat d2 = DateFormat.getDateTimeInstance();
        String time = d2.format(now);
        return time;
    }

}
