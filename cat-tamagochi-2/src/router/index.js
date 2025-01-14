import { createRouter, createWebHistory } from "vue-router";
import ProfilePage from "../components/ProfilePage.vue";
import Friends from "../components/Friends.vue";
import Greeting from "@/components/Greeting.vue"
import Kitty from "@/components/Kitty.vue";

const routes = [
  { path: "/", component: Greeting },
  { path: "/profile", component: ProfilePage },
  { path: "/cat", component: Kitty },
  { path: "/friends", component: Friends },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;