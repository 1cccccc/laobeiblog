import axios from "../axios/index"


async function getPublicKey(){
    return await axios.get("/key/getPublicKey");
}

const keyApi={
    getPublicKey
}

export default keyApi;