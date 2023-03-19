import { defineStore } from "pinia";
import { ref, reactive } from "vue";
import { JSEncrypt } from "jsencrypt";

export const useMainStore = defineStore("counter", () => {
  const count = ref(0);
  const SearchVariable = ref(false); //搜索框
  const LoginVariable = ref(false); //登录框
  const RegisterVariable = ref(false); //注册框
  const ForgetPasswordVariable = ref(false); //找回密码框
  const ForgetPasswordSteptwoVariable = ref(false); //找回密码下一步框
  const CategoryRelationVariable = ref(false);
  const scrollDown = ref(false);
  const scrolltop = ref(0);
  let email_reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/; //邮箱格式校验正则
  let phone_reg =
    /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/; //电话号码格式校验正则
  let code_reg = /^[A-Za-z0-9]{6}$/; //验证码格式校验正则

  //el-img引入本地图片
  const getImageUrl = (name) => {
    return new URL(`../assets/img/${name}`, import.meta.url).href;
  };

//
function startTime() {
  let time = setInterval(() => {
    let token_starttime = localStorage.getItem("token_starttime");
    if (token_starttime) {
      let currentDate = new Date().getTime();

      let result = currentDate - token_starttime;
      if (result > 1000 * 60 * 59 * 2) {
        alert("您的登录信息已过期，请重新登录!");
        localStorage.removeItem("u");
        localStorage.removeItem("token");
        localStorage.removeItem("token_starttime");

        store.userinfo = null;
        clearInterval(time);
      }
    }
  }, 1000 * 60);
}

//加密
const publicKey="";

const rsaCrypt=(str,publicKey)=>{
  const crypt = new JSEncrypt();
  crypt.setPublicKey(publicKey);
  let ciphertext = crypt.encrypt(str);
  return ciphertext;
}

  //用户信息
  let userinfo = reactive();
  userinfo=JSON.parse(localStorage.getItem("u"))

  const returnObject = {
    userinfo,
    count,
    SearchVariable,
    LoginVariable,
    RegisterVariable,
    ForgetPasswordVariable,
    ForgetPasswordSteptwoVariable,
    CategoryRelationVariable,
    email_reg,
    phone_reg,
    code_reg,
    scrollDown,
    getImageUrl,
    startTime,
    rsaCrypt,
    publicKey
  };
  return returnObject;
});
