<template>
    <div class="header">
        <div class="headerLeft">
            <div class="header-logo"></div>
            <div class="city"><span>重庆<i class="el-icon-caret-bottom"></i></span></div>
        </div>
        <div class="headerCenter">
            <el-menu active-text-color="#22AC38"
                     text-color="black"
                     :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                <el-menu-item index="1">首页</el-menu-item>
                <el-menu-item index="2">我要买车</el-menu-item>
                <el-menu-item index="3">我要卖车</el-menu-item>
                <el-menu-item index="4">瓜子服务</el-menu-item>
                <el-menu-item index="5">瓜子金融</el-menu-item>
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
</template>
<script>
    import Login from '../page/Login';

    export default {
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1',
                loginDialogVisible: false,
                showdropdown:false
            };
        },
        components: {
            Login
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            toLogin() {
                this.loginDialogVisible = true;
            },
            loginOut(){
                this.$store.state.user = ''
            }
        }
    };
</script>


<style scoped>
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
        _margin-left: 20px;
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
        right:180px;
        width: 105px;
        border: 1px solid #e6e6e6;
        background: #fff;
        z-index: 9999;
    }
    .dropmenu{
        width:100px;
        height: 20px;
        text-align: center;
        line-height: 20px;
        cursor: pointer;
    }
    .dropmenu:hover{
        color: #22ac38;
    }
</style>
