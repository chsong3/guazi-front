import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';

Vue.use(ElementUI, {
    size: 'small'
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
