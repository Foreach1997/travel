package com.bs.travel.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bs.travel.common.Const;
import com.bs.travel.common.ResponseCode;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.Customization;
import com.bs.travel.entity.User;
import com.bs.travel.service.ICustomizationService;
import com.bs.travel.util.RepResult;
import com.bs.travel.vo.CustomizationVO;
import com.bs.travel.vo.CustomizedInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/1/27.
 */
@Controller
@CrossOrigin
@RequestMapping("/managerCustomization")
public class CustomizedManageController {

    @Autowired
    private ICustomizationService customizationService;

    /**
     *查看详情
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ServerResponse<Customization> detail(@PathVariable String id){
        Customization customization=new Customization();
        customization.setId(id);
        return ServerResponse.createBySuccess(customization.selectById()) ;
    }

    @RequestMapping("/detailView/{id}")
    public String detailView(@PathVariable String id, Model model){
        Customization customization=new Customization();
        customization.setId(id);
        model.addAttribute("customization",customization.selectById());
        return "backend/customization";
    }

    /**
     * 设置状态
     */
    @RequestMapping("/update/{id}")
    @ResponseBody
    public ServerResponse updateStauts(@PathVariable String id, String status){
        Customization customization=new Customization();
        customization.setStatus(Integer.parseInt(status));
        customization.setId(id);
        return ServerResponse.createByResult(customization.updateById());
    }

    /*
    * 分页查看
    *
    * */

    @RequestMapping("list")
    @ResponseBody
    public ServerResponse list(HttpServletRequest request,String status, @RequestParam(value="current",defaultValue="1") int current, @RequestParam(value="size",defaultValue="10") int size){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (current<=0||size<=0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        EntityWrapper entityWrapper=null;
        if (status!=null&&!"".equals(status)){
            entityWrapper=new EntityWrapper();
            entityWrapper.eq("status",Integer.parseInt(status));
        }
        Page customizations = customizationService.selectPage(new Page(current,size),entityWrapper);
        List<CustomizationVO> customizationVOS = new ArrayList<>();
        List<Customization>  customizationList =customizations.getRecords();
        Integer role = user.getRole();
        CustomizationVO customizationVO = null;
        for (Customization record : customizationList) {
            customizationVO = new CustomizationVO();
            BeanUtils.copyProperties(record,customizationVO);
            if (role == 1){
                customizationVO.setFlag(1);
            }else {
                customizationVO.setFlag(0);
            }
            customizationVOS.add(customizationVO);
        }
        customizations.setRecords(customizationVOS);
        return ServerResponse.createBySuccess(customizations) ;
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public Object updateInfo(@RequestBody CustomizedInfoVO customizedInfoVO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        Customization customization = new Customization();
        customization.setCustomizedInfo(customizedInfoVO.getCustomizedInfo());
        customization.setCustomizedId(user.getId());
        customizationService.update(customization,new EntityWrapper<Customization>().eq("id",customizedInfoVO.getId()));
        return RepResult.repResult(0,"成功",null);
    }

    @GetMapping("/delCus")
    @ResponseBody
    public Object delCus(String id, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        customizationService.delete(new EntityWrapper<Customization>().eq("id",id));
        return RepResult.repResult(0,"成功",null);
    }

}
