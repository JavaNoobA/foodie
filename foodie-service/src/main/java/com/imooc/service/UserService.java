package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;

/**
 * Created by eru on 2020/2/2.
 */
public interface UserService {
    /**
     * 用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBo 前端封装的用户对象
     * @return 新创建的用户
     */
    Users createUser(UserBo userBo);
}
