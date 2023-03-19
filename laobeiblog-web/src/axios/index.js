import axios from "axios"

const api =axios.create({
    baseURL: "http://localhost:8001",
    // timeout:5000,
    headers: {
        token: localStorage.getItem("token")
    }
})

export default api;