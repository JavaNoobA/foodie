package com.imooc.service;

import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.SubmitOrderBo;
import com.imooc.pojo.vo.OrderVO;

/**
 * Created by eru on 2020/2/7.
 */
public interface OrderService {

    /**
     * 创建订单
     * @param submitOrderBo
     */
    OrderVO createOrder(SubmitOrderBo submitOrderBo);

    /**
     * 更新订单状态
     * @param merchantOrderId 订单id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String merchantOrderId, Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId 订单id
     * @return
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 超时的订单
     */
    void closeOrder();
}
