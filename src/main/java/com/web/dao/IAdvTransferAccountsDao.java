package com.web.dao;

import com.web.bean.DO.AdvTransferAccounts;
import org.springframework.stereotype.Repository;

/**
 * @author chxy
 * @date 2019/1/18 3:41 PM
 */
@Repository
public interface IAdvTransferAccountsDao {

    /**
     * 新增转账
     * @param advTransferAccounts
     * @return
     */
    int insertTransferAccount(AdvTransferAccounts advTransferAccounts);
}
