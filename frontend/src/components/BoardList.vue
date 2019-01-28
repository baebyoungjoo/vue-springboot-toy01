<template>
    <div class="list row">
      <h4>Board List</h4>
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">NO</th>
            <th scope="col">title</th>
            <th scope="col">writer</th>
            <th scope="col">text</th>
            <th scope="col">hit</th>
            <th scope="col">created_on</th>
            <th scope="col">updated_on</th>
          </tr>
        </thead>
        <tbody>
          <tr class="table-info" v-for="(board, index) in boards" :key="index">
            <th scope="row">{{ board.id }}</th>
            <td>
              <router-link :to="{ name: 'board-details', params: { board: board, id: board.id } }">
                {{ board.title }}
              </router-link>
            </td>
            <td>{{ board.writer }}</td>
            <td>{{ board.text }}</td>
            <td>{{ board.hit }}</td>
            <td>{{ board.createdOn }}</td>
            <td>{{ board.updatedOn }}</td>
          </tr>
        </tbody>
      </table>
      <router-view @refreshData="refreshList"></router-view>
    </div>
</template>

<script>
import http from "../http-common"
/* eslint-disable no-console */
export default {
  name: 'HelloWorld',
  data () {
    return {
      boards: []
    }
  },
  methods: {
    retrieveBoards() {
      http.get("/boards")
      .then(response => {
        this.boards = response.data
        // console.log(response.data)
      })
      .catch(e => {
        console.log(e)
      })
    },
    refreshList() {
      this.retrieveBoards()
    }
  },
  mounted() {
    this.retrieveBoards()
  }
}
/* eslint-disable no-console */
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
