package com.web.service.Impl;

import com.web.bean.DO.AdvBusiness;
import com.web.dao.BusinessDao;
import com.web.dao.WalletDao;
import com.web.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 商家表实现类
 * @author chxy
 */
@Service("bussinessService")
public class BussinessServiceImpl implements IBusinessService {

    @Autowired
    private BusinessDao bussinessDao;

    @Autowired
    private WalletDao walletDao;
    /**
     * 交易token
     * @param clickToken
     * @param businessId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean changeToken(BigDecimal clickToken, int businessId, int userId) {
        boolean boo = bussinessDao.decrease(businessId, clickToken);
;        if (!boo){
            return false;
        }
        boolean bool = walletDao.addToken(userId, clickToken);
        if (!bool) {
            throw new RuntimeException("交易token失败!");
        }
        return true;

    }

    /**
     * 通过用户id查找商家
     * @param userId
     * @return
     */
    @Override
    public AdvBusiness selectByUserId(int userId) {
        return bussinessDao.selectByUserId(userId);
    }
}
