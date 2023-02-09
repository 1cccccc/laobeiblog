<template>
  <div id="TagsInfo">
    <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">
              {{ router.currentRoute.value.params.id + "标签详情" }}
            </h1>
          </div>
        </el-row>
        <el-row :gutter="20" id="content" justify="center">
          <div id="archives">
            <!-- 循环文章列表 -->
            <div class="archive" v-for="item in archivesData">
              <img
                src="@/assets/img/61ac5e440dac4d97a6624e05044c4074.jpg"
                alt="61ac5e440dac4d97a6624e05044c4074.jpg"
                class="archive-img"
              />
              <h2 class="archive-title">{{ item.title }}</h2>
              <div class="info">
                <div class="info-left">
                  <svg class="icon icon-shizhong" aria-hidden="true">
                    <use xlink:href="#icon-shizhong"></use>
                  </svg>
                  <p class="archive-createtime">{{ item.createtime }}</p>
                </div>
                <div class="info-right">
                  <svg class="icon icon-biaoqian" aria-hidden="true">
                    <use xlink:href="#icon-biaoqian"></use>
                  </svg>
                  <p class="archive-tag">{{ item.tag }}</p>
                </div>
              </div>
            </div>
          </div>
          <p class="tip">修改文章标签请移步到文章管理页，谢谢！</p>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
console.log();

let archivesData = reactive([
  {
    id: 0,
    url: "",
    createtime: "2023/2/8",
    title: "linux安装logstash同步mysql数据到es",
    tag: "linux",
  },
  {
    id: 1,
    url: "",
    createtime: "2023/2/8",
    title: "linux使用docker安装elasticsearch",
    tag: "linux",
  },
]);
</script>

<style scoped>
#TagsInfo {
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

#archives {
  display: flex;
  justify-content: center;
  padding-bottom: 5em;
}

.archive {
  height: 33vh;
  width: 18vw;
  margin-top: 1em;
  border-radius: var(--label-border-radius);
  box-shadow: var(--label-box-shadow);
  transition: var(--laber-transition);
  background-color: var(--label-backgroud-color);
  overflow: hidden;
  margin: 2em;
  padding-bottom: 7em;
  position: relative;
}

.archive:hover {
  box-shadow: var(--label-box-shadow-moveover);
}

.archive-img {
  width: 100%;
}
.archive-title {
  font-size: 1em;
  margin: 1em;
  font-weight: bolder;
}
.info {
  display: flex;
  justify-content: space-between;
  margin: 1em;
  box-sizing: content-box;
  padding-bottom: 1em;
  border-bottom: solid 1px #ccc;
}
.info-left,
.info-right {
  display: flex;
  justify-content: start;
}
.info-left svg,
.info-right svg {
  font-size: 1.6em;
  margin-right: 0.3em;
}
.tip {
  position: absolute;
  bottom: 5%;
  left: 50%;
  transform: translateX(-50%);
}
</style>
