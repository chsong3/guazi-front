<template>
    <div>
        <div class="footer">
            <div class="company-info">
                <!--加载footer 信息部分 -->
                <div class="footer-logo"></div>
                <div class="phone-email">
                    <p>
                        咨询电话：<i>400-069-7632</i><span>（周一至周日 8:00-21:00）</span>
                        <br> 服务监督热线：
                        <i>400-066-8780</i><span>（周一至周日 9:00-18:00）</span>
                    </p>
                    <p>互联网违法或不良信息举报联系方式：
                        <br> 邮箱：
                        <i>jubao@guazi.com</i>&nbsp;&nbsp;电话：<i>010-89191670</i>
                    </p>
                </div>
                <div class="ewm-box">
                    <img src="../../assets/logo.png" alt="" class="gz-ewm">
                    <p class="ewm-tit">关注微信</p>
                </div>
                <div class="ewm-box">
                    <span class="bg-old-ewm"></span>
                    <p class="ewm-tit">下载APP</p>
                    <span class="ewm-tit-tip">瓜分十亿红包</span>
                </div>
                <div class="basic-info">
                    <div class="info-link">
                        <a rel="nofollow" href="https://www.guazi.com/cq/aboutus/" target="_blank">关于瓜子</a>
                        <a rel="nofollow" href="https://www.guazi.com/cq/aboutusGroup/" target="_blank">集团介绍</a>
                        <a rel="nofollow" href="https://job.guazi.com" target="_blank">加入我们</a>
                        <a rel="nofollow" href="https://www.guazi.com/cq/contact/" target="_blank">联系我们</a>
                        <a rel="nofollow" href="https://www.guazi.com/cq/intro/" target="_blank">服务保障</a>
                        <a rel="nofollow" href="https://image.guazistatic.com/gz01191219/14/22/29a34d8443b4a998e9b96592797f27d5.pdf" target="_blank">隐私声明</a>
                        <a rel="nofollow" href="https://image.guazistatic.com/gz01190926/17/21/21c7f57e0f6b77e4b7dd3b608e44d04f.pdf" target="_blank">服务条款</a>
                        <a rel="nofollow" href="https://image1.guazistatic.com/qn190823150944d18750f828b8e68b3f1b3c7288569ed3.jpeg" target="_blank">营业执照</a>
                        <a rel="nofollow" href="https://www.guazi.com/cq/intro/terms" target="_blank">环评公示</a>
                    </div>
                    <p class="arc-info">Copyright 2019 www.guazi.com All Rights Reserved</p>
                    <!--        <p class="arc-info">京ICP备15053955号  ICP证151071号 </p>-->
                    <a class="arc-info" rel="nofollow" target="_blank" href="http://www.beian.miit.gov.cn/publish/query/indexFirst.action " style="color: #939393">京ICP备15053955号  ICP证151071号 </a>
                    <div class="protect clearfix info-link">
                        <a class="police" rel="nofollow" target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010502037713">京公网安备11010502037713号</a>
                    </div>
                    <!--        <div class="protect protect-icon-xin315 clearfix">-->
                    <!--            <a id='___szfw_logo___' href="https://credit.szfw.org/CX20170627038575010339.html" class="icon-xin" target="_blank"></a>-->
                    <!--            <a id='___szfw_logo___1' href="https://credit.szfw.org/CX20170627038575571073.html" class="icon-315" target="_blank"></a>-->
                    <!--        </div>-->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {};
</script>


<style>
    .footer {
        width: 100%;
        padding: 50px 0 30px;
        background: #2a2c37;
        margin-top: 30px;
    }
    .footer-logo {
        width: 102px;
        height: 102px;
        background-repeat: no-repeat;
        background-image: url(../../assets/logo.png);
        background-size: 100% 100%;
        float: left;
        margin-left: 40px;
        _margin-left: 20px;
    }
    .phone-email {
        float: left;
        width: 378px;
        padding-left: 30px;
    }
    .phone-email p{
        font-size: 14px;
        margin-top: 10px;
    }
    .company-info {
        width: 1190px;
        height: 90px;
        margin: 0 auto;
        color: #b8b8b8;
        padding-bottom: 70px;
        position: relative;
    }
    .ewm-box .ewm-tit {
        line-height: 22px;
        font-size: 14px;
    }
    .ewm-box {
        float: left;
        width: 85px;
        height: 105px;
        margin-top: 4px;
        margin-right: 30px;
        text-align: center;
        display: block;
    }
    .bg-old-ewm {
        width: 102px;
        height: 102px;
        background-repeat: no-repeat;
        background-image: url(../../assets/logo.png);
        background-size: 100% 100%;
        float: left;
        _margin-left: 20px;
    }
    .bg-old-ewm, .ewm-box img {
         display: block;
         width: 85px;
         height: 85px;
     }
    .ewm-box .ewm-tit-tip {
        width: 80px;
        height: 16px;
        background: #22ac38;
        font-family: PingFangSC-Regular;
        font-size: 10px;
        color: #fff;
        display: inline-block;
        line-height: 16px;
    }
    .company-info .basic-info {
        float: left;
        width: 390px;
        padding-left: 20px;
        background: transparent;
        margin: 0;
        overflow: hidden;
    }
    .info-link {
        line-height: 22px;
        font-size: 14px;
        padding-bottom: 8px;
    }
    .info-link a {
        color: #b8b8b8;
        margin-right: 5px;
        text-decoration:none;
    }
    .arc-info {
        line-height: 20px;
        font-family: simhei;
        font-size: 12px;
    }
    .protect {
        padding-top: 3px;
        height: 28px;
        line-height: 28px;
    }
    .clearfix:after {
        content: " ";
        display: block;
        clear: both;
        height: 0;
        visibility: hidden;
    }
    .friendly-link {
        width: 1100px;
        padding: 0 0 0 90px;
        position: relative;
        overflow: hidden;
    }
     .friendly-link, .instructions-guazi-name {
         height: 28px;
         line-height: 28px;
         margin: 0 auto;
     }
</style>
