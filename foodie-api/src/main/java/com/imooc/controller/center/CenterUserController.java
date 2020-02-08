package com.imooc.controller.center;

import com.imooc.common.utils.CookieUtils;
import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.common.utils.JsonUtils;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eru on 2020/2/8.
 */
@Api(value = "用户中心-用户相关", tags = {"用户中心-用户相关api"})
@RequestMapping("userInfo")
@RestController
public class CenterUserController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(@ApiParam(name = "userId", value = "用户id")String userId,
                                  @RequestBody @Valid CenterUserBO centerUserBO,
                                  BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response){

        if (result.hasErrors()){
            Map<String, String> map = getErrors(result);
            return IMOOCJSONResult.errorMap(map);
        }

        Users user = centerUserService.updateUserInfo(userId, centerUserBO);

        user = setNullProperty(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话

        return IMOOCJSONResult.ok();
    }

    public Map<String, String> getErrors(BindingResult result){
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error: fieldErrors){
            String field = error.getField();
            String msg = error.getDefaultMessage();
            map.put(field, msg);
        }
        return map;
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
