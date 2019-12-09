package com.cs.shop.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: csong
 * @create: 2019-12-01
 **/
@Data
public class CarInfo implements Serializable {
    int id;                          //编号
    String brandName;               //品牌
    String seriesName;              //车系
    String userName;                  //车主
    double price;                  // 价格
    String defaultImg;            //默认图片
    String carDesc;              //描述
    String buyTime;              //车辆买入时间
    String createTime;          //车辆在瓜子上架时间
    int saleState;              //车辆销售状态，1表示已经售出，0表示没有售出
}
