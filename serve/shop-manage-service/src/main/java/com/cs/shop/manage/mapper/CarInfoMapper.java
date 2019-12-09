package com.cs.shop.manage.mapper;
import com.cs.shop.bean.CarBrand;
import com.cs.shop.bean.CarInfo;
import com.cs.shop.bean.CarSeries;

import java.util.List;
import java.util.Map;

/**
 * @author: csong
 * @create: 2019-12-01
 **/
public interface CarInfoMapper{
    List<CarBrand> selectBrandList();

    List<CarSeries> selectSeriesList(Map param);

    List<CarInfo> selectCarInfoList(Map query);

    void upDateCarInfo(Map carInfo);

    void deleteCarInfo(String id);

    void inserCarInfo(Map carFrom);

    void insertCarInfo(Map carFrom);

    CarInfo selectMaxId();
}
