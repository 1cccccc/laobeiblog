import { defineStore } from "pinia";
import {ref} from "vue"

export const useMainStore =defineStore("counter", () => {
  const count = ref(0);
  const SearchVariable=ref(false);
  const LoginVariable=ref(false);
  const RegisterVariable=ref(false);

  const returnObject={ count,SearchVariable,LoginVariable,RegisterVariable };
  return returnObject;
});
