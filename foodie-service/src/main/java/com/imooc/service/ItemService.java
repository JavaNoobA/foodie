package com.imooc.service;

import com.imooc.common.utils.PagedGridResult;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;

import java.util.List;

/**
 * 商品接口
 * Created by eru on 2020/2/5.
 */
public interface ItemService {

    /**
     * 根据商品id查询商品信息
     * @param itemId 商品id
     * @return Items
     */
    Items queryItemByItemId(String itemId);

    /**
     * 根据商品id查询商品图片
     * @param itemId 商品id
     * @return ItemsImg
     */
    List<ItemsImg> queryItemImgByItemId(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId 商品id
     * @return ItemsSpec
     */
    List<ItemsSpec> queryItemSpecByItemId(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId 商品id
     * @return ItemsParam
     */
    ItemsParam queryItemParamByItemId(String itemId);

    /**
     * 查询商品评价等级数
     * @param itemId 商品id
     * @return CommentLevelCountsVO
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 查询商品评价信息
     * @param itemId 商品id
     * @param level 商品评价等级
     * @return ItemsComments
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);
}
