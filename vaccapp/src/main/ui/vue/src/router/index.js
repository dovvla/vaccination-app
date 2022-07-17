import Vue from "vue";
import VueRouter from "vue-router";
import UnregisteredPage from "../views/UnregisteredPage";
import Login from "../views/Login";
import Logout from "../views/Logout";
import Register from "../components/Register";
import Interesovanje from "../components/Interesovanje";
import Saglasnost from "../components/Saglasnost";
import ZahtevZaSertifikat from "../components/ZahtevZaSertifikat";
import MojiDokumenti from "../components/MojiDokumenti";

Vue.use(VueRouter);

const Role = {
  Gradjanin: "???",
  Radnik: "???",
  Sluzbenik: "???",
};

const routes = [
  {
    path: "/",
    name: UnregisteredPage,
    component: UnregisteredPage,
  },
  {
    path: "/Login",
    name: "Login",
    component: Login,
  },
  {
    path: "/Logout",
    name: "Logout",
    component: Logout,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/interesovanje",
    name: "Interesovanje",
    component: Interesovanje,
  },
  {
    path: "/saglasnost",
    name: "Saglasnost",
    component: Saglasnost,
  },
  {
    path: "/zahtev",
    name: "ZahtevZaSertifikat",
    component: ZahtevZaSertifikat,
  },
  {
    path: "/moji-dokumenti",
    name: "MojiDokumenti",
    component: MojiDokumenti,
  },
  // {
  // 	path: "/AdminPage",
  // 	name: "AdminPage",
  // 	component: AdminPage,
  // 	children: [
  // 		{
  // 			path: "CreateCSR",
  // 			name: "CreateCSRAdmin",
  // 			component: CreateCSR,
  // 			meta: {
  // 				roles: [Role.Admin]
  // 			},
  // 		},
  // 	],
  // 	meta: {
  // 		roles: [Role.Admin]
  // 	},
  // },
  {
    path: "*",
    redirect: "/Login",
  },
];

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
    ).role;
    if (roles.length && !roles.includes(userRole)) {
      return next({ path: "Login" });
    }
  }
  next();
});
