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
              <el-button round>全选</el-button>
              <el-button type="primary" round>重命名</el-button>
              <el-button type="danger" round>删除</el-button>
            </div>

            <el-upload
              action="http://localhost:8001/file/simpleManyUpload"
              method="post"
              :multiple="true"
              list-type="picture-card"
              :auto-upload="false"
              id="upload"
            >
              <el-icon><Plus /></el-icon>

              <template #file="{ file }">
                <div>
                  <img
                    class="el-upload-list__item-thumbnail"
                    :src="file.url"
                    alt=""
                  />
                  <span class="el-upload-list__item-actions">
                    <span
                      class="el-upload-list__item-preview"
                      @click="handlePictureCardPreview(file)"
                    >
                      <el-icon><zoom-in /></el-icon>
                    </span>
                    <span
                      v-if="!disabled"
                      class="el-upload-list__item-delete"
                      @click="handleDownload(file)"
                    >
                      <el-icon><Download /></el-icon>
                    </span>
                    <span
                      v-if="!disabled"
                      class="el-upload-list__item-delete"
                      @click="handleRemove(file)"
                    >
                      <el-icon><Delete /></el-icon>
                    </span>
                  </span>
                </div>
              </template>
            </el-upload>

            <el-upload
              v-model:file-list="fileList"
              class="upload-demo"
              action="http://127.0.0.1:8001/api/file/simpleManyUpload"
              multiple
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              :limit="3"
              :on-exceed="handleExceed"
            >
              <el-button type="primary">Click to upload</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  jpg/png files with a size less than 500KB.
                </div>
              </template>
            </el-upload>

            <el-dialog v-model="dialogVisible">
              <img
                w-full
                :src="dialogImageUrl"
                alt="Preview Image"
                style="width: 100%"
              />
            </el-dialog>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { useMainStore } from "@/store/index.js";
import { Delete, Download, Plus, ZoomIn } from "@element-plus/icons-vue";

const store = useMainStore();
let photos = reactive([
  {
    id: 0,
    url: "",
    photoname: "1111.jpg",
  },
]);
let srcList = reactive([]);

const dialogImageUrl = ref("");
const dialogVisible = ref(false);
const disabled = ref(false);

//删除图片
const handleRemove = (file) => {
  console.log(file);
};

//图片预览
const handlePictureCardPreview = (file) => {
  dialogImageUrl.value = file.url;
  dialogVisible.value = true;
};

//下载图片
const handleDownload = (file) => {
  console.log(file);
};
</script>

<style scoped>
#upload {
  margin-top: 2em;
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
  width: 60%;
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
