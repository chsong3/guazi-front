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
                <div v-show="user.phone==''" style="float: left;cursor:pointer" @click="toLogin"><i
                        class="el-icon-user-solid"></i>登录
                </div>
                <div class="phone" v-show="user.phone!=''" @mouseover="showdropdown=true">
                  <span>
                    {{user.phone}}
                  </span>
                </div>
                <div class="uc-app" v-show="showdropdown" @mouseover="showdropdown=true" @mouseout="showdropdown=false">
                    <div class="dropmenu" @click="toPersonal">个人中心</div>
                    <div class="dropmenu" @click="toPersonal">收藏车辆</div>
                    <div class="dropmenu" @click="toRecord">浏览记录</div>
                    <div class="dropmenu">降价提醒</div>
                    <div class="dropmenu" @click="loginOut">退出</div>
                </div>
            </div>
            <el-dialog
                    title="瓜子二手车直卖网"
                    :visible.sync="loginDialogVisible"
                    width="30%"
                    center>
                <div class="login-form">
                    <el-form :rules="loginRules" ref="loginForm" :model="loginForm" label-width="0">
                        <el-form-item prop="phone">
                            <el-input type="tel" size="small" @keyup.enter.native="handleLogin" v-model="loginForm.phone"
                                      auto-complete="off" placeholder="请输入手机号码">
                                <i slot="prefix" class="icon-shouji"></i>
                            </el-input>
                        </el-form-item>
                        <el-form-item prop="code">
                            <el-input size="small" @keyup.enter.native="handleLogin" v-model="loginForm.code" auto-complete="off"
                                      placeholder="请输入验证码">
                                <i slot="prefix" class="icon-yanzhengma" style="margin-top:6px;"></i>
                                <template slot="append" class="code">
                                    <span @click="sendCode" class="msg-text" :class="[{display:msgKey}]">{{msgText}}</span>
                                </template>
                            </el-input>
                            <div><span style="color: red;font-size: 12px;">{{codeTip}}</span></div>
                        </el-form-item>
                        <el-form-item>
                            <el-button size="small" type="primary" @click.native.prevent="handleLogin" class="login-submit">登录
                            </el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-dialog>
        </div>
        <router-view></router-view>
    </div>

</template>
<script>
    const MSGINIT = '发送验证码',
        MSGERROR = '验证码发送失败',
        MSGSCUCCESS = '${time}秒后重发',
        MSGTIME = 60;
    import quest from '../../api/index';

    export default {
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
                user:{phone:''},
                activeIndex: '1',
                loginDialogVisible: false,
                showdropdown: false,
                areaOption: {},//车牌所在地
                FristPin: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'],
                cityJson: [],
                carFrom: {
                    area: this.$store.state.cityId
                },
                /**login**/
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
                codeTip:''
            };
        },
        mounted() {
            if (this.$route.path!='/carDetail'){
                this.$router.push('/index')
            }
            let user = this.$cookies.get("user")
            user != null ? this.user = user : this.user == this.user
            this.getCityList();
        },
        methods: {
            //城市列表
            getCityList() {
                //先拿到全部城市的名字
                let cityArr = [];
                quest.getCityList().then(response => {
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
               // console.log(key);
            },
            toLogin() {
                this.loginDialogVisible = true;
            },
            loginOut() {
                if (this.$route.path == '/personal'){
                    this.$router.push('/index')
                    this.$router.go(0)
                }else {
                    this.$router.go(0)
                }
                this.$cookies.remove("user")
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
            },
            toPersonal(){
                this.$router.push({
                    path:'/personal',
                    query:{
                        name:'collect'
                    }
                });
            },
            toRecord(){
                this.$router.push({
                    path:'/personal',
                    query:{
                        name:'record'
                    }
                });
            },
            /******/
            /*登录*/
            /****/
            sendCode(){
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        quest.sendCode(this.loginForm.phone).then(response => {
                            this.codeTip=response.data.message;
                        });
                        if (this.msgKey) return;
                        this.msgText = MSGSCUCCESS.replace('${time}', this.msgTime);
                        this.msgKey = true;
                        const time = setInterval(() => {
                            this.msgTime--;
                            this.msgText = MSGSCUCCESS.replace('${time}', this.msgTime);
                            if (this.msgTime == 0) {//时间到了
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
                this.$cookies.config(1, '/');
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        let code = this.loginForm.code;
                        if (code.length != 6) {
                            alert('请输入6位数的验证码');
                        } else {
                            let val = code.trim();
                            const phoneReg = /^[0-9]\d*$|^$/;
                            if (!(phoneReg.test(val))) {
                                alert('请输入数字');
                            } else {
                                quest.login(this.loginForm).then(response => {
                                    if (response.data.verifyRes === 'success') {
                                        // 以秒为单位，设置1天过去
                                        this.$cookies.set("user",response.data.user,60 * 60 * 24)
                                        this.loginDialogVisible = false;
                                        this.$router.go(0)
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
        right: 90px;
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

    .dropmenu:hover{
        background: #00a854;
    }

    .el-select-dropdown{
        width:150px;
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
        width: 300px;
        margin: 0 auto;
        margin-top: 50px;
    }

    .code {
        margin-left: 20px;
    }
</style>
