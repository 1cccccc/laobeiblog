<template>
  <div id="Home">
    <el-row>
      <el-col>
        <el-row>
          <el-col :span="24" id="head">
            <img
              src="@/assets/img/1536a1ad880511ebb6edd017c2d2eca2.jpg"
              id="bg"
              ref="bg"
            />
            <div id="slogan">
              <h1 id="title">捞杯博客</h1>
              <p id="titledescribe">欲买桂花同醉酒，终不似，少年游。</p>
            </div>
            <svg
              class="icon icon-xiangxia"
              aria-hidden="true"
              @click="scrollDown()"
            >
              <use xlink:href="#icon-xiangxia"></use>
            </svg>
          </el-col>
        </el-row>

        <el-row :gutter="20" id="content" justify="center">
          <el-col :span="12" class="info_box">
            <el-row id="info">
              <el-col>
                <el-row>
                  <el-col :span="1">
                    <svg class="icon icon-liangdang" aria-hidden="true">
                      <use xlink:href="#icon-lingdang"></use>
                    </svg>
                  </el-col>
                  <el-col :span="22">
                    <p>
                      如入您眼，请上Github给仓库点上您的小星星，谢谢您的支持!
                    </p>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>

            <!-- 循环文章列表 -->
            <el-row class="label" v-for="article in articles">
              <el-col class="label-left" :span="10">
                <img
                  src="@/assets/img/8373377e69c3499d904877fab8c6f329.jpg"
                  alt="8373377e69c3499d904877fab8c6f329.jpg"
                  class="label-img"
                />
              </el-col>
              <el-col :span="12" class="label-right">
                <router-link :to="'/articles/' + article.articleId">
                  <h2>{{ article.articleTitle }}</h2>
                </router-link>
                <div>
                  <svg
                    class="icon icon-tuding"
                    aria-hidden="true"
                    v-if="article.isTop"
                  >
                    <use xlink:href="#icon-tuding" v-if="article.isTop"></use>
                  </svg>
                  <span v-if="article.isTop">置顶</span>
                  <svg class="icon icon-rili" aria-hidden="true">
                    <use xlink:href="#icon-rili"></use>
                  </svg>
                  <span>{{ article.createTime }}</span>
                  <svg class="icon icon-icon" aria-hidden="true">
                    <use xlink:href="#icon-icon"></use>
                  </svg>
                  <span>{{ article.tagName }}</span>
                </div>
                <p class="label-describe">
                  {{ article.articleDescribe }}
                </p>
              </el-col>
            </el-row>
          </el-col>

          <el-col :span="4" id="sidebar">
            <!-- 用户信息栏 -->
            <div id="user-info">
              <div class="user-img">
                <img :src="userinfo.userlogourl" :alt="userinfo.userlogourl" />
              </div>
              <h2 class="user-name">{{ userinfo.username }}</h2>
              <p class="user-describe">{{ userinfo.userslogan }}</p>
              <el-row justify="space-between">
                <el-col :span="8">
                  <h3 class="archives-title">文章</h3>
                  <p class="archives-num">{{ userinfo.articlenum }}</p>
                </el-col>
                <el-col :span="8">
                  <h3 class="archives-title">分类</h3>
                  <p class="archives-num">{{ userinfo.classificationnum }}</p>
                </el-col>
                <el-col :span="8">
                  <h3 class="archives-title">标签</h3>
                  <p class="archives-num">{{ userinfo.labelnum }}</p>
                </el-col>
              </el-row>
              <el-button
                type="primary"
                class="bookmark"
                :icon="Discount"
                round
                @click="joinbookmark()"
                >加入书签</el-button
              >
            </div>

            <div id="followUs">
              <div class="followUs-title">
                <svg class="icon icon-yonghu" aria-hidden="true">
                  <use xlink:href="#icon-yonghu"></use>
                </svg>
                <h3>关注我们</h3>
              </div>
              <div class="followUs-content">
                <div>
                  <svg class="icon icon-QQ" aria-hidden="true">
                    <use xlink:href="#icon-QQ"></use>
                  </svg>
                  <p>{{ aboutus.qq }}</p>
                </div>

                <div>
                  <svg class="icon icon-xinfeng" aria-hidden="true">
                    <use xlink:href="#icon-xinfeng"></use>
                  </svg>
                  <p>{{ aboutus.email }}</p>
                </div>

                <div>
                  <svg class="icon icon-github" aria-hidden="true">
                    <use xlink:href="#icon-github"></use>
                  </svg>
                  <p>
                    <a href="https://github.com/1cccccc/-">{{
                      aboutus.github
                    }}</a>
                  </p>
                </div>

                <div>
                  <svg class="icon icon-wechat" aria-hidden="true">
                    <use xlink:href="#icon-wechat"></use>
                  </svg>
                  <p>{{ aboutus.wechat }}</p>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { Discount } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import { reactive, ref,watch } from "vue";
import { useRouter } from "vue-router";
import { useMainStore } from "@/store/index";
import homeApi from "@/api/HomeApi";

const store = useMainStore();
const router = useRouter();

let articles = reactive([]);

let userinfo = reactive({
  username: "请先登录",
  userslogan: "欲买桂花同载酒，终不似，少年游",
  articlenum: 0,
  classificationnum: 0,
  labelnum: 0,
  userlogourl: "https://orange-product-my.oss-cn-shenzhen.aliyuncs.com/laobeiblog/2023-03-14/1_a2c65bad-3ee0-4b8b-80c8-1ed3d6e4a08b.jpg",
});
userinfo.username=store.userinfo==null?"请先登录":store.userinfo.nickname


let aboutus = reactive({
  qq: "2952309223",
  email: "lb16673514910@163.com",
  github: "https://github.com/1cccccc/-",
  gitee: "https://github.com/1cccccc/-",
  wechat: "lb16673514910",
});

let tags = reactive({});
//获取文章描述列表
homeApi.getArticleDescribeList().then(async (d) => {
  articles.push(...d.data.data.list);

  let s = "";
  articles.forEach((item) => {
    s += item.tagId + ",";
  });
  s = s.substring(0, s.length - 1);
  await homeApi.getTagByIds(s).then((d) => {
    d.data.data.forEach((item) => {
      if (!(item.tagId in tags)) {
        tags[item.tagId] = item.tagName;
      }
    });

    articles.forEach((item) => {
      item["tagName"] = tags[item.tagId];
    });
  });
});

//添加书签按钮
const joinbookmark = () => {
  ElMessage({
    showClose: true,
    grouping: true,
    message: "请使用Ctrl+D键加入书签",
    type: "warning",
  });
};

//向下看按钮
let bg = ref(null);
const scrollDown = () => {
  let bgParentHeight = bg.value.parentNode.clientHeight; //上面背景页面的高度

  store.scrollDown = true;
};

const opengithub = () => {};

watch(
  () => store.userinfo,
  (newV, oldV) => {
    if (newV) {
      userinfo.username=store.userinfo.nickname;
    } else {
      userinfo.username="请先登录"
    }
  }
);
</script>

<style scoped>
a {
  text-decoration: none; /* 去除默认的下划线 */
  outline: none; /* 去除旧版浏览器的点击后的外虚线框 */
  color: #000; /* 去除默认的颜色和点击后变化的颜色 */
}

#Home {
  overflow: hidden;
  width: 100vw;
  --label-border-radius: 0.5em;
  --info-font-size: 1.2em;
  --label-box-shadow: 0px 5px 50px 1px rgba(49, 49, 49, 0.1);
  --label-box-shadow-moveover: 0px 5px 30px 1px rgba(49, 49, 49, 0.6);
  --label-backgroud-color: #fff;
  --laber-transition: all 200ms linear;
}
#oneLine,
#twoLine {
  width: 100vw;
  height: 100vh;
}
#bg {
  z-index: -10;
  width: 100%;
  height: 100vh;
}
#head {
  width: 100vw;
  height: 100vh;
}
#content {
  background-image: linear-gradient(
    to bottom right,
    rgba(255, 218, 218, 0.5),
    rgba(196, 255, 232, 0.5)
  );
  padding-top: 3em;
}
#slogan {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-weight: bolder;
  text-align: center;
}
#title {
  font-size: 3em;
}
#titledescribe {
  font-size: 1.5em;
}
.icon-xiangxia {
  color: white;
  font-size: 2em;
  position: absolute;
  bottom: 1%;
  left: 50%;
  transform: translateX(-50%);
  animation: jiantou 0.7s ease-out alternate infinite;
  cursor: pointer;
}
@keyframes jiantou {
  0% {
    bottom: 1%;
  }
  100% {
    bottom: 3%;
  }
}

/* 最上面的信息 */
#info,
#followUs {
  background: var(--label-backgroud-color);
  box-shadow: var(--label-box-shadow);
  padding: 1em;
  border-radius: var(--label-border-radius);
  transition: var(--laber-transition);
}
#info:hover,
.label:hover,
#user-info:hover,
#followUs:hover {
  box-shadow: var(--label-box-shadow-moveover);
}
#info .icon-liangdang,
#info:nth-child(1) {
  font-size: var(--info-font-size);
}

/* 文章 */
.label {
  margin: 1em 0;
  border-radius: var(--label-border-radius);
  box-shadow: var(--label-box-shadow);
  transition: all 200ms linear;
  background-color: var(--label-backgroud-color);
  overflow: hidden;
}

.label-img {
  width: 100%;
  height: 100%;
}

.label-right {
  padding: 0px 3em;
  margin: auto 0;
}
.label-right div {
  margin: 0.5em 0;
}

.label-right h2 {
  word-break: break-all;
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  color: rgba(0, 0, 0, 0.7);
}
.label-right h2:hover {
  color: #9896dc;
  transition: all 0.2s linear;
  cursor: pointer;
}
.label-right div svg {
  margin-right: 0.5em;
}
.label-right div svg:first-child {
  margin-left: 0;
}
.label-right div span {
  margin-right: 0.5em;
  padding-right: 0.5em;
  border-right: solid 1px rgb(145, 145, 145);
}
.label-describe {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3; /* 可以显示的行数，超出部分用...表示*/
  -webkit-box-orient: vertical;
}

/* 用户信息栏 */
#user-info {
  padding: 2em;
  text-align: center;
  transition: all;
  background-color: var(--label-backgroud-color);
  border-radius: var(--label-border-radius);
  box-shadow: var(--label-box-shadow);
  transition: var(--laber-transition);
}
.user-img {
  width: 8em;
  height: 8em;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
  left: 50%;
  transform: translateX(-50%);
  transform-origin: 0% 50%;
}

.user-img img {
  width: 8em;
  /* transform: translateX(-20%); */
}

.user-name,
.user-describe,
.user-describe + .el-row,
.bookmark {
  margin: 0.5em 0;
}
.user-name {
  margin-bottom: 0.1em;
}
.user-describe {
  margin: 0 0 1em 0;
}

#followUs {
  margin-top: 1.5em;
  padding: 2em !important;
}
.followUs-title {
  margin-bottom: 1em;
  text-align: center;
}
.followUs-title svg {
  font-size: 3em;
  color: #ff3838;
  margin-right: 0.1em;
}
.followUs-title h3 {
  font-size: 1.7em;
  display: inline-block;
  transform: translateY(-12%);
}
.followUs-content div {
  margin-bottom: 1em;
  position: relative;
  border: solid 1px #ccc;
  padding: 0.3em;
  cursor: pointer;
}
.followUs-content svg {
  font-size: 1.5em;
  margin-right: 0.1em;
}
.followUs-content p {
  font-size: 0.8em;
  display: inline-block;
  transform: translateY(-20%);
}

.archives-title {
  font-weight: bolder;
}
.archives-num {
  font-size: 1.3em;
}
</style>
