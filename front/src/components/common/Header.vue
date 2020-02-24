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
            <div style="float: left;cursor:pointer" @click="toLogin"><i class="el-icon-user-solid"></i>登录</div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1'
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            toLogin(){
               this.$router.push('/login');
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
        width: 30%;
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
        width: 20%;
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
        margin-left:120px;
    }
</style>
