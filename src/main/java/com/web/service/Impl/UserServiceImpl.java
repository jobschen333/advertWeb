package com.web.service.Impl;

import com.web.bean.DO.AdvUser;
import com.web.bean.DO.AdvWallet;
import com.web.bean.VO.UserVO;
import com.web.dao.UserDao;
import com.web.dao.WalletDao;
import com.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;

/**
 * 服务层
 * @author LinkinStar
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WalletDao walletDao;



    /**
     * 获得user信息
     * @param id
     * @return
     */
    @Override
    public UserVO getUser(int id) {
        return userDao.getUser(id);
    }

    /**
     * 通过用户名和密码查找
     * @param userAccount
     * @param password
     * @return
     */
    @Override
    public AdvUser selectByUserAccountAndPassword(String userAccount, String password) {
        return userDao.selectByUserAccountAndPassword(userAccount, password);
    }

    /**
     * 注册
     * @param advUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(AdvUser advUser) {
        boolean boo = userDao.insertAdvUser(advUser);
        if (!boo) {
            return false;
        }
        AdvWallet advWallet = AdvWallet.builder()
                .balance(new BigDecimal(0))
                .user_id(advUser.getId())
                .build();
        int affect = walletDao.insert(advWallet);
        if (affect == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        } else {
            return true;
        }
    }

}
