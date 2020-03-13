<template>
    <div>
        <div>
            <el-main>
                <!--头部导航-->
                <el-row>
                    <el-col>
                        <!--申请框-->
                        <el-row class="apply-bg">
                            <el-col>
                                <!--验证码申请-->
                                <div class="login-form">
                                    <div style="margin-left: 45px;padding-top: 20px">
                                        <div style="font-size: 16px;color: #666;padding-bottom: 10px;">定制方案 <span style="font-size: 12px">资质越好，方案越划算</span></div>
                                        <el-form :rules="loginRules" ref="loginForm" :model="loginForm" label-width="0">
                                            <el-form-item prop="phone">
                                                <el-input type="tel" size="small" @keyup.enter.native="handleLogin"
                                                          v-model="loginForm.phone"
                                                          auto-complete="off" placeholder="请输入手机号码">
                                                    <i slot="prefix" class="icon-shouji"></i>
                                                </el-input>
                                            </el-form-item>
                                            <el-form-item prop="code">
                                                <el-input size="small" @keyup.enter.native="handleLogin"
                                                          v-model="loginForm.code" auto-complete="off"
                                                          placeholder="请输入验证码">
                                                    <i slot="prefix" class="icon-yanzhengma" style="margin-top:6px;"></i>
                                                    <template slot="append" class="code">
                                                    <span @click="sendCode" class="msg-text"
                                                          :class="[{display:msgKey}]">{{msgText}}</span>
                                                    </template>
                                                </el-input>
                                            </el-form-item>
                                            <el-form-item>
                                                <el-button size="medium" type="danger" @click.native.prevent="handleLogin"
                                                           class="login-submit">立即申请
                                                </el-button>
                                                <div v-if="applySuccess" style="color: red">申请成功，稍后会有瓜子客户联系您！</div>
                                            </el-form-item>
                                        </el-form>
                                    </div>
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
            </el-main>
        </div>
        <Myfooter/>
    </div>
</template>

<script>
    import Myfooter from '../common/Footer.vue';

    const MSGINIT = '发送验证码',
        MSGERROR = '验证码发送失败',
        MSGSCUCCESS = '${time}秒后重发',
        MSGTIME = 60;
    import quest from '../../api/index';

    export default {
        name: 'Finance',
        components: {
            Myfooter
        },
        data() {
            const validatePhone = (rule, value, callback) => {
                const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/;
                if (!value) {
                    return callback(new Error('电话号码不能为空'));
                }
                setTimeout(() => {
                    // Number.isInteger是es6验证数字是否为整数的方法,但是我实际用的时候输入的数字总是识别成字符串
                    // 所以我就在前面加了一个+实现隐式转换

                    if (!Number.isInteger(+value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (phoneReg.test(value)) {
                            callback();
                        } else {
                            callback(new Error('电话号码格式不正确'));
                        }
                    }
                }, 100);
            };
            return {
                numberValidateForm: {
                    phone: ''
                },
                centerDialogVisible: false,
                msgText: MSGINIT,
                msgTime: MSGTIME,
                msgKey: false,
                loginForm: {
                    phone: '',
                    code: ''
                },
                loginRules: {
                    phone: [{ required: true, trigger: 'blur', validator: validatePhone }]
                },
                applySuccess:false
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
            toBuy(id) {
                this.$router.push({ path: '/buycar', query: { id: id } });
            },
            sendCode() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        quest.sendCode(this.loginForm.phone).then(response => {
                            alert(response.data.message);
                        });
                        if (this.msgKey) return;
                        this.msgText = MSGSCUCCESS.replace('${time}', this.msgTime);
                        this.msgKey = true;
                        const time = setInterval(() => {
                            this.msgTime--;
                            this.msgText = MSGSCUCCESS.replace('${time}', this.msgTime);
                            if (this.msgTime == 0) {
                                this.msgTime = MSGTIME;
                                this.msgText = MSGINIT;
                                this.msgKey = false;
                                clearInterval(time);
                            }
                        }, 1000);
                    }
                    ;
                });
            },
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        let code = this.loginForm.code;
                        if (code.length != 6) {
                            alert('请输入6位数的验证码');
                        } else {
                            let val = code.trim();
                            const phoneReg = /^[1-9]\d*$|^$/;
                            if (!(phoneReg.test(val))) {
                                alert('请输入数字');
                            } else {
                                quest.addFinancialPlan(this.loginForm).then(response => {
                                    if (response.data.verifyRes === 'success') {
                                        this.applySuccess = true
                                    } else if (response.data.verifyRes === 'fail') {
                                        alert('验证码错误，请重试！');
                                    };
                                });
                            }
                        }
                    }
                });
            }
        }
    };
</script>

<style scoped>

    /*申请流程*/
    .shengqing_2 ul {
        margin-top: 25px;
        margin-left: 90px;
        float: left
    }

    .shengqing_2 ul li {
        list-style: none;
        margin-left: 38px;
    }

    /*验证码部分*/
    .shengqing_1 {
        margin-top: 25px;
        margin-left: 200px;
        width: 300px;
        height: 250px;
        border: 2px red solid;
    }

    .shengqing_1 /deep/ input {
        margin-left: -80px;
        margin-top: 30px;
        float: left;
        width: 260px;
        height: 40px;
    }

    /*分期购车图片*/
    .jump_buy ul {
        float: left;
        width: 228px;
        height: 220px;
    }

    .big_img {
        background-image: url("../../assets/Finance/car_1.png");
        background-size: 100% 100%;
    }

    #img_1 {
        background-image: url("../../assets/Finance/car_2.png");
        background-size: 100% 100%;
    }

    #img_2 {
        background-image: url("../../assets/Finance/car_3.png");
        background-size: 100% 100%;
    }

    #img_3 {
        background-image: url("../../assets/Finance/car_4.png");
        background-size: 100% 100%;
    }

    #img_4 {
        background-image: url("../../assets/Finance/car_5.png");
        background-size: 100% 100%;
    }

    #img_5 {
        background-image: url("../../assets/Finance/car_6.png");
        background-size: 100% 100%;
    }

    #img_6 {
        background-image: url("../../assets/Finance/car_7.png");
        background-size: 100% 100%;
    }

    /*贷款流程*/
    .loan ul {
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

    .msg-text {
        display: block;
        width: 60px;
        font-size: 12px;
        text-align: center;
        cursor: pointer;
    }

    .msg-text.display {
        color: #ccc;
    }

    .login-form {
        width: 20%;
        height: 200px;
        margin-top: 50px;
        margin-left:200px;
        background: white;
    }

    .code {
        margin-left: 20px;
    }

    .apply-bg {
        height: 300px;
        background: ghostwhite;
        background-image: url(https://jr-sta.guazistatic.com/finance_web/banner.5355155850faa0c33e6316a71c94c6d9.png);
    background-size: cover;
        background-repeat: no-repeat;
    }
</style>
