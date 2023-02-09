import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";
import Articles from "../views/Articles.vue";
import Archives from "../views/Archives.vue";
import PersonnalCloud from "../views/PersonnalCloud.vue";
import Tags from "../views/Tags.vue";
import ArchiveManage from "../views/ArchiveManage.vue";
import ReleaseArchive from "../views/ReleaseArchive.vue";
import UserManage from "../views/UserManage.vue";
import TagsInfo from "../views/TagsInfo.vue";
import Writing from "../views/Writing.vue";
import User from "../views/User.vue";
import Category from "../views/Category.vue";
import Explain from "../views/Explain.vue";

const routes = [
  { path: "/", component: Home ,meta: {title: "首页"}},
  {
    path: "/articles/:articleId",
    component: Articles,
    meta: { title: "文章详情" },
  },
  { path: "/archives", component: Archives, meta: { title: "归档" } },
  { path: "/personnalcloud", component: PersonnalCloud, meta: { title: "个人云" } },
  { path: "/tags", component: Tags, meta: { title: "标签" } },
  { path: "/archivemanage", component: ArchiveManage, meta: { title: "文章管理" } },
  { path: "/releasearchive", component: ReleaseArchive, meta: { title: "发布文章" } },
  { path: "/usermanage", component: UserManage, meta: { title: "用户管理" } },
  { path: "/tags/:id", component: TagsInfo, meta: { title: "标签详情" } },
  { path: "/writing", component: Writing, meta: { title: "创作" } },
  { path: "/user", component: User, meta: { title: "个人中心" } },
  { path: "/category", component: Category, meta: { title: "分类" } },
  { path: "/explain", component: Explain, meta: { title: "使用说明" } },
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
