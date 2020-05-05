package com.bs.travel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomizationMsg extends Model<CustomizationMsg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;


    @TableField("customized_id")
    private String customizedId;
    @TableField("customized_info")
    private String customizedInfo;
    @TableField("create_time")
    private Date createTime;
    @TableField("customization_id")
    private String customizationId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
