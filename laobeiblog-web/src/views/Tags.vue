<template>
  <div id="Tags">
    <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">标签</h1>
          </div>
        </el-row>
        <el-row :gutter="20" id="content" justify="center">
          <div id="data">
            <h2 id="totalinfo">{{ '共有'+dataObj.length+'个标签' }}</h2>
            <div id="tags">
              <router-link v-for="item in dataObj" :to="'/tags/' + item.id">
                <p>{{ item.tagname }}</p>
              </router-link>
            </div>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
let dataObj = reactive([
  {
    id: 0,
    tagname: "springboot",
  },
  {
    id: 1,
    tagname: "linux",
  },
]);
</script>

<style scoped>
#totalinfo {
  font-size: 2em;
  font-weight: 900;
  margin-bottom: .5em;
}
#tags {
  display: flex;
  justify-content: start;
}
#tags p {
  margin: 0 .3em;
  font-size: 1.5em;
  transition: all .5s ease;
  transform-origin: 50% 50%;
}
#tags p:hover {
  color: #9896dc;
  transform: scale(1.1);
}


#Tags {
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
  width: 50%;
  min-height: 40vh;
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
  text-align: center;
}

#data:hover {
  box-shadow: var(--label-box-shadow-moveover);
}

a {
  text-decoration: none;
  color: #000;
}
</style>
