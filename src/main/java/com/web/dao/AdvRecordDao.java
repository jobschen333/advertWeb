package com.web.dao;
import com.web.bean.DO.AdvRecordDO;
import com.web.bean.VO.AdvRecordVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 钱包转账记录表
 * @author chxy
 */
@ResponseBody
public interface AdvRecordDao {

    /**
     * 添加点击广告记录
     * @param advRecordDO
     * @return
     */
    boolean insertRecord(AdvRecordDO advRecordDO);

    /**
     * 分页查找
     * @param userId
     * @return
     */
    List<AdvRecordVO> selectRecordList(@Param("userId") int userId);

    /**
     * 搜索点击广告记录
     * @param advertId
     * @param userId
     * @return
     */
    AdvRecordDO selectRecord(@Param("advertId") Integer advertId,@Param("userId") int userId);
}
