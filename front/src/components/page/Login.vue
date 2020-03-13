<template>
    <div>
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
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click.native.prevent="handleLogin" class="login-submit">登录
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    const MSGINIT = '发送验证码',
        MSGERROR = '验证码发送失败',
        MSGSCUCCESS = '${time}秒后重发',
        MSGTIME = 60;
    import quest from '../../api/index';
    import { mapMutations } from 'vuex';

    import img0 from '../../assets/slider/img.jpg';
    import img1 from '../../assets/slider/img1.jpg';
    import img2 from '../../assets/slider/img2.jpg';
    import img3 from '../../assets/slider/img3.jpg';
    import img4 from '../../assets/slider/img4.jpg';
    import img5 from '../../assets/slider/img5.jpg';

    export default {
        name: 'codelogin',
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
                }
            };
        },
        props: [],
        methods: {
            sendCode(){
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        quest.sendCode(this.loginForm.phone).then(response => {
                            alert(response.data.message)
                            this.$store.state.user = response.data.user
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
                                quest.login(this.loginForm).then(response => {
                                    if (response.data.verifyRes === 'success') {
                                        this.$router.push('/buyCar');
                                    } else if (response.data.verifyRes === 'fail') {
                                        alert('验证码错误，请重试！');
                                    };
                                });
                            }
                        }
                    }
                });
            },
        }
    };
</script>

<style>
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
