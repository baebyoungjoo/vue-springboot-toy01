<template>
    <div class="list row">
      <h4>Board List</h4>
      <table class="table table-hover">
        <thead>
          <tr>
            <th style="width: 10%;" scope="col">NO</th>
            <th style="width: 10%;" scope="col">title</th>
            <th style="width: 15%;" scope="col">writer</th>
            <th scope="col">text</th>
            <th style="width: 5%;" scope="col">hit</th>
            <th style="width: 15%;" scope="col">created_on</th>
            <th style="width: 15%;" scope="col">updated_on</th>
          </tr>
        </thead>
        <tbody>
          <tr class="table-info" v-for="(board, index) in boards" :key="index">
            <th scope="row">{{ board.id }}</th>
            <td>
              <router-link :to="{ name: 'board-details', params: { board: board, id: board.id } }" >
                <span @click="hitup(board.id)">{{ board.title }}</span>
              </router-link>
            </td>
            <td>{{ board.writer }}</td>
            <td>{{ board.text }}</td>
            <td>{{ board.hit }}</td>
            <td>{{ board.createdOn.slice(0,19).replace("T"," ") }}</td>
            <td>{{ board.updatedOn.slice(0,19).replace("T"," ") }}</td>
          </tr>
        </tbody>
      </table>
      <!-- <router-view @refreshData="refreshList"></router-view> -->
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
    },
    hitup(boardId) {
      http
        .put("/board/" + boardId)
        .then(response => {
          // console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
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
