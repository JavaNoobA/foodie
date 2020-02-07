package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

/**
 * Created by eru on 2020/2/6.
 */
public interface AddressService {

    /**
     * 查询用户地址列表
     * @param userId 用户id
     * @return
     */
    List<UserAddress> queryUserAddressList(String userId);

    /**
     * 添加用户收货地址
     * @param addressBO
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * 修改用户收货地址
     * @param addressBO
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 删除用户收货地址
     * @param userId 用户Id
     * @param addressId 地址id
     */
    void delUserAddress(String userId, String addressId);

    /**
     * 设置用户默认收货地址
     * @param userId 用户Id
     * @param addressId 地址id
     */
    void setUserAddressToDefault(String userId, String addressId);

    /**
     * 查询单一用户收货地址
     * @param userId 用户Id
     * @param addressId 地址id
     * @return
     */
    UserAddress queryUserAddress(String userId, String addressId);
}
