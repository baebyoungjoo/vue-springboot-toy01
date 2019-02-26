<template>
  <div id="app">
    
    <Header/>
    
    <div id="content">
      <br/>
      <router-view/>
    </div>
    
    <Footer />

  </div>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'

export default {
  name: 'App',
  components: {
    'Header': Header,
    'Footer': Footer
  }
}
</script>

<style>
#app {
  position: relative;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

#content {
  padding: 40px 0 0;
  margin: 0 auto 40px;
}

.lnb {
  position: fixed;
  z-index: 100;
  float: left;
  width: 200px;
  height: 100vh;
  color: aliceblue;
  background-color: cadetblue;
}
</style>
