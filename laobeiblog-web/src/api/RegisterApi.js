import axios from "../axios/index";

async function register(register, rsaPassword) {
  return await axios.post("/user/register", {
    nickname: register.nickname,
    username: register.username,
    password: rsaPassword,
    phone: register.phone,
    email: register.email,
    code: register.code,
    loginType: register.loginType,
  });
}

export default {
  register,
};
