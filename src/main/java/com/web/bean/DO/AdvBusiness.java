package com.web.bean.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商家表
 * @author chxy
 */
@NoArgsConstructor
@Data
public class AdvBusiness {

    /**主键id*/
    private int id;

    /** 用户id*/
    private int user_id;

    /**余额*/
    private double balance;

    /** */
    private double freeze_count;

    /**
     * 状态 1审核中， 2审核通过 3 审核不通过
     */
    private int status;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 负责人姓名
     */
    private String personInCharge;

    /** 负责人电话 */
    private String personInChargePhone;

    /** 公司地址*/
    private String companyAddress;

}
