package com.bs.travel.service;

import com.baomidou.mybatisplus.service.IService;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzh
 * @since 2019-01-24
 */
public interface IUserService extends IService<User> {


    public ServerResponse logion(String username, String password);//登录

    public User adminLogion(String username, String password);//登录
}
