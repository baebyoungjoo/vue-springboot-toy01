<template>
  <div>
    <!-- TODO -->
    <input type="text" id="id" name="name" placeholder="id" v-model="$v.name.$model"/>
    <div v-if="!$v.name.required">Field is required</div>
    <div v-if="!$v.name.minLength">Name must have at least {{$v.name.$params.minLength.min}} letters.</div>
    <input type="password" id="password" placeholder="password"/>

    <router-link class="btn btn-sm btn-outline-primary" to="/home">로그인</router-link>
    <router-link class="btn btn-sm btn-outline-info" to="/join/joinTerms">회원가입</router-link>
  </div>
</template>

<script>
  /* TODO */
import { required, minLength, between } from 'vuelidate/lib/validators'

export default {
  data() {
    return {
      name: '',
    }
  },
  validations: {
    name: {
      required,
      minLength: minLength(3),
    },
  }
}
</script>