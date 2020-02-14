package com.imooc.service.center;

import com.imooc.common.utils.PagedGridResult;

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
}
