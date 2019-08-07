package com.wl.dao;

import com.wl.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface SuccessKilledDao {

    /**
     * 插入购买明细
     * 返回插入的行数
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone, @Param("successTime")Date successTime);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * 查询结果也要返回seckill里面的信息
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userphone") long userphnoe);
}
