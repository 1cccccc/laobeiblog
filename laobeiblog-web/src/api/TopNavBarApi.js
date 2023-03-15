import axios from "@/axios/index"



async function loginout(token){
    return await axios.get("/user/loginout",{
        headers: {
            token: token
        }
    })
}

const topNavBarApi={
    loginout
}

export default topNavBarApi;