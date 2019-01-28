<template>
  <div class="searchform">
    <h4>Find Board By Id</h4>
    <div class="form-group">
      <input type="number" class="form-control" id="id" required v-model="id" name="id">
    </div>
 
    <div class="btn-group">
      <button @click="searchBoards" class="btn btn-success">Search</button>
    </div>
 
    <ul class="search-result">
      <li v-for="(board, index) in boards" :key="index">
        <h6>{{board.title}} ({{board.writer}})</h6>
      </li>
    </ul>
  </div>
</template>
 
<script>
import http from "../http-common";
 
export default {
  name: "search-customer",
  data() {
    return {
      id: 1,
      boards: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    searchBoards() {
      http
        .get("/board/" + this.id)
        .then(response => {
          this.boards = response.data; // JSON are parsed automatically.
          // console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>