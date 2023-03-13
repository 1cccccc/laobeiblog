<template>
  <div id="Albums">
    <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">个人云</h1>
          </div>
        </el-row>
        <el-row :gutter="20" id="content" justify="center">
          <div id="data">
            <div id="options">
              <el-button type="danger" round @click="removeHandler()">删除</el-button>
            </div>

            <div id="files">
              <div class="file" v-for="item,index in urls">
                <el-image
                  style="width: 150px; height: 150px"
                  :src="item.ossUrl"
                  :zoom-rate="1.2"
                  :preview-src-list="srcList"
                  :initial-index="4"
                  fit="cover"
                />

                <el-checkbox
                  class="checkbox"
                  size="large"
                  :true-label="item.fileMd5"
                  :false-label="index"
                  @change="checkboxChange"
                />
              </div>
            </div>

            <el-upload
              class="upload-demo"
              drag
              action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
              multiple
            >
              <el-icon class="el-icon--upload"><upload-filled /></el-icon>
              <div class="el-upload__text"> 
                Drop file here or <em>click to upload</em>
              </div>
              <template #tip>
                <div class="el-upload__tip">
                  jpg/png files with a size less than 500kb
                </div>
              </template>
            </el-upload>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { useMainStore } from "@/store/index.js";
import api from "../api/PersonalCloudApi"
import { sortedUniq } from "lodash";

const store = useMainStore();
let urls = reactive([])
let srcList = reactive([]);
//${store.userinfo.userId}
const requestUrl=`http://localhost:8001/file/simpleManyUpload/1`

function getFiles(){
  api.getFileList(1).then(data=>{
  urls.push(...data.data.data)
  urls.forEach(item=>{
    srcList.push(item.ossUrl);
  })
})
}
getFiles()

let arr = reactive([true, false, true]);

let files=[];
const checkboxChange = (value) => {
  if(value.length==32){
    files.push(value)
  }else{
    files=files.splice(value,1);
  }
};

const removeHandler=()=>{
  api.removeFiles(files).then(d=>{
    getFiles();
  })
}
</script>

<style scoped>
.checkbox {
  position: absolute;
  left: 0.5em;
  top: 0em;
}

#files {
  width: 100%;
  margin-top: 1em;
  display: flex;
  justify-content: start;
  align-items: center;
  flex-wrap: wrap;
}
.file {
  margin: 1em;
  position: relative;
}
#upload {
  width: 150px;
  height: 150px;
  margin: 1em;
}

#Albums {
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
  width: 62%;
  margin-top: 1em;
  border-radius: var(--label-border-radius);
  box-shadow: var(--label-box-shadow);
  transition: var(--laber-transition);
  background-color: var(--label-backgroud-color);
  overflow: hidden;
  margin-bottom: 3em;
  padding: 3em;
  position: relative;
}

#data:hover {
  box-shadow: var(--label-box-shadow-moveover);
}

.photo {
  position: relative;
  width: 20%;
  padding-bottom: 2em;
}

.photo-name {
  position: absolute;
  left: 50%;
  bottom: 5%;
  transform: translateX(-50%);
  font-size: 0.8em;
  color: #666;
}

#upload-img {
  position: absolute;
  right: 5%;
  bottom: 5%;
}
</style>
