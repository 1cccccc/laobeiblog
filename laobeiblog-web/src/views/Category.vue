<template>
  <div id="Category">
    <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">分类</h1>
          </div>
        </el-row>
        <el-row :gutter="20" id="content" justify="center">
          <div id="data">
            <el-table :data="tableData" stripe style="width: 100%" v-loading="loading">
              <el-table-column prop="name" label="分类名称" width="180" />
              <el-table-column
                prop="num"
                label="分类所拥有文章的数量"
                width="180"
              />
              <el-table-column prop="createtime" label="创建时间" width="180" />
              <el-table-column>
                <template #default="scope">
                  <el-button type="success" @click="querycategoryarchive(scope)">查看分类所关联的文章</el-button>
                  <el-button type="danger" @click="deletecategory(scope)"
                    >删除分类</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ElMessage, ElMessageBox } from "element-plus";
import { ref,reactive } from "vue";
import { useMainStore } from "@/store/index";

const store = useMainStore();

const loading = ref(false);
let tableData = reactive([
  {
    id: 1,
    name: "后端",
    num: 3,
    createtime: "2023/2/7",
  },
  {
    id: 2,
    name: "前端",
    num: 0,
    createtime: "2023/2/7",
  },
]);

//查看分类下的文章
const querycategoryarchive=(scope)=>{
    store.CategoryRelationVariable=true;
}

//删除分类
const deletecategory = (scope) => {
  ElMessageBox.confirm(
    "您当前分类中还有文章，如果删除分类这些文章将会被移动到默认分类下，是否删除？",
    "Warning",
    {
      confirmButtonText: "OK",
      cancelButtonText: "Cancel",
      type: "warning",
    }
  ).then(() => {
    //发送请求删除该分类并移动该分类下的所有文章到默认分类

    ElMessage({
      type: "success",
      message: "Delete completed",
    });
  }).catch(()=>{});
};
</script>

<style scoped>
#Category {
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
  width: 50%;
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
