import axios from "../axios/index"
import defaultAxios from "axios"


async function login(username,password){
    return await axios.post("/user/login",{
            username: username,
            password: password
    })
}


const loginApi={
    login
}

export default loginApi;