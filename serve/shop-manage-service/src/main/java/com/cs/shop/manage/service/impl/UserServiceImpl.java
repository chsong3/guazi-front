package com.cs.shop.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cs.shop.bean.CarOwner;
import com.cs.shop.manage.dataUtils.CommonReturn;
import com.cs.shop.manage.mapper.UserServiceMapper;
import com.cs.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author: csong
 * @create: 2019-12-04
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserServiceMapper userServiceMapper;

    @Override
    public Map getCarOwnerList() {
        List<CarOwner> carOwnerList = userServiceMapper.selectCarOwnerList();
        return CommonReturn.ResultMessage2(carOwnerList);
    }
}
