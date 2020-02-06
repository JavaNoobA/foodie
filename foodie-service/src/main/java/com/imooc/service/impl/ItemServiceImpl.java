package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.common.enums.CommentLevel;
import com.imooc.common.utils.DesensitizationUtil;
import com.imooc.common.utils.PagedGridResult;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemsVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by eru on 2020/2/5.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemByItemId(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgByItemId(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecByItemId(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParamByItemId(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        int goodCounts = getCommentCounts(itemId, CommentLevel.good.level);
        int normalCounts = getCommentCounts(itemId, CommentLevel.normal.level);
        int badCounts = getCommentCounts(itemId, CommentLevel.bad.level);

        CommentLevelCountsVO countsVO = new CommentLevelCountsVO();
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setBadCounts(badCounts);
        countsVO.setTotalCounts(goodCounts + normalCounts + badCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);

        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> commentVOList = itemsCustomMapper.queryItemComments(map);
        for (ItemCommentVO vo: commentVOList){
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
         }
        return setPageGrid(commentVOList, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItems(String keyword, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsCustomMapper.searchItems(map);
        return setPageGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("catId", catId);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsCustomMapper.searchItemsByThirdCat(map);
        return setPageGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVO> queryItemsBySpecIds(String specIds) {
        String[] strings = specIds.split(",");
        List<String> ids = new ArrayList<>();
        Collections.addAll(ids, strings);

        return itemsCustomMapper.queryItemsBySpecIds(ids);
    }

    public PagedGridResult setPageGrid(List<?> list, int page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int getCommentCounts(String itemId, Integer level){
        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        if (level != null){
            itemsComments.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(itemsComments);
    }
}
