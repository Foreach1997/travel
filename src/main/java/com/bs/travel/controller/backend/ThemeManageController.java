package com.bs.travel.controller.backend;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.Theme;
import com.bs.travel.entity.ThemeProduct;
import com.bs.travel.service.IThemeProductService;
import com.bs.travel.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzh
 * @since 2019-02-02
 */
@Controller
@CrossOrigin
@RequestMapping("/managerTheme")
public class ThemeManageController {

    @Autowired
    private IThemeService themeService;

    @Autowired
    private IThemeProductService themeProductService;

    @ResponseBody
    @RequestMapping("/save")
    public ServerResponse save(Theme theme){
        return  ServerResponse.createByResult(theme.insert());
    }

    @ResponseBody
    @RequestMapping("/update/{tId}")
    public ServerResponse update(@PathVariable String tId, Theme theme){
        ThemeProduct themeProduct = new ThemeProduct();
        themeProduct.setThemeName(theme.getThemeName());
        themeProductService.update(themeProduct,new EntityWrapper<ThemeProduct>().eq("theme_id",tId));
        theme.setId(tId);
        return ServerResponse.createByResult(theme.updateById());
    }



    @ResponseBody
    @RequestMapping("/delete/{tId}")
    public ServerResponse delete(@PathVariable String tId){
        Theme theme=new Theme();
        theme.setId(tId);
        return ServerResponse.createByResult(theme.deleteById());
    }

    @RequestMapping("/detailView/{id}")
    public String themeView(@PathVariable String id, Model model){
        Theme theme=new Theme();
        theme.setId(id);
        model.addAttribute("theme",theme.selectById());
        return "backend/theme_add";
    }

    @RequestMapping("/addView")
    public String addView(){
        return "backend/theme_add";
    }



    @RequestMapping("/test")
    @ResponseBody
    public List test(){
        return themeService.listorderBythemeName();
    }


}

