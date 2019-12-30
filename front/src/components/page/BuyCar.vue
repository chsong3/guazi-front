<template>
    <div>
        <div class="body">
            <el-header>nav</el-header>
            <el-main>
                <!--条件筛选部分-->
                <!--搜索框-->
                <el-row>
                    <el-col :span="16">
                        <div>
                            <span>瓜子二手车>重庆二手车</span>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <el-input v-model="input" placeholder="请输入内容">
                            <i slot="prefix" class="el-input__icon el-icon-search"></i>
                        </el-input>
                    </el-col>
                </el-row>
                <!--条件筛选部分-->
                <el-row class="condition">
                    <el-row class="condition-item">
                        <el-col :span="22">
                            <el-row>
                                <el-col class="item-body item-head" :span="2">
                                    <span class="item-head-font gray-font">品牌</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-body-font item-body-active item-pointer">不限</span>
                                </el-col>
                                <el-col :span="1.5" v-for="(item,index) in brandList">
                                    <span v-if="index<13" class="item-body-font item-pointer">{{item.name}}</span>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="2" class="item-body allClick">
                            <div v-show="!show.brandAllShow" class="item-body-font allClick-font item-pointer"
                                 @click="clickBrandAll">
                                全部<i class="el-icon-arrow-down"></i>
                            </div>
                            <div v-show="show.brandAllShow" class="item-body-font allClick-font item-pointer"
                                 @click="clickBrandAll">
                                收起<i class="el-icon-arrow-up"></i>
                            </div>
                        </el-col>
                    </el-row>
                    <!--下拉框 品牌-->
                    <el-row class="condition-item" v-show="show.brandAllShow">
                        <el-col :span="22">
                            <el-row>
                                <el-col :span="2" class="item-head drop-head">
                                    <span class="item-head-font gray-font"></span>
                                </el-col>
                                <el-col :span="22" class="drop-item">
                                    <!--按字母排序分为左边 右边，这是左边部分-->
                                    <div class="item-col">
                                        <div v-for="(item,index) in brandJson">
                                            <div class="item-list" v-show="index<(brandJson.length/2)">
                                                <div class="pinyin">
                                                    <span>{{item.name}}</span>
                                                </div>
                                                <div v-for="(dd,index2) in item.data">
                                                    <span class="item-pointer">{{dd.name}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--按字母排序分为左边 右边，这是右边部分-->
                                    <div class="item-col">
                                        <div v-for="(item,index) in brandJson">
                                            <div class="item-list" v-show="index>(brandJson.length/2)">
                                                <div class="pinyin">
                                                    <span>{{item.name}}</span>
                                                </div>
                                                <div v-for="(dd,index2) in item.data">
                                                    <span class="item-pointer">{{dd.name}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                    <el-row :span="24" class="condition-item">
                        <el-col :span="22">
                            <el-row>
                                <el-col class="item-body item-head" :span="2">
                                    <span class="item-head-font gray-font">车系</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-body-font item-body-active">不限</span>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="2" class="item-body allClick">
                            <el-link v-show="!show.seriesAllShow" class="item-body-font allClick-font"
                                     :underline="false"
                                     @click="clickSeriesAll">
                                全部<i class="el-icon-arrow-down"></i>
                            </el-link>
                            <el-link v-show="show.seriesAllShow" class="item-body-font allClick-font"
                                     :underline="false"
                                     @click="clickSeriesAll">
                                收起<i class="el-icon-arrow-up"></i>
                            </el-link>
                        </el-col>
                    </el-row>
                    <!--下拉框 车系-->
                    <el-row :span="24" class="condition-item" v-show="show.seriesAllShow">
                        <el-col :span="22">
                            <el-row>
                                <el-col :span="2" class="item-head">
                                    <span class="item-head-font gray-font"></span>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>

                    <el-row :span="24" class="condition-item">
                        <el-col :span="22">
                            <el-row class="price">
                                <el-col class="item-body item-head" :span="2">
                                    <span class="item-head-font gray-font">价格</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-body-font item-body-active">不限</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-price-font">3万以下</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-price-font">3-5万</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-price-font">7-9万</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-price-font">9-12万</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-price-font">12-16万</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-price-font">16-20万</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-price-font">20万以上</span>
                                </el-col>
                                <el-col :span="3">
                                    <el-input  v-model="input"></el-input>-
                                    <el-input  v-model="input"></el-input>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>

                    <el-row :span="24" class="condition-item">
                        <el-col :span="22">
                            <el-row>
                                <el-col class="item-body item-head" :span="2">
                                    <span class="item-head-font gray-font">更多</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-body-font item-body-active">不限</span>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="2" class="item-body allClick">
                            <el-link v-show="!show.moreAllShow" class="item-body-font allClick-font"
                                     :underline="false"
                                     @click="clickMoreAll">
                                全部<i class="el-icon-arrow-down"></i>
                            </el-link>
                            <el-link v-show="show.moreAllShow" class="item-body-font allClick-font"
                                     :underline="false"
                                     @click="clickMoreAll">
                                收起<i class="el-icon-arrow-up"></i>
                            </el-link>
                        </el-col>
                    </el-row>
                    <!--下拉框 更多-->
                    <el-row :span="24" class="condition-item" v-show="show.moreAllShow">
                        <el-col :span="22">
                            <el-row>
                                <el-col :span="2" class="item-head">
                                    <span class="item-head-font gray-font"></span>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-row>
                <el-row>
                    <span class="gray-font">在 "重庆二手车" 中为您找到95042辆好车</span>
                </el-row>
                <!--list部分-->
                <!--list头部筛选-->
                <el-row class="list">
                    <!--list头部-->
                    <el-row :gutter="24">
                        <el-col :span="8" class="list-head-left">
                            <span class="list-head-font1">全部</span>
                            <em></em>
                            <span class="list-head-font1">付三成</span>
                            <em></em>
                            <span class="list-head-font1">严选车<i></i></span>
                        </el-col>
                        <el-col :span="16">
                            <div class="list-head-right">
                                <div class="border"><span class="list-head-font2">默认排序</span></div>
                                <div class="border"><span class="list-head-font2">最新发布</span></div>
                                <div class="border">
                                    <span class="list-head-font2">价格</span>
                                    <i class="el-icon-caret-top"></i>
                                </div>
                                <div class="border">
                                    <span class="list-head-font2">车龄</span>
                                    <i class="el-icon-caret-bottom"></i>
                                </div>
                                <div class="border">
                                    <span class="list-head-font2">里程</span>
                                    <i class="el-icon-caret-top"></i>
                                </div>
                                <el-autocomplete
                                        class="inline-input"
                                        v-model="state1"
                                        :fetch-suggestions="querySearch"
                                        placeholder="热门筛选"
                                        @select="handleSelect">
                                </el-autocomplete>
                            </div>
                        </el-col>
                    </el-row>
                </el-row>
                <el-divider></el-divider>
                <el-row class="cardList">
                    <el-col :span="6">
                        <el-card>
                            <img class="card-image"
                                 src="http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg">
                            <div>
                                <h2 class="car-dec">路虎 揽胜 2016款 3.0 V6 SC Vogue(进口)</h2>
                                <div class="car-time">
                                    2015年
                                    <span>|</span>
                                    6.0万公里
                                </div>
                                <div class="car-price">
                                    <p>
                                        85.00
                                        <span>万</span>
                                    </p>
                                    <em class="line-through">169.2万</em>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="6">
                        <el-card>
                            <img class="card-image"
                                 src="http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg">
                            <div>
                                <h2 class="car-dec">路虎 揽胜 2016款 3.0 V6 SC Vogue(进口)</h2>
                                <div class="car-time">
                                    2015年
                                    <span>|</span>
                                    6.0万公里
                                </div>
                                <div class="car-price">
                                    <p>
                                        85.00
                                        <span>万</span>
                                    </p>
                                    <em class="line-through">169.2万</em>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="6">
                        <el-card>
                            <img class="card-image"
                                 src="http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg">
                            <div>
                                <h2 class="car-dec">路虎 揽胜 2016款 3.0 V6 SC Vogue(进口)</h2>
                                <div class="car-time">
                                    2015年
                                    <span>|</span>
                                    6.0万公里
                                </div>
                                <div class="car-price">
                                    <p>
                                        85.00
                                        <span>万</span>
                                    </p>
                                    <em class="line-through">169.2万</em>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="6">
                        <el-card>
                            <img class="card-image"
                                 src="http://106.54.28.115/group1/M00/00/00/rBEACF3x6gKAALfPAADkv6X0STU802.jpg">
                            <div>
                                <h2 class="car-dec">路虎 揽胜 2016款 3.0 V6 SC Vogue(进口)</h2>
                                <div class="car-time">
                                    2015年
                                    <span>|</span>
                                    6.0万公里
                                </div>
                                <div class="car-price">
                                    <p>
                                        85.00
                                        <span>万</span>
                                    </p>
                                    <em class="line-through">169.2万</em>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </div>
        <div>
            <Footer></Footer>
        </div>
    </div>

</template>

<script>
    import Footer from '../common/Footer.vue';
    import quest from '../../api/index';
    import pinyin from '../../../node_modules/js-pinyin/index';

    export default {
        data() {
            return {
                FristPin: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'],
                brandJson: [],
                show: {
                    brandAllShow: false,
                    seriesAllShow: false,
                    moreAllShow: false
                },
                brandList: []//品牌
            };
        },
        components: {
            Footer
        },
        mounted() {
            this.loadData();
        },
        methods: {
            loadData() {
                this.getBrandList();
            },
            getBrandList() {
                //先拿到全部的名字
                let brandArr = [];
                quest.getBrandList().then(response => {
                    let pinyin = require('js-pinyin');
                    pinyin.setOptions({ checkPolyphone: false, charCase: 0 });
                    this.brandList = response.data.map;
                    for (let i = 0; i < response.data.map.length; i++) {
                        //遍历数组,拿到name
                        let brandName = response.data.map[i].name;
                        //取全部name的首字母
                        let fristName = pinyin.getCamelChars(brandName).substring(0, 1);    //这里截取首字母的第一位
                        //给原json添加首字母键值对
                        response.data.map[i].first = fristName;
                        //放入新数组
                        brandArr.push(response.data.map[i]);
                    }
                    let brandJson = [];
                    //根据首字母键值对给原数据按首字母分类
                    for (let i = 0; i < this.FristPin.length; i++) {    //这里的FirstPin是一个写入了所有字母的数组,见data中
                        brandJson.push({
                            name: this.FristPin[i],
                            data: brandArr.filter((value) => {
                                return value.first === this.FristPin[i];
                            })
                        });
                    }
                    this.brandJson = brandJson;
                    var deleData = brandJson.filter((value) => {
                        return value.data.length == 0;
                    });//过滤出没有该字母开头的
                    for (let i = 0; i < deleData.length; i++) {
                        var index = brandJson.indexOf(deleData[i]);
                        brandJson.splice(index, 1);
                    }
                });
            },
            clickBrandAll() {
                if (this.show.brandAllShow == false) {
                    this.show.brandAllShow = true;
                } else {
                    this.show.brandAllShow = false;
                }
            },
            clickSeriesAll() {
                if (this.show.seriesAllShow == false) {
                    this.show.seriesAllShow = true;
                } else {
                    this.show.seriesAllShow = false;
                }
            },
            clickMoreAll() {
                if (this.show.moreAllShow == false) {
                    this.show.moreAllShow = true;
                } else {
                    this.show.moreAllShow = false;
                }
            }
        }
    };
</script>


<style>
    li {
        list-style: none;
        text-align: -webkit-match-parent;
    }

    .gray-font {
        font-family: "Apple Color Emoji";
        color: #777777;
        font-size: 16px;
    }

    .condition {
        height: 100%;
        margin-top: 20px;
        border: 1px #DCDFE6 solid;
        border-bottom: white;
    }

    .condition-item {
        width: 100%;
        height:100%;
        border-bottom: 1px #DCDFE6 solid;
    }

    .item-head {
        height: 49px;
        background-color: #FAFAFA;
    }

    .item-head-font {
        display: block;
        text-align: center;
        line-height: 50px;
    }

    .item-body {
        height: 49px;
    }

    .item-body-font {
        width: 50px;
        height: 20px;
        margin-top: 15px;
        margin-left: 20px;
        display: block;
        font-family: "Apple Color Emoji";
        font-size: 14px;
        text-align: center;
        line-height: 20px;
    }

    .item-price-font {
        width: 60px;
        height: 20px;
        margin-top: 15px;
        margin-left: 20px;
        display: block;
        font-family: "Apple Color Emoji";
        font-size: 14px;
        text-align: center;
        line-height: 20px;
    }

    .price input{
        width: 50px;
        height:30px;
        float: left;
    }

    .drop-head{
        height:220px;
    }

    .item-list {
        width: 100%;
        height: 20px;
        margin-left: 10px;
        border: 1px white solid;
    }

    .pinyin {
        height: 100%;
    }

    .pinyin span {
        color: #22AC38;
        font-weight: bolder;
        cursor: default;
    }

    .item-pointer {
        cursor: pointer;
    }

    .item-list span {
        font-family: "Microsoft YaHei";
    }

    .item-list div {
        float: left;
        margin-left: 10px;
    }

    .item-col {
        float: left;
    }

    .allClick {

    }

    .allClick-font {

    }

    .item-body-active {
        color: #fff;
        background-color: #22ac38;
    }

    .list {
        margin-top: 20px;
    }


    .list-head-font1 {
        display: block;
        height: 46px;
        float: left;
        line-height: 46px;
        padding: 3px 10px 0;
        font-size: 22px;
        color: #495056;
    }

    .list-head-font1:hover {
        border-bottom: 3px solid #22ac38;
    }

    em {
        width: 10px;
        height: 16px;
        display: block;
        float: left;
        border-left: 1px solid #e6e6e6;
        margin-left: 10px;
        margin-top: 18px;
    }

    .list-head-font1 i {
        background-image: url(../../assets/tuijian.png);
        background-size: 23px 18px;
        background-repeat: no-repeat;
        margin-top: -35px;
        margin-left: 70px;
        display: block;
        width: 23px;
        height: 18px;
    }


    .list-head-right {
        margin-left: 230px;
        margin-top: 20px;
    }

    .list-head-right .el-autocomplete {
        width: 90px !important;
    }

    .list-head-right .border {
        width: 90px;
        height: 20px;
        float: left;
        margin-top: 8px;
        border-right: 1px solid #e6e6e6;
    }

    .list-head-font2 {
        height: 18px;
        margin-top: 15px;
        margin-left: 20px;
        line-height: 18px;
        font-size: 14px;
        text-align: center;
    }

    .list-head-font2:hover {
        color: #22ac38;
        text-decoration: none;
    }


    .cardList .el-card {
        background-color: #FFFFFF !important;
        box-shadow: 0 0 0 0 rgba(0, 0, 0, .1) !important;
        border: #FFFFFF solid;
        width: 282px;
        height: 307px;
    }

    .cardList .el-card:hover {
        border: #DCDFE6 solid;
    }

    .card-image {
        width: 260px;
        height: 192px;
        margin-left: -10px;
    }

    .car-dec {
        font-size: 17px;
        color: #495056;
        font-weight: 400;
        padding: 1px 0 6px 4px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .car-time {
        color: #a5abb2;
        font-size: 14px;
        padding-left: 4px;
        padding-bottom: 2px;
    }

    .car-price p {
        float: left;
        line-height: 30px;
        color: red;
    }

    .car-price em.line-through {
        text-decoration: line-through;
    }

    .car-price em {
        width: 100px;
        float: left;
        margin-left: 10px;
        margin-top: 7px;
        color: #a5abb2;
    }

    .car-price em, .car-price span {
        font-size: 14px;
    }
</style>
