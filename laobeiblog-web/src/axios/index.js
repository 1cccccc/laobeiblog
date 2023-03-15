import axios from "axios"

const api =axios.create({
    baseURL: "http://localhost:8001",
    // timeout:5000,
    headers: {
        // "Access-Control-Allow-Origin": "http://localhost:8080",
        // "Access-Control-Allow-Headers": "*",
        // "Access-Control-Allow-Credentials": true,
        // "Vary": "Origin,Access-Control-Request-Method,Access-Control-Request-Headers",
        // "Vary": "Access-Control-Request-Method",
        // "Vary":"Access-Control-Request-Headers"
    }
})

export default api;