<template>
  <div v-if="this.board">
    <h4>Board-details</h4>
    <table class="table table-hover" style="width: 100%;">
      <tbody>
        <tr>
          <td>ID</td>
          <td>{{ this.board.id }}</td>
          <td>DATE</td>
          <td>{{ this.board.createdOn.slice(0,19).replace("T"," ") }}</td>
        </tr>
        <tr>
          <td>WRITER</td>
          <td>{{ this.board.writer }}</td>
          <td>TITLE</td>
          <td>{{ this.board.title }}</td>
        </tr>
        <tr>
          <td><label for="text">TEXT</label></td>
          <td colspan="3">
          <textarea class="form-control" id="text" maxlength="255" required v-model="board.text" name="text"/>
          </td>
        </tr>
      </tbody>
    </table>
    
    <span class="button btn-lg btn-info" @click="updateBoard()">Update</span>
    <span class="button btn-lg btn-danger" @click="deleteBoard()">Delete</span>
  </div>
</template>

<script>
import { axiosInstanceBoard } from "../http-common";
 
export default {
  name: "board",
  props: ["board"],
  methods: {
    /* eslint-disable no-console */
    updateBoard() {
      var data = {
        id: this.board.id,
        title: this.board.title,
        text: this.board.text
      };
 
      axiosInstanceBoard
        .put("/board/" + this.board.id, data)
        .then(response => {
          // console.log(response.data);
          this.$router.push('/list');
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteBoard() {
      axiosInstanceBoard
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