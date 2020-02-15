package com.imooc.service.center;

import com.imooc.common.utils.PagedGridResult;
import com.imooc.pojo.Orders;
import com.imooc.pojo.vo.OrderStatusCountsVO;

import java.util.List;

/**
 * Created by eru on 2020/2/14.
 */
 public interface MyOrdersService {
    /**
     * 查询订单列表
     * @param userId 用户id
     * @param orderStatus 订单状态
     * @param page 页数
     * @param pageSize 每页显示条目
     * @return
     */
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /**
     * 查询我的订单
     * @param userId 用户id
     * @param orderId 订单id
     * @return
     */
    Orders queryMyOrder(String userId, String orderId);

    /**
     * @Description: 订单状态 --> 商家发货
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 更新订单状态 -> 确认收货
     * @param orderId
     * @return
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId
     * @param orderId
     * @return
     */
     boolean deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     * @param userId
     */
     OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获得分页的订单动向
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
     PagedGridResult getOrdersTrend(String userId,
                                          Integer page,
                                          Integer pageSize);
}
