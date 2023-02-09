<template>
    <div id="Explain">
      <el-row>
      <el-col>
        <el-row>
          <div id="head">
            <img
              src="@/assets/img/58549087b0d8455eb399ff0dba34f6c0.jpg"
              alt="58549087b0d8455eb399ff0dba34f6c0.jpg"
              class="head-bg"
            />
            <h1 class="title">使用说明</h1>
          </div>
        </el-row>
        <el-row :gutter="20" id="content" justify="center">
          <div id="data">
            <h1>致使用者</h1>
            <p>该项目定位于个人博客，您可以在这里于其他大神进行交流</p>
            <h2>技术栈</h2>
            <p>前端：Vite+ElementUi+Vue-router+Pinia+Axios+</p>
            <p>后端：springboot+docker+redis+mysql+Swagger+MybatisPlus+ElasticSearch</p>
            <p>其他：接入QQ、微信、微博、Gitee第三方登陆，接入阿里云OSS对象存储，阿里云短信服务</p>
            <p>如果该项目对您有用，麻烦动动您的小手，给我的仓库点上您的Star!</p>
            <p>项目地址：https://github.com/1cccccc/-</p>
            <h2>使用说明</h2>
            <p>您可以在个人板块的发布文章中来发布您的作品，在文章管理模块中您可以对您的文章进行修改、删除、修改分类和标签等功能，甚至为您提供了个人云功能，帮您安全保存您的文件!</p> 
          </div>
        </el-row>
      </el-col>
    </el-row>
    </div>
  </template>
  
  <script setup></script>
  
  <style scoped>
  #Explain {
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
  