package com.web.bean.VO;

import com.web.bean.DO.AdvRecordDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 广告表
 * @author chxy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvAdvertVO {

    /**主键id*/
    private int id;

    /** 标题*/
    private String title;

    /** 内容*/
    private String content;

    /** 图片链接*/
    private String url;

    /** 图片地址*/
    private String pic;

    /** 花费token*/
    private BigDecimal waste_token;

    /** 最大点击数*/
    private int must_click;

    /** 已点击总数*/
    private int count_click;

    /** 添加时间*/
    private Date add_time;

    /**
     * 状态
     */
    private int status;

    /**
     * 商家id
     */
    private int businessId;

    /**
     * 排名
     */
    private int rank;

    /**
     * 单次点击的金额
     */
    private BigDecimal clickToken;

    /**
     * 是否点击
     */
    private AdvRecordDO advRecordDO;

}
