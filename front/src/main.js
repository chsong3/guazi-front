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

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
