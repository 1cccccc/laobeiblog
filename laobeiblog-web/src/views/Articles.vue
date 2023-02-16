<template>
  <div id="Articles">
    <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <div class="head-title">
              <h1>{{ headinfo.title }}</h1>
              <div id="head-content">
                <div>
                  <svg class="icon icon-zhifeiji" aria-hidden="true">
                    <use xlink:href="#icon-zhifeiji"></use>
                  </svg>
                  <p>{{ "发表于：" + headinfo.createtime }}</p>
                  <svg class="icon icon-gengxin" aria-hidden="true">
                    <use xlink:href="#icon-gengxin"></use>
                  </svg>
                  <p>{{ "更新于：" + headinfo.updatetime }}</p>
                  <svg class="icon icon-biaoqian1" aria-hidden="true">
                    <use xlink:href="#icon-biaoqian1"></use>
                  </svg>
                  <p>{{ headinfo.tag }}</p>
                </div>
                <div>
                  <svg class="icon icon-ziti" aria-hidden="true">
                    <use xlink:href="#icon-ziti"></use>
                  </svg>
                  <p>{{ "字数统计：" + headinfo.wordnum }}</p>
                  <svg class="icon icon-shizhong" aria-hidden="true">
                    <use xlink:href="#icon-shizhong"></use>
                  </svg>
                  <p>{{ "阅读时长：" + headinfo.readtime }}</p>
                  <svg class="icon icon-yanjing1" aria-hidden="true">
                    <use xlink:href="#icon-yanjing1"></use>
                  </svg>
                  <p>{{ "阅读量：" + headinfo.readnum }}</p>
                  <svg class="icon icon-pinglun1" aria-hidden="true">
                    <use xlink:href="#icon-pinglun1"></use>
                  </svg>
                  <p>{{ "评论数：" + headinfo.comment }}</p>
                </div>
              </div>
            </div>
          </div>
        </el-row>
        <el-row :gutter="20" id="content" justify="center">
          <el-col :span="12">
            <div id="data">
              <v-md-preview-html
                ref="preview"
                :html="html"
                preview-class="github-markdown-body"
              ></v-md-preview-html>
            </div>
          </el-col>
          <el-col :span="4">
            <div id="catalogue">
              <div
                class="anchor-tag"
                v-for="anchor in state.titleData"
                :key="anchor"
              >
                {{ anchor.title }}
              </div>
            </div>
            <div id="recommend"></div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted,getCurrentInstance } from "vue";

let headinfo = reactive({
  title: "文章详情",
  createtime: "2023/2/13",
  updatetime: "2023/2/13",
  tag: "linux",
  wordnum: "3.5k",
  readtime: "9分钟",
  readnum: "3000",
  comment: 19,
});

let html = ref(
  '<h1 data-v-md-heading="kasjdkajsdk" data-v-md-line="1">kasjdkajsdk</h1>'
);

const state=reactive({
  id: 0,
  titleData: []
})

const preview = ref(null)

const { proxy } = getCurrentInstance()
onMounted(async () => {
  // await article.GetById(state.id).then(res => {
  //   articleForm.content = res.data.data.content
  //   articleForm.title = res.data.data.title
  // })

  const anchors = proxy.$refs.preview.$el.querySelectorAll('h1,h2,h3,h4,h5,h6')
  // console.log('%c [ anchors ]-35', 'font-size:13px; background:pink; color:#bf2c9f;', anchors)
  const title = Array.from(anchors).filter((title) => {
    return !!title.innerText.trim()
  })
  if (!title.length) {
    state.titleData = []
    return
  }
  const hTags = Array.from(
    new Set(
      title.map((title) => {
        return title.tagName
      })
    )
  ).sort()
  state.titleData = title.map((el) => {
    return {
      title: el.innerText,
      lineIndex: el.getAttribute('data-v-md-line'),
      indent: hTags.indexOf(el.tagName)
    }
  })
})
</script>

<style scoped>
#Articles {
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
.head-title {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
#head-content {
  color: #fff;
  font-size: 1rem;
}
#head-content div {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0.5em 0;
}
#head-content div svg {
  margin: 0 0.2em;
}
#head-content div p {
  padding-right: 0.3em;
  border-right: solid #fff 2px;
}
#head-content div p:last-child {
  border: none;
}

#content {
  background-image: linear-gradient(
    to bottom right,
    rgba(255, 218, 218, 0.5),
    rgba(196, 255, 232, 0.5)
  );
  padding-top: 2em;
}

#data,
#catalogue,
#recommend {
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
}

#data:hover {
  box-shadow: var(--label-box-shadow-moveover);
}
</style>
