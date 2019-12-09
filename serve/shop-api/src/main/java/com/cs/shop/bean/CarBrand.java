package com.cs.shop.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: csong
 * @create: 2019-12-01
 **/
@Data
public class CarBrand implements Serializable {
     int id;//编号
     String name;//品牌名称
}
