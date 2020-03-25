<template>
    <div>
        <div class="body">
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
                        <el-autocomplete v-model="searchInput" placeholder="请输入内容"
                                         :fetch-suggestions="querySearchAsync"
                                         suffix-icon="el-input__icon el-icon-search"
                                         @select="searchSelect"
                        >
                        </el-autocomplete>
                    </el-col>
                </el-row>
                <!--条件筛选部分-->
                <el-row class="condition">
                    <!--品牌筛选-->
                    <el-row class="condition-item">
                        <el-col :span="22">
                            <el-row>
                                <el-col class="item-body item-head" :span="2">
                                    <span class="item-head-font gray-font">品牌</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-body-font item-body-active item-pointer"
                                          :class="{hoverStyle:!(999==showClass.activeIndex),
                                                   selectActive:999==showClass.activeIndex}"
                                          @click="clickBrand(999)"
                                    >不限

                                    </span>
                                </el-col>
                                <el-col :span="2" v-for="(item,index) in brandList">
                                    <span v-if="index<10" class="item-body-font item-pointer"
                                          :class="{hoverStyle:!(item.id==showClass.activeIndex),
                                          selectActive:item.id==showClass.activeIndex}"
                                          @click="clickBrand(item.id)"
                                    >
                                        {{item.name}}
                                    </span>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="2" class="item-body allClick">
                            <div v-show="!show.brandAllShow"
                                 class="item-body-font allClick-font item-pointer hoverStyle"
                                 @click="clickBrandAll">
                                全部<i class="el-icon-arrow-down"></i>
                            </div>
                            <div v-show="show.brandAllShow" class="item-body-font allClick-font item-pointer hoverStyle"
                                 @click="clickBrandAll">
                                收起<i class="el-icon-arrow-up"></i>
                            </div>
                        </el-col>
                    </el-row>
                    <!--品牌筛选 下拉框 品牌-->
                    <el-row class="condition-item" v-show="show.brandAllShow">
                        <el-col :span="22">
                            <el-row>
                                <el-col :span="2" class="item-head drop-head">
                                    <span class="item-head-font gray-font"></span>
                                </el-col>
                                <el-col :span="22" class="drop-item">
                                    <!--按字母排序分为左边 右边，这是左边部分-->
                                    <div class="item-col"><!--item-for:html内容超出了div或p的宽度让内容自动换行-->
                                        <div class="item-for" v-for="(item,index) in brandJson">
                                            <div class="item-list" v-show="index<(brandJson.length/2)">
                                                <div class="pinyin">
                                                    <span>{{item.name}}</span>
                                                </div>
                                                <div v-for="(dd,index2) in item.data">
                                                    <span class="item-pointer"
                                                          :class="{hoverStyle:!(dd.id==showClass.activeIndex),
                                                           selectActive:dd.id==showClass.activeIndex}"
                                                          @click="clickBrand(dd.id)">{{dd.name}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--按字母排序分为左边 右边，这是右边部分-->
                                    <div class="item-col">
                                        <div class="item-for" v-for="(item,index) in brandJson">
                                            <div class="item-list" v-show="index>(brandJson.length/2)">
                                                <div class="pinyin">
                                                    <span>{{item.name}}</span>
                                                </div>
                                                <div v-for="(dd,index2) in item.data">
                                                    <span class="item-pointer"
                                                          :class="{hoverStyle:!(dd.id==showClass.activeIndex),
                                                           selectActive:dd.id==showClass.activeIndex}"
                                                          @click="clickBrand(dd.id)"
                                                    >{{dd.name}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                    <!--车系筛选-->
                    <el-row :span="24" class="condition-item">
                        <el-col :span="22">
                            <el-row>
                                <el-col class="item-body item-head" :span="2">
                                    <span class="item-head-font gray-font">车系</span>
                                </el-col>
                                <el-col :span="1.5">
                                    <span class="item-body-font item-pointer"
                                          :class="{hoverStyle:!(999==showClass.seriesActiveIndex),
                                                   selectActive:999==showClass.seriesActiveIndex}"
                                          @click="clickSeries(999)"
                                    >不限
                                   </span>
                                </el-col>
                                <el-col :span="2" v-for="(item,index) in seriesList">
                                    <span v-if="index<10"
                                          class="item-body-font item-pointer"
                                          :class="{hoverStyle:!(item.id==showClass.seriesActiveIndex),
                                                           selectActive:item.id==showClass.seriesActiveIndex}"
                                          @click="clickSeries(item.id)"
                                    >{{item.name}}</span>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="2" class="item-body allClick">
                            <div v-show="!show.seriesAllShow"
                                 class="item-body-font allClick-font item-pointer hoverStyle"
                                 @click="clickSeriesAll">
                                全部<i class="el-icon-arrow-down"></i>
                            </div>
                            <div v-show="show.seriesAllShow"
                                 class="item-body-font allClick-font item-pointer hoverStyle"
                                 @click="clickSeriesAll">
                                收起<i class="el-icon-arrow-up"></i>
                            </div>
                        </el-col>
                    </el-row>
                    <!--车系筛选 下拉框 车系-->
                    <el-row :span="24" class="condition-item" v-show="show.seriesAllShow">
                        <el-col :span="22">
                            <el-row>
                                <el-col :span="2" class="item-head drop-head">
                                    <span class="item-head-font gray-font"></span>
                                </el-col>
                                <el-col :span="22" class="drop-item">
                                    <!--按字母排序分为左边 右边，这是左边部分-->
                                    <div class="item-col">
                                        <div class="item-for" v-for="(item,index) in seriesJson">
                                            <div class="item-list" v-show="index<(seriesJson.length/2)">
                                                <div class="pinyin">
                                                    <span>{{item.name}}</span>
                                                </div>
                                                <div v-for="(dd,index2) in item.data">
                                                    <span class="item-pointer"
                                                          :class="{hoverStyle:!(dd.id==showClass.seriesActiveIndex),
                                                           selectActive:dd.id==showClass.seriesActiveIndex}"
                                                          @click="clickSeries(dd.id)"
                                                    >{{dd.name}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--按字母排序分为左边 右边，这是右边部分-->
                                    <div class="item-col">
                                        <div class="item-for" v-for="(item,index) in seriesJson">
                                            <div class="item-list" v-show="index>(seriesJson.length/2)">
                                                <div class="pinyin">
                                                    <span>{{item.name}}</span>
                                                </div>
                                                <div v-for="(dd,index2) in item.data">
                                                    <span class="item-pointer"
                                                          :class="{hoverStyle:!(dd.id==showClass.seriesActiveIndex),
                                                           selectActive:dd.id==showClass.seriesActiveIndex}"
                                                          @click="clickSeries(dd.id)"
                                                    >{{dd.name}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                    <!--价格筛选-->
                    <el-row :span="24" class="condition-item">
                        <el-col :span="22">
                            <el-row class="price">
                                <el-col class="item-body item-head" :span="2">
                                    <div class="item-head-font gray-font">价格</div>
                                </el-col>
                                <div v-for="(price,index) in showClass.priceActiveIndex">
                                    <el-col :span="1.5">
                                    <span class="item-body-font"
                                          :class="{hoverStyle:!(price.active),
                                                   selectActive:price.active}"
                                          @click="clickPrice(index,price.id)"
                                    >{{price.name}}</span>
                                    </el-col>
                                </div>
                                <el-col class="input-price" :span="5">
                                    <div>
                                        <el-input v-model="carFrom.beginPrice"></el-input>
                                    </div>
                                    <div class="price-line"><span>-</span></div>
                                    <div>
                                        <el-input v-model="carFrom.endPrice"></el-input>
                                    </div>
                                    <div class="price-line">万</div>
                                    <div>
                                        <el-button type="success" plain @click="confirmPrice">确定</el-button>
                                    </div>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                    <!--更多筛选-->
                    <el-row :span="24" class="condition-item">
                        <el-col :span="22">
                            <el-row>
                                <el-col class="item-body item-head" :span="2">
                                    <span class="item-head-font gray-font">更多</span>
                                </el-col>

                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.type' placeholder="车型">
                                        <el-option
                                                v-for="items in carTypeOption"
                                                :key="items.id"
                                                :label="items.name"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.mileage' placeholder="里程">
                                        <el-option
                                                v-for="items in mileageOption"
                                                :key="items.id"
                                                :label="items.name"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.displacement' placeholder="排量">
                                        <el-option
                                                v-for="items in displacementOption"
                                                :key="items.id"
                                                :label="items.name"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.color' placeholder="颜色">
                                        <el-option
                                                v-for="items in colorOption"
                                                :key="items.id"
                                                :label="items.color"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.country' placeholder="国别">
                                        <el-option
                                                v-for="items in countryOption"
                                                :key="items.id"
                                                :label="items.country"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.seatNum' placeholder="座位数">
                                        <el-option
                                                v-for="items in carSeatNumOption"
                                                :key="items.id"
                                                :label="items.seatNum+'座'"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.area' placeholder="车牌所在地">
                                        <el-option
                                                v-for="items in areaOption"
                                                :key="items.id"
                                                :label="items.name"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="2" class="item-body allClick">
                            <div v-show="!show.moreAllShow" class="item-body-font allClick-font item-pointer hoverStyle"
                                 @click="clickMoreAll">
                                全部<i class="el-icon-arrow-down"></i>
                            </div>
                            <div v-show="show.moreAllShow" class="item-body-font allClick-font item-pointer hoverStyle"
                                 @click="clickMoreAll">
                                收起<i class="el-icon-arrow-up"></i>
                            </div>
                        </el-col>
                    </el-row>
                    <!--更多筛选 下拉框 更多-->
                    <el-row :span="24" class="condition-item" v-show="show.moreAllShow">
                        <el-col :span="22">
                            <el-row>
                                <el-col :span="2" class="item-head">
                                    <span class="item-head-font gray-font"></span>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.dischargeStandard' placeholder="排放标准">
                                        <el-option
                                                v-for="items in dischargeStandardOption"
                                                :key="items.id"
                                                :label="items.dischargeStandard"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" :span="3">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.oilType' placeholder="燃油类型">
                                        <el-option
                                                v-for="items in oilTypeOption"
                                                :key="items.id"
                                                :label="items.oilType"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                                <el-col class="more-select" style="margin-left: 20px" :span="2">
                                    <el-select clearable
                                               :popper-append-to-body="false"
                                               size="mini"
                                               class="select-style2"
                                               popper-class="select-popper"
                                               v-model='carFrom.driveType' placeholder="驱动类别">
                                        <el-option
                                                v-for="items in driveTypeOption"
                                                :key="items.id"
                                                :label="items.drive"
                                                :value="items.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-row>
                <!--当前筛选-->
                <el-row style="margin-top: 10px">
                    <el-col>
                        <template v-for="(tag,index) in tagArr">
                            <span v-show="index==0&&tag.name!=''">当前筛选:</span>
                            <el-tag v-show="tag.name!=''" closable :disable-transitions="false"
                                    @close="handleClose(index,tag.id)">
                                {{tag.name}}
                            </el-tag>
                        </template>
                        <span class="gray-font">在 "重庆二手车" 中为您找到{{carCountNum}}辆好车</span>
                    </el-col>
                </el-row>
                <!--list部分-->
                <!--list头部筛选-->
                <el-row class="list">
                    <!--list头部-->
                    <el-row>
                        <el-col :span="8" class="list-head-left">
                            <span class="list-head-font1 item-pointer list-head-active"
                                  :class="{listHeadActive:listHeadActive.all}"
                                  @click="clickAll">
                                全部
                            </span>
                            <em></em>
                            <span class="list-head-font1 item-pointer"
                                  :class="{listHeadActive:listHeadActive.payThree}"
                                  @click="clickPayThree">
                                付三成
                            </span>
                            <em></em>
                            <span class="list-head-font1 item-pointer"
                                  :class="{listHeadActive:listHeadActive.selectCar}"
                                  @click="clickSelect">
                                严选车<i></i>
                            </span>
                        </el-col>
                        <el-col :span="16">
                            <div class="list-head-right">
                                <div class="border" @click="getCarInfoBySort('default')"><span
                                        :class="{greenFont:selectedSort.default}"
                                        class="list-head-font2 item-pointer">默认排序</span></div>
                                <div :class="{greenFont:selectedSort.bestNew}"
                                     class="border" @click="getCarInfoBySort('createTime')"><span
                                        class="list-head-font2 item-pointer">最新发布</span></div>
                                <div :class="{greenFont:selectedSort.price}"
                                     class="border" @click="getCarInfoBySort('price')">
                                    <span class="list-head-font2 item-pointer">价格</span>
                                    <i v-show="sort.sortByPrice" class="el-icon-caret-top"></i>
                                    <i v-show="!sort.sortByPrice" class="el-icon-caret-bottom"></i>
                                </div>
                                <div :class="{greenFont:selectedSort.mileage}"
                                     class="border" @click="getCarInfoBySort('mileage')">
                                    <span class="list-head-font2 item-pointer">里程</span>
                                    <i v-show="sort.sortByMileage" class="el-icon-caret-top"></i>
                                    <i v-show="!sort.sortByMileage" class="el-icon-caret-bottom"></i>
                                </div>
                                <el-autocomplete
                                        class="inline-input"
                                        placeholder="热门筛选">
                                </el-autocomplete>
                            </div>
                        </el-col>
                    </el-row>
                </el-row>
                <el-divider></el-divider>
                <el-row class="cardList">
                    <el-col v-show="carList.length==0">
                        抱歉，没有找到您的爱车，请换个条件试试吧！
                    </el-col>
                    <template v-for="(car,index) in carList">
                        <el-col :span="6">
                            <el-card>
                                <div @click="clickOneCar(car)">
                                    <img class="card-image"
                                         :src="car.defaultImg">
                                    <div>
                                        <h2 class="car-dec">{{car.brandName}} {{car.seriesName}} {{car.carDesc}}</h2>
                                        <div class="car-time">
                                            {{car.buyTime}}
                                            <span>|</span>
                                            6.0万公里
                                        </div>
                                        <div class="car-price">
                                            <p>
                                                {{car.price}}.00
                                                <span>万</span>
                                            </p>
                                            <em style="color: red;" v-if="car.otherDeploy=='1'">首付{{(car.price*0.3).toFixed(2)}}万</em>
                                            <em v-else="car.otherDeploy!='1'" class="line-through">{{car.price+1.2}}万</em>
                                        </div>
                                    </div>
                                </div>
                            </el-card>
                        </el-col>
                    </template>
                    <el-col>
                        <el-row type="flex" justify="center">
                            <el-col :span="6" class="pagination">
                                <el-pagination
                                        background
                                        layout="total, prev, pager, next"
                                        :current-page="carFrom.pageIndex"
                                        :page-size="carFrom.pageSize"
                                        :total="carCountNum"
                                        @current-change="handlePageChange"
                                ></el-pagination>
                            </el-col>
                        </el-row>
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
    //import Header from '../common/Header.vue';
    import quest from '../../api/index';
    import { currentDate, currentDateStr } from '../../api/index';
    import pinyin from '../../../node_modules/js-pinyin/index';

    export default {
        data() {
            return {
                cityId:this.$store.state.cityId,
                searchInput: '',
                FristPin: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'],
                brandJson: [],
                seriesJson: [],
                tagArr: [
                    { id: 'brand', name: '' },
                    { id: 'series', name: '' },
                    { id: 'price', name: '' },
                    { id: 'type', name: '' },//车型
                    { id: 'mileage', name: '' },//里程数
                    { id: 'displacement', name: '' },//排量
                    { id: 'seatNum', name: '' },//座位数量
                    { id: 'dischargeStandard', name: '' },//排放标准
                    { id: 'oilType', name: '' },//燃油类型
                    { id: 'color', name: '' },//颜色
                    { id: 'area', name: '' },//车辆所在地
                    { id: 'driveType', name: '' },//驱动类型
                    { id: 'country', name: '' }//国别
                ],
                showTags: {
                    typeTags: true
                },
                listHeadActive: {
                    all: true,
                    payThree: false,
                    selectCar: false
                },
                showClass: {
                    active: true,
                    activeIndex: 999,//品牌不限
                    seriesActiveIndex: 999,//车系不限
                    priceActiveIndex: [{ id: 999, name: '不限', active: true }, { id: 1, name: '3万以下', active: false },
                        { id: 2, name: '3-5万', active: false }, { id: 3, name: '5-7万', active: false },
                        { id: 4, name: '7-9万', active: false }, { id: 5, name: '9-12万', active: false }, {
                            id: 6,
                            name: '12-16万',
                            active: false
                        },
                        { id: 7, name: '16-20万', active: false }, { id: 8, name: '20万以上', active: false }]//价格选项
                },
                sort: {
                    sortByPrice: true,//按照价格排序
                    sortByMileage: true//按照里程排序
                },
                selectedSort: {
                    default: true,
                    bestNew: false,
                    price: false,
                    mileage: false
                },
                input: '',
                show: {
                    brandAllShow: false,
                    seriesAllShow: false,
                    moreAllShow: false
                },
                brandList: [],//品牌
                seriesList: [],//车系
                carList: [],//车列表
                carFrom: {
                    id: '', //编号
                    brandId: '',//品牌
                    seriesId: '',//系列
                    userId: '',//车的所属人
                    price: '',//价格
                    beginPrice: '',//价格范围
                    endPrice: '',//价格范围
                    carImageList: [],//图片列表
                    defaultImg: '',//默认图片
                    carDesc: '',//描述
                    buyTime: '',//车辆买入时间
                    createTime: '',//车辆在瓜子上架时间
                    //saleState: '',//车辆销售状态，1表示已经售出，0表示没有售出
                    //isShow: '',//是否显示该记录
                    type: '',//车型
                    mileage: '',//里程数
                    displacement: '',//排量
                    seatNum: '',//座位数量
                    dischargeStandard: '',//排放标准
                    oilType: '',//燃油类型
                    color: '',//颜色
                    area: '',//车辆所在地
                    driveType: '',//驱动类型
                    country: '',//国别
                    otherDeploy: '',//其他亮点配置',
                    pageIndex: 1,
                    pageSize: 10
                },
                carTypeOption: [{ id: '0', name: '不限' },
                    { id: '2', name: '两厢轿车' }, { id: '3', name: '三厢轿车' }, { id: '4', name: '跑车' },
                    { id: '5', name: 'suv' }, { id: '6', name: 'mpv' }, { id: '7', name: '面包车' }, {
                        id: '8',
                        name: '皮卡'
                    }
                ],//车型
                mileageOption: [
                    { id: '0', name: '不限' }, { id: '2', name: '1万公里内' }, { id: '3', name: '3万公里内' },
                    { id: '4', name: '5万公里内' }, { id: '5', name: '8万公里内' }, { id: '6', name: '10万公里内' },
                    { id: '7', name: '10万公里以上' }
                ],//里程
                displacementOption: [
                    { id: '0', name: '不限' }, { id: '2', name: '1.0L以下' }, { id: '3', name: '1.0-1.6L' },
                    { id: '4', name: '1.6-2.0L' }, { id: '5', name: '2.0-3.0L' }, { id: '6', name: '3.0-4.0L' },
                    { id: '7', name: '4.0L以上' }
                ],//排放量
                carSeatNumOption: [
                    { id: '2', seatNum: '2' }, { id: '3', seatNum: '4' }, { id: '4', seatNum: '5' },
                    { id: '5', seatNum: '6' }, { id: '6', seatNum: '7' }
                ],//座位数量
                dischargeStandardOption: [{ id: '1', dischargeStandard: '国四' },
                    { id: '2', dischargeStandard: '国五' }, { id: '3', dischargeStandard: '国六' }],//排放标准
                oilTypeOption: [{ id: '1', oilType: '汽油' }, { id: '2', oilType: '柴油' }, { id: '3', oilType: '电动' },
                    { id: '4', oilType: '油电混合' }],//燃油类型
                colorOption: [{ id: '1', color: '白色' }, { id: '2', color: '深灰色' }, { id: '3', color: '黑色' }, {
                    id: '4',
                    color: '红色'
                }],//颜色
                areaOption: {},//车牌所在地
                driveTypeOption: [{ id: '1', drive: '两驱' }, { id: '2', drive: '四驱' }],//驱动类别
                countryOption: [{ id: '1', country: '德系' }, { id: '2', country: '日系' }, {
                    id: '3', country: '美系'
                }, { id: '4', country: '法系' }, { id: '5', country: '韩系' }, { id: '6', country: '国产' }]//国别
            };
        },
        watch: {
            carFrom: {
                handler(newName, oldName) {
                    this.getCarInfo(newName);
                },
                deep: true,
                immediate: true
            },
            cityId:{
                handler(newName,oldName){
                    this.carFrom.area=newName
                }
            }
        },
        computed: {
            //计算属性 总条数
            carCountNum() {
                return this.carList.length;
            }
        },
        components: {
            //Header,
            Footer
        },
        mounted() {
            this.carFrom.area = this.$store.state.cityId;
            this.loadData();
            switch (this.$route.query.id) {
                case 1:
                    this.clickPrice(1,1)
                    break;
                case 2:
                    this.clickPrice(2,2)
                    break;
                case 3:
                    this.clickPrice(3,3)
                    break;
                case 4:
                    this.clickPrice(3,3)
                    break;
            }
        },
        methods: {
            loadData() {
                this.getBrandList();
                this.getSeriesList('');
                this.getCarInfo(this.carFrom);
            },
            getCarInfo(carForm) {
                quest.getCarInfoList(carForm).then(response => {
                    this.carList = [];
                    this.carList = response.data.map;
                });
            },
            getCarInfoBySort(name) {
                if (name == 'default') {
                    let selected = this.selectedSort;
                    selected.default = true;
                    selected.bestNew = false;
                    selected.price = false;
                    selected.mileage = false;
                    this.getCarInfo(this.carFrom);
                }
                if (name == 'createTime') {
                    let selected = this.selectedSort;
                    selected.default = false;
                    selected.bestNew = true;
                    selected.price = false;
                    selected.mileage = false;
                    this.carList.sort((a, b) => {
                        var x = a.createTime;
                        var y = b.createTime;
                        return ((x > y) ? -1 : (x < y) ? 1 : 0);
                    });
                    //_this.createTime = 'true';//车辆在瓜子上架时间
                }
                if (name == 'price') {
                    let selected = this.selectedSort;
                    selected.default = false;
                    selected.bestNew = false;
                    selected.price = true;
                    selected.mileage = false;
                    this.sort.sortByPrice == true ? this.sort.sortByPrice = false : this.sort.sortByPrice = true;
                    if (this.sort.sortByPrice == true) {//升序
                        this.carList.sort((a, b) => {
                            var x = a.price;
                            var y = b.price;
                            return ((x < y) ? -1 : (x > y) ? 1 : 0);
                        });
                    }
                    if (this.sort.sortByPrice == false) {//降序
                        this.carList.sort((a, b) => {
                            var x = a.price;
                            var y = b.price;
                            return ((x > y) ? -1 : (x < y) ? 1 : 0);
                        });
                    }
                }
                if (name == 'mileage') {
                    let selected = this.selectedSort;
                    selected.default = false;
                    selected.bestNew = false;
                    selected.price = false;
                    selected.mileage = true;
                    this.sort.sortByMileage == true ? this.sort.sortByMileage = false : this.sort.sortByMileage = true;
                    if (this.sort.sortByMileage == true) {//升序
                        this.carList.sort((a, b) => {
                            var x = a.mileage;
                            var y = b.mileage;
                            return ((x < y) ? -1 : (x > y) ? 1 : 0);
                        });
                    }
                    if (this.sort.sortByMileage == false) {//降序
                        this.carList.sort((a, b) => {
                            var x = a.mileage;
                            var y = b.mileage;
                            return ((x > y) ? -1 : (x < y) ? 1 : 0);
                        });
                    }
                }
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
                    var deleData = brandJson.filter((value) => {
                        return value.data.length == 0;
                    });//过滤出没有该字母开头的
                    for (let i = 0; i < deleData.length; i++) {
                        let index = brandJson.indexOf(deleData[i]);
                        brandJson.splice(index, 1);
                    }//删除在brandJson中过滤处理的delData
                    this.brandJson = brandJson;
                });
            },
            getSeriesList(brandId) {
                //先拿到全部的名字
                let brandArr = [];
                quest.getSeriesList(brandId).then(response => {
                    let pinyin = require('js-pinyin');
                    pinyin.setOptions({ checkPolyphone: false, charCase: 0 });
                    this.seriesList = response.data.map;
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
                    this.seriesJson = brandJson;
                    var deleData = brandJson.filter((value) => {
                        return value.data.length == 0;
                    });//过滤出没有该字母开头的
                    for (let i = 0; i < deleData.length; i++) {
                        var index = brandJson.indexOf(deleData[i]);
                        brandJson.splice(index, 1);
                    }
                });
            },
            querySearchAsync(queryString, cb) {
                var results = [];
                results = queryString ? this.createStateFilter(queryString) : this.carList;
                let cbResult = [];
                for (let i = 0; i < results.length; i++) {
                    cbResult.push({ 'value': results[i].seriesName });
                }
                cb(cbResult);
            },
            createStateFilter(queryString) {
                return this.carList.filter((value) => {
                    return value.seriesName == queryString;
                });
            },
            searchSelect(item) {
                let seriesName = this.carList.filter((value) => {
                    return value.seriesName = item.value;
                });
                this.carFrom.seriesId = seriesName[0];
                this.getCarInfo(this.carFrom);
            },
            clickBrand(id) {
                this.carFrom.seriesId = '';//清空条件
                this.showClass.seriesActiveIndex = 999;//车系回到不限
                this.showClass.activeIndex = id;
                if (id == 999) {//不限
                    this.tagArr[0].name = '';//重置标签
                    this.tagArr[1].name = '';//重置标签
                    this.tagArr[2].name = '';//重置标签
                    this.getSeriesList('');
                    this.carFrom.brandId = '';
                    //this.getCarInfo(this.carFrom);
                } else {
                    let arr = this.brandList.filter((value) => {
                        return value.id == id;
                    });
                    this.tagArr[0].name = arr[0].name;//保存标签
                    this.showClass.activeIndex = id;
                    this.getSeriesList(id);
                    this.carFrom.brandId = id;
                    //this.getCarInfo(this.carFrom);
                }
            },
            clickSeries(id) {
                this.carFrom.seriesId = id;
                //this.getCarInfo(this.carFrom);
                if (id == 999) {//不限
                    this.tagArr[1].name = '';//重置标签
                    this.showClass.seriesActiveIndex = 999;
                } else {
                    let arr = this.seriesList.filter((value) => {
                        return value.id == id;
                    });
                    this.tagArr[1].name = arr[0].name;//保存标签
                    this.showClass.seriesActiveIndex = id;
                }
            },
            clickPrice(index, id) {
                this.showClass.priceActiveIndex.forEach((item, index, arr) => {
                    item.active = false;
                });
                this.carFrom.price = this.showClass.priceActiveIndex[index].id;
                //this.getCarInfo(this.carFrom);
                this.showClass.priceActiveIndex[index].active = true;//点击价格
                if (id == 999) {//不限
                    this.tagArr[2].name = '';//如果是不限就关闭tag
                } else {
                    this.tagArr[2].name = this.showClass.priceActiveIndex[index].name;//保存标签
                    this.carFrom.beginPrice = '';//清空价格输入框
                    this.carFrom.endPrice = '';//清空价格输入框
                }


            },
            confirmPrice() {
                if (this.carFrom.beginPrice != '' && this.carFrom.endPrice != '') {
                    this.carFrom.price = '';
                    let priceArr = this.showClass.priceActiveIndex;
                    priceArr.forEach((item, index, arr) => {
                        item.active = false;
                    });
                    this.tagArr[2].name = this.carFrom.beginPrice + '-' + this.carFrom.endPrice;//更换tag
                    //this.getCarInfo(this.carFrom);
                } else {
                    alert('输入为空！');
                }

            },
            handleClose(index, id) {
                this.tagArr[index].name = '';
                if (id == 'brand') {//关闭品牌标签
                    this.getSeriesList('');
                    this.carFrom.seriesId = '';
                    this.carFrom.brandId = '';
                    //this.getCarInfo(this.carFrom);
                    this.showClass.activeIndex = 999;//品牌回到不限
                    this.showClass.seriesActiveIndex = 999;//车系也跟随品牌回到不限
                    this.tagArr[index + 1].name = '';//同时关闭车系标签
                }
                if (id == 'series') {////关闭车系标签
                    this.carFrom.seriesId = 999;
                    //this.getCarInfo(this.carFrom);
                    this.showClass.seriesActiveIndex = 999;//车系回到不限
                }
                if (id == 'price') {////关闭车系标签
                    this.carFrom.price = 999;
                    //this.getCarInfo(this.carFrom);
                    this.showClass.priceActiveIndex.forEach((item, index, arr) => {
                        item.active = false;
                        if (index == 0) {
                            item.active = true; //价格回到不限
                        }
                    });
                    this.carFrom.beginPrice = '';
                    this.carFrom.endPrice = '';//关闭用户输入的价格
                }
            },
            clickBrandAll() {
                this.show.brandAllShow == false ? this.show.brandAllShow = true : this.show.brandAllShow = false;
            },
            clickSeriesAll() {
                this.show.seriesAllShow == false ? this.show.seriesAllShow = true : this.show.seriesAllShow = false;
            },
            clickMoreAll() {
                this.show.moreAllShow == false ? this.show.moreAllShow = true : this.show.moreAllShow = false;
            },
            // 分页导航
            handlePageChange(val) {
                this.carFrom.pageIndex = val;
            },
            //全部
            clickAll() {
                this.getCarInfo(this.carFrom);
                this.listHeadActive = {
                    all: true,
                    payThree: false,
                    selectCar: false
                };
            },
            //付三成
            clickPayThree() {
                this.listHeadActive = {
                    all: false,
                    payThree: true,
                    selectCar: false
                };
                this.carList = this.carList.filter((value) => {
                    return value.otherDeploy == '1'
                })
            },
            //严选车
            clickSelect() {
                this.listHeadActive = {
                    all: false,
                    payThree: false,
                    selectCar: true
                };
            },
            //浏览记录
            clickOneCar(car){
                let record = this.$cookies.get("carRecord");
                if(record != null){
                    var records = record.split('-');
                    let id=records.filter(value => {
                        return value == car.id
                    })
                    if (id.length == 0){
                        record = record+'-'+car.id
                        this.$cookies.remove("carRecord")
                        this.$cookies.set("carRecord",record,-1)
                    }
                }else {
                    this.$cookies.set("carRecord",car.id,-1)
                }
                let routeUrl = this.$router.resolve({
                    path: "/carDetail",
                    query:car
                });
                window.open(routeUrl .href, '_blank');
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
        height: 100%;
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
        height: 20px;
        margin-top: 15px;
        margin-left: 30px;
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

    .input-price {
        margin-left: 20px;
    }

    .price-line {
        margin-top: 13px !important;
    }

    .input-price div {
        margin-top: 5px;
        float: left;
    }

    .price input {
        width: 50px;
        height: 30px !important;
        float: left;
    }

    .price button {
        width: 55px;
        height: 30px !important;
        margin-top: 5px;
        margin-left: 10px;
        float: left;
    }

    .drop-head {
        height: 220px;
    }

    .price span {
        cursor: pointer;
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
        width: 50%;
        float: left;
    }

    .item-for {
        height: auto;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
    }

    .greenFont {
        color: #22AC38;
        font-weight: bolder;
    }

    .hoverStyle:hover {
        color: #22AC38;
    }

    .select-style {
        width: 120px;
        margin-top: 12px;
        margin-left: 20px;
    }

    .select-style2 {
        width: 120px;
        margin-top: 12px;
        margin-left: 30px;
    }

    .selectActive {
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

    .listHeadActive {
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
        margin-left: 325px;
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
        cursor: pointer;
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
