package com.bs.travel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bs.travel.dao.AreaMapper;
import com.bs.travel.entity.Area;
import com.bs.travel.service.IAreaService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地区码表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2019-02-03
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Override
    public Integer selectIdByAreaName(String areaName) {
        EntityWrapper<Area> entityWrapper=new EntityWrapper();
        entityWrapper.eq("areaName",areaName);
        Area area=selectOne(entityWrapper);
        if (null==area){
            return null;
        }
        return area.getAreaId();
    }
}
