package com.wl.service.impl;

import com.wl.dao.SeckillDao;
import com.wl.dao.SuccessKilledDao;
import com.wl.dto.Exposer;
import com.wl.dto.SeckillExecution;
import com.wl.entity.Seckill;
import com.wl.entity.SuccessKilled;
import com.wl.enums.SeckillStatEnum;
import com.wl.exception.RepeatKillException;
import com.wl.exception.SeckillCloseException;
import com.wl.exception.SeckillException;
import com.wl.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SeckillDao seckillDao;
    
    @Autowired
    private SuccessKilledDao successKilledDao;
    
    //用于混淆md5
    private final String slat = "fsaerwerwqrr";
    
    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null){
             return new Exposer(false,seckillId);
        }
        Date startTime = seckill.getCreateTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime()< startTime.getTime() || nowTime.getTime() >endTime.getTime()){
            return  new
                    Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }
    private String getMd5(long seckilId){
        String base = seckilId + "/" +slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    @Override
    public SeckillExecution executeSeckill(long seckillId, long uerphone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if(md5 == null || md5.equals((getMd5(seckillId)))){
            throw  new SeckillException("Seckill data rewrite");}
            //执行秒杀逻辑:减库存+记录购买行为
               Date nowTime = new Date();
                try {
                    //减库存
                    int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
                    if (updateCount <= 0){
                        //没有更新到记录,秒杀结束
                        throw new SeckillCloseException("Seckill is close");
                    }else {
                        //记录购买行为
                        int insertCount = successKilledDao.insertSuccessKilled(seckillId,uerphone,nowTime);
                        if (insertCount <=0 ){
                            throw  new RepeatKillException("Seckill repeat");
                        }else {
                            SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,uerphone);
                            return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS,successKilled);
                        }
                    }
                }
                catch (SeckillCloseException e1){
                    throw e1;
                }
                catch (RepeatKillException e2){
                    throw e2;
                }
                catch (Exception e){
                    logger.error(e.getMessage());
                    throw new SeckillException("seckill inner error"+e.getMessage());
                }
            }
}
