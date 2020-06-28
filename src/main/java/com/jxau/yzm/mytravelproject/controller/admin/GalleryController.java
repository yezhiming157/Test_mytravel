package com.jxau.yzm.mytravelproject.controller.admin;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.common.Result;
import com.jxau.yzm.mytravelproject.pojo.Gallery;
import com.jxau.yzm.mytravelproject.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @program: mytravelproject
 * @ClassName GalleryController
 * @description:
 * @author: yezhiming
 * @create: 2020-03-15 16:26
 * @Version 1.0
 **/
@CrossOrigin
@Controller
public class GalleryController {

    @Autowired
    GalleryService galleryService;
    private static final Logger log = LoggerFactory.getLogger(GalleryController.class);
    Page<Gallery> pages = null;
    List<Gallery> galleries =null;

    @GetMapping("/admin/gallerys")
    public String toGallery(){
        return "/admin/manage_gallery";
    }

    @GetMapping("/admin/gallery")
    public String toAddGallery(){
        return "/admin/addgallery";
    }

    @PostMapping("/admin/gallerys")
    @ResponseBody
    public Result getGallerys(@RequestParam(value = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                              Integer pageSize){
        Result result = new Result();
        pages = new Page<>() ;
        //设置当前页数
        pages.setCurrentPage(currentPage);
        //设置每页显示记录数
        pages.setPageSize(pageSize);
        galleries = galleryService.findAlgallerys(pages);
        pages.setDatas(galleries);
        result.setData(pages);
        result.setSuccess(true);
        log.debug(result.toString());
        return result;// 将对象序列化为JSON字符串，以流的形式返回
    }

    @PostMapping("/admin/gallery")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file,
                             @RequestParam(value = "galleryname") String name,
                             @RequestParam(value = "comment") String commont,
                             Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        // 上传后的路径  上传到picture上
        String filePath = "G:\\IdeaProjects\\mytravelproject\\src\\main\\resources\\static\\picture\\";
        //由于上传到IDEA上没有重建项目资源就无法显示出来 因此上传到本地D盘上
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        String realPath = request.getServletContext().getRealPath("");
        log.debug(realPath);
       if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/picture/" + fileName;
        Gallery gallery = new Gallery() ;
        gallery.setImg(filename);
        gallery.setComment(commont);
        gallery.setTitle(name);
        Gallery gallery1 = galleryService.saveGalley(gallery);
        if (gallery1!=null) {
            log.debug(gallery.toString());
            log.debug("图片上传路径："+dest.getPath());
            model.addAttribute("gallery", gallery);
        }
            return "/admin/addgallery";
    }

}