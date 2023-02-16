<template>
  <div id="ReleaseArchive">
    <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">发布文章</h1>
          </div>
        </el-row>
        <el-row id="content" justify="center">
          <div id="data">
            <el-button type="primary" plain>保存草稿</el-button>
            <el-button type="danger" plain>发布文章</el-button>
            <v-md-editor
              v-model="text"
              height="100vh"
              :left-toolbar="lefttoolbar"
              :disabled-menus="[]"
              @upload-image="handleUploadImage"
              :include-level="[1, 2, 3, 4]"
              :default-show-toc="true"
              @change="changeHandler"
              @save="saveHandler"
            ></v-md-editor>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref,onMounted,onBeforeUnmount } from "vue";

let text=ref("");

let lefttoolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link image code | save | emoji"

let flag=ref(false);
onMounted(()=>{
  window.addEventListener("beforeunload",beforeunloadHandler);
})

onBeforeUnmount(()=>{
  window.removeEventListener("beforeunload",beforeunloadHandler);
})
const beforeunloadHandler = (e) => {
  e = e || window.event;
  if(flag.value){
    e.returnValue="关闭提示！";
  }else{

  }
};

const changeHandler=(text,html)=>{
  flag.value=true;
}
const saveHandler=(text,html)=>{
  flag.value=false;

  console.log(text)
  console.log(html)
  //TODO 发送文章给后台
}

const handleUploadImage=()=>{

}
</script>

<style scoped>
#ReleaseArchive {
  overflow: hidden;
  width: 100vw;
  --label-border-radius: 0.5em;
  --label-box-shadow: 0px 5px 50px 1px rgba(49, 49, 49, 0.1);
  --label-box-shadow-moveover: 0px 5px 30px 1px rgba(49, 49, 49, 0.6);
  --label-backgroud-color: #fff;
  --laber-transition: all 200ms linear;
}
#head {
  color: #fff;
  font-size: 1.6em;
  font-weight: 900;
  width: 100vw;
  height: 40vh;
}
.head-bg {
  width: 100%;
  height: 100%;
}
.title {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

#content {
  background-image: linear-gradient(
    to bottom right,
    rgba(255, 218, 218, 0.5),
    rgba(196, 255, 232, 0.5)
  );
  padding-top: 2em;
}

#data {
  min-height: 40vh;
  width: 70%;
  margin-top: 1em;
  border-radius: var(--label-border-radius);
  box-shadow: var(--label-box-shadow);
  transition: var(--laber-transition);
  background-color: var(--label-backgroud-color);
  overflow: hidden;
  margin-bottom: 3em;
  padding: 4em;
  padding-bottom: 7em;
  position: relative;
}

#data:hover {
  box-shadow: var(--label-box-shadow-moveover);
}
</style>
