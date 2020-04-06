<template>

    <div>
       <el-main>
           <div>
               <!--大图展示-->
               <div class="block" style="margin-top: 20px;">
                   <el-carousel height="500px" style="width:700px;margin-left: 9%" indicator-position="none">
                       <el-carousel-item v-for="item in imgList" :key="item">
                           <h3 class="small">
                               <img class="card-image" style="height:500px;width: 700px" :src=item></h3>
                       </el-carousel-item>
                   </el-carousel>
               </div>

               <!--车辆信息-->
               <div style="float:left;margin-top: -500px;margin-left: 900px;">
                   <div>
                       <h2 style="font-weight: normal">{{carInfo.brandName+' '+carInfo.seriesName+' '+carInfo.carDesc}}</h2>
                   </div>

                   <div style="float:left">
                       <div>
                           <p style="margin-top: 30px;margin-left: 15px;font-size: 18px;" v-if="carInfo.buyTime!=undefined">
                               {{carInfo.buyTime.slice(0,4)+'-'+carInfo.buyTime.slice(4,6)}}</p>
                           <span style="margin-left: 25px;font-size: 13px;color: #777777;">上牌时间</span>
                       </div>
                       <div style="height:30px;border-right:1px #777777 solid;width:100px;float:left;margin-top: -35px"></div>
                   </div>

                   <div style="float:left;">
                       <div>
                           <p style="margin-top: 30px;margin-left: 15px;font-size: 18px;">{{carInfo.mileage}}万公里</p>
                           <span style="margin-left: 25px;font-size: 13px;color: #777777;">表显里程</span>
                       </div>
                       <div style="height:30px;border-right:1px #777777 solid;width:100px;float:left;margin-top: -35px;margin-left: 15px"></div>
                   </div>

                   <div style="float:left">
                       <div>
                           <p style="margin-top: 30px;margin-left: 15px;font-size: 18px;">{{carInfo.displacement}}T</p>
                           <span style="margin-left: 20px;font-size: 13px;color: #777777;">排量</span>
                       </div>
                       <div style="height:30px;border-right:1px #777777 solid;width:100px;float:left;margin-top: -35px;margin-left: -30px"></div>
                   </div>

                   <div style="float:left">
                       <div>
                           <p style="margin-top: 30px;margin-left: 25px;font-size: 18px;">自动</p>
                           <span style="margin-left: 25px;font-size: 13px;color: #777777;">变速箱</span>
                       </div>
                   </div>

                   <!--价格展示-->
                   <div style="margin-top: 100px;margin-left: 20px;">
                       <p style="font-size: 14px;color: darkorange">金融专享价:</p>
                       <h2 style="margin-top: -25px;margin-left: 80px;color: orangered">{{'￥'+carInfo.price+'万'}}</h2>
                   </div>

                   <!--APP下载-->
                   <div style="margin-top: -5%;margin-left: 55%">
                       <a href="" style="font-size:10px;color: limegreen;text-decoration: none;border:1px limegreen solid;border-radius: 15px"
                          @mouseover="mouseIsOver=true"
                          @mouseout="mouseIsOver=false">更低价格下载APP</a>
                   </div>
                   <img style="margin-left: 56.5%;position:absolute;top:242px;left:-30px;z-index: 10" v-show="mouseIsOver" src="../../assets/download.png"></img>
                   <div>
                       <el-button v-show="showCollectBtn" @click="addCollectCar" style="margin-top: 15px;margin-left: 10px">收藏</el-button>
                   </div>
                   <div>
                       <el-button v-show="!showCollectBtn" @click="cancelCollectCar">已收藏，点击取消收藏</el-button>
                   </div>

                   <div class="icon_pay_one" style="float:left"></div>
                   <div style="color: #22AC38 "><span style="font-size: 25px;color: #22AC38">2.21</span>万开回家　　分期详情></div>
                   <div style="font-size: 10px;margin-top: 55px;float:left">在线分期秒批，最高可分期<span style="color:darkorange;font-size: 10px">50万</span></div>


                   <el-button style="background-color: darkorange;color:white;width: 120px;
                height:60px;margin-left: 220px;float:left;margin-top: -60px;
                font-size: 18px;" @click="isShow=true">我要预约</el-button>
                   <!--                <el-button style="margin-top: 10px;background-color: #22AC38;color:white;width: 120px;height:60px;font-size: 18px">我要砍价</el-button>-->
               </div>

               <el-dialog :visible="isShow" :title="'我要预约'">
                   <el-row>
                       <label style="font-weight: bold;font-size: 20px;color: black;margin-left: 100px">预约今天，既享优惠</label>
                   </el-row>

                   <el-row style="margin-top: 30px">
                       <label style="margin-left: 100px;font-size: 16px">看车方式</label>
                       <label style="margin-left: 40px;color:black;font-size: 17px">到店服务</label>
                   </el-row>

                   <el-row style="margin-top: 30px">
                       <label style="margin-left: 100px;font-size: 16px">选择时间</label>
                       <el-button-group>
                           <el-button style="margin-left: 40px;width: 120px;height:45px;border-radius: 5px"
                                      @click="getButtonIndex('0')">今天</el-button>
                           <el-button style="margin-left: 15px;width: 120px;height:45px;border-radius: 5px"
                                      @click="getButtonIndex('1')">明天</el-button>
                           <el-button style="margin-left: 15px;width: 120px;height:45px;border-radius: 5px"
                                      @click="getButtonIndex('2')">后天</el-button>
                       </el-button-group>
                   </el-row>

                   <el-row style="margin-top: 30px;margin-left: 400px">
                       <el-button style="background-color:#22AC38;color: white;width: 140px;
                    height:55px;border-radius: 5px;font-size: 16px;"
                                  @click="orderCar(carInfo)">点击预约</el-button>
                   </el-row>

               </el-dialog>


               <!--小图展示-->　　
               <div style="width:700px;height:100px;overflow:hidden;margin-left: 205px;margin-top: 20px;" id="smallImg">
                   <img v-for="item in imgList" :key="item" :src=item style="width:150px;height:100px;margin-left: 10px;">
               </div>
           </div>
       </el-main>


        <div style="float:bottom">
            <Footer></Footer>
        </div>



    </div>
</template>


<script>
    import Footer from '../common/Footer.vue';
    import request from '../../api/index'
    export default {

        components: {
            Footer
        },
        data() {
            return {
                buttonIndex:'',
                isShow:false,
                mouseIsOver:false,
                imgList: [],
                carInfo:'',
                collectData:{
                    userId:'',
                    carId:''
                },
                showCollectBtn:true
            };

        },
        mounted() {
            this.carInfo = this.$route.query
            this.carInfo.timeId = '0'
            if (this.$cookies.get("user")!=null) {
                this.collectData.userId = this.$cookies.get("user").id;
            }
            this.collectData.carId = this.$route.query.id
            // this.imgAdd();
            this.catIsCollect();
            this.queryCarImg();
        },
        methods: {
            handleAdd() {

            },
            //查询是否收藏
            catIsCollect(){
                let user = this.$cookies.get("user");
                if (user != null){
                    request.catIsCollect(this.collectData).then(response=>{
                        if(response.data.map=="true"){
                            //已经收藏
                            this.showCollectBtn = false
                        }else if (response.data.map=="false"){
                            //没有收藏
                            this.showCollectBtn = true
                        }
                    })
                }else {

                }
            },

            //收藏车辆
            addCollectCar(){
                let user = this.$cookies.get("user");
                if (user != null){
                    request.addCollectCar(this.collectData).then(response =>{
                        this.$router.go(0)
                    })
                }else {
                    alert("请先登录！")
                }

            },
            //取消收藏车辆
            cancelCollectCar(){
                let user = this.$cookies.get("user");
                if (user != null){
                    request.cancelCollectCar(this.collectData).then(response =>{
                        this.$router.go(0)
                    })
                }else {
                    alert("请先登录！")
                }

            },
            //查询车辆图片
            queryCarImg(){
                request.queryCarImg(this.carInfo.id).then(response =>{
                    this.imgList = response.data
                })
            },
            //获得预约选择日期
            getButtonIndex(type){
                this.carInfo.timeId = type
            },
            //预约车辆
            orderCar(record){
                let user = this.$cookies.get("user");
                if (user != null){
                    record.buyUserId = user.id
                    request.orderCar(record).then(response =>{
                        if(response.data.respInfo == '预约成功'){
                            this.$message({
                                type:'success',
                                message:response.data.respInfo
                            })
                        }else{
                            this.$message({
                                type:'error',
                                message:response.data.respInfo
                            })
                        }
                    })
                }else {
                    alert("请登录")
                }

                this.isShow = false
            }
        }
    };


</script>


<style scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }
    .icon_pay_one{width:80px;height:110px;background:url("https://image.guazistatic.com/gz01191015/17/26/05e043b9adc25657ad3b6cb3512647f5.png") no-repeat;background-size:100% 100%}


</style>
