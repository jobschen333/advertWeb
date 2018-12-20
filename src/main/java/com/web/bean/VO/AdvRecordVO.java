package com.web.bean.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 点击广告记录VO
 * @author chxy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvRecordVO {

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

    /** 广告名称*/
    private String title;
}
