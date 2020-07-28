import Vue from 'vue'
import VueRouter from 'vue-router'

/**
 * 一级路由
 */
import Login from '../views/login.vue'
import Chat from '../views/chat.vue'

Vue.use(VueRouter)

  const routes = [
	{
		name:'login',
		path:'/login',
		component:Login
	},
	{
		name:'chat',
		path:'/chat',
		component:Chat
	}
  ]

const router = new VueRouter({
  mode: 'history',
  base: '/views/',
  routes
})

export default router
