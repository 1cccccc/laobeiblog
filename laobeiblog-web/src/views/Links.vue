<template>
    <div id="Links">
      <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">友链</h1>
          </div>
        </el-row>
        <el-row :gutter="20" id="content" justify="center">
          <div id="data"></div>
        </el-row>
      </el-col>
    </el-row>
    </div>
  </template>
  
  <script setup></script>
  
  <style scoped>
  #Links {
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
  