package com.wl.exception;

import com.wl.entity.SuccessKilled;
import com.wl.enums.SeckillStatEnum;

/**
 * 秒杀相关的异常
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(long seckillId, SeckillStatEnum success, SuccessKilled successKilled) {
    }
    
}
