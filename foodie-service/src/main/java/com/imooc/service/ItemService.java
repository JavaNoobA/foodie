package com.imooc.service;

import com.imooc.common.utils.PagedGridResult;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ShopcartVO;

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
     * 查询单一的商品规格
     * @param specId 商品规格id
     * @return
     */
    ItemsSpec querySingleItemSpec(String specId);

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
     * @param page 定位到的具体页数
     * @param pageSize 每页显示条目数
     * @return ItemsComments
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 商品搜素
     * @param keyword 关键字
     * @param sort 排序规则
     * @param page 定位到的具体页数
     * @param pageSize 每页显示条目数
     * @return
     */
    PagedGridResult searchItems(String keyword, String sort, Integer page, Integer pageSize);

    /**
     * 商品搜素
     * @param catId 三级分类ID
     * @param sort 排序规则
     * @param page 定位到的具体页数
     * @param pageSize 每页显示条目数
     * @return
     */
    PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据商品规格id查询商品信息
     * @param specIds 商品规格id
     * @return
     */
    List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 查询商品图片url
     * @param itemId 商品id
     * @return 商品图片url
     */
    String queryItemMainImgById(String itemId);

    /**
     * 扣库存
     * @param specId 商品规格 id
     * @param pendingCounts 商品数量
     * @return
     */
    void decreaseItemSpecSock(String specId, int pendingCounts);
}
