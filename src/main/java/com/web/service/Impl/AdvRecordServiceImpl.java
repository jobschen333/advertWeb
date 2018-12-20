package com.web.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.VO.AdvRecordVO;
import com.web.dao.AdvRecordDao;
import com.web.service.IAdvRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chxy
 */
@Service("advRecordService")
public class AdvRecordServiceImpl implements IAdvRecordService {

    @Autowired
    private AdvRecordDao advRecordDao;

    @Override
    public PageInfo selectRecordList(int userId, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<AdvRecordVO> list = advRecordDao.selectRecordList(userId);
        return new PageInfo(list);
    }
}
