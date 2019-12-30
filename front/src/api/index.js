
import request from '@/utils/request'

const requestCarInfo = request.requestCarInfo;

export default {

    // 获取品牌列表
    getBrandList() {
        return requestCarInfo({
            url: "getBrandList",
            method: 'post'
        })
    },
    // 获取品牌对应在车系
    getSeriesList(brandFlags) {
        return requestCarInfo({
            url: "getSeriesList?brandFlags="+brandFlags,
            method: 'post'
        })
    },

    // 获取二手车信息
    getCarInfoList(query){
        return requestCarInfo({
            url:"getCarInfoList",
            method:'post',
            data:query
        })
    },

}
