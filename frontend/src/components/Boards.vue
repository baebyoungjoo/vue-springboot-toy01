<template>
  <div v-if="this.board">
    <h4>Board-details</h4>
    <div>
      <label>id: </label> {{this.board.id}}
    </div>
    <div>
      <label>title: </label> {{this.board.title}}
    </div>
    <div>
      <label>writer: </label> {{this.board.writer}}
    </div>
    <div>
      <label>createdOn: </label> {{this.board.createdOn}}
    </div>
    <span class="button btn-danger" @click="deleteBoard()">Delete</span>
  </div>
</template>

<script>
import http from "../http-common";
 
export default {
  name: "board",
  props: ["board"],
  methods: {
    /* eslint-disable no-console */
    updateActive(status) {
      var data = {
        id: this.board.id,
        title: this.board.title,
        writer: this.board.writer
      };
 
      http
        .put("/board/" + this.board.id, data)
        .then(response => {
          // console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteBoard() {
      http
        .delete("/board/" + this.board.id)
        .then(response => {
          // console.log(response.data);
          this.$emit("refreshData");
          this.$router.push('/list');
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>