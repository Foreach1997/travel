package com.bs.travel.service;

import com.baomidou.mybatisplus.service.IService;
import com.bs.travel.entity.Customization;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzh
 * @since 2019-01-27
 */
public interface ICustomizationService extends IService<Customization> {


    public List<Customization> selectListByUid(String uid);

}
