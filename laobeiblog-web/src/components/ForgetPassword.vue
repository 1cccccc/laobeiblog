<template>
  <div id="ForgetPassword">
    <el-dialog
      title="忘记密码?"
      width="25%"
      v-model="ForgetPasswordVariable"
      center
      @close="closeDialog()"
      :destroy-on-close="true"
    >
      <div id="forgetfarme">
        <div class="inputframe">
          <p>帐号</p>
          <div>
            <input
              type="text"
              placeholder="请输入您的邮箱或电话号码"
              name="account"
              v-model="forgetpasswordinfo.account"
              @focus="inputfocus($event, 95)"
              @blur="blurfocus($event)"
              autocomplete="off"
            />
            <div class="underline"></div>
          </div>
          <el-icon
            v-if="forgetpasswordinfo.account != ''"
            size="1.7em"
            class="clearbtn"
            @mousedown="
              forgetpasswordinfo.account != ''
                ? clearvalue($event)
                : (forgetpasswordinfo.account = '')
            "
            ><Close
          /></el-icon>
          <p v-if="!forgetpasswordinforeg.accountflag">
            请输入正确的邮箱或电话
          </p>
        </div>

        <div class="inputframe">
          <p>验证码</p>
          <div>
            <input
              type="text"
              placeholder="请输入6位验证码"
              name="code"
              v-model="forgetpasswordinfo.code"
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
          <p v-if="!forgetpasswordinforeg.codeflag">
            验证码应为6位的英文和数字组成
          </p>
        </div>

        <button id="nextstep" @click="next()">下一步</button>
        <div class="toLogin">
          <span>已有账号？</span>
          <span @click="login()">登录</span>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, watch, reactive } from "vue";
import { useMainStore } from "@/store/index";
import { getCurrentInstance } from "vue";
import { ElMessage } from "element-plus";

const CurrentInstance = getCurrentInstance();
const InstanceGlobalProp = CurrentInstance.appContext.config.globalProperties;
const cookie = InstanceGlobalProp.$cookies;
const store = useMainStore();

const next = () => {
    store.ForgetPasswordVariable=false;
    store.ForgetPasswordSteptwoVariable=true;
};

//找回密码信息
let forgetpasswordinfo = reactive({
  account: "",
  code: "",
  accounttype: "",
});

//找回密码信息校验变量
let forgetpasswordinforeg = reactive({
  accountflag: true,
  codeflag: true,
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

  if (e.target.name == "account") {
    let result = store.accountCheck(forgetpasswordinfo.account);
    if (result == "nothing") {
      forgetpasswordinforeg.accountflag = false;
    } else {
      forgetpasswordinforeg.accountflag = true;
      forgetpasswordinfo.accounttype = result;
    }
  } else if (e.target.name == "code") {
    forgetpasswordinforeg.codeflag = store.code_reg.test(
      forgetpasswordinfo.code
    );
  }
};

//清空输入框按钮点击事件
const clearvalue = (e) => {
  e.preventDefault();

  let inputname =
    e.target.parentNode.parentNode.childNodes[1].childNodes[0].name;
  if (inputname == "email") {
    forgetpasswordinfo.email = "";
  } else if (inputname == "nickname") {
    forgetpasswordinfo.nickname = "";
  } else if (inputname == "account") {
    forgetpasswordinfo.account = "";
  } else if (inputname == "phone") {
    forgetpasswordinfo.phone = "";
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
  if (
    forgetpasswordinforeg.accountflag === false ||
    forgetpasswordinfo.account == ""
  ) {
    //判断账号格式
    ElMessage({
      type: "error",
      message: `您的账号有误`,
      showClose: true,
      grouping: true,
    });
  } else {
    let sendcodebtn = document.querySelector(".sendcodebtn");
    let timestamp = new Date().getTime();
    if (cookie.get("sendcodetimestamp") === null) {
      //防止因为刷新网页导致倒计时丢失，采用cookie存储
      cookie.set("sendcodetimestamp", timestamp, 60);
      if (forgetpasswordinfo.accounttype == "email") {
        //判断账号是邮箱还是手机号
        //请求后端发送验证码，也可以先发送验证码

        ElMessage({
          type: "success",
          message: "验证码已发送到您的邮箱",
          showClose: true,
        });
      } else if (forgetpasswordinfo.accounttype == "phone") {
        //请求后端发送验证码，也可以先发送验证码
        ElMessage({
          type: "success",
          message: "验证码已发送到您的手机",
          showClose: true,
        });
      }
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
let ForgetPasswordVariable = ref(false);
watch(
  () => store.ForgetPasswordVariable,
  (newV, oldV) => {
    ForgetPasswordVariable.value = newV;
  }
);

//对话框关闭事件
const closeDialog = () => {
  store.ForgetPasswordVariable = false;
};

//跳转登录框
const login = () => {
  store.LoginVariable = true;
  store.ForgetPasswordVariable = false;
};
</script>

<style scoped>
#ForgetPassword {
  overflow: hidden;
}
#forgetfarme {
  overflow: hidden;
}

.inputframe {
  margin-bottom: 2em;
  --p-font-size: 0.7em;
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
.inputframe:nth-child(2) input {
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

.sendcodebtn {
  position: absolute;
  right: 5%;
  top: 50%;
  transform: translate(0%, -50%);
}

#nextstep {
  border: none;
  background: #1b9cfc;
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
#nextstep:hover {
  width: 15em;
  border-radius: 3em;
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
