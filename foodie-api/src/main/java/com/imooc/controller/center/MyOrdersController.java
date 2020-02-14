package com.imooc.controller.center;

import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.common.utils.PagedGridResult;
import com.imooc.controller.BaseController;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.service.ItemService;
import com.imooc.service.center.MyOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eru on 2020/2/5.
 */
@Api(value = "用户中心订单", tags = {"用户中心订单相关接口"})
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController{

    @Autowired
    private MyOrdersService myOrdersService;

    @ApiOperation(value = "查询用户订单", notes = "查询用户订单", httpMethod = "POST")
    @PostMapping("/query")
    public IMOOCJSONResult comments(@RequestParam @ApiParam(name = "userId", value = "用户id", required = true) String userId,
                                    @RequestParam @ApiParam(name = "orderStatus", value = "订单状态", required = false) Integer orderStatus,
                                    @RequestParam @ApiParam(name = "page", value = "查询第几页", required = false) Integer page,
                                    @RequestParam @ApiParam(name = "pageSize", value = "每页显示的条目数", required = false) Integer pageSize){
        if (StringUtils.isBlank(userId)){
            return IMOOCJSONResult.errorMsg("该用户不存在");
        }

        if (page == null){
            page = 1;
        }

        if (pageSize == null){
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }

}
