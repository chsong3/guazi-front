import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/common.css'

import pinyin from 'js-pinyin'

Vue.use(ElementUI, {
    size: 'small'
});

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
    render: h => h(App)
}).$mount('#app');
