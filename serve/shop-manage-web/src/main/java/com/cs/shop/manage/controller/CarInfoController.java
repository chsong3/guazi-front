package com.cs.shop.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cs.shop.bean.CarInfo;
import com.cs.shop.manage.util.PmsUploadUtil;
import com.cs.shop.service.CarInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: csong
 * @create: 2019-12-01
 **/
@Controller
@CrossOrigin
public class CarInfoController {

    @Reference
    CarInfoService carInfoService;

    /**
     * 品牌
     *
     * @return
     */
    @RequestMapping("getBrandList")
    @ResponseBody
    public Map getBrandList() {
        Map map = carInfoService.getBrandList();
        return map;
    }

    /**
     * 车系
     *
     * @param brandFlags
     * @return
     */
    @RequestMapping("getSeriesList")
    @ResponseBody
    public Map getSeriesList(String brandFlags) {
        Map param = new HashMap();
        param.put("brandFlags", brandFlags);
        Map map = carInfoService.getSeriesList(param);
        return map;
    }

    /**
     * 获取车的信息
     *
     * @param query
     * @return
     */
    @RequestMapping("getCarInfoList")
    @ResponseBody
    public Map getCarInfoList(@RequestBody Map query) {
        Map map = carInfoService.getCarInfoList(query);
        return map;
    }

    /**
     * 保存
     * @param carFrom
     * @return
     */
    @RequestMapping("saveCarInfo")
    @ResponseBody
    public Map saveCarInfo(@RequestBody Map carFrom){
        return carInfoService.saveCarInfo(carFrom);
    }

    /**
     * 更新
     * @param CarInfo
     * @return
     */
    @RequestMapping("updateCarInfo")
    @ResponseBody
    public Map updateCarInfo(@RequestBody Map CarInfo) {
        Map map = carInfoService.changeCarInfo(CarInfo);
        return map;
    }

    /**
     * 删除二手车信息
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteCarInfo")
    @ResponseBody
    public Map deleteCarInfo(String id) {
        Map map = carInfoService.deleteCarInfo(id);
        return map;
    }

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile){
        //将图片或者文件上传到分布式的文件存储系统
        //将图片的存储路径返回给页面
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        System.out.println(imgUrl);
        return imgUrl;
    }

}
