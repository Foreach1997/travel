package com.bs.travel.service;

import com.baomidou.mybatisplus.service.IService;
import com.bs.travel.entity.Area;

/**
 * <p>
 * 地区码表 服务类
 * </p>
 *
 * @author zzh
 * @since 2019-02-03
 */
public interface IAreaService extends IService<Area> {

    public Integer selectIdByAreaName(String areaName);

}
