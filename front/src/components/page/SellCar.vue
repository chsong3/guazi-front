<template>
    <div>
        <myheader/>
        <el-row>
            <el-col>
                <!--估价卖车-->
                <el-row  style="height:600px;background: blue">
                    <div class="s_search" style="border: crimson solid 2px">
                        <!--标题-->
                        <div class="Title" style="margin-top:70px;margin-left:500px;width: 1000px">
                            <h1 style="color: white;font-size: 60px">瓜子二手车&nbsp;&nbsp;&nbsp;&nbsp;二手车行业领军者</h1>
                        </div>
                        <!--输入框-->
                        <div class="sell_input" style="margin-left: 400px;margin-top:50px;width:1000px;">
                            <el-form ref="form" :model="phone_form" label-width="80px">
                                <el-form-item>
                                        <el-input class="phone_input" v-model="phone_form.number" placeholder="请输入手机号"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <!--卖车对话表单-->
                                    <div class="button_sell_input">
                                        <el-button type="success" style="margin-left: 100px;float: left;" @click="sell_if_car">高价卖车</el-button>
                                    </div>

                                    <div class="form_gu">
                                        <el-dialog title="高价卖车" :visible.sync="dialogSellVisible" width="500px" >
                                            <el-form :model="sellcar_form">
                                                <el-form-item label="车源地" label-width="80px">
                                                    <el-input class="" v-model="sellcar_form.name" autocomplete="off"></el-input>
                                                </el-form-item>
                                                <el-form-item label="品牌" label-width="80px">
                                                    <el-input v-model="sellcar_form.name" autocomplete="off"></el-input>
                                                </el-form-item>
                                                <el-form-item label="型号" label-width="80px">
                                                    <el-input v-model="sellcar_form.name" autocomplete="off"></el-input>
                                                </el-form-item>
                                                <el-form-item label="提车时间" label-width="80px">
                                                    <el-date-picker type="date" placeholder="选择日期" v-model="sellcar_form.date" style="width: 100%;"></el-date-picker>
                                                </el-form-item>
                                                <el-form-item label="行程公里" label-width="80px">
                                                    <el-input v-model="sellcar_form.name" autocomplete="off" placeholder="万公里"></el-input>
                                                </el-form-item>
                                            </el-form>
                                            <div slot="footer" class="dialog-footer">
                                                <el-button type="primary" @click="dialogSellVisible = false">确定</el-button>
                                            </div>
                                        </el-dialog>
                                    </div>
                                    <!--估车表单-->
                                    <div class="button_sell_input">
                                        <el-button type="success" plain @click="dialogFormVisible=true" style="margin-left: 350px">快速估车</el-button>
                                    </div>

                                    <div class="form_gu">
                                        <el-dialog title="快速估车" :visible.sync="dialogFormVisible" width="500px" >
                                            <el-form :model="estimate_form">
                                                <el-form-item label="车源地" label-width="80px">
                                                    <el-input class="" v-model="estimate_form.name" autocomplete="off"></el-input>
                                                </el-form-item>
                                                <el-form-item label="品牌" label-width="80px">
                                                    <el-input v-model="estimate_form.name" autocomplete="off"></el-input>
                                                </el-form-item>
                                                <el-form-item label="型号" label-width="80px">
                                                    <el-input v-model="estimate_form.name" autocomplete="off"></el-input>
                                                </el-form-item>
                                                <el-form-item label="提车时间" label-width="80px">
                                                    <el-date-picker type="date" placeholder="选择日期" v-model="estimate_form.date" style="width: 100%;"></el-date-picker>
                                                </el-form-item>
                                                <el-form-item label="行程公里" label-width="80px">
                                                    <el-input v-model="estimate_form.name" autocomplete="off" placeholder="万公里"></el-input>
                                                </el-form-item>
                                            </el-form>
                                            <div slot="footer" class="dialog-footer">
                                                <el-button type="primary" @click="dialogFormVisible = false">开始估价</el-button>
                                            </div>
                                        </el-dialog>
                                    </div>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                </el-row>
                <!--卖车流程-->
                <el-row>
                    <el-col>
                        <div class="sell_process">
                            <!--标题-->
                            <div style="margin-left: 530px;margin-top: 30px;width: 400px;">
                                <h1 style="font-size: 40px;color: green;">瓜子保卖服务</h1>
                            </div>
                            <!--简要介绍-->
                            <div style="width: 100%;height: 370px;margin-top: 45px">
                                <el-row>
                                    <el-col :span="8">
                                        <div class="process_1" style="width: 300px;">
                                            <i class="el-icon-s-finance"></i>
                                            <div style="margin-left:15px;width: 100px;">
                                                <h2>当天拿钱</h2>
                                                <li>最低可的50%过户的余款</li>
                                            </div>
                                        </div>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="process_1" style="width: 300px;">
                                            <i class="el-icon-money"></i>
                                            <div style="margin-left:15px;width: 100px;">
                                                <h2>快速高价</h2>
                                                <li>面向全国买家
                                                    锁定最优价格</li>
                                            </div>
                                        </div>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="process_1" style="width: 300px;">
                                            <i class="el-icon-truck"></i>
                                            <div style="margin-left:15px;width: 100px;">
                                                <h2>托管省心</h2>
                                                <li>专人帮你卖车
                                                    省时省力</li>
                                            </div>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </div>
                    </el-col>
                </el-row>
                <!--最近成交-->
                <el-row style="height:900px;background: white">
                    <div class="deal_Car" style="margin-top: 400px;margin-left:350px;width: 1200px;height: 400px;">
                        <h1 style="margin-left: 550px;">最近成交</h1>
                        <!--标题底部横线-->
                        <div style="margin-left: 600px;width: 30px;border-bottom: green solid 3px"/>
                        <!--图片浏览框-->
                        <div style="margin: 30px auto;width: 1190px;height: 320px;">
                            <el-carousel  :interval='3000'
                                          arrow="never"
                                          indicator-position="none">
                                <el-carousel-item>
                                    <ul class="c_img">
                                        <img id="img_1" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                    <ul class="c_img">
                                        <img id="img_2" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                    <ul class="c_img">
                                        <img id="img_3" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                </el-carousel-item>
                                <el-carousel-item>
                                    <ul class="c_img">
                                        <img id="img_4" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                    <ul class="c_img">
                                        <img id="img_5" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                    <ul class="c_img">
                                        <img id="img_6" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                </el-carousel-item>
                                <el-carousel-item>
                                    <ul class="c_img">
                                        <img id="img_7" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                    <ul class="c_img">
                                        <img id="img_8" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                    <ul class="c_img">
                                        <img id="img_9" style="width: 300px;height: 210px;">
                                        <div style="width: 300px;height: 90px;">
                                            <li style="list-style: none;margin-top: 5px">宝马X1 2013款 sDrive18i 时尚型</li>
                                            <li style="margin-top: 20px;float: left;list-style: none;">成交价：</li>
                                            <h2 style="margin-top: 15px;color: red">10.6万</h2>
                                        </div>
                                    </ul>
                                </el-carousel-item>
                            </el-carousel>
                        </div>
                    </div>
                </el-row>
                <!--瓜子问答-->
                <el-row style="height:600px;background: gainsboro">
                    <div class="car_question" style="margin-left:350px;width: 1200px;height: 400px;">
                        <h1 style="margin-left: 550px;">瓜子问答</h1>
                        <!--标题底部横线-->
                        <div style="margin-left: 600px;width: 30px;border-bottom: green solid 3px"/>
                        <!--问题部分-->
                        <div style="margin-left: -20px;margin-top:130px;width: 1300px;height: 300px">
                            <el-col :span="8">
                                <h2 style="">Q2：卖车需要准备什么？</h2>
                                <li style="list-style: none">1、在本页填写卖车信息或拨打400-023-1529联系我们</li>
                                <li style="list-style: none">2、由检测师进行车辆信息收集，并经过瓜子评估，给出瓜子价参考，由车主确定发布报价。</li>
                                <li style="list-style: none">3、达成交易合同，支付车款并过户。</li>
                            </el-col>
                            <el-col :span="8" style="margin-left: 400px">
                                <h2>Q1: 卖车流程是怎么样的？</h2>
                                <li style="list-style: none">1,身份证；2、环保标；3、购置税本；4、行驶证;</li>
                                <li style="list-style: none">5、检字标 6、购置税发票；7、车辆登记证；8、交强险标；</li>
                                <li style="list-style: none">9、购车发票/最近一次过户发票；10、交强险单。</li>
                            </el-col>
                        </div>
                    </div>
                </el-row>
                <myfooter/>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import Myheader from '../common/Header.vue';
    import Myfooter from '../common/Footer.vue';
export default {
    name: "SellCar",
    components: {Myheader, Myfooter},

    data() {
        return {
            phone_form:{
                number:'',
            },
            estimate_form: {
                name: '',
                date:'',
            },
            sellcar_form:{
                name: '',
                date:'',
            },
            dialogFormVisible: false,
            dialogSellVisible: false,
        }
    },
    methods: {
        sell_if_car(){
            if(this.phone_form.number) {
                this.dialogSellVisible=true
            }else {
                this.$message('请填写电话');
            }
        }
        }
    }
</script>


<style scoped>
    /*卖车，估价*/
    .s_search{
        height: 600px;
        background: url("../../assets/index/index_1.jpg");
        background-size: 100% 100%;
    }
    .form_gu/deep/ input{
        width: 300px;
    }
    .sell_brand/deep/ input{
        width: 100px;
    }
    /*卖车流程简介*/
    .sell_process{
        position: absolute;
        margin-left: 280px;
        margin-top: -170px;
        width: 70%;
        height: 500px;
        background: white;
        z-index: 999;
    }
    .phone_input/deep/ input{
        width: 900px;
        height: 60px;
        font-size: 20px;
    }
    .button_sell_input/deep/ button{
        width: 150px;
        height: 60px;
        font-size: 25px;
    }
    .el-icon-s-finance{
        font-size: 130px;
        color: green;
    }
    .el-icon-money{
        font-size: 130px;
        color: green;
    }
    .el-icon-truck{
        font-size: 130px;
        color: green;
    }
    .process_1{
        margin-left: 130px;
        list-style: none;
        margin-top: 70px;
    }

    .process_1 li{
        font-size: 16px;
    }

    /*图片浏览*/
    .c_img{
        width:300px ;
        height: 295px;
        float: left;
        margin-left: 65px;
    }
    #img_1{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_2{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_3{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_4{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_5{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_6{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_7{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_8{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
    #img_9{
        background-image: url("../../assets/sellcar/sc_img1.png");
        background-size: 100% 100%;
    }
</style>
