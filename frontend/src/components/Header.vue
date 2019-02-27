<template>
  <div>
    <div class="navbar fixed-top navbar-dark bg-primary">
      <!-- TODO -->
        <router-link class="btn btn-primary" to="/">Home</router-link>
        <router-link class="btn btn-primary" to="/list">Boards</router-link>
        <router-link class="btn btn-primary" to="/add">Add</router-link>
        <router-link class="btn btn-primary" to="/search">Search</router-link>
        <router-link class="btn btn-primary" to="/login">Login</router-link>
      </nav>
      <span>{{ dpTime }}</span>
    </div>
  </div>
</template>

<script>
import moment from 'moment'

/* TODO */
export default {
  name: 'header',
  data() {
    return{
      dpTime: moment().format('YYYY-MM-DD HH:mm:ss')
    }
  },
  methods: {
    updateTime() {
      setInterval(() => {
        this.dpTime = moment().format('YYYY-MM-DD HH:mm:ss')
      }, 1000);
    }
  },
  created: function() {
    this.updateTime()
  }
}
</script>

<style>
</style>