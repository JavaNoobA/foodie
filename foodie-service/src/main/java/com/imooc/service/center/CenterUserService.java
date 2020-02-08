package com.imooc.service.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;

/**
 * Created by eru on 2020/2/8.
 */
public interface CenterUserService {
    /**
     * 根据 id 查询用户
     * @param userId 用户id
     * @return
     */
    Users queryUserById(String userId);

    /**
     * 更新用户信息
     * @param userId 用户id
     * @param centerUserBO 前端传递的用户对象
     * @return
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);
}
