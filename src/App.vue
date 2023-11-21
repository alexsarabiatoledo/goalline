<template>
  <div id="app">
    <router-view class="mt-5"></router-view>
    <SideBar />
    
  </div>
</template>

<script>
import SideBar from './components/SideBar.vue'

export default {
  name: 'App',
  components: {
    SideBar
  }
}
</script>

<style></style>
