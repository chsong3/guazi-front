<template>
  <div>
      <el-container>
          <el-header>
          <el-row>
              <el-col :span="8">
                  <!--城市选择器-->
                  <div class="L_region">
                      <el-row>
                          <el-col :span="14">
                              <div class="logo">
                                  <el-image
                                      style="width: 100px; height: 60px"
                                      :src="headerimage">
                                  </el-image>
                              </div>
                          </el-col>
                          <el-col :span="10">
                              <div class="region">
                                  <el-cascader
                                          v-model="value"
                                          :options="options"
                                          @change="handleChange">
                                  </el-cascader>
                              </div>
                          </el-col>
                      </el-row>
                  </div>
              </el-col>
              <el-col :span="12">
                  <!--头部导航-->
                  <div class="Navigation">
                      <el-menu
                              :default-active="activeIndex2"
                              class="el-menu-demo"
                              mode="horizontal"
                              @select="handleSelect"
                              background-color="#545c64"
                              text-color="#fff"
                              active-text-color="#ffd04b">
                              <el-menu-item index="1"><router-link to="/index">首页</router-link></el-menu-item>
                              <el-menu-item index="2"><router-link to="/sellcar">我要卖车</router-link></el-menu-item>
                              <el-menu-item index="3"><router-link to="/buycar">我要买车</router-link></el-menu-item>
                              <el-menu-item index="4"><router-link to="/finance">瓜子金融</router-link></el-menu-item>
                      </el-menu>
                  </div>
              </el-col>
              <el-col :span="4">
                  <!--注册登录位置-->
                  <div class="Login">
                      <el-button type="text" @click="open_z">注册</el-button>
                      <el-avatar icon="el-icon-user-solid"></el-avatar>
                      <el-button type="text" @click="open_d">登录</el-button>
                  </div>
              </el-col>
          </el-row>
          </el-header>
      </el-container>
  </div>
</template>

<script>
export default {
    data() {
        return {
            activeIndex2: '1',
            value: [],
            options: [{
                value: '省级1',
                label: '浙江',
                children: [{
                    value: '地区1',
                    label: '杭州'
                },{
                    value:'地区2',
                    label:'宁波'
                    }
                ]
            },{
                value: '省级2',
                label: '重庆',
                children: [{
                    value: '地区1',
                    label: '重庆市'
                }],
            }
            ],
            headerimage:require('../../assets/logo.png'),
        };
    },
    methods: {
        handleChange(value) {
            console.log(value);
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        toURL_s(){
            this.$router.push({ path: '/sellcar' })
        },
        toURL_b(){
            this.$router.push({ path: '/buycar' })
        },
        toURL_i(){
            this.$router.push({ path: '/index' })
        },
        open_z(){
            this.$confirm('','注册',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
            this.$message({
                type: 'success',
                message: '删除成功!'
            });
        }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消删除'
            });
        })
        },
        open_d(){
            this.$alert('登录', '登录')
        }



    }
}
</script>


<style scoped>
    .el-header{
        background: #545c64;
    }
    .L_region{
        height: 50px;
    }
    .Navigation{
        width: 650px;
        margin-left: 130px;
    }
    .Login{
        height: 50px;

    }
    .logo{
        height: 50px;
        width: 150px;
        margin-left: 50px;

    }
    .region{
        height: 50px;

    }
    .el-cascader{
        margin-top: 10px;
        margin-left: 50px;
    }
    .el-button{
        font-size: 24px;
        margin-left: 30px;
        color: white;
    }
    .el-avatar{
        margin-top: 8px;
        margin-left: 30px;
    }
</style>
