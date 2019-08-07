CREATE TABLE seckill(
  `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int NOT  NULL COMMENT '库存数量',
  `start_time` timestamp not NULL  COMMENT '秒杀开启时间',
  `end_time` timestamp not NULL  COMMENT '秒杀结束时间',
  `create_time` timestamp  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY PK_SECKILL_ID(seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表'
;

-- 初始化数据
insert into seckill
    (name,number,start_time,end_time)
values 
       ('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-03 00:00:00'),
       ('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
       ('300元秒杀小米4',300,'2015-11-01 00:00:00','2015-11-03 00:00:00'),
       ('200元秒杀红米note4',400,'2015-11-01 00:00:00','2015-11-02 00:00:00');


-- 秒杀成功明细表
-- 用户登录认证的相关信息
create table success_killed(
  `seckill_id` bigint not null COMMENT '秒杀商品id',
  `user_phone` bigint not null COMMENT  '用户手机号',
  `state` tinyint not null DEFAULT -1 COMMENT  '状态标示:-1:无效 0:成功 1:已付款',
  `create_time` timestamp not null  COMMENT  '创建时间',
  primary key (seckill_id,user_phone),
  key idx_create_time(create_time)
) ENGINE =InnoDB default CHARSET =utf8 COMMENT '秒杀成功明细表'