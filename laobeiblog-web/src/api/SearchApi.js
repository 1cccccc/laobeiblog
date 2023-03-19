import axios from "../axios/index"

async function search(searchCriteria){
    return await axios.get(`/search/${searchCriteria}`);
}