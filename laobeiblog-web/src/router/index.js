import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";
import Articles from "../views/Articles.vue";
import Archives from "../views/Archives.vue";
import Albums from "../views/Albums.vue";
import AlbumsInfo from "../views/AlbumsInfo.vue";
import Tags from "../views/Tags.vue";
import Links from "../views/Links.vue";
import About from "../views/About.vue";
import Message from "../views/Message.vue";
import User from "../views/User.vue";
import Category from "../views/Category.vue";
import CategoryInfo from "../views/CategoryInfo.vue";
import Explain from "../views/Explain.vue";

const routes = [
  { path: "/", component: Home ,meta: {title: "首页"}},
  {
    path: "/articles/:articleId",
    component: Articles,
    meta: { title: "文章详情" },
  },
  { path: "/archives", component: Archives, meta: { title: "归档" } },
  { path: "/albums", component: Albums, meta: { title: "相册" } },
  {
    path: "/albums/:albumId",
    component: AlbumsInfo,
    meta: { title: "图片详情" },
  },
  { path: "/tags", component: Tags, meta: { title: "标签" } },
  { path: "/links", component: Links, meta: { title: "友链" } },
  { path: "/about", component: About, meta: { title: "关于" } },
  { path: "/message", component: Message, meta: { title: "留言" } },
  { path: "/user", component: User, meta: { title: "个人中心" } },
  { path: "/category", component: Category, meta: { title: "分类" } },
  { path: "/explain", component: Explain, meta: { title: "使用说明" } },
  {
    path: "/category/:categoryId",
    component: CategoryInfo,
    meta: { title: "分类详情" },
  },
];

// 3. 创建路由实例并传递 `routes` 配置
// 你可以在这里输入更多的配置，但我们在这里
// 暂时保持简单
const router = createRouter({
  // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  history: createWebHashHistory(),
  routes, // `routes: routes` 的缩写
});

export default router;
