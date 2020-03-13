
import request from '@/utils/request'

const requestCarInfo = request.requestCarInfo;
const requestPassport = request.requestPassport;
const requestLogin = request.requestPassport;

export default {

    // 获取品牌列表
    getBrandList() {
        return requestCarInfo({
            url: "getBrandList",
            method: 'post'
        })
    },
    // 获取品牌对应在车系
    getSeriesList(brandId) {
        return requestCarInfo({
            url: "getSeriesList?brandId="+brandId,
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

    //发送验证码
    sendCode(phoneNum){
        return requestPassport({
            url:"sendCode?phoneNum="+phoneNum,
            method:'post'
        })
    },
    login(loginForm){
        return requestPassport({
            url:"fontLogin",
            method:'post',
            data:loginForm
        })
    },
    addFinancialPlan(loginForm){
        return requestPassport({
            url:"addFinancialPlan",
            method:'post',
            data:loginForm
        })
    }
}
Date.prototype.format = function (fmt) { //author: meizz
    let o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 *当前日期
 * @param day 日期偏移量
 */
export function currentDateStr({day = 0, month = 0, year = 0, fmt = 'yyyy-MM-dd'} = {}) {
    return currentDate({day: day, month: month, year: year, fmt: fmt}).format(fmt);
}

/**
 *当前日期
 * @param day 日期偏移量
 */
export function currentDate({day = 0, month = 0, year = 0, fmt = 'yyyy-MM-dd'} = {}) {
    let currentDate = new Date();
    if (year != 0) {
        currentDate.setFullYear(currentDate.getFullYear() + year);
    }
    if (month != 0) {
        currentDate.setMonth(currentDate.getMonth() + month);
    }
    if (day != 0) {
        currentDate.setTime(currentDate.getTime() + (86400000 * day));
    }
    return currentDate;
}
