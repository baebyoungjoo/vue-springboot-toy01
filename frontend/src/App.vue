<template>
  <div id="app" class="container-fluid">
    
    <Header/>
    
    <div id="content">
      <!-- <nav>
        <router-link class="btn btn-primary" to="/">Home</router-link>
        <router-link class="btn btn-primary" to="/list">Boards</router-link>
        <router-link class="btn btn-primary" to="/add">Add</router-link>
        <router-link class="btn btn-primary" to="/search">Search</router-link>
      </nav> -->
      <br/>
      <router-view/>
    </div>
    <div id="footer">
      <Footer />
    </div>
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
  padding: 130px 0 0;
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
