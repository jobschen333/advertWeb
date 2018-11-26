package com.web.service;

import com.github.pagehelper.PageInfo;
import com.web.bean.DO.AdvAdvert;

import java.util.List;

/**
 * 广告
 * @author chxy
 */
public interface IAdvertismentService {
    /**
     * 查找
     * @param advAdvert
     * @return
     */
    List<AdvAdvert> select(AdvAdvert advAdvert);

    /**
     * 分页查找
     * @param advAdvert
     * @param page
     * @param limit
     * @return
     */
    PageInfo selectPage(AdvAdvert advAdvert, int page, int limit);

    /**
     * 新增
     * @param advAdvert
     * @return
     */
    boolean insert(AdvAdvert advAdvert);

    /**
     * 查找一个
     * @param id
     * @return
     */
    AdvAdvert selectOne(int id);

    /**
     * 点击广告
     * @param advAdvert
     * @return
     */
    int clickAdv(AdvAdvert advAdvert);
}
