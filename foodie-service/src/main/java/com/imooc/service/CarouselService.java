package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * Created by eru on 2020/2/3.
 */
public interface CarouselService {

    /**
     * 查询首页轮播图
     * @param isShow 是否展示
     * @return
     */
    List<Carousel> queryAll(Integer isShow);
}
