package com.imooc.controller;

import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.pojo.bo.ShopcartBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by eru on 2020/2/6.
 */
@Api(value = "购物车接口", tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
@RestController
public class ShopcartController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(@RequestParam String userId,
                               @RequestBody ShopcartBO shopcartBO,
                               HttpServletRequest request,
                               HttpServletResponse response){
        if (StringUtils.isBlank(userId)){
            return IMOOCJSONResult.errorMsg("");
        }

        // TODO 前端用户在登录的情况下, 添加商品到购车，会同时同步到后端redis
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "从购物车删除商品", notes = "从购物车删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public IMOOCJSONResult del(@RequestParam String userId,
                               @RequestParam String itemSpecId,
                               HttpServletRequest request,
                               HttpServletResponse response){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return IMOOCJSONResult.errorMsg("");
        }

        // TODO 前端用户在登录的情况下, 添加商品到购车，会同时同步到后端redis
        return IMOOCJSONResult.ok();
    }
}
