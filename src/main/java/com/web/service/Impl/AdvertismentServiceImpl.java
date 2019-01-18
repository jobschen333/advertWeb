package com.web.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.DO.AdvAdvert;
import com.web.bean.VO.AdvAdvertVO;
import com.web.dao.AdvAdvertDao;
import com.web.service.IAdvertismentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 广告
 * @author chxy
 */
@Service("advertismentService")
public class AdvertismentServiceImpl implements IAdvertismentService {

    @Autowired
    private AdvAdvertDao advAdvertDao;

    /**
     * 查找
     * @param advAdvert
     * @return
     */
    @Override
    public List<AdvAdvertVO> select(AdvAdvert advAdvert, int userId) {
        return advAdvertDao.select(advAdvert, userId);
    }

    /**
     * 分页查找
     * @param advAdvert
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo selectPage(AdvAdvert advAdvert, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<AdvAdvert> list = advAdvertDao.selectPage(advAdvert);
        PageInfo<AdvAdvert> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 新增
     * @param advAdvert
     * @return
     */
    @Override
    public boolean insert(AdvAdvert advAdvert) {
        return advAdvertDao.insert(advAdvert) > 0;

    }

    /**
     * 查找一个
     * @param id
     * @return
     */
    @Override
    public AdvAdvert selectOne(int id) {
        return advAdvertDao.selectOne(id);
    }

    /**
     * 点击广告
     * @param advAdvert
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int clickAdv(AdvAdvert advAdvert) {
        //如果广告点击数大于等于
        if (advAdvert.getCount_click()>=advAdvert.getMust_click()){
            return -1;
        }
        //如果广告数小于那么点击数+1
        if (advAdvert.getCount_click()<advAdvert.getMust_click()){
            int affect = advAdvertDao.addClick(advAdvert.getId());
            if (affect == 0){
                return -2;
            }
        }
        //如果加一后那么修改状态
        if ((advAdvert.getCount_click()+1)==advAdvert.getMust_click()){
            int affect = advAdvertDao.updateStatus(advAdvert.getId(),2);
            if (affect == 0){
                throw new RuntimeException("修改失败");
            }
        }
        return 1;
    }
}
