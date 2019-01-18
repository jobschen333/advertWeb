package com.web.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.DO.AdvTransferAccounts;
import com.web.dao.IAdvTransferAccountsDao;
import com.web.service.IAdvTransferAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chxy
 * @date 2019/1/18 3:40 PM
 */

@Service
public class AdvTransferAccountsService implements IAdvTransferAccountsService {

    @Autowired
    private IAdvTransferAccountsDao advTransferAccountsDao;

    @Override
    public boolean insertTransferAccount(AdvTransferAccounts advTransferAccounts) {
        return advTransferAccountsDao.insertTransferAccount(advTransferAccounts) > 0;
    }

    @Override
    public PageInfo selectTransferRecordList(int userId, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<AdvTransferAccounts> list = advTransferAccountsDao.selectList(userId);
        return new PageInfo(list);
    }
}
