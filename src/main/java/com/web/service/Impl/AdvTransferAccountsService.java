package com.web.service.Impl;

import com.web.bean.DO.AdvTransferAccounts;
import com.web.dao.IAdvTransferAccountsDao;
import com.web.service.IAdvTransferAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
