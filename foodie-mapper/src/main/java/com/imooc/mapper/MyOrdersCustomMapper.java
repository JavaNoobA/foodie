package com.imooc.mapper;

import com.imooc.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by eru on 2020/2/14.
 */
public interface MyOrdersCustomMapper {
    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);
}
