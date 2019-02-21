<template>
  <div>
    <!-- TODO -->
    <div class="paging_step">
      <span class="ico_join ico_paging on"></span>
      <span class="ico_join ico_paging"></span>
    </div>
    
    <div class="row justify-content-md-center terms_agreement">
      <div class="col-md-3 title_agreement">약관 동의</div>
      <div class="col-md-1 ml-auto right"><input type="checkbox"/></div>
      <div class="w-100"></div>
      <Terms/>
    </div>

    <router-link class="btn btn-sm" to="/login">이전으로</router-link>
    <router-link class="btn btn-sm" to="/join/signUp">동의하기</router-link>
  </div>
</template>

<script>
/* TODO */
import Terms from '@/components/joins/Terms.vue'

export default {
  name: 'JoinTerms',
  components: {
    'Terms': Terms
  }
}
</script>

<style src="@/assets/styles/style.css">
/* @import '../../../../frontend/src/assets/styles/style.css'; */
</style>