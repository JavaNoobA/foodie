package com.imooc.controller;

import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.common.utils.MobileEmailUtils;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eru on 2020/2/6.
 */
@Api(value = "地址相关", tags = {"地址相关api"})
@RequestMapping("address")
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "根据用户id用户收货地址", notes = "根据用户id用户收货地址", httpMethod = "POST")
    @PostMapping("/list")
    public IMOOCJSONResult list(@RequestParam @ApiParam(name = "userId", value = "用户id", required = true) String userId){

        if (StringUtils.isBlank(userId)){
            return IMOOCJSONResult.errorMsg("");
        }

        List<UserAddress> userAddressList = addressService.queryUserAddressList(userId);
        return IMOOCJSONResult.ok(userAddressList);
    }

    @ApiOperation(value = "创建用户收货地址", notes = "创建用户收货地址", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(@RequestBody AddressBO addressBO){

        IMOOCJSONResult result = checkAddress(addressBO);
        if (result.getStatus() != 200){
            return result;
        }

        addressService.addNewUserAddress(addressBO);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "修改用户收货地址", notes = "修改用户收货地址", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(@RequestBody AddressBO addressBO){

        IMOOCJSONResult result = checkAddress(addressBO);
        if (result.getStatus() != 200){
            return result;
        }

        addressService.updateUserAddress(addressBO);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "删除用户收货地址", notes = "修改用户收货地址", httpMethod = "POST")
    @PostMapping("/delete")
    public IMOOCJSONResult delete(@RequestParam @ApiParam(name = "userId", value = "用户id", required = true) String userId,
                                  @RequestParam @ApiParam(name = "addressId", value = "地址id", required = true) String addressId){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return IMOOCJSONResult.errorMsg("用户id或地址id不能为空");
        }

        addressService.delUserAddress(userId, addressId);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "设置用户默认收货地址", notes = "设置用户默认收货地址", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public IMOOCJSONResult setDefalut(@RequestParam @ApiParam(name = "userId", value = "用户id", required = true) String userId,
                                  @RequestParam @ApiParam(name = "addressId", value = "地址id", required = true) String addressId){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return IMOOCJSONResult.errorMsg("用户id或地址id不能为空");
        }

        addressService.setUserAddressToDefault(userId, addressId);
        return IMOOCJSONResult.ok();
    }

    private IMOOCJSONResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return IMOOCJSONResult.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return IMOOCJSONResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return IMOOCJSONResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return IMOOCJSONResult.errorMsg("收货人手机号长度不正确");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return IMOOCJSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return IMOOCJSONResult.errorMsg("收货地址信息不能为空");
        }

        return IMOOCJSONResult.ok();
    }
}
