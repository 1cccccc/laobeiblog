<template>
  <el-scrollbar style="height: 100vh">
    <TopNavBar />
    <!-- <transition name="fade"> -->
    <router-view></router-view>
    <!-- </transition> -->
    <Search />
    <Login />
    <Register />
    <ForgetPassword />
    <ForgetPasswordSteptwo />
    <Footer></Footer>
  </el-scrollbar>
</template>

<script setup>
import TopNavBar from "./components/TopNavBar.vue";
import Search from "./components/Search.vue";
import Login from "./components/Login.vue";
import Register from "./components/Register.vue";
import ForgetPassword from "./components/ForgetPassword.vue";
import ForgetPasswordSteptwo from "./components/ForgetPasswordSteptwo.vue";
import Footer from "./components/Footer.vue";
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
  transition: all 0.5s ease;
}
.fade-enter-form,
.fade-leave-to {
  transform: translateY(-100px);
}
.fade-enter-to,
.fade-leave-form {
  transform: translateY(0px);
}
</style>
