<template>
  <div id="app" class="container-fluid">
      <div class="site-info">
          <h1>##########</h1>
          <h3>Vue SpringBoot Board</h3>
      </div>
      <nav>
          <router-link class="btn btn-primary" to="/">Home</router-link>
          <router-link class="btn btn-primary" to="/list">Boards</router-link>
          <router-link class="btn btn-primary" to="/add">Add</router-link>
          <router-link class="btn btn-primary" to="/search">Search</router-link>
      </nav>
      <br/>
      <router-view/>
    </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
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
