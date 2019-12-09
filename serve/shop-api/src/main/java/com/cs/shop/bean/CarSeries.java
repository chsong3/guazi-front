package com.cs.shop.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: csong
 * @create: 2019-12-01
 **/
@Data
public class CarSeries implements Serializable {
    int id; //编号,
    String name; //系列名称',
    int brandId; //对应的品牌编号',
}
