<template>
  <div id="Login">
    <el-dialog
      title="登录"
      width="25%"
      v-model="LoginVariable"
      center
      @close="closeDialog()"
      :destroy-on-close="true"
      :close-on-press-escape="false"
      :close-on-click-modal="false"
    >
      <div id="loginfarme">
        <div class="inputframe">
          <p>帐号</p>
          <div>
            <input
              type="text"
              placeholder="请输入您的账号"
              name="email"
              v-model="logininfo.account"
              @focus="inputfocus($event)"
              @blur="blurfocus($event)"
              autocomplete="off"
              maxlength="20"
            />
            <div class="underline"></div>
          </div>
          <el-icon
            v-if="logininfo.account != ''"
            size="1.7em"
            class="clearbtn"
            @mousedown="
              logininfo.account != ''
                ? clearvalue($event)
                : (logininfo.account = '')
            "
            ><Close
          /></el-icon>
        </div>

        <div class="inputframe">
          <p>密码</p>
          <div>
            <input
              type="password"
              placeholder="请输入您的密码"
              name="password"
              v-model="logininfo.password"
              @focus="inputfocus($event)"
              @blur="blurfocus($event)"
              autocomplete="off"
              maxlength="16"
              @keydown.enter="login"
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

        <button id="signbtn" @click="login">上号</button>
        <div id="other">
          <p @click="register()">立即注册</p>
          <p @click="forgetpassword()">忘记密码?</p>
        </div>
        <div id="socialcontact">
          <el-divider>社交帐号登录</el-divider>
        </div>
        <div id="socialcontactlogo">
          <svg class="icon icon-weibo" aria-hidden="true">
            <use xlink:href="#icon-weibo"></use>
          </svg>
          <svg class="icon icon-wechat" aria-hidden="true">
            <use xlink:href="#icon-wechat"></use>
          </svg>
          <svg class="icon icon-QQ" aria-hidden="true">
            <use xlink:href="#icon-QQ"></use>
          </svg>
          <svg class="icon icon-gitee" aria-hidden="true">
            <use xlink:href="#icon-gitee"></use>
          </svg>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, ref, watch } from "vue";
import { Close, SortUp } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import { useMainStore } from "@/store/index";
import loginApi from "@/api/LoginApi";
import keyApi from "@/api/KeyApi";
import { JSEncrypt } from "jsencrypt";

const store = useMainStore();

//输入框数据绑定对象
let logininfo = reactive({
  account: "",
  password: "",
});

//监听store中的数据变化来决定是否打开登录框
let LoginVariable = ref(false);
watch(
  () => store.LoginVariable,
  (newV, oldV) => {
    logininfo = reactive({
      account: "",
      password: "",
    });
    LoginVariable.value = newV;
  }
);

const closeDialog = () => {
  store.LoginVariable = false;
};

//登录按钮点击事件
const login = async () => {
  if (logininfo.account.length < 4) {
    ElMessage({
      type: "error",
      message: "账号的长度必须在4位及以上",
    });
    return;
  }
  if (logininfo.password.length < 6) {
    ElMessage({
      type: "error",
      message: "无效密码或密码为空",
    });
    return;
  }

  //密码进行加密,rsa
  //请求公钥
  let publicKey;
  await keyApi.getPublicKey().then((d) => {
    publicKey = d.data.data;
  });

  //将密码进行加密
  const crypt = new JSEncrypt();
  crypt.setPublicKey(publicKey);
  let ciphertext = crypt.encrypt(logininfo.password);

  //收集数据并发送请求
  await loginApi.login(logininfo.account, ciphertext).then((d) => {
    localStorage.setItem("token", d.data.data.token);
    let info = JSON.stringify(d.data.data.userinfo);
    localStorage.setItem("u", info);

    store.userinfo = d.data.data.userinfo;
  });

  store.LoginVariable = false;
};

const ClearInfo = () => {
  logininfo.account = "";
  logininfo.password = "";
};

//打开注册界面
const register = () => {
  store.RegisterVariable = true;
  store.LoginVariable = false;
  ClearInfo();
};

//打开忘记密码界面
const forgetpassword = () => {
  store.ForgetPasswordVariable = true;
  store.LoginVariable = false;
  ClearInfo();
};

//输入框获得焦点事件
const inputfocus = (e) => {
  let parentNodeChilds = e.target.parentNode.parentNode.childNodes;
  let ptitle = parentNodeChilds[0];
  let underline = parentNodeChilds[1].childNodes[1];

  ptitle.style.color = "#1B9CFC";
  underline.style.width = "95%";
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
  logininfo.account = "";
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
</script>

<style scoped>
#Login {
  overflow: hidden;
}

#loginfarme {
  margin: 0 0em 1em 2em;
}

.inputframe {
  margin-bottom: 3em;
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
.inputframe input:focus {
  outline: none;
}
.clearbtn {
  position: absolute;
  right: 5%;
  top: 22%;
  transform: translate(0%, -50%);
  cursor: pointer;
}

#visible {
  position: absolute;
  right: 5%;
  top: 40%;
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

#signbtn {
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
#signbtn:hover {
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
#socialcontact {
  width: 20em;
  position: relative;
  left: 47%;
  transform: translateX(-50%);
}

#socialcontactlogo {
  display: flex;
  justify-content: center;
  font-size: 2em;
  padding-right: 1.2em;
}
#socialcontactlogo svg {
  margin: 0 0.3em;
  cursor: pointer;
}
</style>
