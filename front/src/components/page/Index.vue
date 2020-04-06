<template>
    <div>
        <el-main>
            <el-row>
                <el-col>
                    <div class="index">
                        <el-row>
                            <el-col>
                                <div class="search">
                                    <div class="sinput">
                                        <div class="inputcontent">
                                            <el-input
                                                    v-model="search_input"
                                                    placeholder="请输入关键字：如2.4L 宝马">
                                            </el-input>
                                        </div>
                                        <div class="inputbutton" @click="serachToBuyCar">
                                            <el-button type="primary" icon="el-icon-search">搜索</el-button>
                                        </div>
                                    </div>
                                </div>
                            </el-col>
                        </el-row>
                        <!--                列表部分-->
                        <el-row class="cardList">
                            <div><h1>热卖车辆</h1></div>
                            <el-col v-show="carList.length==0">
                                抱歉，没有找到您的爱车，请换个条件试试吧！
                            </el-col>
                            <template v-for="(car,index) in carList">
                                <el-col :span="6">
                                    <el-card>
                                        <div @click="clickOneCar(car)">
                                            <img class="card-image"
                                                 :src="car.defaultImg">
                                            <div>
                                                <h2 class="car-dec">{{car.brandName}} {{car.seriesName}}
                                                    {{car.carDesc}}</h2>
                                                <div class="car-time">
                                                    {{car.buyTime}}
                                                    <span>|</span>
                                                    6.0万公里
                                                </div>
                                                <div class="car-price">
                                                    <p>
                                                        {{car.price}}.00
                                                        <span>万</span>
                                                    </p>
                                                    <em style="color: red;" v-if="car.otherDeploy=='1'">首付{{(car.price*0.3).toFixed(2)}}万</em>
                                                    <em v-else="car.otherDeploy!='1'"
                                                        class="line-through">{{car.price+1.2}}万</em>
                                                </div>
                                            </div>
                                        </div>
                                    </el-card>
                                </el-col>
                            </template>
                            <el-col>
                                <el-row type="flex" justify="center">
                                    <el-col :span="6" class="pagination">
                                        <el-pagination
                                                background
                                                layout="total, prev, pager, next"
                                                :current-page="carFrom.pageIndex"
                                                :page-size="carFrom.pageSize"
                                                :total="carCountNum"
                                                @current-change="handlePageChange"
                                        ></el-pagination>
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col>
                                <!--流程介绍-->
                                <div class="introduce">
                                    <el-row>
                                        <el-col>
                                            <el-row>
                                                <el-col>
                                                    <div class="in_search">
                                                        <el-link class="link_1" icon="el-icon-position" :underline="false" @click="setActiveItem('sell')">
                                                            买车流程</el-link>
                                                        <div class="B"/>
                                                        <el-link class="link_1" icon="el-icon-position" :underline="false" @click="setActiveItem('buy')">卖车流程</el-link>
                                                    </div>
                                                </el-col>
                                            </el-row>
                                            <el-row>
                                                <el-col>
                                                    <div class="in_content">
                                                        <el-carousel :autoplay="false"
                                                                     indicator-position="none"
                                                                     ref="carousel"
                                                                     :height="440+'px'"
                                                                     arrow="never">
                                                            <el-carousel-item name="sell">
                                                                <el-row>
                                                                    <el-col :span="2">
                                                                        <div class="In_search">
                                                                            <div class="in_search_con" style="height: 400px;">
                                                                                <el-steps direction="vertical" :active="sell_con" finish-status="success">
                                                                                    <el-step></el-step>
                                                                                    <el-step></el-step>
                                                                                    <el-step></el-step>
                                                                                    <el-step></el-step>
                                                                                </el-steps>
                                                                            </div>
                                                                            <div class="sell_search">
                                                                                <el-link class="link_2" icon="el-icon-phone-outline" :underline="true" @click="changesell_con(0);setActiveItem_2('sell_1')">预约看车</el-link>
                                                                                <el-link class="link_2" icon="el-icon-s-custom" :underline="false" @click="changesell_con(1);setActiveItem_2('sell_2')">专人带看</el-link>
                                                                                <el-link class="link_2" icon="el-icon-notebook-1" :underline="false" @click="changesell_con(2);setActiveItem_2('sell_3')">签署合同</el-link>
                                                                                <el-link class="link_2" icon="el-icon-edit-outline" :underline="false" @click="changesell_con(3);setActiveItem_2('sell_4')">交易过户</el-link>
                                                                            </div>
                                                                        </div>
                                                                    </el-col>
                                                                    <el-col :span="22">
                                                                        <div class="sell_con">
                                                                            <el-carousel :autoplay="false"
                                                                                         indicator-position="none"
                                                                                         ref="sell_carousel"
                                                                                         :height="440+'px'"
                                                                                         arrow="never">
                                                                                <el-carousel-item name="sell_1">
                                                                                    <div class="buy_1_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>预约看车</h1>
                                                                                            <li>热线电话：400-8686886</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                                <el-carousel-item name="sell_2">
                                                                                    <div class="buy_2_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>专人带看</h1>
                                                                                            <li>瓜子将安排售车顾问陪同您看车</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                                <el-carousel-item name="sell_3">
                                                                                    <div class="buy_3_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>签署合同</h1>
                                                                                            <li>卖方、买方与居间人(瓜子)签署二手车买卖及居间服务合同</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                                <el-carousel-item name="sell_4">
                                                                                    <div class="buy_4_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>交易过户</h1>
                                                                                            <li>协助过户，支付车款，好车开回家</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                            </el-carousel>
                                                                        </div>
                                                                    </el-col>
                                                                </el-row>
                                                            </el-carousel-item>
                                                            <el-carousel-item name="buy">
                                                                <el-row>
                                                                    <el-col :span="2">
                                                                        <div class="In_search">
                                                                            <div class="in_search_con" style="height: 400px;">
                                                                                <el-steps direction="vertical" :active="sell_con" finish-status="success">
                                                                                    <el-step></el-step>
                                                                                    <el-step></el-step>
                                                                                    <el-step></el-step>
                                                                                    <el-step></el-step>
                                                                                </el-steps>
                                                                            </div>
                                                                            <div class="sell_search">
                                                                                <el-link class="link_2" icon="el-icon-phone-outline" :underline="false" @click="changesell_con(0);setActiveItem_3('buy_1')">在线预约</el-link>
                                                                                <el-link class="link_2" icon="el-icon-s-custom" :underline="false" @click="changesell_con(1);setActiveItem_3('buy_2')">全网代售</el-link>
                                                                                <el-link class="link_2" icon="el-icon-notebook-1" :underline="false" @click="changesell_con(2);setActiveItem_3('buy_3')">用户看车</el-link>
                                                                                <el-link class="link_2" icon="el-icon-edit-outline" :underline="false" @click="changesell_con(3);setActiveItem_3('buy_4')">签约过户</el-link>
                                                                            </div>
                                                                        </div>
                                                                    </el-col>
                                                                    <el-col :span="22">
                                                                        <div class="sell_con">
                                                                            <el-carousel :autoplay="false"
                                                                                         indicator-position="none"
                                                                                         ref="buy_carousel"
                                                                                         :height="440+'px'"
                                                                                         arrow="never">
                                                                                <el-carousel-item name="buy_1">
                                                                                    <div class="sell_1_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>在线预约</h1>
                                                                                            <li>到店免费检测服务</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                                <el-carousel-item name="buy_2">
                                                                                    <div class="sell_2_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>全网代售</h1>
                                                                                            <li>海量卖家快速售出</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                                <el-carousel-item name="buy_3">
                                                                                    <div class="sell_3_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>用户看车</h1>
                                                                                            <li>快速匹配意向买家</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                                <el-carousel-item name="buy_4">
                                                                                    <div class="sell_4_img"></div>
                                                                                    <div class="s_introduce_1">
                                                                                        <div class="s_1">
                                                                                            <h1>签约过户</h1>
                                                                                            <li>省心省时，全程陪同</li>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div style="width: 300px;height: 440px;background: white;float: left;opacity:0.5"></div>
                                                                                </el-carousel-item>
                                                                            </el-carousel>
                                                                        </div>
                                                                    </el-col>
                                                                </el-row>
                                                            </el-carousel-item>
                                                        </el-carousel>
                                                    </div>
                                                </el-col>
                                            </el-row>
                                        </el-col>
                                    </el-row>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </el-col>
            </el-row>
        </el-main>

<Myfooter></Myfooter>
    </div>
</template>


<script>
    //import Myheader from '../common/Header.vue';
    import Myfooter from '../common/Footer.vue';
    import quest from '../../api/index';

    export default {
        name: 'index',
        components: { Myfooter },

        data() {
            return {
                show: true,
                carList: [],//车列表
                carFrom: {
                    id: '', //编号
                    brandId: '',//品牌
                    seriesId: '',//系列
                    seriesName: '',
                    userId: '',//车的所属人
                    price: '',//价格
                    beginPrice: '',//价格范围
                    endPrice: '',//价格范围
                    carImageList: [],//图片列表
                    defaultImg: '',//默认图片
                    carDesc: '',//描述
                    buyTime: '',//车辆买入时间
                    createTime: '',//车辆在瓜子上架时间
                    //saleState: '',//车辆销售状态，1表示已经售出，0表示没有售出
                    //isShow: '',//是否显示该记录
                    type: '',//车型
                    mileage: '',//里程数
                    displacement: '',//排量
                    seatNum: '',//座位数量
                    dischargeStandard: '',//排放标准
                    oilType: '',//燃油类型
                    color: '',//颜色
                    area: '',//车辆所在地
                    driveType: '',//驱动类型
                    country: '',//国别
                    otherDeploy: '',//其他亮点配置',
                    pageIndex: 1,
                    pageSize: 10
                },
                imgs: [
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg'
                ],
                img2s: [
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg',
                    'http://106.54.28.115/group1/M00/00/00/rBEACF4AsQGAfunSAADkv6X0STU989.jpg'
                ],
                Vehicle_type_name: 'first',
                url: 'D:\\study\\idea\\idea_worker\\guazi-front\\front\\src\\assets\\1.png',
                search_input: '',
                activeName: '1',
                sell_con: 0,
                s_image_1: 'http://sta.guazistatic.com/static/c2c/web/index/buy-step-order.jpg'
            };
        },
        computed: {
            //计算属性 总条数
            carCountNum() {
                return this.carList.length;
            }
        },
        mounted() {
            this.getCarInfo(this.carFrom);
        },
        methods: {
            getCarInfo(carForm) {
                quest.getCarInfoList(carForm).then(response => {
                    this.carList = [];
                    this.carList = response.data.map;
                    this.clickPayThree()
                });
            },
            clickPayThree() {
                this.listHeadActive = {
                    all: false,
                    payThree: true,
                    selectCar: false
                };
                this.carList = this.carList.filter((value) => {
                    return value.otherDeploy == '1';
                });
            },
            clickOneCar(car) {
                let record = this.$cookies.get('carRecord');
                if (record != null) {
                    var records = record.split('-');
                    let id = records.filter(value => {
                        return value == car.id;
                    });
                    if (id.length == 0) {
                        record = record + '-' + car.id;
                        this.$cookies.remove('carRecord');
                        this.$cookies.set('carRecord', record, -1);
                    }
                } else {
                    this.$cookies.set('carRecord', car.id, -1);
                }
                let routeUrl = this.$router.resolve({
                    path: '/carDetail',
                    query: car
                });
                window.open(routeUrl.href, '_blank');
            },
            serachToBuyCar(){
              this.$router.push({
                  path:'/buyCar',
                  query:{
                      name:'search',
                      value:this.search_input
                  }
              })
            },
            // 分页导航
            handlePageChange(val) {
                this.carFrom.pageIndex = val;
            },
            setActiveItem(item){
                this.sell_con=0;
                this.$refs.carousel.setActiveItem(item);
            },
            setActiveItem_2(item){
                this.$refs.sell_carousel.setActiveItem(item);
            },
            setActiveItem_3(item){
                this.$refs.buy_carousel.setActiveItem(item);
            },
            changesell_con(shu){
                this.sell_con=shu;
            }
        }
    };

</script>


<style scoped>
    .transition-box {
        margin-bottom: 10px;
        width: 200px;
        height: 100px;
        border-radius: 4px;
        background-color: #409EFF;
        text-align: center;
        color: #fff;
        padding: 40px 20px;
        box-sizing: border-box;
        margin-right: 20px;
    }


    /*搜索区域*/
    .search {
        position: relative;
        background: url("../../assets/index-bg.png") #07370d no-repeat;
        background-size: 1920px 100%;
        background-position: 50%;
        height: 340.5px;
        width: 100%;
    }

    /*热卖品牌区域*/
    .brand {
        margin: 0 auto;
        width: 70%;
        height: 750px;
    }

    .b_sign_4 {
        margin-top: 5px;
        margin-left: 40px;
        width: 50px;
        height: 50px;
        background-image: url("../../assets/index/fute.jpg");
        background-size: 100% 100%;
    }

    .b_sign_3 {
        margin-top: 5px;
        margin-left: 40px;
        width: 70px;
        height: 50px;
        background-image: url("../../assets/index/bentian.jpg");
        background-size: 100% 100%;
    }

    .b_sign_2 {
        margin-top: 5px;
        margin-left: 40px;
        width: 100px;
        height: 50px;
        background-image: url("../../assets/index/bike.jpg");
        background-size: 100% 100%;
    }

    .b_sign_1 {
        margin-top: 5px;
        margin-left: 40px;
        width: 50px;
        height: 50px;
        background-image: url("../../assets/index/volkswagen.jpg");
        background-size: 100% 100%;
    }

    /*热卖车型区域*/
    .Vehicle_type {
        margin: 0 auto;
        margin-top: -50px;
        width: 70%;
        height: 1000px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        background: white;
    }

    .A {
        margin-left: 40px;
        margin-top: 60px;
        float: left;
        width: 20%;
        height: 300px;
        border: blueviolet solid 2px;
    }

    .block {
        margin-top: 60px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }

    .fl_img {
        margin-left: 40px;
        margin-top: 20px;
        float: left;
        width: 20%;
        height: 250px;
        border: blueviolet solid 2px;
    }


    /*搜索宽*/
    .sinput {
        margin-left: 420px;
        margin-top: 100px;
        border-radius: 15px;
        width: 622px;
        height: 60px;
        background: white;
        float: left;
    }

    .sinput /deep/ input {
        width: 450px;
        height: 60px;
        margin-left: 20px;
        border: 0px;
        border-radius: 0px;
        font-size: 20px;
        float: left;
    }

    .inputcontent {
        float: left;
    }

    .sinput /deep/ button {
        background: green;
        width: 150px;
        height: 55px;
        border-radius: 10px;
        font-size: 38px;
        margin-top: 2px;
    }

    /*流程介绍*/
    .introduce {
        margin-top: 60px;
        height: 500px;
    }

    .in_search {
        height: 50px;
        width: 600px;
        margin-left: 700px;
        border: chocolate 0px solid;
        font-size: 20px;
    }

    .link_1 {
        margin-top: 8px;
        margin-left: 30px;
        font-size: 26px;
        float: left;
    }

    .B {
        height: 50px;
        margin-left: 30px;
        width: 0px;
        border: black 1px solid;
        float: left;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 18px;
        opacity: 0.75;
        line-height: 300px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    .In_search {
        background: white;
        height: 480px;
    }

    .in_search_con {
        width: 30px;
        margin-top: 20px;
        float: left;
    }

    .sell_search {
        height: 400px;
        width: 11px;
        float: left;
    }

    .link_2 {
        margin-top:10px;
        font-size: 18px;
    }

    .s_introduce_1 {
        background: white;
        width: 450px;
        height: 440px;
        border: black 0px solid;
        float: left;
        opacity: 0.8;
    }

    .buy_1_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/sellorder.jpg);
        background-size: 100% 100%;
    }

    .s_1 {
        margin-top: 160px;
        text-align: center;
        height: 100px;
        list-style: none;
    }

    .buy_2_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/sell_see.jpg);
        background-size: 100% 100%;
    }

    .buy_3_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/contract.jpg);
        background-size: 100% 100%;
    }

    .buy_4_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/deal.jpg);
        background-size: 100% 100%;
    }

    .sell_1_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/sell_yy.png);
        background-size: 100% 100%;
    }

    .sell_2_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/sell_qw.png);
        background-size: 100% 100%;
    }

    .sell_3_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/sell_dai.png);
        background-size: 100% 100%;
    }

    .sell_4_img {
        position: absolute;
        width: 1750px;
        height: 440px;
        background-image: url(../../assets/index/sell_deal.png);
        background-size: 100% 100%;
    }


    .in_tab /deep/ .el-tab-pane {
        font-size: 20px;
    }


    .cardList .el-card {
        background-color: #FFFFFF !important;
        box-shadow: 0 0 0 0 rgba(0, 0, 0, .1) !important;
        border: #FFFFFF solid;
        width: 282px;
        height: 307px;
    }

    .cardList .el-card:hover {
        border: #DCDFE6 solid;
        cursor: pointer;
    }

    .card-image {
        width: 260px;
        height: 192px;
        margin-left: -10px;
    }

    .car-dec {
        font-size: 17px;
        color: #495056;
        font-weight: 400;
        padding: 1px 0 6px 4px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .car-time {
        color: #a5abb2;
        font-size: 14px;
        padding-left: 4px;
        padding-bottom: 2px;
    }

    .car-price p {
        float: left;
        line-height: 30px;
        color: red;
    }

    .car-price em.line-through {
        text-decoration: line-through;
    }

    .car-price em {
        width: 100px;
        float: left;
        margin-left: 10px;
        margin-top: 7px;
        color: #a5abb2;
    }

    .car-price em, .car-price span {
        font-size: 14px;
    }
</style>
