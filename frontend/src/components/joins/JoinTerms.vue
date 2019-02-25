<template>
  <div>
    <!-- TODO -->
    <div class="paging_step">
      <span class="ico_join ico_paging on"></span>
      <span class="ico_join ico_paging"></span>
    </div>
    
    <div class="row justify-content-md-center terms_agreement">
      <div class="col-md-3 title_agreement">약관 동의</div>
      <div class="col-md-1 ml-auto right"><input type="checkbox" name="terms" id="terms" v-model="terms" :checked="terms" @change="updatedTermsCheckedStatus"/></div>
      <div class="w-100"></div>
      <Terms/>
    </div>

    <div class="row justify-content-md-center terms_agreement">
      <div class="col-md-4 title_agreement">개인정보 수집 및 이용 동의</div>
      <div class="col-md-1 ml-auto right"><input type="checkbox" name="privacy" id="privacy" v-model="privacy" :checked="privacy" @change="updatePrivacyCheckedStatus"/></div>
      <div class="w-100"></div>
      <TermsPrivacy/>
    </div>

    <router-link class="btn btn-sm btn-outline-warning" to="/login">이전으로</router-link>
    <router-link to="/join/signUp">
      <button class="btn btn-sm btn-primary" :disabled="checkedStatus == 1">동의하기</button>
    </router-link>
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
      checkedStatus: 1,
      terms: false,
      privacy: false,
    }
  },
  components: {
    'Terms': Terms,
    'TermsPrivacy': TermsPrivacy
  },
  methods: {
    updatedTermsCheckedStatus() {
      this.terms == true && this.privacy == true ? this.checkedStatus = -1 : this.checkedStatus = 1
    },
    updatePrivacyCheckedStatus() {
      this.terms == true && this.privacy == true  ? this.checkedStatus = -1 : this.checkedStatus = 1
    },
  }
}
</script>

<style src="@/assets/styles/style.css">
/* @import '../../../../frontend/src/assets/styles/style.css'; */
</style>