<template>
  <div id="Search">
    <button @click="dialogVisible = true" class="btn">button</button>

    <el-dialog
      title="本地搜索"
      width="60%"
      v-model="dialogVisible"
      center
      @close="closeDialog()"
    >
      <div class="searchframe">
        <el-input
          v-model="input"
          placeholder="Please input"
          clearable
          prefix-icon="Search"
          size="large"
          class="searchtitle"
        />
        <el-scrollbar>
          <p v-if="searchresults.length == 0">找不到您查询的内容</p>
          <div class="article" v-for="searchresult in searchresults">
            <h2 class="articletitle">{{ searchresult.title }}</h2>
            <p class="articlecontent">{{ searchresult.content }}</p>

            <div class="infoframe">
              <div class="searchresult_left">
                <div>
                  <svg class="icon icon-chakan" aria-hidden="true">
                    <use xlink:href="#icon-chakan"></use>
                  </svg>
                  <p>{{ searchresult.look }}</p>
                </div>

                <div>
                  <svg class="icon icon-icon1" aria-hidden="true">
                    <use xlink:href="#icon-icon1"></use>
                  </svg>
                  <p>{{ searchresult.like }}</p>
                </div>

                <div>
                  <svg class="icon icon-pinglun" aria-hidden="true">
                    <use xlink:href="#icon-pinglun"></use>
                  </svg>
                  <p>{{ searchresult.comment }}</p>
                </div>
              </div>

              <div class="searchresult_right">
                <p>{{ "文章id: " + searchresult.articleid }}</p>
                <p>{{ "作者: " + searchresult.author }}</p>
                <p>{{ "发布时间: " + searchresult.releasetime }}</p>
              </div>
            </div>
          </div>
        </el-scrollbar>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, ref, watch } from "vue";
import { useMainStore } from "@/store/index";

const store = useMainStore();

let input = ref("");
let dialogVisible = ref(false);
let searchresults = reactive([
  {
    title: "文章标题",
    content:
      "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容",
    look: 0,
    like: 0,
    comment: 0,
    articleid: "000000",
    author: "作者",
    releasetime: "2023/2/3",
  },
  {
    title: "文章标题",
    content:
      "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容",
    look: 0,
    like: 0,
    comment: 0,
    articleid: "000000",
    author: "作者",
    releasetime: "2023/2/3",
  },
  {
    title: "文章标题",
    content:
      "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容",
    look: 0,
    like: 0,
    comment: 0,
    articleid: "000000",
    author: "作者",
    releasetime: "2023/2/3",
  },
]);

watch(
  () => store.dialogVisible,
  (newV, oldV) => {
    dialogVisible.value = newV;
  }
);

const closeDialog = () => {
  store.dialogVisible = false;
};
</script>

<style scoped>
.searchframe {
  height: 60vh;
}
.searchtitle {
  padding-bottom: 1.5em;
  border-bottom: dashed #eee 3px;
}

.article {
  padding: 2em;
}
.articletitle {
  text-decoration: underline;
}
.infoframe {
  margin-top: 1em;
  padding-bottom: 2em;
  display: flex;
  justify-content: space-between;
  align-items: end;
  border-bottom: dashed 1px #000;
}

.searchresult_left {
  display: flex;
  font-size: 1.2em;
}
.searchresult_left div {
  margin-left: 2em;
}
.searchresult_left div:first-child {
  margin-left: 0;
}
.searchresult_left p {
  display: inline-block;
}
.searchresult_left svg {
  margin-right: 0.3em;
}
</style>
