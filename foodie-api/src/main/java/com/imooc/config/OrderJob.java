package com.imooc.config;

import com.imooc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by eru on 2020/2/8.
 */
@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    /**
     * 定时任务弊端：
     * 1. 全表扫描
     * 2. 会有时间差：比如下单 10:30，定时任务11点开始，每小时执行一次；那么就会有30分钟时间差
     * 3. 不支持集群
     */
    //@Scheduled(cron = "0/3 * * * * ? ")
    public void autoCloseOrder(){
        orderService.closeOrder();
    }
}
