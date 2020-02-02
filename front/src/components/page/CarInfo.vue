<template>
    <div>
        <div>
    <Header></Header>
        </div>
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
                    <div style="float:left;margin-top: -500px;margin-left: 900px;border:1px red solid">
                        <div>
                        <h2 style="font-weight: normal">奔驰C级 2016款 C 200 轿跑版(进口)</h2>
                        </div>

                        <div style="float:left">
                            <div>
                        <p style="margin-top: 30px;margin-left: 15px;font-size: 18px;">2016-05</p>
                        <span style="margin-left: 25px;font-size: 13px;color: #777777;">上牌时间</span>
                            </div>
               <div style="height:30px;border-right:1px #777777 solid;width:100px;float:left;margin-top: -35px"></div>
                        </div>

                        <div style="float:left;">
                            <div>
                                <p style="margin-top: 30px;margin-left: 15px;font-size: 18px;">3.09万公里</p>
                                <span style="margin-left: 25px;font-size: 13px;color: #777777;">表显里程</span>
                            </div>
                            <div style="height:30px;border-right:1px #777777 solid;width:100px;float:left;margin-top: -35px;margin-left: 15px"></div>
                        </div>

                        <div style="float:left">
                            <div>
                                <p style="margin-top: 30px;margin-left: 15px;font-size: 18px;">2.0T</p>
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
                          <h2 style="margin-top: -25px;margin-left: 80px;color: orangered">￥6.05万</h2>
                      </div>

                     <!--APP下载-->
                      <div style="margin-top: -7%;margin-left: 55%">
                       <a href="" style="color: black;text-decoration: none">更低价格下载APP</a>
                      </div>

                    </div>

        <!--小图展示-->
        <div style="width:700px;height:100px;overflow:hidden;margin-left: 205px;margin-top: 20px;" id="smallImg">
            <img v-for="item in imgList" :key="item" :src=item style="width:150px;height:100px;margin-left: 10px;">
        </div>
        </div>


    <div style="float:bottom">
        <Footer></Footer>
    </div>
    </div>
</template>



<script>
    import Footer from '../common/Footer.vue';
    import Header from "../common/Header";

export default {

    components:{
        Footer,
        Header
    },
    data(){
        return{
          imgList:[
           'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
           'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
           'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg',
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg']
        }
    },
    methods:{
        handleAdd(){

        }
    }
}



</script>


<style scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }

</style>
