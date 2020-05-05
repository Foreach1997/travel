package com.bs.travel.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.bs.travel.common.ResponseCode;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.Theme;
import com.bs.travel.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzh
 * @since 2019-02-02
 */
@Controller
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private IThemeService themeService;

    //
    @ResponseBody
    @RequestMapping("/{tid}")
    public ServerResponse detail(@PathVariable String tid){
        Theme theme=new Theme();
        theme.setId(tid);
        return ServerResponse.createBySuccess(theme.selectById());
    }


    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse list(@RequestParam(value="current",defaultValue="1") int current,@RequestParam(value="size",defaultValue="10") int size){
        System.out.println(current+"--"+size);
        if (current<0||size<0){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        return ServerResponse.createBySuccess(themeService.selectPage(new Page(current,size))) ;
    }


}

