package com.web.service;

import com.web.bean.DO.AdvTransferAccounts;

/**
 * @author chxy
 * @date 2019/1/18 3:40 PM
 */
public interface IAdvTransferAccountsService {

    /**
     * 新增
     * @param advTransferAccounts
     * @return
     */
    boolean insertTransferAccount(AdvTransferAccounts advTransferAccounts);

}
