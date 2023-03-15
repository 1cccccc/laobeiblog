<template>
  <div id="ForgetPasswordSteptwo">
    <el-dialog
      title="忘记密码?"
      width="25%"
      v-model="ForgetPasswordSteptwoVariable"
      center
      @close="closeDialog()"
      :destroy-on-close="true"
      :close-on-press-escape="false"
      :close-on-click-modal="false"
    >
      <div class="inputframe">
        <p>密码</p>
        <div>
          <input
            type="password"
            placeholder="请输入您的密码"
            name="password"
            v-model="forgetpasswordinfo.password"
            @focus="inputfocus($event, 95)"
            @input="passwordCheck(forgetpasswordinfo.password)"
            @blur="blurfocus($event)"
            autocomplete="off"
            maxlength="16"
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
        <p>
          密码由6-16位的数字、英文字母和特殊字符组成，请使用至少两种类型组合
        </p>
        <div id="colorblock"></div>
      </div>

      <!-- 确认密码 -->
      <div class="inputframe">
        <p>确认密码</p>
        <div>
          <input
            type="password"
            placeholder="请再次输入您的密码"
            name="confirmpassword"
            v-model="forgetpasswordinfo.confirmpassword"
            @focus="inputfocus($event, 95)"
            @blur="blurfocus($event)"
            maxlength="16"
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
        <p v-if="!forgetpasswordinforeg.confirmpasswordflag">两次输入的密码不一致</p>
      </div>
      <button id="resetpasswordbtn" @click="resetpassword()">更改密码</button>
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

let forgetpasswordinfo = reactive({
  password: "",
  confirmpassword: ""
});
//找回密码信息校验变量
let forgetpasswordinforeg = reactive({
    confirmpasswordflag: true
});

//更改密码
const resetpassword=()=>{
  //校验数据
  if(forgetpasswordinforeg.confirmpasswordflag!=false || forgetpasswordinfo.password!='' || forgetpasswordinfo.confirmpassword!=''){
    if(grade>=2){//判断密码强度
      //发送请求

      //处理完成给用户反馈结果并在3s后跳转到登陆页
    }else{
      ElMessage({
        type: "error",
        message: "密码强度不足",
        grouping: true,
        showClose: true
      })
    }
  }else{
    ElMessage({
        type: "error",
        message: "数据错误",
        grouping: true,
        showClose: true
      })
  }
}

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


//输入框获得焦点事件
const inputfocus = (e, num) => {
  let parentNodeChilds = e.target.parentNode.parentNode.childNodes;
  let ptitle = parentNodeChilds[0];
  let underline = parentNodeChilds[1].childNodes[1];

  ptitle.style.color = "#1B9CFC";
  underline.style.width = num + "%";
};

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
  } else if (e.target.name == "confirmpassword") {
    forgetpasswordinforeg.confirmpasswordflag =
      forgetpasswordinfo.password == forgetpasswordinfo.confirmpassword;
  }
};

//监听store中的数据变化来决定是否打开登录框
let ForgetPasswordSteptwoVariable = ref(false);
watch(
  () => store.ForgetPasswordSteptwoVariable,
  (newV, oldV) => {
    ForgetPasswordSteptwoVariable.value = newV;
  }
);

//对话框关闭事件
const closeDialog = () => {
  store.ForgetPasswordSteptwoVariable = false;
};
</script>

<style scoped>
#ForgetPasswordSteptwo {
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
#colorblock {
  width: 2em;
  height: 0.5em;
  position: absolute;
  left: 0;
  bottom: -20%;
  background-color: red;
  border: solid 1px #000;
}

#resetpasswordbtn {
  border: none;
  background: #1b9cfc;
  width: 15em;
  border-radius: 3em;
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
</style>
