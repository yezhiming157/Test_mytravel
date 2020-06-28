package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.GalleryDao;
import com.jxau.yzm.mytravelproject.pojo.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mytravelproject
 * @ClassName GalleryService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-15 16:27
 * @Version 1.0
 **/
@Service
public class GalleryService {


    @Autowired
    GalleryDao galleryDao;


    public Gallery saveGalley(Gallery gallery) {
        Gallery g = galleryDao.save(gallery);
        return g;
    }

    public List<Gallery> findAlgallerys(Page<Gallery> pages) {
        int count = (int) galleryDao.count();
        //设置总记录数
        pages.setTotalSize(count);
        return galleryDao.findAlgallerys((pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
    }
}
