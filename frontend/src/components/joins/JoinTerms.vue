<template>
  <div>
    <!-- TODO -->
    <div class="paging_step">
      <span class="ico_join ico_paging on"></span>
      <span class="ico_join ico_paging"></span>
    </div>
    
    <div class="row justify-content-md-center terms_agreement">
      <div class="col-md-3 title_agreement">약관 동의</div>
      <div class="col-md-1 ml-auto right"><input type="checkbox" name="terms" id="terms"/></div>
      <div class="w-100"></div>
      <Terms/>
    </div>

    <div class="row justify-content-md-center terms_agreement">
      <div class="col-md-4 title_agreement">개인정보 수집 및 이용 동의</div>
      <div class="col-md-1 ml-auto right"><input type="checkbox" name="privacy" id="privacy"/></div>
      <div class="w-100"></div>
      <TermsPrivacy/>
    </div>

    <router-link class="btn btn-sm btn-outline-warning" to="/login">이전으로</router-link>
    <router-link class="btn btn-sm btn-primary" :class="{disabled: checkedStatus}" to="/join/signUp">동의하기</router-link>
  </div>
</template>

<script>
/* TODO */
import Terms from '@/components/joins/Terms.vue'
import TermsPrivacy from '@/components/joins/Terms_privacy.vue'

export default {
  name: 'JoinTerms',
  data() {
    return {
      disabled: true,
    }
  },
  components: {
    'Terms': Terms,
    'TermsPrivacy': TermsPrivacy
  },
  methods: {
    checkedStatus() {
      /* TODO 약관 및 이용동의에 체크되어있지 않으면 비활성화시킴(return true) */
    },
  }
}
</script>

<style src="@/assets/styles/style.css">
/* @import '../../../../frontend/src/assets/styles/style.css'; */
</style>