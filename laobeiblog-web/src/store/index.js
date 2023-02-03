import { defineStore } from "pinia";
import {ref} from "vue"

export const useMainStore =defineStore("counter", () => {
  const count = ref(0);
  const dialogVisible=ref(false);

  return { count,dialogVisible };
});
