package com.cs.shop.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: csong
 * @create: 2019-12-05
 **/
@Data
public class CarImage implements Serializable {
    int id;//编号
    int carid;//商品id
    String imgName;//图片名称imgName
    String imgUrl;//图片路径imgUrl
    String imgType;//图片类型
    String isDefault;//是否默认
}
