<template>
  <div id="Archives">
    <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">归档</h1>
          </div>
        </el-row>

        <el-row :gutter="20" id="content" justify="center">
          <div id="archives-data">
            <div id="datainfo">
              <h3>
                {{
                  "截止目前您一共发布了" +
                  dataobj.data.length +
                  "篇文章，继续加油！"
                }}
              </h3>
              <el-timeline>
                <el-timeline-item
                  v-for="(item, index) in dataobj.data"
                  :key="index"
                  :timestamp="item.createtime"
                >
                  <router-link :to="'/articles/' + item.id">
                    <span class="data-title">{{ item.title }}</span>
                  </router-link>
                </el-timeline-item>
              </el-timeline>
            </div>

            <div class="demo-pagination-block">
              <el-pagination
                v-model:current-page="currentPage3"
                v-model:page-size="pageSize3"
                :small="small"
                :disabled="disabled"
                :background="background"
                layout="prev, pager, next, jumper"
                :total="dataobj.data.length"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </div>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";

let dataobj = reactive({
  data: [
    {
      id: 0,
      createtime: "2023/2/7",
      title: " linux安装logstash同步mysql数据到es",
    },
    {
      id: 0,
      createtime: "2023/2/7",
      title: " linux安装logstash同步mysql数据到es",
    },
  ],
});
</script>

<style scoped>
.data-title {
    font-size: 1.2em;
    color: #666;
}
#datainfo h3:first-child{
    margin-bottom: 1.5em;
}
#archives-data {
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
}

#archives-data:hover {
  box-shadow: var(--label-box-shadow-moveover);
}

#Archives {
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
  height: 100vh;
  background-image: linear-gradient(
    to bottom right,
    rgba(255, 218, 218, 0.5),
    rgba(196, 255, 232, 0.5)
  );
  padding-top: 2em;
}

.demo-pagination-block {
    position: absolute;
    left: 50%;
    bottom: 8%;
    transform: translateX(-50%);
}
.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}
</style>
