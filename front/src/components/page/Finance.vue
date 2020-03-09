<template>
    <div>
        <Myheader/>
        <el-row>
            <el-col>
                <!--头部导航-->
                <el-row style="height: 400px;background: red">
                    <el-col>
                        <!--申请框-->
                        <el-row style="height: 300px;background: ghostwhite">
                            <el-col>
                                <!--验证码申请-->
                                <div class="shengqing_1">
                                    <h3 style="float: left">定制方案</h3>
                                    <li style="list-style: none;margin-left: 100px;margin-top: 2px">资质越好，方案越划算</li>
                                    <el-form :model="numberValidateForm" ref="numberValidateForm" label-width="100px" class="demo-ruleForm">
                                        <el-form-item prop="age">
                                            <el-input  type="age" placeholder="请输入手机号" v-model.number="numberValidateForm.phone" autocomplete="off"></el-input>
                                            <el-link>获取验证码</el-link>
                                            <el-input type="age" placeholder="验证码"  v-model.number="numberValidateForm.phone" autocomplete="off"></el-input>
                                        </el-form-item>
                                        <el-form-item>
                                            <el-button type="primary" @click="submitForm('numberValidateForm')">立即申请</el-button>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </el-col>
                        </el-row>
                        <!--申请流程-->
                        <el-row style="height: 100px;background: darkgray">
                            <el-col>
                                <div class="shengqing_2">
                                    <div style="width: 1200px;margin: 0 auto;">
                                        <ul>
                                            <h1 class="el-icon-date">选择多</h1>
                                            <li>几十款金融选择方案</li>
                                        </ul>
                                        <ul>
                                            <h1 class="el-icon-s-promotion">审核快</h1>
                                            <li>最快30分钟完成审批</li>
                                        </ul>
                                        <ul>
                                            <h1 class="el-icon-edit">通过率高</h1>
                                            <li>定制方案 高审批通过率</li>
                                        </ul>
                                        <ul>
                                            <h1 class="el-icon-user-solid">专属服务</h1>
                                            <li>金融专员全程服务</li>
                                        </ul>
                                    </div>
                                </div>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
                <!--内容部分-->
                <el-row style="height: 1300px;background: blue">
                    <el-col>
                        <el-row style="height: 600px;background: white">
                            <h2 style="margin-left: 350px;margin-top: 20px">受欢迎的分期购车</h2>
                            <div style="margin: 0 auto;width:1300px;border-top: black 3px solid"/>
                            <div class="jump_buy" style="margin-top: 50px;width: 1300px;margin: 0 auto;">
                                <div class="big_img"
                                     style="width: 600px;
                                            height:450px;
                                            float: left"
                                      @click="toBuy(1)"></div>
                                <ul id="img_1" @click="toBuy(2)"></ul>
                                <ul id="img_2" @click="toBuy(2)"></ul>
                                <ul id="img_3" @click="toBuy(2)"></ul>
                                <ul id="img_4" @click="toBuy(2)"></ul>
                                <ul id="img_5" @click="toBuy(2)"></ul>
                                <ul id="img_6" @click="toBuy(2)"></ul>
                            </div>
                        </el-row>
                    </el-col>
                    <!--贷款流程-->
                    <el-col>
                        <el-row style="height: 250px;background: white">
                            <h2 style="margin-left: 350px;margin-top: 20px">贷款流程</h2>
                            <div style="margin: 0 auto;width:1300px;border-top: black 3px solid"/>
                            <div class="loan" style="margin: 0 auto;margin-top: 40px;width:1300px;height:150px;">
                                <ul style="width: 150px;">
                                    <i class="el-icon-document" style="font-size: 50px;margin-left: 30px"></i>
                                        <h2 style="margin-left: 15px">30秒申请</h2>
                                        <li style="list-style: none;">需要分期购车,提交基本信息即可申请</li>
                                </ul>
                                <ul>
                                    <i class="el-icon-arrow-right" style="font-size: 50px"></i>
                                </ul>
                                <ul style="width: 200px;">
                                    <i class="el-icon-headset" style="font-size: 50px;margin-left: 60px"></i>
                                    <h2 style="margin-left: 40px">预约看车</h2>
                                    <li style="list-style: none">在线挑选爱车，线下售车顾问一对一全程陪同讲解</li>
                                </ul>
                                <ul>
                                    <i class="el-icon-arrow-right" style="font-size: 50px"></i>
                                </ul>
                                <ul style="width: 230px;">
                                    <i class="el-icon-s-promotion" style="font-size: 50px;margin-left: 70px"></i>
                                    <h2 style="margin-left: 50px">极速审批</h2>
                                    <li style="list-style: none">资料提交后立即审批，免流水、免家访，最快30分钟出结果</li>
                                </ul>
                                <ul>
                                    <i class="el-icon-arrow-right" style="font-size: 50px"></i>
                                </ul>
                                <ul style="width: 200px;">
                                    <i class="el-icon-coin" style="font-size: 50px;margin-left: 70px"></i>
                                    <h2 style="margin-left: 45px">放款提车</h2>
                                    <li style="list-style: none">瓜子助您完成车辆过户,抵押等手续后，当天放款</li>
                                </ul>
                            </div>
                        </el-row>
                    </el-col>
                    <!--合作机构-->
                    <el-col>
                        <el-row style="height: 450px;background: white">
                            <h2 style="margin-left: 350px;margin-top: 20px">合作机构</h2>
                            <div style="margin: 0 auto;width:1300px;border-top: black 3px solid"/>
                            <div class="cooperation"></div>
                            <el-backtop :bottom="350">
                                <div>
                                    <h1>立即注册</h1>
                                </div>
                            </el-backtop>

                        </el-row>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
        <Myfooter/>
    </div>
</template>

<script>
    import Myheader from '../common/Header.vue';
    import Myfooter from '../common/Footer.vue';
    export default {
        name: "Finance",
        components: {Myheader, Myfooter},

        data() {
            return {
                numberValidateForm: {
                    phone: ''
                }
            };

        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            toBuy(id){
                this.$router.push({path:'/buycar',query: {id:id}})
            }
        }
    }
</script>

<style scoped>

    /*申请流程*/
    .shengqing_2 ul{
        margin-top: 25px;
        margin-left: 90px;
        float: left
    }
    .shengqing_2 ul li{
        list-style:none;
        margin-left: 38px;
    }
    /*验证码部分*/
    .shengqing_1{
        margin-top: 25px;
        margin-left: 200px;
        width: 300px;
        height: 250px;
        border: 2px red solid;
    }
    .shengqing_1/deep/ input{
        margin-left: -80px;
        margin-top: 30px;
        float: left;
        width: 260px;
        height: 40px;
    }

    /*分期购车图片*/
    .jump_buy ul{
        float: left;
        width: 228px;
        height: 220px;
    }
    .big_img{
        background-image: url("../../assets/Finance/car_1.png");
        background-size: 100% 100%;
    }
    #img_1{
        background-image: url("../../assets/Finance/car_2.png");
        background-size: 100% 100%;
    }
    #img_2{
        background-image: url("../../assets/Finance/car_3.png");
        background-size: 100% 100%;
    }
    #img_3{
        background-image: url("../../assets/Finance/car_4.png");
        background-size: 100% 100%;
    }
    #img_4{
        background-image: url("../../assets/Finance/car_5.png");
        background-size: 100% 100%;
    }
    #img_5{
        background-image: url("../../assets/Finance/car_6.png");
        background-size: 100% 100%;
    }
    #img_6{
        background-image: url("../../assets/Finance/car_7.png");
        background-size: 100% 100%;
    }

    /*贷款流程*/
    .loan ul{
        float: left;
        margin-left: 50px;
    }
    /*合作单位*/
    .cooperation {
        margin-top: 20px;
        margin-left: 340px;
        width: 1199px;
        height: 219px;
        background: url("../../assets/Finance/cooperation.png");
    }



</style>