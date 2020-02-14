package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.common.utils.PagedGridResult;
import com.imooc.mapper.MyOrdersCustomMapper;
import com.imooc.pojo.vo.MyOrdersVO;
import com.imooc.service.center.MyOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eru on 2020/2/14.
 */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

    @Autowired
    private MyOrdersCustomMapper ordersCustomMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderStatus", orderStatus);

        PageHelper.startPage(page, pageSize);

        List<MyOrdersVO> list = ordersCustomMapper.queryMyOrders(map);
        return setPageGrid(list, page);
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
}
