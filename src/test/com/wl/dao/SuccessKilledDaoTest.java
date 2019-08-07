package com.wl.dao;

import com.wl.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax. annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration(locations="classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;
    
    
    @Test
    public void insertSuccessKilled() {
        
        long id = 1001;
        long phone = 13602181181L;
        Date successTime = new Date();
        int inserCount = successKilledDao.insertSuccessKilled(id,phone,successTime);
        System.out.println(inserCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1000;
        long phone = 13502181181L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}