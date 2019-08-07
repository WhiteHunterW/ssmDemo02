package com.wl.service;

import com.wl.dto.Exposer;
import com.wl.dto.SeckillExecution;
import com.wl.entity.Seckill;
import com.wl.exception.RepeatKillException;
import com.wl.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration(locations="classpath:spring/spring-*.xml")
public class SeckillServiceTest {

   private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() {

        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1003;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 135021771128L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",execution);
            }
            catch (RepeatKillException e){
                logger.error(e.getMessage());
            }
            catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        }
        else {
            logger.warn("exposer={}",exposer);
        }
    }
}