package com.bs.travel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bs.travel.dao.ThemeProductMapper;
import com.bs.travel.entity.ThemeProduct;
import com.bs.travel.service.IThemeProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzh
 * @since 2019-02-13
 */
@Service
public class ThemeProductServiceImpl extends ServiceImpl<ThemeProductMapper, ThemeProduct> implements IThemeProductService {

    @Override
    public List<ThemeProduct> selectByPid(String pid) {

        return selectList(new EntityWrapper<ThemeProduct>().eq("product_id",pid));
    }
}
