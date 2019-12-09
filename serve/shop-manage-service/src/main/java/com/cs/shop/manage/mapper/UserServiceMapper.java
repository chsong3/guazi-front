package com.cs.shop.manage.mapper;

import com.cs.shop.bean.CarOwner;

import java.util.List;

/**
 * @author: csong
 * @create: 2019-12-04
 **/
public interface UserServiceMapper {
    List<CarOwner> selectCarOwnerList();
}
