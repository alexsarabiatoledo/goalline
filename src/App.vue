<template>
<div id="app"></div>
  <NavBar/>
  <router-view></router-view>
  <VirtualAssistant />
  <FooterComponent/>
</template>

<script>
import NavBar from './components/NavBar.vue'
import VirtualAssistant from './components/VirtualAssistant.vue';
import FooterComponent from './components/FooterComponent.vue'

export default {
  name: 'App',
  components: {
    NavBar,
    VirtualAssistant,
    FooterComponent
}
    
  }

</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 0px;
} 
</style>