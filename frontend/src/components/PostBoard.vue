<template>
  <div class="submitform">
      <h3>Add Board</h3>
      <div v-if="!submitted">
        <div class="form-group">
            <label for="writer">writer</label>
            <input type="text" class="form-control" id="writer" required v-model="board.writer" name="writer">
        </div>
    
        <div class="form-group">
          <label for="title">title</label>
          <input type="text" class="form-control" id="title" maxlength="30" required v-model="board.title" name="title">
        </div>
    
        <div class="form-group">
          <label for="text">text</label>
          <input type="text" class="form-control" id="text" maxlength="255" required v-model="board.text" name="text">
        </div>

        <button @click="saveBoard" class="btn btn-success">Submit</button>
    </div>
    
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newBoard">Add</button>
    </div>
  </div>
</template>

<script>
import { axiosInstanceBoard } from '../http-common'

export default {
  name: 'post-board',
  data() {
    return {
      board: {
        id: 0,
        title: '',
        writer: '',
        text: '',
        hit: 0
      },
      submitted: false
    }
  },
  methods: {
    /* eslint-disable no-console */
    saveBoard() {
      var data = {
        title: this.board.title,
        writer: this.board.writer,
        text: this.board.text
      };
      axiosInstanceBoard
        .post("/board", data)
        .then(response => {
          this.board.id = response.data.id;
          // console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    
        this.submitted = true;
    },
    newBoard() {
      this.submitted = false;
      this.board = {};
    }
    /* eslint-enable no-console */
  }
}
</script>