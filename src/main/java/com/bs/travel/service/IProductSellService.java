package com.bs.travel.service;

import com.baomidou.mybatisplus.service.IService;
import com.bs.travel.entity.ProductSell;
import com.bs.travel.vo.PriceCalendar;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzh
 * @since 2019-02-19
 */
public interface IProductSellService extends IService<ProductSell> {

    public List<PriceCalendar> getPriCal(String pid);

}
