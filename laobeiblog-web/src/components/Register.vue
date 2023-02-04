<template>
  <div id="Register">
    <el-dialog
      title="注册"
      width="25%"
      v-model="RegisterVariable"
      center
      @close="closeDialog()"
      :destroy-on-close="true"
    >
      <div id="registerfarme">
        <div class="inputframe">
          <p>邮箱</p>
          <div>
            <input
              type="text"
              placeholder="请输入您的邮箱"
              name="email"
              v-model="registerinfo.email"
              @focus="inputfocus($event, 95)"
              @blur="blurfocus($event)"
              autocomplete="off"
            />
            <div class="underline"></div>
          </div>
          <el-icon
            v-if="registerinfo.email != ''"
            size="1.7em"
            class="clearbtn"
            @mousedown="
              registerinfo.email != ''
                ? clearvalue($event)
                : (registerinfo.email = '')
            "
            ><Close
          /></el-icon>
          <p>邮箱格式错误</p>
        </div>

        <div class="inputframe">
        <p>昵称</p>
        <div>
          <input
            type="text"
            placeholder="请输入您的昵称"
            name="nickname"
            v-model="registerinfo.nickname"
            @focus="inputfocus($event, 95)"
            @blur="blurfocus($event)"
            autocomplete="off"
          />
          <div class="underline"></div>
        </div>
        <el-icon
          v-if="registerinfo.nickname != ''"
          size="1.7em"
          class="clearbtn"
          @mousedown="
            registerinfo.nickname != ''
              ? clearvalue($event)
              : (registerinfo.nickname = '')
          "
          ><Close
        /></el-icon>
        <p>邮箱格式错误</p>
      </div>

        <div class="inputframe">
          <p>电话</p>
          <div>
            <input
              type="text"
              placeholder="请输入您的电话号码"
              name="phone"
              v-model="registerinfo.phone"
              @focus="inputfocus($event, 95)"
              @blur="blurfocus($event)"
              autocomplete="off"
            />
            <div class="underline"></div>
          </div>
          <el-icon
            v-if="registerinfo.phone != ''"
            size="1.7em"
            class="clearbtn"
            @mousedown="
              registerinfo.phone != ''
                ? clearvalue($event)
                : (registerinfo.phone = '')
            "
            ><Close
          /></el-icon>
          <p>邮箱格式错误</p>
        </div>

        <div class="inputframe">
          <p>验证码</p>
          <div>
            <input
              type="text"
              placeholder="请输入6位验证码"
              name="code"
              v-model="registerinfo.code"
              @focus="inputfocus($event, 80)"
              @blur="blurfocus($event)"
              autocomplete="off"
            />
            <div class="underline"></div>
          </div>
          <el-button type="primary" circle size="large" class="sendcodebtn"
            >发送</el-button
          >
        </div>

        <div class="inputframe">
          <p>密码</p>
          <div>
            <input
              type="password"
              placeholder="请输入您的密码"
              name="password"
              v-model="registerinfo.password"
              @focus="inputfocus($event, 95)"
              @blur="blurfocus($event)"
              autocomplete="off"
            />
            <div class="underline"></div>
          </div>
          <div id="visible" @click="vislblebtnclick($event)">
            <svg class="icon icon-taiyang1 novisible" aria-hidden="true">
              <use xlink:href="#icon-taiyang1"></use>
            </svg>
            <svg class="icon icon-yueliang" aria-hidden="true">
              <use xlink:href="#icon-yueliang"></use>
            </svg>
          </div>
        </div>

        <div class="inputframe">
          <p>确认密码</p>
          <div>
            <input
              type="password"
              placeholder="请再次输入您的密码"
              name="confirmpassword"
              v-model="registerinfo.confirmpassword"
              @focus="inputfocus($event, 95)"
              @blur="blurfocus($event)"
            />
            <div class="underline"></div>
          </div>
          <div id="visible" @click="vislblebtnclick($event)">
            <svg class="icon icon-taiyang1 novisible" aria-hidden="true">
              <use xlink:href="#icon-taiyang1"></use>
            </svg>
            <svg class="icon icon-yueliang" aria-hidden="true">
              <use xlink:href="#icon-yueliang"></use>
            </svg>
          </div>
          <p>邮箱格式错误</p>
        </div>

        <button id="registerbtn" @click="login">注册</button>
      </div>

      <div class="toLogin">
        <span>已有账号？</span>
        <span @click="login">登录</span>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, ref, watch } from "vue";
import { useMainStore } from "@/store/index";

const store = useMainStore();

let registerinfo = reactive({
  email: "",
  phone: "",
  password: "",
  nickname: "",
  confirmpassword: ""
});

//输入框获得焦点事件
const inputfocus = (e, num) => {
  let parentNodeChilds = e.target.parentNode.parentNode.childNodes;
  let ptitle = parentNodeChilds[0];
  let underline = parentNodeChilds[1].childNodes[1];

  ptitle.style.color = "#1B9CFC";
  underline.style.width = num + "%";
};

//输入框失去焦点事件
const blurfocus = (e) => {
  let parentNodeChilds = e.target.parentNode.parentNode.childNodes;
  let ptitle = parentNodeChilds[0];
  let underline = parentNodeChilds[1].childNodes[1];

  ptitle.style.color = "#000";
  underline.style.width = "0%";
};

//清空输入框按钮点击事件
const clearvalue = (e) => {
  e.preventDefault();

  let inputname =
    e.target.parentNode.parentNode.childNodes[1].childNodes[0].name;
  if (inputname == "email") {
    registerinfo.email = "";
  } else if (inputname == "nickname") {
    registerinfo.nickname = "";
  } else if (inputname == "phone") {
    registerinfo.phone = "";
  }
};

//密码可见按钮点击事件
const vislblebtnclick = (e) => {
  let parentNode = e.target.parentNode;
  let tagName = parentNode.tagName;
  if (tagName != "DIV") {
    parentNode = parentNode.parentNode;
  }
  parentNode.childNodes.forEach((item, index) => {
    item.classList.toggle("novisible");
  });

  if (parentNode.previousSibling.childNodes[0].type == "password") {
    parentNode.previousSibling.childNodes[0].type = "text";
  } else {
    parentNode.previousSibling.childNodes[0].type = "password";
  }
};

//监听store中的数据变化来决定是否打开登录框
let RegisterVariable = ref(false);
watch(
  () => store.RegisterVariable,
  (newV, oldV) => {
    RegisterVariable.value = newV;
  }
);

//对话框关闭事件
const closeDialog = () => {
  store.RegisterVariable = false;
};

//跳转登录框
const login = () => {
  store.LoginVariable = true;
  store.RegisterVariable = false;
};
</script>

<style scoped>
* {
  overflow: hidden;
}
#Register {
  overflow: hidden;
}
.inputframe {
  margin-bottom: 2em;
  --p-font-size: 0.5em;
  --input-font-size: 1.25em;
  --input-padding: 0.2em;
  --input-width: 95%;
}
.underline {
  width: 0%;
  height: 2px;
  background-color: #1b9cfc;
  transition: all 0.4s ease;
}

.inputframe p {
  font-size: var(--p-font-size);
  transform: all 0.5s ease;
}
.inputframe input {
  width: var(--input-width);
  font-size: var(--input-font-size);
  padding: var(--input-padding);
  border: none;
  border-bottom: solid 1px #000;
  padding-right: 2em;
}
.inputframe:nth-child(4) input {
  width: 80%;
}
.inputframe input:focus {
  outline: none;
}
.clearbtn {
  position: absolute;
  right: 5%;
  top: 50%;
  transform: translate(0%, -50%);
  cursor: pointer;
}

#visible {
  position: absolute;
  right: 5%;
  top: 50%;
  transform: translate(0%, -50%);
  width: 2em;
  overflow: hidden;
  height: 2em;
}
#visible svg {
  font-size: 2em;
  position: absolute;
  left: 0;
  top: 0;
  cursor: pointer;
}
.novisible {
  display: none;
}

#registerbtn {
  border: none;
  background: #fd7272;
  width: 5em;
  border-radius: 50%;
  height: 5em;
  color: #fff;
  letter-spacing: 0.2em;
  position: relative;
  left: 47%;
  transform: translateX(-50%);
  cursor: pointer;
  transition: all 0.5s ease;
  margin-bottom: 1em;
}
#registerbtn:hover {
  width: 15em;
  border-radius: 3em;
}

#other {
  display: flex;
  justify-content: space-between;
  padding-right: 2em;
}

#other p {
  cursor: pointer;
}

#registerfarme {
  margin: 0 0em 1em 2em;
}

.sendcodebtn {
  position: absolute;
  right: 5%;
  top: 50%;
  transform: translate(0%, -50%);
}

.toLogin span:nth-child(2) {
  cursor: pointer;
}
</style>
