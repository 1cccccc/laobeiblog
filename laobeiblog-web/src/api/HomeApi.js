import axios from "../axios/index"

async function getArticleDescribeList(){
    return axios.get("/articledescribe/list",{
        params:{
            page: 0,
            size: 5
        }
    })
}

async function getTagByIds(tagIds){
    return await axios.get(`/tag/getList`,{
        params: {
            "ids": tagIds
        }
    })
}

const homeApi={
    getArticleDescribeList,
    getTagByIds
}

export default homeApi;