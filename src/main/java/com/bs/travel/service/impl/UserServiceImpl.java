package com.bs.travel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.dao.UserMapper;
import com.bs.travel.entity.User;
import com.bs.travel.service.IUserService;
import com.bs.travel.util.RepResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzh
 * @since 2019-01-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {



    @Override
    public ServerResponse logion(String username, String password) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.eq("username",username).eq("password",password);
        User user=new User();
        if ((user=user.selectOne(ew))!=null){
            user.setPassword("");
            return ServerResponse.createBySuccess(user);
        }else{
           return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
    }

    @Override
    public User adminLogion(String username, String password) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.eq("username",username).eq("password",password).in("role",0,1);
        User user=new User();
        if ((user=user.selectOne(ew))!=null){
            return user;
        }else{
            return null;
        }
    }
}
