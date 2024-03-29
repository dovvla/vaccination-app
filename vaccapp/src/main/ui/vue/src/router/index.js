import Vue from 'vue'
import VueRouter from 'vue-router'
import UnregisteredPage from '../views/UnregisteredPage'
import Login from '../views/Login'
import Logout from '../views/Logout'
import SluzbenikPage from '../views/SluzbenikPage'
import Izvestaj from '../components/Izvestaj'
import BrojVakcinaUpdate from '../components/BrojVakcinaUpdate'
import Search from '../components/Search'
import GradjaninPage from "../views/GradjaninPage";
import Register from "../components/Register";
import Interesovanje from "../components/Interesovanje";
import Saglasnost from "../components/Saglasnost";
import ZahtevZaSertifikat from "../components/ZahtevZaSertifikat";
import MojiDokumenti from "../components/MojiDokumenti";
import SluzbenikHome from "../components/SluzbenikHome";
import MedicinskiRadnikHome from "../components/MedicinskiRadnikHome";

Vue.use(VueRouter);

const Role = {
	Gradjanin: "Gradjanin",
	Radnik: "Zdravstveni_radnik",
	Sluzbenik: "Sluzbenik",
};

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
			{
				path: "Search",
				name: "Search",
				component: Search,
				// meta: {
				// 	roles: [Role.Sluzbenik]
				// },
			},
			{
				path: "sluzbenik-home",
				name: "SluzbenikHome",
				component: SluzbenikHome,
			},
		],
		// meta: {
		// 	roles: [Role.Sluzbenik]
		// },
	},
	{
		path: "/GradjaninPage",
		name: GradjaninPage,
		component: GradjaninPage,
		children: [
			{
				path: "interesovanje",
				name: "Interesovanje",
				component: Interesovanje,
			},
			{
				path: "saglasnost",
				name: "Saglasnost",
				component: Saglasnost,
			},
			{
				path: "zahtev",
				name: "ZahtevZaSertifikat",
				component: ZahtevZaSertifikat,
			},
			{
				path: "moji-dokumenti",
				name: "MojiDokumenti",
				component: MojiDokumenti,
			},
		],
	},
	{
		path: "/register",
		name: "Register",
		component: Register,
	},
	{
		path: "/medicinski-radnik-home",
		name: "MedicinskiRadnikHome",
		component: MedicinskiRadnikHome,
	},
	{
		path: '*',
		redirect: "/Login"
	}

]

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

export default router;
router.beforeEach((to, from, next) => {
	const { roles } = to.meta;
	if (roles) {
		const userRole = JSON.parse(
			atob(sessionStorage.getItem("token").split(".")[1])
		).role[0].authority;
		if (roles.length && !roles.includes(userRole)) {
			return next({ path: "Login" });
		}
	}
	next();
});
