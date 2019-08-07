package com.wl.controller;

import com.wl.entity.Seckill;
import com.wl.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 */

@Controller
@RequestMapping("/seckill")
public class SeckillContrller {
    
    @Autowired
    private SeckillService seckillService;
    
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public Seckill getById(long seckillId, HttpServletResponse response, HttpServletRequest request){
        Seckill seckill = seckillService.getById(seckillId);
        return seckill;
    }
    
    @RequestMapping(value = "/getSeckillList",method = RequestMethod.GET)
    @ResponseBody
    public List getSeckillList() {
        List<Seckill> lists = seckillService.getSeckillList();
        return lists;
    }
}
