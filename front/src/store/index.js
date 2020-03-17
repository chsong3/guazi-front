import Vue from 'vue'
import Vuex from 'vuex'

//使用vuex
Vue.use(Vuex);

const store = new Vuex.Store({
    state:{
        user:'',
        cityId:''
    },

    mutations:{
        setUser(state,user){
            state.user = user
        }
    }

});

export default store;
