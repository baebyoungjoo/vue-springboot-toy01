<template>
  <div>
    <!-- TODO -->
    <h1>header</h1>
    <span>{{ dpTime }}</span>
    <hr/>
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