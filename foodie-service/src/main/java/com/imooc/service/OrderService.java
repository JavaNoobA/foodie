package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBo;

/**
 * Created by eru on 2020/2/7.
 */
public interface OrderService {

    /**
     * 创建订单
     * @param submitOrderBo
     */
    void createOrder(SubmitOrderBo submitOrderBo);
}
