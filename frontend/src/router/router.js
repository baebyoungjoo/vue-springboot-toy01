import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home.vue'
/* */
import BoardList from '@/components/BoardList.vue'
import PostBoard from '@/components/PostBoard.vue'
import SearchBoard from '@/components/SearchBoard.vue'
import Boards from '@/components/Boards.vue'
/* */
import Login from '@/components/Login.vue'
import JoinTerms from '@/components/joins/JoinTerms.vue'
import SignUp from '@/components/joins/SignUp.vue'

Vue.use(Router)

export default new Router({
  // url/# 안붙게 - https://kimyhcj.tistory.com/239?category=537759
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/list',
      name: 'boards',
      alias: '/boards',
      component: BoardList
    },
    {
      path: '/board/:id',
      name: 'board-details',
      component: Boards,
      props: true
    },
    {
      path: '/add',
      name: 'postBoard',
      component: PostBoard
    },
    {
      path: '/search',
      name: 'search',
      component: SearchBoard
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/join/joinTerms',
      name: 'join-terms',
      component: JoinTerms
    },
    {
      path: '/join/signUp',
      name: 'sign-up',
      component: SignUp
    }
  ]
})
