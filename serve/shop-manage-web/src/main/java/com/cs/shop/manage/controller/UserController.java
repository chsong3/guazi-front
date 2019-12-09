package com.cs.shop.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cs.shop.bean.CarOwner;
import com.cs.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author: csong
 * @create: 2019-12-04
 **/
@Controller
@CrossOrigin
public class UserController {
    @Reference
    UserService userService;

    /**
     * 获取车主信息
     * @return
     */
    @RequestMapping("getCarOwnerList")
    @ResponseBody
    public Map getCarOwnerList(){
        Map map = userService.getCarOwnerList();
        return map;
    }
}
