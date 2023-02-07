<template>
  <div id="CategoryRelation">
    <el-dialog
      title="分类关联的文章"
      width="50%"
      v-model="CategoryRelationVariable"
      center
      @close="closeDialog()"
    >
      <el-table :data="tableData" stripe style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="Id" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="releasetime" label="文章发布时间" />
      </el-table>
      <p class="tip">修改文章分类请移步到文章管理页，谢谢！</p>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref,reactive, watch } from "vue";
import { useMainStore } from "@/store/index";

const store = useMainStore();

const loading = ref(false);
let tableData=reactive([])



let CategoryRelationVariable = ref(false);
watch(
  () => store.CategoryRelationVariable,
  (newV, oldV) => {
    CategoryRelationVariable.value = newV;
  }
);

const closeDialog = () => {
  store.CategoryRelationVariable = false;
};
</script>

<style scoped>
#CategoryRelation {
    position: relative;
}
.tip {
    margin-top: 2em;
}
</style>
