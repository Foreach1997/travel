package com.bs.travel.vo;

import com.bs.travel.entity.ProductOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrdersVO extends ProductOrder implements Serializable {


    private Integer flag;



}
