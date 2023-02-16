<template>
  <el-scrollbar style="height: 100vh" ref="scrollbar" @scroll="scroll">
    <TopNavBar /><!-- 头部导航栏 -->
    <transition name="fade">
      <router-view> </router-view>
    </transition>

    <Search /><!-- 搜索框 -->
    <Login /><!--登录框 -->
    <Register /><!-- 注册框 -->
    <ForgetPassword /><!-- 忘记密码框 -->
    <ForgetPasswordSteptwo /><!-- 忘记密码下一步框 -->
    <CategoryRelation /><!-- 分类关联文章信息框 -->
    <Footer></Footer
    ><!-- 页脚 -->
  </el-scrollbar>
</template>

<script setup>
import { ref, watch } from "vue";
import { useMainStore } from "@/store/index";

import TopNavBar from "./components/TopNavBar.vue";
import Search from "./components/Search.vue";
import Login from "./components/Login.vue";
import Register from "./components/Register.vue";
import ForgetPassword from "./components/ForgetPassword.vue";
import ForgetPasswordSteptwo from "./components/ForgetPasswordSteptwo.vue";
import CategoryRelation from "./components/CategoryRelation.vue";
import Footer from "./components/Footer.vue";

const store = useMainStore();

let scrollbar = ref(null);
let top = 0;
watch(
  () => store.scrollDown,
  (newV, oldV) => {
    if (newV) {
      let speed;
      let h = document.documentElement.offsetHeight;
      let time = setInterval(() => {
        speed = Math.ceil((h - top) / 10);
        scrollbar.value.setScrollTop(top + speed);

        if(h-top<10){
          clearInterval(time)
          store.scrollDown = false;
          scrollbar.value.setScrollTop(h);
        }
      }, 20);
    }
  }
  );
  const scroll = (scrollTop) => {
    top = scrollTop.scrollTop;
    store.scrolltop=scrollTop.scrollTop;
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
#app {
  width: 100vw;
  display: block !important;
  box-sizing: content-box;
  max-width: 100vw !important;
  padding: 0 !important;
  margin: 0 !important;
  overflow-x: hidden;
  user-select: none;
}

body {
  width: 100% !important;
  display: block !important;
}
a {
  text-decoration: none;
}
a:visited {
  color: #666;
}
.router-link-active {
  text-decoration: none;
}

.el-scrollbar__wrap {
  overflow-x: hidden;
}

.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

.fade-enter-active,
.fade-leave-active {
  transition: all 1s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-2em);
}
</style>
