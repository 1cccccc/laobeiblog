import axios from "../axios/index"

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