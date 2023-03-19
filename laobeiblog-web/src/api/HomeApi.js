import axios from "../axios/index"

async function getArticleDescribeList(page,size){
    return await axios.get("/articledescribe/list",{
        params:{
            page: page,
            size: size
        }
    })
}

async function getTagByIds(tagIds){
    return await axios.get(`/tag/getList`,{
        params: {
            ids: tagIds
        }
    })
}

async function getOtherInfo(id){
    return await axios.get("/user/otherInfo",{
        params:{
            id: id
        }
    })
}

const homeApi={
    getArticleDescribeList,
    getTagByIds,
    getOtherInfo
}

export default homeApi;