package com.imooc.controller;

import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eru on 2020/2/2.
 */
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){
        if (StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/register")
    public IMOOCJSONResult register(@RequestBody UserBo userBo){
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPwd = userBo.getConfirmPassword();
        if (StringUtils.isBlank(username)
            || StringUtils.isBlank(password)
            || StringUtils.isBlank(confirmPwd)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        if (password.length() < 6){
            return IMOOCJSONResult.errorMsg("密码不能小于6位");
        }

        if (!password.equals(confirmPwd)){
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }

        Users user = userService.createUser(userBo);
        return IMOOCJSONResult.ok(user);
    }
}
