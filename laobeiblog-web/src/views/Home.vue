<template>
  <div id="Home">
    <el-row>
      <el-col>
        <el-row>
          <el-col :span="24">
            <img
              src="@/assets/img/1536a1ad880511ebb6edd017c2d2eca2.jpg"
              id="bg"
            />
            <div id="slogan">
              <h1 id="title">捞杯博客</h1>
              <p id="titledescribe">欲买桂花同醉酒，终不似，少年游。</p>
            </div>
            <svg class="icon icon-xiangxia" aria-hidden="true">
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
                    <p>如入您眼，请上码云给仓库点上您的小星星，谢谢您的支持!</p>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>

            <!-- 循环文章列表 -->
            <el-row class="label">
              <el-col class="label-left" :span="10">
                <img
                  src="@/assets/img/8373377e69c3499d904877fab8c6f329.jpg"
                  alt="8373377e69c3499d904877fab8c6f329.jpg"
                  class="label-img"
                />
              </el-col>
              <el-col :span="12" class="label-right">
                <h2>文章标题</h2>
                <div>
                  <svg class="icon icon-tuding" aria-hidden="true">
                    <use xlink:href="#icon-tuding"></use>
                  </svg>
                  <span>置顶</span>
                  <svg class="icon icon-rili" aria-hidden="true">
                    <use xlink:href="#icon-rili"></use>
                  </svg>
                  <span>2023/1/31</span>
                  <svg class="icon icon-icon" aria-hidden="true">
                    <use xlink:href="#icon-icon"></use>
                  </svg>
                  <span>linux</span>
                </div>
                <p class="label-describe">
                  文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容
                  文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容
                </p>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="6" class="sidebar">
            <div>

            </div>
        </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup></script>

<style scoped>
#Home {
  width: 100vw;
  --label-border-radius: 0.5em;
  --info-font-size: 1.2em;
  --label-box-shadow: 10px 5px 40px 1px rgba(49, 49, 49, 0.3);
  --label-box-shadow-moveover: 10px 5px 30px 1px rgba(49, 49, 49, 0.6);
  --label-backgroud-color: #fff;
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

#content {
  transform: translateY(-6px);
  height: 100vh;
  background-image: linear-gradient(
    to bottom right,
    rgba(255, 218, 218, 0.5),
    rgba(196, 255, 232, 0.5)
  );
}
.info_box {
  margin-top: 3em;
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
#info {
  background: var(--label-backgroud-color);
  box-shadow: var(--label-box-shadow);
  padding: 1em;
  border-radius: var(--label-border-radius);
  transition: all 200ms linear;
}
#info:hover,
.label:hover {
  box-shadow: var(--label-box-shadow-moveover);
}
#info .icon-liangdang,
#info:nth-child(1) {
  font-size: var(--info-font-size);
}

/* 文章 */
.label {
  margin-top: 1em;
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
.label-right div{
    margin: .5em;
}
.label-right h2:hover {
    color: #9896DC;
    transition: all .2s linear;
}
.label-right div svg{
    margin: 0 .5em;
}
.label-right div svg:first-child{
    margin-left: 0;
}
.label-right div span{
    padding-right: .5em;
    border-right: solid 1px rgb(145, 145, 145);
}
.label-describe {
    overflow : hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;      /* 可以显示的行数，超出部分用...表示*/
  -webkit-box-orient: vertical;
}
</style>
