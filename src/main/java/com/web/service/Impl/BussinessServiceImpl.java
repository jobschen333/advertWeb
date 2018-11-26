package com.web.service.Impl;

import com.web.dao.BussinessDao;
import com.web.dao.WalletDao;
import com.web.service.IBussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商家表实现类
 * @author chxy
 */
@Service("bussinessService")
public class BussinessServiceImpl implements IBussinessService{

    @Autowired
    private BussinessDao bussinessDao;

    @Autowired
    private WalletDao walletDao;
    /**
     * 交易token
     * @param clickToken
     * @param businessId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean changeToken(double clickToken, int businessId, int userId) {

        boolean boo = bussinessDao.decrease(businessId, clickToken);
        if (boo == false){
            return false;
        }
        boolean bool = walletDao.addToken(userId, clickToken);
        if (bool == false){
            throw new RuntimeException("交易token失败!");
        } else {
            return true;
        }
    }
}
