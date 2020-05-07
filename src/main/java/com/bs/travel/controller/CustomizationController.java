package com.bs.travel.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bs.travel.common.Const;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.dao.CustomizationMsgMapper;
import com.bs.travel.entity.Customization;
import com.bs.travel.entity.CustomizationMsg;
import com.bs.travel.entity.User;
import com.bs.travel.service.ICustomizationService;
import com.bs.travel.service.IUserService;
import com.bs.travel.util.RepResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzh
 * @since 2019-01-27
 */
@Controller
@RequestMapping("/customization")
public class CustomizationController {

    @Resource
    private ICustomizationService customizationService;
    @Resource
    private IUserService userService;
    @Resource
    private CustomizationMsgMapper customizationMsgMapper;

    @PostMapping("/save")
    @ResponseBody
    public ServerResponse<String> save(Customization customization, HttpSession session) throws IOException {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("请登录");
        }
//        List<User> users = userService.selectList(new EntityWrapper<User>()
//                .eq("area",customization.getStartAreaname())
//                .eq("role",1)
//                .eq("areaName",customization.getEndAreaname()));
        customization.setUid(user.getId());
        customization.setStatus(0);
        System.out.println(customization.toString());
        return ServerResponse.createByResult(customization.insert());
    }


    @RequestMapping("listView")
    public String listView(HttpSession session, Model model){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return "index/index";
        }
        List<Customization> customizations=customizationService.selectListByUid(user.getId());
        model.addAttribute("customizations",customizations);
        model.addAttribute("user",user);
        return "index/cuslist";

    }

    @RequestMapping("/detailView/{id}")
    public String detailView(@PathVariable String id, Model model){
        Customization customization=customizationService.selectById(id);
       List<CustomizationMsg> customizationMsgs = customizationMsgMapper.selectList(new EntityWrapper<CustomizationMsg>()
                .eq("customization_id",customization.getId()));
        model.addAttribute("cus",customization);
        model.addAttribute("customizationMsgs",customizationMsgs);
        return "index/cus_detail";
    }

    @GetMapping("/levelMsg")
    @ResponseBody
    public Object levelMsg(String levelMsg, String id){
        Customization customization = new Customization();
        customization.setLeaveMsg(levelMsg);
        customization.setCustStatus(1);
        customizationService.update(customization,new EntityWrapper<Customization>().eq("id",id));
        return RepResult.repResult(0,"成功",null);
    }

    @GetMapping("/customizedMsg")
    @ResponseBody
    public Object customizedMsg(HttpSession session,String customizedMsg, String id){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        Customization customization = new Customization();
        //customization.setCustomizedMsg(customizedMsg);
        customization.setCustStatus(2);
        customizationService.update(customization,new EntityWrapper<Customization>().eq("id",id));
        CustomizationMsg customizationMsg = new CustomizationMsg();
        customizationMsg.setCustomizedId(user.getId());
        customizationMsg.setCustomizedInfo(customizedMsg);
        customizationMsg.setCustomizationId(id);
        customizationMsg.setCustomizedName(user.getUsername());
        customizationMsgMapper.insert(customizationMsg);
        return RepResult.repResult(0,"成功",null);
    }


}

