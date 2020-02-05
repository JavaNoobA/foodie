package com.imooc.controller;

import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.common.utils.PagedGridResult;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
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
@Api(value = "商品", tags = {"商品接口"})
@RestController
@RequestMapping("items")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(@PathVariable @ApiParam(name = "itemId", value = "商品id") String itemId){
        if (StringUtils.isBlank(itemId)){
            return IMOOCJSONResult.errorMsg(null);
        }

        Items items = itemService.queryItemByItemId(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImgByItemId(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecByItemId(itemId);
        ItemsParam itemsParam = itemService.queryItemParamByItemId(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgs);
        itemInfoVO.setItemSpecList(itemsSpecs);
        itemInfoVO.setItemParams(itemsParam);

        return IMOOCJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId) {

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);

        return IMOOCJSONResult.ok(countsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @GetMapping("/comments")
    public IMOOCJSONResult comments(@RequestParam @ApiParam(name = "itemId", value = "商品id", required = true) String itemId,
                                    @RequestParam @ApiParam(name = "level", value = "商品评价等级", required = false) Integer level,
                                    @RequestParam @ApiParam(name = "page", value = "查询第几页", required = false) Integer page,
                                    @RequestParam @ApiParam(name = "pageSize", value = "每页显示的条目数", required = false) Integer pageSize){
        if (StringUtils.isBlank(itemId)){
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null){
            page = 1;
        }

        if (pageSize == null){
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPagedComments(itemId, level, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }
}
