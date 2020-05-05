package com.bs.travel.controller.backend;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bs.travel.common.Const;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.User;
import com.bs.travel.service.IUserService;
import com.bs.travel.util.RepResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzh
 * @since 2019-01-24
 */
@Controller
@CrossOrigin
@RequestMapping("/managerUser")
public class UserMangeController {


    @Autowired
    private IUserService userService;


    /**
     * 用户列表
     * @param keyword
     * @param current
     * @param size
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public ServerResponse list(String keyword, @RequestParam(value="current",defaultValue="1") int current, @RequestParam(value="size",defaultValue="10") int size){
        System.out.println(keyword);
        EntityWrapper<User> userEntityWrapper=new EntityWrapper<User>();
        userEntityWrapper.like("username",keyword).or().like("email",keyword).or().like("phone",keyword);
        return ServerResponse.createBySuccess(userService.selectPage(new Page<User>(current,size),userEntityWrapper));
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/count")
    public int getCountPage(){
        return userService.selectCount(null);
    }

    /**
     * 批量删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public  ServerResponse delete(String[] id){
        return ServerResponse.createByResult(userService.deleteBatchIds(Arrays.asList(id)));
    }

    /**
     * 用户登录
     * @param  username
     * @param  password
     * @param  session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpSession session) {
        User response = userService.adminLogion(username, password);
        if (response != null) {
            session.setAttribute(Const.CURRENT_USER, response);
            return RepResult.repResult(0, "成功", response);
        }
        return RepResult.repResult(0, "密码或者账号错误", null);
    }
    /**
     * 更新
     * @param user
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ServerResponse update(User user){
        user.setUpdateTime(new Date());
        return ServerResponse.createByResult(user.updateById());

    }

    @RequestMapping("/updateView/{id}")
    public String updateView(@PathVariable String id, Model model){
        User user=userService.selectById(id);
        model.addAttribute(user);
        return "backend/user_update";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Object addUser(@RequestBody User user){
        if (userService.selectCount(new EntityWrapper<User>().eq("username",user.getUsername()))>0){
            return  RepResult.repResult(1,"已存在",null);
        }
        userService.insert(user);
        return  RepResult.repResult(0,"添加成功",null);
    }
    @RequestMapping("/loginView")
    public String loginView(){
        return "backedn/login";
    }


    /**
     * 登出
     */
    @GetMapping("/loginOut")
    @ResponseBody
    public Object loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(Const.CURRENT_USER);
        return  RepResult.repResult(0,"成功",null);
    }
}

