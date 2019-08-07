package com.wl.service;

import com.wl.dto.Exposer;
import com.wl.dto.SeckillExecution;
import com.wl.entity.Seckill;
import com.wl.exception.RepeatKillException;
import com.wl.exception.SeckillCloseException;
import com.wl.exception.SeckillException;

import java.util.List;

/**
 * 
 */
public interface  SeckillService {

    /**
     * 查询所有的秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址,否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param uerphone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long uerphone, String md5) 
            throws SeckillException, SeckillCloseException, RepeatKillException;
}
