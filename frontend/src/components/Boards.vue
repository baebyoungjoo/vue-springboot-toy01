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
<!-- 
    <span v-if="this.board"
      @click="updateActive(false)"
      class="button is-small btn-primary">Inactive</span>
    <span v-else
      @click="updateActive(true)"
      class="button is-small btn-primary">Active</span>
   -->
    <span class="button btn-danger" @click="deleteBoard()">Delete</span>
  </div>
  <div v-else>
    <br/>
    <p>Please click on a Customer...</p>
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
        writer: this.board.writer,
        createdOn: this.board.createdOn
      };
 
      http
        .put("/board/" + this.board.id, data)
        .then(response => {
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteBoard() {
      http
        .delete("/board/" + this.board.id)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push('/');
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>