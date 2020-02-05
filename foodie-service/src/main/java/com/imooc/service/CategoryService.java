package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * Created by eru on 2020/2/3.
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id 查询子分类信息
     * @param rootCatId 一级分类id
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 根据一级分类id 查询该分类下最新的6个商品
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
