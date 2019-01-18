package com.web.dao;

import com.web.bean.DO.AdvAdvert;
import com.web.bean.VO.AdvAdvertVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 广告
 * @author chxy
 */
@Repository
public interface AdvAdvertDao {

    /**
     * 查找图片页面列表
     * @param advAdvert
     * @param userId
     * @return
     */
    List<AdvAdvertVO> select(@Param("advAdvert") AdvAdvert advAdvert, @Param("userId") int userId);

    /**
     * 查找商家后台
     * @param advAdvert
     * @return
     */
    List<AdvAdvert> selectPage(AdvAdvert advAdvert);

    /**
     * 新增
     * @param advAdvert
     * @return
     */
    int insert(AdvAdvert advAdvert);

    /**
     * 查找一个
     * @param id
     * @return
     */
    AdvAdvert selectOne(int id);

    /**
     * 增加点击数
     * @param id
     * @return
     */
    int addClick(int id);

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id") int id, @Param("status") int status);
}
