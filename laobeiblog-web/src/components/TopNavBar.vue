<template>
  <el-row id="TopNavBar" justify="space-between" ref="TopNavBar">
    <el-col :span="3">
      <router-link to="/">
        <h2 id="Title">捞杯</h2>
      </router-link>
    </el-col>
    <el-col :span="12">
      <ul id="Navigation">
        <el-row justify="space-between">
          <el-col :span="2">
            <li @click="Search()">
              <svg class="icon icon-Magnifier" aria-hidden="true">
                <use xlink:href="#icon-Magnifier"></use>
              </svg>
              <p class="content">搜索</p>
            </li>
          </el-col>

          <el-col :span="2">
            <router-link to="/">
              <li>
                <svg class="icon icon-shouye" aria-hidden="true">
                  <use xlink:href="#icon-shouye"></use>
                </svg>
                <p class="content">首页</p>
              </li>
            </router-link>
          </el-col>

          <el-col :span="2">
            <el-dropdown size="large" :hide-on-click="false">
              <li>
                <svg class="icon icon-gongneng" aria-hidden="true">
                  <use xlink:href="#icon-gongneng"></use>
                </svg>
                <p class="content">
                  功能
                  <el-icon :size="18">
                    <CaretBottom />
                  </el-icon>
                </p>
              </li>
              <template #dropdown>
                <el-dropdown-menu>
                  <router-link to="/archives">
                    <el-dropdown-item>归档</el-dropdown-item>
                  </router-link>
                  <router-link to="/category">
                    <el-dropdown-item>分类</el-dropdown-item>
                  </router-link>
                  <router-link to="/tags">
                    <el-dropdown-item>标签</el-dropdown-item>
                  </router-link>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-col>

          <el-col :span="2">
            <el-dropdown size="large" :hide-on-click="false">
              <router-link to="/writing">
                <li>
                  <svg class="icon icon-lianjie" aria-hidden="true">
                    <use xlink:href="#icon-lianjie"></use>
                  </svg>
                  <p class="content">
                    个人
                    <el-icon :size="18">
                      <CaretBottom />
                    </el-icon>
                  </p>
                </li>
              </router-link>
              <template #dropdown>
                <el-dropdown-menu>
                  <router-link to="/archivemanage">
                    <el-dropdown-item>文章管理</el-dropdown-item>
                  </router-link>
                  <router-link to="/releasearchive">
                    <el-dropdown-item>发布文章</el-dropdown-item>
                  </router-link>
                  <router-link to="/usermanage">
                    <el-dropdown-item>用户管理</el-dropdown-item>
                  </router-link>
                  <router-link to="/personnalcloud">
                    <el-dropdown-item>个人云</el-dropdown-item>
                  </router-link>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-col>

          <el-col :span="2">
            <el-dropdown size="large" :hide-on-click="false">
              <li>
                <svg class="icon icon-zhifeiji" aria-hidden="true">
                  <use xlink:href="#icon-zhifeiji"></use>
                </svg>
                <p class="content">
                  关于
                  <el-icon :size="18">
                    <CaretBottom />
                  </el-icon>
                </p>
              </li>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>Gitee</el-dropdown-item>
                  <a href="https://github.com/1cccccc/-">
                    <el-dropdown-item> Github </el-dropdown-item>
                  </a>
                  <router-link to="/explain">
                    <el-dropdown-item>使用说明</el-dropdown-item>
                  </router-link>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-col>

          <el-col :span="4" v-if="!isLogin">
            <li @click="Login()">
              <svg class="icon icon-yonghu" aria-hidden="true">
                <use xlink:href="#icon-yonghu"></use>
              </svg>
              <p class="content">登录</p>
            </li>
          </el-col>

          <el-col :span="4" v-if="isLogin">
            <el-dropdown size="large" :hide-on-click="false">
              <li>
                <svg class="icon icon-yonghu" aria-hidden="true">
                  <use xlink:href="#icon-yonghu"></use>
                </svg>
                <p class="content">
                  {{ userinfo != null ? userinfo.username : null }}
                </p>
              </li>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="SetUp">设置</el-dropdown-item>
                  <el-dropdown-item @click="LoginOut"
                    >退出登录</el-dropdown-item
                  >
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-col>
        </el-row>
      </ul>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, watch, reactive } from "vue";
import { useMainStore } from "@/store/index";
import topNavBarApi from "@/api/TopNavBarApi";
import {ElMessageBox,ElMessage } from "element-plus"
import axios from "@/axios/index"

let store = useMainStore();
let TopNavBar = ref(null);
let scrollTopLS = 0; //原来的位置

let userinfo = reactive();
let isLogin = ref(false);

function getUserinfo() {
  if (!isLogin.value) {
    let info = localStorage.getItem("u");
    if (info) {
      userinfo = JSON.parse(info);
      isLogin.value = true;
    }
  }
}
getUserinfo();


watch(
  () => store.userinfo,
  (newV, oldV) => {
    if (newV) {
      getUserinfo();
    } else {
      isLogin.value = false;
      userinfo = null;
    }
  }
);

const Search = () => {
  store.SearchVariable = !store.SearchVariable;
};
const Login = () => {
  store.LoginVariable = !store.LoginVariable;
};

watch(
  () => store.scrolltop,
  (newV, oldV) => {
    let Y = newV - oldV;
    //屏幕向下滚动
    if (Y > 0) {
      TopNavBar.value.$el.classList.add("active");
    } else {
      //向上滚动
      TopNavBar.value.$el.classList.remove("active");
      if (newV > 100) {
        TopNavBar.value.$el.style.backgroundColor = "rgba(255,255,255,.7)";
        TopNavBar.value.$el.style.setProperty("--text-color", "#5E5B5A");
      } else {
        TopNavBar.value.$el.style.backgroundColor = "rgba(255,255,255,0)";
        TopNavBar.value.$el.style.setProperty("--text-color", "#eee");
      }
    }
  }
);

const Setup = () => {};



const LoginOut = async () => {
  ElMessageBox.confirm("您确认要退出登录吗?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    let token = localStorage.getItem("token");

    topNavBarApi.loginout(token).then((d) => {
      if (d.data.data) {
        userinfo = null;
        store.userinfo=null
        isLogin.value = false;
        localStorage.removeItem("u");
        localStorage.removeItem("token");

        ElMessage({
          type: "success",
          message: "退出登录成功",
        });
      }
    });
  });
};
</script>

<style scoped>
#TopNavBar {
  z-index: 10;
  width: 100vw;
  background-color: rgba(0, 0, 0, 0);
  padding-bottom: 0.5em;
  transition: all 0.5s ease;
  position: fixed;
  top: 0;
  --text-color: #eee;
  --text-size: 15px;
}
.icon {
  margin-right: 0.3rem;
  color: var(--text-color);
}

.active {
  top: -10% !important;
}
#Navigation {
  position: relative;
  top: 50%;
  transform: translateY(-25%);
}
#Navigation .icon {
  width: 1.5em;
  height: 1.5em;
}
#Navigation li {
  width: fit-content;
  list-style: none;
  overflow: hidden;
  position: relative;
  display: flex;
  padding-bottom: 0.5rem;
  cursor: pointer;
}
#Navigation li::after {
  content: "";
  width: 100%;
  height: 0.2rem;
  display: block;
  background-color: #80c8f8;
  position: absolute;
  left: -100%;
  bottom: 0;
  transition: left ease 0.3s;
}
#Navigation li:hover::after {
  left: 0%;
}

#Title {
  width: fit-content;
  margin: 0 auto;
  color: var(--text-color);
  font-weight: 900;
  cursor: default;
  transform: translateY(10%);
  cursor: pointer;
}
.content {
  width: fit-content;
  color: var(--text-color);
  font-size: var(--text-size);
  font-weight: 900;
  border: none;
}
</style>
