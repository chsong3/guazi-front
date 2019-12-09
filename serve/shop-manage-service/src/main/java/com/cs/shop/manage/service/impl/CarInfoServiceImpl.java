package com.cs.shop.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cs.shop.bean.CarBrand;
import com.cs.shop.bean.CarImage;
import com.cs.shop.bean.CarInfo;
import com.cs.shop.bean.CarSeries;
import com.cs.shop.manage.dataUtils.CommonReturn;
import com.cs.shop.manage.mapper.CarImageMapper;
import com.cs.shop.manage.mapper.CarInfoMapper;
import com.cs.shop.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: csong
 * @create: 2019-12-01
 **/
@Service
public class CarInfoServiceImpl implements CarInfoService {

    @Autowired
    CarInfoMapper carInfoMapper;
    @Autowired
    CarImageMapper carImageMapper;

    @Override
    public Map getBrandList() {
        List<CarBrand> carBrandList = carInfoMapper.selectBrandList();
        Map map=new HashMap();
        map.put("map",carBrandList);
        return map;
    }

    @Override
    public Map getSeriesList(Map param) {
        List<CarSeries> carSeriesList = carInfoMapper.selectSeriesList(param);
        return CommonReturn.ResultMessage2(carSeriesList);
    }

    @Override
    public Map getCarInfoList(Map query) {
        List<CarInfo> carInfoList=carInfoMapper.selectCarInfoList(query);
        return CommonReturn.ResultMessage2(carInfoList);
    }

    @Override
    public Map changeCarInfo(Map carFrom) {
        try {
            //保存基本信息
            carInfoMapper.upDateCarInfo(carFrom);
        }catch (Exception e){
            e.printStackTrace();
            return CommonReturn.ResultMessage3("保存失败！");
        }
        return CommonReturn.ResultMessage3("保存成功！");
    }

    @Override
    public Map saveCarInfo(Map carFrom){

        try{
            //保存基本信息
            carInfoMapper.inserCarInfo(carFrom);
            CarInfo carInfo=carInfoMapper.selectMaxId();
            //保存图片
            List imageList = (List) carFrom.get("imageList");
            if (imageList.size()!=0){
                for (Object object : imageList) {
                    Map map = (Map)object;
                    map.put("carId",carInfo.getId()+1);
                    String imgName = (String) map.get("imgName");
                    String[] imgNamesplit = imgName.split("-");
                    map.put("imgType",imgNamesplit[0]);
                    carImageMapper.insertCarImage(map);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return CommonReturn.ResultMessage3("保存失败！");
        }
        return CommonReturn.ResultMessage3("保存成功");

    }

    @Override
    public Map deleteCarInfo(String id) {
        Map map = new HashMap();
        try {
            carInfoMapper.deleteCarInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("map", "操作失败");
            return map;
        }
        map.put("map", "操作成功");
        return map;
    }
}
