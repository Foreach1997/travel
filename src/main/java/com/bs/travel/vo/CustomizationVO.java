package com.bs.travel.vo;

import com.bs.travel.entity.Customization;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomizationVO extends Customization implements Serializable {


    private Integer flag;



}
