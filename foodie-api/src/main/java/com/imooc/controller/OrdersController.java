package com.imooc.controller;

import com.imooc.common.enums.PayMethod;
import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.pojo.bo.SubmitOrderBo;
import com.imooc.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eru on 2020/2/7.
 */
@Api(value = "创建订单", tags = {"订单相关api"})
@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public IMOOCJSONResult create(@RequestBody SubmitOrderBo submitOrderBo){
        /**
         * 1. 创建订单
         * 2. 创建订单以后, 移除购物车已结算的商品
         * 3. 向支付中心发送当前订单, 用于保存支付中心的订单数据
         */
        if (!submitOrderBo.getPayMethod().equals(PayMethod.WEIXIN.type) ||
                !submitOrderBo.getPayMethod().equals(PayMethod.ALIPAY.type)){
            return IMOOCJSONResult.errorMsg("支付类型不支持");
        }

        orderService.createOrder(submitOrderBo);
        return IMOOCJSONResult.ok();
    }
}
