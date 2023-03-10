import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'// Element-ui图标库
import VueCookies from 'vue-cookies'//vue-cookies

//markdown
import VMdEditor from "@/plugins/mdEditor/index.js"
import VMdPreviewHtml from "@/plugins/mdEditor/previehtml.js"


let app=createApp(App);

// Element-ui图标库
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

  app.config.globalProperties.$cookies=VueCookies;
app.use(router).use(ElementPlus).use(createPinia()).use(VMdEditor).use(VMdPreviewHtml);
app.mount('#app')
