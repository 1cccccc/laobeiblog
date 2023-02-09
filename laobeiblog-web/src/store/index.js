import { defineStore } from "pinia";
import { ref } from "vue";

export const useMainStore = defineStore("counter", () => {
  const count = ref(0);
  const SearchVariable = ref(false); //搜索框
  const LoginVariable = ref(false); //登录框
  const RegisterVariable = ref(false); //注册框
  const ForgetPasswordVariable = ref(false); //找回密码框
  const ForgetPasswordSteptwoVariable = ref(false); //找回密码下一步框
  const CategoryRelationVariable = ref(false);
  let email_reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/; //邮箱格式校验正则
  let phone_reg =
    /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/; //电话号码格式校验正则
  let code_reg = /^[A-Za-z0-9]{6}$/; //验证码格式校验正则

  //校验账号是邮箱还是电话
  const accountCheck = (account) => {
    return email_reg.test(account)
      ? "email"
      : phone_reg.test(account)
      ? "phone"
      : "nothing";
  };
  //el-img引入本地图片
  const getImageUrl = (name) => {
    return new URL(`../assets/img/${name}`, import.meta.url).href;
  };

  const returnObject = {
    count,
    SearchVariable,
    LoginVariable,
    RegisterVariable,
    ForgetPasswordVariable,
    email_reg,
    phone_reg,
    code_reg,
    accountCheck,
    getImageUrl,
  };
  return returnObject;
});
