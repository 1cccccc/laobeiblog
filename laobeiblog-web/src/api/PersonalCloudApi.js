import axios from "../axios/index"

async function getFileList(userId){
    return await axios.get(`/file/getFileList/${userId}`);
}

async function removeFiles(md5s){
    return await axios.delete(`/file/removeFiles`,{
        data: md5s
    })
}

const api={
    getFileList,
    removeFiles
}

export default api;