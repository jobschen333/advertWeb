package com.web.service.Impl;

import com.web.bean.DO.AdvBusiness;
import com.web.bean.DO.AdvRecordDO;
import com.web.dao.AdvRecordDao;
import com.web.dao.BusinessDao;
import com.web.dao.WalletDao;
import com.web.service.IBusinessService;
import com.web.util.DateUtil;
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

    @Autowired
    private AdvRecordDao advRecordDao;
    /**
     * 交易token
     * @param advertId 广告id
     * @param clickToken
     * @param businessId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean changeToken(int advertId,BigDecimal clickToken, int businessId, int userId) {
        boolean boo = bussinessDao.decrease(businessId, clickToken);
;        if (!boo){
            return false;
        }
        boolean bool = walletDao.addToken(userId, clickToken);
        if (!bool) {
            throw new RuntimeException("交易token失败!");
        }
        AdvRecordDO advRecordDO = AdvRecordDO.builder()
                .advertId(advertId)
                .money(clickToken)
                .userId(userId)
                .addTime(DateUtil.getCurrentTime())
                .build();
        boolean insertRecordResult = advRecordDao.insertRecord(advRecordDO);
        if (!insertRecordResult) {
            throw new RuntimeException("添加记录失败");
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
