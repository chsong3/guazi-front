<template>
    <div>
        <div class="header">
            <div class="headerLeft">
                <div class="header-logo"></div>
                <div class="city">
                    <el-select @change="cityChange" class="test" filterable clearable v-model='carFrom.area' placeholder="默认全国" size="mini">
                        <el-option v-for="item in cityJson"
                                   :key="item.id"
                                   :label="item.districtName"
                                   :value="item.id">
                            <span style="float: left">{{ item.districtName }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">#{{item.first }}</span>
                        </el-option>
                    </el-select>
                </div>
            </div>
            <div class="headerCenter">
                <el-menu active-text-color="#22AC38"
                         text-color="black"
                         :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                    <el-menu-item route="/" index="1" @click="clickIndex">首页</el-menu-item>
                    <el-menu-item route="buyCar" @click="clickBuyCar" index="2">我要买车</el-menu-item>
                    <el-menu-item route="sellCar" @click="clickSellCar" index="3">我要卖车</el-menu-item>
                    <el-menu-item index="5" @click="clickFinance">瓜子金融</el-menu-item>
                </el-menu>
            </div>
            <div class="headerRight">
                <div style="float: left">热线电话 400-069-8627</div>
                <span style="float: left">&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <div v-show="this.$store.state.user==''" style="float: left;cursor:pointer" @click="toLogin"><i
                        class="el-icon-user-solid"></i>登录
                </div>
                <div class="phone" v-show="this.$store.state.user!=''" @mouseover="showdropdown=true">
                  <span>
                    {{this.$store.state.user.phone}}
                  </span>
                </div>
                <div class="uc-app" v-show="showdropdown" @mouseover="showdropdown=true" @mouseout="showdropdown=false">
                    <div class="dropmenu">收藏车辆</div>
                    <div class="dropmenu">降价提醒</div>
                    <div class="dropmenu">浏览记录</div>
                    <div class="dropmenu" @click="loginOut">退出</div>
                </div>
            </div>
            <el-dialog
                    title="瓜子二手车直卖网"
                    :visible.sync="loginDialogVisible"
                    width="30%"
                    center>
                <Login></Login>
            </el-dialog>
        </div>
        <router-view></router-view>
    </div>

</template>
<script>
    import Login from '../page/Login';
    import request from '../../api/index';

    export default {
        data() {
            return {
                activeIndex: '1',
                loginDialogVisible: false,
                showdropdown: false,
                areaOption: {},//车牌所在地
                FristPin: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'],
                cityJson: [],
                carFrom: {
                    area: this.$store.state.cityId
                }
            };
        },
        components: {
            Login
        },
        mounted() {
            this.getCityList();
        },
        methods: {
            //城市列表
            getCityList() {
                //先拿到全部城市的名字
                let cityArr = [];
                request.getCityList().then(response => {
                    let pinyin = require('js-pinyin');
                    pinyin.setOptions({ checkPolyphone: false, charCase: 0 });
                    this.areaOption = response.data.map;
                    for (let i = 0; i < response.data.map.length; i++) {
                        //遍历数组,拿到城市名称
                        let cityName = response.data.map[i].districtName;
                        //取全部城市的首字母
                        let fristName = pinyin.getCamelChars(cityName).substring(0, 1);//这里截取首字母的第一位
                        //给原json添加首字母键值对
                        response.data.map[i].first = fristName;
                        response.data.map[i].pinyin = pinyin.getFullChars(cityName);//将全拼也存入
                        //放入新数组
                        cityArr.push(response.data.map[i]);
                    }

                    let cityJson = [];
                    //根据首字母键值对给原数据按首字母分类
                    for (let i = 0; i < this.FristPin.length; i++) {    //这里的FirstPin是一个写入了所有字母的数组,见data中
                        cityJson=cityJson.concat(cityArr.filter((value =>{
                            return value.first === this.FristPin[i];
                            })))
                    }

                    this.cityJson = cityJson;

                });
            },
            cityChange(cityId){
                this.$store.state.cityId = cityId
                this.$router.push({
                    path: '/blank',
                    query: {
                        path: this.$route.path
                    }
            })

            },
            handleSelect(key, keyPath) {
                console.log(key);
            },
            toLogin() {
                this.loginDialogVisible = true;
            },
            loginOut() {
                this.$store.state.user = '';
            },
            clickIndex() {
                this.$router.push('/index');
            },
            clickBuyCar() {
                this.$router.push('/buyCar');
            },
            clickSellCar() {
                this.$router.push('/sellCar');
            },
            clickFinance() {
                this.$router.push('/finance');
            }
        }
    };
</script>


<style>
    .header {
        width: 100%;
        height: 70px;
        float: left;
        font-size: 14px;
    }

    .headerLeft {
        width: 27%;
        height: 60px;
        float: left;
        border-bottom: 1px #DCDFE6 solid;
    }

    .headerCenter {
        width: 49%;
        height: 70px;
        float: left;
    }

    .headerRight {
        width: 23%;
        height: 60px;
        float: left;
        border-bottom: 1px #DCDFE6 solid;
        line-height: 65px;
    }

    .header-logo {
        width: 50px;
        height: 50px;
        background-repeat: no-repeat;
        background-image: url(../../assets/header-logo.png);
        background-size: 100% 100%;
        float: left;
        margin-left: 40px;
        margin-top: 5px;
    }

    .city {
        line-height: 55px;
        margin-left: 120px;
    }

    .phone {
        width: 60px;
        float: left;
        cursor: pointer;
        text-overflow: ellipsis;
        overflow: hidden;
        position: relative;
    }

    .uc-app {
        position: absolute;
        top: 50px;
        right: 180px;
        width: 105px;
        border: 1px solid #e6e6e6;
        background: #fff;
        z-index: 9999;
    }

    .dropmenu {
        width: 100px;
        height: 20px;
        text-align: center;
        line-height: 20px;
        cursor: pointer;
    }

    .el-select-dropdown{
        width:150px;
    }
</style>
