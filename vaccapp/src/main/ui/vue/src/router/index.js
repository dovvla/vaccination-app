import Vue from 'vue'
import VueRouter from 'vue-router'
import UnregisteredPage from '../views/UnregisteredPage'
import Login from '../views/Login'
import Logout from '../views/Logout'
import SluzbenikPage from '../views/SluzbenikPage'
import Izvestaj from '../components/Izvestaj'
import BrojVakcinaUpdate from '../components/BrojVakcinaUpdate'

Vue.use(VueRouter)

const Role = {
	Gradjanin: '???',
	Radnik: '???',
	Sluzbenik: '???'
}

const routes = [
	{
		path: "/",
		name: UnregisteredPage,
		component: UnregisteredPage
	},
	{
		path: "/Login",
		name: "Login",
		component: Login
	},
	{
		path: "/Logout",
		name: "Logout",
		component: Logout
	},
	{
		path: "/SluzbenikPage",
		name: "SluzbenikPage",
		component: SluzbenikPage,
		children: [
			{
				path: "Izvestaj",
				name: "Izvestaj",
				component: Izvestaj,
				// meta: {
				// 	roles: [Role.Sluzbenik]
				// },
			},
			{
				path: "BrojVakcinaUpdate",
				name: "BrojVakcinaUpdate",
				component: BrojVakcinaUpdate,
				// meta: {
				// 	roles: [Role.Sluzbenik]
				// },
			},
		],
		// meta: {
		// 	roles: [Role.Sluzbenik]
		// },
	},
	{
		path: '*',
		redirect: "/Login"
	}

]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

export default router
router.beforeEach((to, from, next) => {
	const { roles } = to.meta;
	if (roles) {
		const userRole = JSON.parse(atob(sessionStorage.getItem('token').split('.')[1])).role;
		if(roles.length && !roles.includes(userRole)){
			return next({path: 'Login'});
		}
	}
	next();
});
