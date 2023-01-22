<template>
  <TopNavBar />
  <router-view></router-view>
</template>

<script setup>
import TopNavBar from "./components/TopNavBar.vue";
</script>

<style>
#app {
  max-width: 100vw !important;
  padding: 0 !important;
  margin: 0 !important;
}

.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
