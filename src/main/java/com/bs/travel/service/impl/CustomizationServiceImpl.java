package com.bs.travel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bs.travel.dao.CustomizationMapper;
import com.bs.travel.entity.Customization;
import com.bs.travel.service.ICustomizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzh
 * @since 2019-01-27
 */
@Service
public class CustomizationServiceImpl extends ServiceImpl<CustomizationMapper, Customization> implements ICustomizationService {

    @Override
    public List<Customization> selectListByUid(String uid) {
       return selectList(new EntityWrapper<Customization>().eq("uid",uid));
    }
}
