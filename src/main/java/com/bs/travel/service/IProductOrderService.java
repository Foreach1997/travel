package com.bs.travel.service;

import com.baomidou.mybatisplus.service.IService;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.ProductOrder;
import com.bs.travel.entity.ProductSell;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzh
 * @since 2019-03-04
 */
public interface IProductOrderService extends IService<ProductOrder> {

    public ServerResponse createOrder(ProductOrder productOrder, ProductSell productSell)throws Exception;

    public List myOrderList(String uid);
}
