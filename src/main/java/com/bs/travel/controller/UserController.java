package com.bs.travel.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bs.travel.common.Const;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.EmailValidate;
import com.bs.travel.entity.User;
import com.bs.travel.service.IEmailValidateService;
import com.bs.travel.service.IUserService;
import com.bs.travel.util.RepResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzh
 * @since 2019-01-24
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;
    @Autowired
    private IEmailValidateService emailValidateService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public ServerResponse<String> register (User user,String registerCode){
        System.out.println(user.toString());
        //对传参进行校验

        //验证邮箱验证码
        EntityWrapper<EmailValidate> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("email",user.getEmail())
                .eq("validate_code",registerCode);
        if(emailValidateService.selectCount(entityWrapper)<=0){
            return ServerResponse.createByErrorMessage("注册码错误");
        }

        //保存
        user.setCreateTime(new Date());
        user.setRole(10);
        if (user.insert()){
            return ServerResponse.createBySuccessMessage("注册成功");
        }else{
            return ServerResponse.createByError();
        }

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
    public ServerResponse<User> login(String username, String password, HttpSession session){

        ServerResponse<User> response= userService.logion(username,password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return  response;
    }

    /**
     * 用户登录
     * @param  username
     * @param  password
     * @param  session
     * @return
     */
    @RequestMapping("/admin/login")
    @ResponseBody
    public ServerResponse<User> adminLogin(String username, String password, HttpSession session){

        ServerResponse<User> response= userService.logion(username,password);

        if (response.isSuccess()){
            if (response.getData().getRole()>=10){
                return ServerResponse.createByErrorMessage("请登录管理员用户");
            }
            session.setAttribute(Const.ADMIN_USER,response.getData());
        }
        return  response;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ServerResponse update(User user){
        return ServerResponse.createByResult(userService.updateById(user));
    }

    @RequestMapping("updateView")
    public String updateView(HttpSession session, Model model){
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        model.addAttribute("user",user);
        return "index/user_info";
    }

    @GetMapping("/getAllQq")
    @ResponseBody
    public Object getAllQq(String areaName){
       return RepResult.repResult(0,"",userService.selectList(new EntityWrapper<User>()
               .eq("role",1)
                .eq("area_name",areaName)));
    }
    /*
    @GetMapping("/check_email/{id}")
    @ResponseBody
    public ServerResponse<String>checkEmail(@PathVariable String id){

    }*/

    /**
     * 登出
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(Const.CURRENT_USER);
        return "index/index";
    }


    /**
     * 登出
     */
//    @GetMapping("/getAllCustom")
//    public String loginOut(String areaName) {
//        userService.selectList(new EntityWrapper<User>()
//                .eq("role",1)
//                .eq("area_name",areaName));
//        return RepResult.repResult(0,"",);
//    }

}

