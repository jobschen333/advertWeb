package com.web.bean.DO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 广告点击记录表
 * @author chxy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvRecordDO {

    /**主键id*/
    private Integer id;

    /**用户id*/
    private Integer userId;

    /**广告id*/
    private Integer advertId;

    /** 单次money*/
    private BigDecimal money;

    /** 添加时间*/
    private Timestamp addTime;
}
