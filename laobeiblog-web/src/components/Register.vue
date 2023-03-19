<template>
  <div id="Register">
    <el-dialog
      title="注册"
      width="25%"
      v-model="RegisterVariable"
      center
      @close="closeDialog()"
      :destroy-on-close="true"
      :close-on-press-escape="false"
      :close-on-click-modal="false"
    >
      <div id="registerfarme">
        <!-- 邮箱 -->
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
          <p v-if="!registerinforeg.emailflag">邮箱格式错误</p>
        </div>

        <!-- 昵称 -->
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
              maxlength="20"
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
        </div>

        <!-- 电话 -->
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
          <p v-if="!registerinforeg.phoneflag">电话格式错误</p>
        </div>

        <!-- 验证码 -->
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
              maxlength="6"
              autocomplete="off"
            />
            <div class="underline"></div>
          </div>
          <el-button
            type="primary"
            circle
            size="large"
            class="sendcodebtn"
            @click="sendcode"
            >发送</el-button
          >
          <p v-if="!registerinforeg.codeflag">验证码应为6位的英文和数字组成</p>
        </div>

        <!-- 账号 -->
        <div class="inputframe">
          <p>账号</p>
          <div>
            <input
              type="text"
              placeholder="请输入您的账号"
              name="username"
              v-model="registerinfo.username"
              @focus="inputfocus($event, 95)"
              @blur="blurfocus($event)"
              autocomplete="off"
              maxlength="20"
            />
            <div class="underline"></div>
          </div>
        </div>

        <!-- 密码 -->
        <div class="inputframe">
          <p>密码</p>
          <div style="position: relative">
            <input
              type="password"
              placeholder="请输入您的密码"
              name="password"
              v-model="registerinfo.password"
              @focus="inputfocus($event, 95)"
              @input="passwordCheck(registerinfo.password)"
              @blur="blurfocus($event)"
              autocomplete="off"
              maxlength="16"
            />
            <div class="underline"></div>
            <div id="visible" @click="vislblebtnclick($event)">
              <svg class="icon icon-taiyang1 novisible" aria-hidden="true">
                <use xlink:href="#icon-taiyang1"></use>
              </svg>
              <svg class="icon icon-yueliang" aria-hidden="true">
                <use xlink:href="#icon-yueliang"></use>
              </svg>
            </div>
          </div>

          <p>
            密码由6-16位的数字、英文字母和特殊字符组成，请使用至少两种类型组合
          </p>
          <div id="colorblock"></div>
        </div>

        <!-- 确认密码 -->
        <div class="inputframe">
          <p>确认密码</p>
          <div style="position: relative">
            <input
              type="password"
              placeholder="请再次输入您的密码"
              name="confirmpassword"
              v-model="registerinfo.confirmpassword"
              @focus="inputfocus($event, 95)"
              @blur="blurfocus($event)"
              maxlength="16"
            />
            <div class="underline"></div>
            <div id="visible" @click="vislblebtnclick($event)">
              <svg class="icon icon-taiyang1 novisible" aria-hidden="true">
                <use xlink:href="#icon-taiyang1"></use>
              </svg>
              <svg class="icon icon-yueliang" aria-hidden="true">
                <use xlink:href="#icon-yueliang"></use>
              </svg>
            </div>
          </div>

          <p v-if="!registerinforeg.confirmpasswordflag">
            两次输入的密码不一致
          </p>
          <div id="colorblock"></div>
        </div>

        <button id="registerbtn" @click="register">注册</button>
      </div>

      <div class="toLogin">
        <span>已有账号？</span>
        <span @click="login()" style="color: #2e86de">登录</span>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, ref, watch } from "vue";
import { useMainStore } from "@/store/index";
import { getCurrentInstance } from "vue";
import { ElMessage } from "element-plus";
import registerApi from "../api/RegisterApi";
import keyApi from "../api/KeyApi"

const CurrentInstance = getCurrentInstance();
const InstanceGlobalProp = CurrentInstance.appContext.config.globalProperties;
const cookie = InstanceGlobalProp.$cookies;
const store = useMainStore();

//注册信息
let registerinfo = reactive({
  nickname: "orange",
  code: "000000",
  email: "111111111@qq.com",
  phone: "16673514910",
  username: "2952309223",
  password: "000000",
  confirmpassword: "000000",
  loginType: 1,
});

//注册信息校验变量
let registerinforeg = reactive({
  emailflag: true,
  phoneflag: true,
  codeflag: true,
  confirmpasswordflag: true,
});

//密码强度色块颜色
let grade = ref(0);
const passwordCheck = (password) => {
  let colorblock = document.querySelector("#colorblock");
  let weak = /^(\d{6,16})|([a-z]{6,16})|([A-Z]{6,16})$/; //强度弱，6-10位的纯数字或纯英文
  let middle = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,16}$/; //强度中，6-10位的数字+英文
  let strong = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,16}/; //强度强，6-10位的数字+英文+特殊字符

  if (strong.test(password)) {
    colorblock.style.backgroundColor = "#32ff7e"; //green
    grade.value = 3;
  } else if (middle.test(password)) {
    colorblock.style.backgroundColor = "#ffaf40"; //yellow
    grade.value = 2;
  } else if (weak.test(password)) {
    colorblock.style.backgroundColor = "#ff4d4d"; //red
    grade.value = 1;
  } else {
    colorblock.style.backgroundColor = "#ff4d4d"; //red
    grade.value = 0;
  }
};

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

  if (e.target.name == "email") {
    registerinforeg.emailflag = store.email_reg.test(registerinfo.email);
  } else if (e.target.name == "phone") {
    registerinforeg.phoneflag = store.phone_reg.test(registerinfo.phone);
  } else if (e.target.name == "code") {
    registerinforeg.codeflag = store.code_reg.test(registerinfo.code);
  } else if (e.target.name == "confirmpassword") {
    registerinforeg.confirmpasswordflag =
      registerinfo.password == registerinfo.confirmpassword;
  }
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

//发送验证码
const sendcode = () => {
  if (registerinforeg.phoneflag === false || registerinfo.phone == "") {
    ElMessage({
      type: "error",
      message: `您的电话号码有误`,
      showClose: true,
      grouping: true,
    });
  } else {
    let sendcodebtn = document.querySelector(".sendcodebtn");
    let timestamp = new Date().getTime();

    if (cookie.get("sendcodetimestamp") === null) {
      //请求后端发送验证码，也可以先发送验证码
      ElMessage({
        type: "success",
        message: "验证码已发送到您的手机，验证码在五分钟内有效",
        showClose: true,
      });
    } else {
      let currenttime = new Date().getTime();
      let timedifference =
        (currenttime - cookie.get("sendcodetimestamp")) / 1000;
      ElMessage({
        type: "error",
        message: `请不要重复发送验证码，您可以在${Math.round(
          60 - timedifference
        )}s后重试`,
        showClose: true,
        grouping: true,
      });
    }
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

  registerinfo = reactive({
    password: "",
    nickname: "",
    confirmpassword: "",
  });

  registerinforeg = reactive({
    codeflag: true,
    nicknameflag: true,
    confirmpasswordflag: true,
  });
};

//跳转登录框
const login = () => {
  store.LoginVariable = true;
  store.RegisterVariable = false;
};

const register = async () => {
  let flag=false;
  //判断注册信息
  for (let item in registerinfo) {
    if (registerinfo[item] == "") {
      ElMessage({
        type: "error",
        message: "您有信息未填写！",
      });

      return;
    }
  }

  for (let item in registerinforeg) {
    if (!registerinforeg[item]) {
      ElMessage({
        type: "error",
        message: "信息有误！",
      });

      return;
    }
    flag = true;
  }

  if (flag) {
    let publicKey;
    if (store.publicKey) {
      publicKey = store.publicKey;
    } else {
      await keyApi.getPublicKey().then((d) => {
        publicKey = d.data.data;
      });
    }

    let rsaPassword = store.rsaCrypt(registerinfo.password,publicKey);
    console.log(rsaPassword);

    await registerApi.register(registerinfo, rsaPassword).then((d) => {
      console.log(d);
    });
  }

  // store.RegisterVariable = false;
};
</script>

<style scoped>
#Register {
  overflow: hidden;
}
.inputframe {
  margin-bottom: 2em;
  --p-font-size: 0.7em;
  --input-font-size: 1.25em;
  --input-padding: 0.2em;
  --input-width: 95%;
  position: relative;
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
.inputframe p:last-child {
  color: red;
  transform-origin: 0% 0%;
  transform: scale(0.8);
  letter-spacing: 0.2em;
  position: absolute;
  left: 0;
  bottom: -37%;
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
  right: 10%;
  top: 61%;
  transform: translate(0%, -50%);
  width: 2em;
  overflow: hidden;
  height: 2em;
}
#visible svg {
  font-size: 2em;
  position: absolute;
  left: 0;
  top: -20%;
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
  right: 10%;
  top: 41%;
  transform: translate(0%, -50%);
}

.toLogin span:nth-child(2) {
  cursor: pointer;
}
#colorblock {
  width: 2em;
  height: 0.5em;
  position: absolute;
  left: 0;
  bottom: -20%;
  background-color: red;
  border: solid 1px #000;
}
</style>
