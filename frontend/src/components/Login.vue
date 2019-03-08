<template>
  <div>
    <!-- TODO -->
    <input type="text" id="userId" name="userId" placeholder="userId" v-model="$v.userId.$model"/>
    <div v-if="!$v.userId.required">Field is required</div>
    <div v-else-if="!$v.userId.minLength">Name must have at least {{$v.userId.$params.minLength.min}} letters.</div>
    <input type="password" id="password" placeholder="password" v-model="password"/>

    <!-- <router-link to="/"> -->
      <button @click="loginMember" class="btn btn-sm btn-info">로그인</button>
    <!-- </router-link> -->
    <router-link class="btn btn-sm btn-outline-info" to="/join/joinTerms">회원가입</router-link>
  </div>
</template>

<script>
  /* TODO */
import { axiosInstanceMember } from '../http-common'
import { required, minLength, between } from 'vuelidate/lib/validators'

export default {
  name: 'login',
  data() {
    return {
      userId: '',
      password: '',
    }
  },
  validations: {
    userId: {
      required,
      minLength: minLength(3),
    },
  },
  methods: {
    loginMember() {
      var data = {
        userId: this.userId,
        password: this.password,
      }

      axiosInstanceMember
        .post('/login', data)
        .then(response => {
          console.log("success")
        })
        .catch(error => {
          this.$noty.warning("아이디 혹은 비밀번호를 확인해주세요!", {
            theme: 'semanticui',
            timeout: 3000
          })
        });
    },
  }
}
</script>