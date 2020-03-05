import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
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
            path: '/carDetail',
            component: () => import(/* webpackChunkName: "index" */ '../components/page/CarInfo.vue'),
            meta: { title: '车辆详情' },
        },
        {
            path: '/test',
            component: () => import(/* webpackChunkName: "index" */ '../components/page/test.vue'),
            meta: { title: '测试' },
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
