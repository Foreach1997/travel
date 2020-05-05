package com.bs.travel.service;

import com.baomidou.mybatisplus.service.IService;
import com.bs.travel.common.ServerResponse;
import com.bs.travel.entity.Product;
import com.bs.travel.entity.ProductDesc;
import com.bs.travel.entity.ThemeProduct;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzh
 * @since 2019-02-13
 */
public interface IProductService extends IService<Product> {

    public ServerResponse create(Product product, ProductDesc productDesc, ThemeProduct[] themeProducts) throws Exception;

    public void cascadeDeleteById(String pid);

    public void deleteBatchIds(String[] pids);

    public void update(Product product, ProductDesc productDesc, ThemeProduct[] themeProducts) throws Exception;

    public List<Product> getIndexproduct(int size);

    public List<Product> hotProduct(int size);

}
