<template>
    <div>
    <Header></Header>
        <div class="block">
            <span class="demonstration">默认 Hover 指示器触发</span>
            <el-carousel height="500px" style="width:700px;margin-left: 10%" indicator-position="none">
                <el-carousel-item v-for="item in imgList" :key="item">
                    <h3 class="small">
                        <img class="card-image" style="height:500px;width: 700px" :src=item></h3>
                </el-carousel-item>
            </el-carousel>
        </div>
    <div>
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
              'http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg']
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
