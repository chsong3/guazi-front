package com.cs.shop.service;
import com.cs.shop.bean.CarInfo;

import java.util.Map;

/**
 * @author: csong
 * @create: 2019-12-01
 **/
public interface CarInfoService {
    Map getBrandList();

    Map getSeriesList(Map param);

    Map getCarInfoList(Map query);

    Map saveCarInfo(Map carFrom);

    Map changeCarInfo(Map CarInfo);

    Map deleteCarInfo(String id);
}
