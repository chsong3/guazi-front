<template>
    <div>
        <div class="person-main">
            <!-- 左菜单 s -->
            <div class="person-left">
                <div class="block avatar">
                    <el-avatar :size="80"
                               src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                    <div>&nbsp;个人中心</div>
                </div>
                <div><span style="font-size: 12px;color: #8492a6">&nbsp;&nbsp;&nbsp;手机号码:{{user.phone}}</span></div>

                <div class="person-menu">
                    <div class="person-menu-item" :class="{greenBgActive:activeClass.collectCar}" @click="catCollect">收藏车辆</div>
                    <!--                    <div class="person-menu-item">降价提醒</div>-->
                    <div class="person-menu-item" :class="{greenBgActive:activeClass.carRecord}" @click="catRecord">浏览记录</div>
        </div>
            </div>
            <!-- 左菜单 e -->

            <!-- 右列表 s -->
            <div class="person-right">
                <el-row class="cardList">

                    <template v-for="(car,index) in carList">
                        <el-col :span="8" style="cursor: pointer;">
                            <el-card>
                                <div>
                                    <img class="card-image"
                                         :src="car.defaultImg">
                                    <div>
                                        <h2 class="car-dec">{{car.brandName}} {{car.seriesName}} {{car.carDesc}}</h2>
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
            </div>
        </div>
    </div>
</template>

<script>
    import request from '../../api/index';

    export default {
        data() {
            return {
                carList: [],//车列表
                carFrom: {
                    id: '', //编号
                    brandId: '',//品牌
                    seriesId: '',//系列
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
                user: '',
                activeClass: {
                    collectCar: false,
                    carRecord: false
                }
            };
        },
        computed: {
            //计算属性 总条数
            carCountNum() {
                return this.carList.length;
            }
        },
        mounted() {
            this.user = this.$cookies.get('user');
            let routeParam=this.$route.query.name;
            if (routeParam == 'collect'){
                this.catCollect();
            }else if (routeParam == 'record'){
                this.catRecord();
            }
        },
        methods: {
            // 分页导航
            handlePageChange(val) {
                this.carFrom.pageIndex = val;
            },
            //查看收藏车辆
            catCollect() {
                this.activeClass = {
                    collectCar: true,
                    carRecord: false
                }
                this.carList = []
                request.getCollectCarList(this.user.id).then(response => {
                    this.carList = response.data.map;
                });
            },
            //查看浏览记录
            catRecord() {
                this.activeClass = {
                    collectCar: false,
                    carRecord: true
                }
                let record = this.$cookies.get('carRecord');
                console.log(record)
                this.getCarInfoListByIds(record);
            },
            //根据多个id查询
            getCarInfoListByIds(record) {
                request.getCarInfoListByIds(record).then(response => {
                    this.carList = response.data.map;
                    console.log(this.carList)
                });
            }
        }
    };
</script>

<style>
    .person-main {
        width: 1100px;
        margin: 33px auto;
        overflow: hidden;
    }

    .person-left {
        float: left;
        width: 148px;
        margin-right: 20px;
    }

    .person-left, .person-right {
        background-color: #fff;
        border: 1px solid #dcdcdc;
    }

    .person-right {
        float: left;
        width: 928px;
    }

    .avatar {
        margin-left: 30px;
        margin-top: 20px;
    }

    .person-menu {
        margin-top: 20px;
    }

    .person-menu-item {
        width: 100%;
        height: 40px;
        text-align: center;
        line-height: 40px;
        font-size: 18px;
        margin-top: 10px;

    }

    .greenBgActive {
        background: #00a854;
    }

    .person-menu-item:hover {
        background: #00a854;
        cursor: pointer;
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
