import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import request from "@/utils/request";
Vue.config.productionTip = false

// main.js全局注册
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)

Vue.use(ElementUI,{size:"mini"});

Vue.prototype.request=request //设置axios的全局request

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
