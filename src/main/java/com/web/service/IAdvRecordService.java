package com.web.service;

import com.github.pagehelper.PageInfo;
import com.web.bean.DO.AdvRecordDO;

/**
 * 广告点击记录
 * @author chxy
 */
public interface IAdvRecordService {

    /**
     * 分页查找
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    PageInfo selectRecordList(int userId, Integer page, Integer limit);

    /**
     * 搜索点击记录
     * @param id
     * @param userId
     * @return
     */
    AdvRecordDO selectRecord(Integer id, int userId);

}
