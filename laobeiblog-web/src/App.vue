<template>
  <TopNavBar />
  <!-- <transition name="fade"> -->
    <router-view></router-view>
  <!-- </transition> -->
</template>

<script setup>
import TopNavBar from "./components/TopNavBar.vue";
</script>

<style>
#app {
  max-width: 100vw !important;
  padding: 0 !important;
  margin: 0 !important;
  overflow-x: hidden;
  user-select: none;
}

.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

.fade-enter-active,.fade-leave-active{
  transition: all .5s ease;
}
.fade-enter-form,.fade-leave-to{
    transform: translateY(-100px);
}
.fade-enter-to,.fade-leave-form{
  transform: translateY(0px);
}
</style>
