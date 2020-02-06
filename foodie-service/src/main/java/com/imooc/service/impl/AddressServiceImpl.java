package com.imooc.service.impl;

import com.imooc.common.enums.YesOrNo;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by eru on 2020/2/6.
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryUserAddressList(String userId) {
        UserAddress ua = new UserAddress();
        ua.setUserId(userId);
        return userAddressMapper.select(ua);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserAddress(AddressBO addressBO) {
        int isDefault = 0;
        List<UserAddress> userAddresses = this.queryUserAddressList(addressBO.getUserId());
        if (userAddresses.isEmpty() || userAddresses.size() == 0 || userAddresses == null){
            isDefault = 1;
        }
        UserAddress ua = new UserAddress();
        BeanUtils.copyProperties(addressBO, ua);
        String addressId = sid.nextShort();
        ua.setId(addressId);
        ua.setIsDefault(isDefault);
        ua.setCreatedTime(new Date());
        ua.setUpdatedTime(new Date());
        userAddressMapper.insert(ua);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();
        UserAddress ua = new UserAddress();
        BeanUtils.copyProperties(addressBO, ua);
        ua.setId(addressId);
        ua.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(ua);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delUserAddress(String userId, String addressId) {
        UserAddress ua = new UserAddress();
        ua.setId(addressId);
        ua.setUserId(userId);
        userAddressMapper.delete(ua);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void setUserAddressToDefault(String userId, String addressId) {
        UserAddress tmp = new UserAddress();
        tmp.setUserId(userId);
        tmp.setIsDefault(YesOrNo.YES.type);
        List<UserAddress> list = userAddressMapper.select(tmp);
        for (UserAddress ua: list){
            ua.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(ua);
        }

        UserAddress defaultAddress =  new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
    }
}
