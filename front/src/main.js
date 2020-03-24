import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/common.css'
import SlideVerify from './components/lib/index';
import store from './store/index'
import pinyin from 'js-pinyin'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)

Vue.use(ElementUI, {
    size: 'small'
});
Vue.use(SlideVerify)
router.beforeEach((to, from, next) => {
    if(to.path === '/login') {
        next();
    } else {
        let token = localStorage.getItem('token');
        if(token === null || token === '') {
            next();
        }else {
            next();
        }
    }
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
