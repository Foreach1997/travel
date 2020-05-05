package com.bs.travel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bs.travel.entity.Theme;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2019-02-02
 */
@Component
@Mapper
public interface ThemeMapper extends BaseMapper<Theme> {


    public List<Map<String,String>> listorderBythemeName();

}
