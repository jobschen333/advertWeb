package com.web.dao;

import com.web.bean.DO.AdvTransferAccounts;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 查询转账
     * @param userId
     * @return
     */
    List<AdvTransferAccounts> selectList(@Param("userId") int userId);
}
