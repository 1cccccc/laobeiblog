import { createStore } from 'vuex'

const store=createStore({
    state(){
        return {
            sum:0
        }
    },
    mutations:{
        increment(state){
            state.sum++;
        }
    }
});
export default store;