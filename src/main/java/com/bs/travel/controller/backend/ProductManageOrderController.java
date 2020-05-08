package com.bs.travel.controller.backend;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bs.travel.common.Const;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.Customization;
import com.bs.travel.entity.ProductOrder;
import com.bs.travel.entity.User;
import com.bs.travel.service.IProductOrderService;
import com.bs.travel.util.RepResult;
import com.bs.travel.vo.CustomizationVO;
import com.bs.travel.vo.OrdersVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzh
 * @since 2019-03-04
 */
@Controller
@RequestMapping("/managerProductOrder")
public class ProductManageOrderController {

    @Autowired
    private IProductOrderService productOrderService;

    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse list(HttpServletRequest request, String keyword, @RequestParam(value="current",defaultValue="1") int current, @RequestParam(value="size",defaultValue="10") int size){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        EntityWrapper entityWrapper=new EntityWrapper();
        if (keyword!=null&!"".equals(keyword)){
            entityWrapper.eq("order_no",keyword).or().eq("username",keyword);
            entityWrapper.orderBy("create_time",false);
        }
        Page page = productOrderService.selectPage(new Page<ProductOrder>(current,size),entityWrapper);
        List<OrdersVO> ordersVOs = new ArrayList<>();
        List<ProductOrder>  pageRecords =page.getRecords();
        Integer role = user.getRole();
        OrdersVO ordersVO = null;
        for (ProductOrder record : pageRecords) {
            ordersVO = new OrdersVO();
            BeanUtils.copyProperties(record,ordersVO);
            if (role == 1){
                ordersVO.setFlag(1);
            }else {
                ordersVO.setFlag(0);
            }
            ordersVOs.add(ordersVO);
        }
        page.setRecords(ordersVOs);
        return ServerResponse.createBySuccess(page);
    }

    @RequestMapping("/detailView/{id}")
    public String detailView(@PathVariable String id, Model model){
        ProductOrder productOrder=productOrderService.selectById(id);
        model.addAttribute("order",productOrder);
        return "backend/order_detail";
    }

    @RequestMapping("/listView")
    public String listView(){
        return "backend/order_list";
    }

    @GetMapping("/delOrder")
    @ResponseBody
    public Object delCus(String id, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        productOrderService.delete(new EntityWrapper<ProductOrder>().eq("id",id));
        return RepResult.repResult(0,"成功",null);
    }

}

