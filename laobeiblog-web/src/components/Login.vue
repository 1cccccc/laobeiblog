<template>
    <div id="Search">
      <el-dialog
        title="本地搜索"
        width="40%"
        v-model="LoginVariable"
        center
        @close="closeDialog()"
      >

        <div id="loginfarme">
            <div class="inputframe">
                <p>邮箱号</p>
                <input type="text" placeholder="请输入您的邮箱号">
                <div class="underline"></div>
            </div>

            <div class="inputframe">
                <p>密码</p>
                <input type="password" placeholder="请输入您的密码">
                <svg class="icon icon-taiyang1" aria-hidden="true">
                    <use xlink:href="#icon-taiyang1"></use>
                  </svg>
                  <svg class="icon icon-yueliang" aria-hidden="true">
                    <use xlink:href="#icon-yueliang"></use>
                  </svg>
                <div class="underline"></div>
            </div>

            <button id="signbtn">上号</button>
            <div id="other">
                <p>立即注册</p>
                <p>忘记密码?</p>
            </div>
            <div id="socialcontact">
                <div class="line"></div>
                <p>社交账号登录</p>
                <div class="line"></div>
            </div>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script setup>
  import { reactive, ref, watch } from "vue";
  import { useMainStore } from "@/store/index";
  
  const store = useMainStore();
  
  let LoginVariable = ref(false);
  
  watch(
    () => store.LoginVariable,
    (newV, oldV) => {
        LoginVariable.value = newV;
    }
  );
  
  const closeDialog = () => {
    store.LoginVariable = false;
  };
  </script>
  
  <style scoped>
 
  </style>
  