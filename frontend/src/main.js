// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/router.js'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import VueNoty from 'vuejs-noty'

require('swiper/dist/css/swiper.css')
require('vuejs-noty/dist/vuejs-noty.css')

Vue.use(VueAwesomeSwiper)
Vue.use(VueNoty)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
