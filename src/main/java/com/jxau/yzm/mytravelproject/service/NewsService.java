package com.jxau.yzm.mytravelproject.service;

import com.jxau.yzm.mytravelproject.common.Page;
import com.jxau.yzm.mytravelproject.dao.NewsDao;
import com.jxau.yzm.mytravelproject.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: mytravelproject
 * @ClassName SloganService
 * @description:
 * @author: yezhiming
 * @create: 2020-03-13 16:07
 * @Version 1.0
 **/
@Service
public class NewsService {

    @Autowired
    NewsDao newsDao;

    public List<News> findAllNewsByName(Page<News> pages, String name) {
        int count = newsDao.getCountByName("%" + name + "%");
        pages.setTotalSize(count);
        List<News> newsList = newsDao.getNewsByName("%" + name + "%",(pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
        return newsList;
    }

    public List<News> findAllNews(Page<News> pages) {
        int count = (int) newsDao.count();
        //设置总记录数
        pages.setTotalSize(count);
        return newsDao.findAllNews((pages.getCurrentPage()-1)*pages.getPageSize(), pages.getPageSize());
    }

    public void deleteNewsById(Integer id) {
        newsDao.deleteById(id);
    }

    public News findNews(Integer id) {
        Optional<News> newsOpt = newsDao.findById(id);
        News news = newsOpt.get();
        return news;
    }

    public News modifyNews(News news) {
        News news1 = newsDao.save(news);
        return news1;
    }

    public List<News> findNews() {
        List<News> news = newsDao.findAll();
        return news;
    }
}
