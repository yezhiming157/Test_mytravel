package com.jxau.yzm.mytravelproject.controller.user;

import com.jxau.yzm.mytravelproject.pojo.Hotel;
import com.jxau.yzm.mytravelproject.pojo.Message;
import com.jxau.yzm.mytravelproject.pojo.News;
import com.jxau.yzm.mytravelproject.pojo.Scenic;
import com.jxau.yzm.mytravelproject.service.HotelService;
import com.jxau.yzm.mytravelproject.service.MessageService;
import com.jxau.yzm.mytravelproject.service.NewsService;
import com.jxau.yzm.mytravelproject.service.ScenicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName IndexController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-17 17:13
 * @Version 1.0
 **/
@Controller
@CrossOrigin
public class IndexController {

    @Autowired
    MessageService messageService;
    @Autowired
    ScenicService scenicService ;
    @Autowired
    HotelService hotelService;
    @Autowired
    NewsService newsService;
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/index")
    public String toIndex(Model model){
       List<Message> allMessage = messageService.findAllMessage();
       model.addAttribute("messages",allMessage);
       model.addAttribute("act","index");
       return "/user/index";
    }

    @GetMapping("/user/about")
    public String toAbout(Model model){
        List<News> news = newsService.findNews();
        model.addAttribute("newss",news);
        model.addAttribute("act","about");
        return "/user/about";
    }

    @GetMapping("/user/booking")
    public String toBooking(Model model){
        List<Scenic> scenicName = scenicService.findAllName();
        model.addAttribute("scenicName",scenicName);
        log.debug(scenicName.toString());
        List<Hotel> allHotel = hotelService.findAllHotel();
        model.addAttribute("hotelLists",allHotel);
        List<Hotel> allHotelType = hotelService.findAllType();
        model.addAttribute("hotels",allHotelType);
        model.addAttribute("act","booking");
        return "/user/booking";
    }

    @GetMapping("/user/gallery")
    public String toGallery(Model model){
        model.addAttribute("act","gallery");
        return "/user/gallery";
    }

    @GetMapping("/user/cuisines")
    public String toCuisines(Model model){
        model.addAttribute("act","cuisines");
        return "/user/cuisines";
    }


    @GetMapping("/user/login")
    public String toLogin(Model model){
        model.addAttribute("act","login");
        return "/user/login";
    }

    @GetMapping("/user/myBooking")
    public String myBooking(Model model){
        model.addAttribute("act","myBooking");
        return "/user/myBooking";
    }

}
