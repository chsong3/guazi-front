import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path:'/',
            //redirect: '/index'
            component: () => import(/* webpackChunkName: "index" */ '../components/common/Header.vue'),
            children:[
                {
                    path: '/index',
                    component: () => import(/* webpackChunkName: "index" */ '../components/page/Index.vue'),
                    meta: { title: '首页' },
                },
                {
                    path: '/buyCar',
                    component: () => import(/* webpackChunkName: "index" */ '../components/page/BuyCar.vue'),
                    meta: { title: '我要买车' },
                },
                {
                    path: '/sellCar',
                    component: () => import(/* webpackChunkName: "index" */ '../components/page/SellCar.vue'),
                    meta: { title: '我要卖车' },
                },
                {
                    path: '/carDetail',
                    component: () => import(/* webpackChunkName: "index" */ '../components/page/CarInfo.vue'),
                    meta: { title: '车辆详情' },
                },
                {
                    path: '/finance',
                    component: () => import(/* webpackChunkName: "index" */ '../components/page/Finance.vue'),
                    meta: { title: '瓜子金融' },
                },
            ]
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
