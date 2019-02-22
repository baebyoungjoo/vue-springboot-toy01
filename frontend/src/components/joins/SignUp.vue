<template>
  <div>
    <!-- TODO -->
    <div class="paging_step">
      <span class="ico_join ico_paging"></span>
      <span class="ico_join ico_paging on"></span>
    </div>
    가입 정보 입력
    
    <div class="form-group">
      <div>
        <dl>
          <dt><label>아이디</label></dt>
          <dd>
            <div>
              <input type="text" class="form-control" id="userId" required v-model="userId" @change="duplicateIdCheckHandler" @focus="isDuplicated = false" @blur="duplicateIdCheckHandler" placeholder="아이디를 입력해 주세요" name="userId">
            </div>
            <div v-if="isDuplicated">
              <p class="txt_message">이미 사용된 아이디여서 또 사용할 수 없어요. 다른 아이디를 입력해 주세요.</p>
            </div>
          </dd>
        </dl>
        <dl>
          <dt><label>비밀번호</label></dt>
          <dd>
            <div>
              <input type="password" class="form-control" id="password1" required v-model="password1" maxlength="32" placeholder="비밀번호(8자 이상)" @focus="passwordOnlyNum = false" @blur="passwordValidCheck" name="password1">
            </div>
            <div v-if="passwordOnlyNum">
              <p class="txt_message">숫자로 된 비밀번호는 사용할 수 없어요! 영문자, 특수문자를 함께 입력해 주세요.
</p>
            </div>
          </dd>
          <dt><label>비밀번호 재확인</label></dt>
          <dd>
            <div>
              <input type="password" class="form-control" id="password2" required v-model="password2" maxlength="32" placeholder="비밀번호 재확인" name="password2" @focus="isMatched = false" @blur="compareTwoPassword">
            </div>
            <div v-if="isMatched">
              <p class="txt_message">비밀번호와 일치하지 않습니다. 다시 입력해 주세요.</p>
            </div>
          </dd>
        </dl>
      </div>

      <div>
        <dl>
          <dt><label>이름</label></dt>
          <dd>
            <div>
              <input type="text" class="form-control" id="userName" required v-model="userName" placeholder="이름" maxlength="32" name="userName" @blur="nameValidCheck">
            </div>
            <div v-if="isDuplicated">
              <p class="txt_message">사용할 수 없는 문자가 있어요. 한글(성과 이름을 공백없이 입력) 또는 영문만 입력해 주세요.</p>
            </div>
          </dd>
        </dl>
        <dl>
          <dt><label>이메일 주소</label></dt>
          <dd>
            <div>
              <input type="text" class="form-control" id="email" required v-model="email" placeholder="이메일" name="email">
            </div>
          </dd>
        </dl>
      </div>
    </div>

    <router-link class="btn btn-sm" to="/join/joinTerms">이전으로</router-link>

  </div>
</template>

<script>
/* TODO */
import { axiosInstanceMember } from '../../http-common'

export default {
  name: "signUp",
  data() {
    return {
      userId: '',
      password1: '',
      password2: '',
      userName: '',
      email: '',
      isDuplicated: false,
      isMatched: false,
      passwordOnlyNum: false,
    }
  },
  methods: {
    duplicateIdCheckHandler() {
      axiosInstanceMember
        .get("/check/" + this.userId)
        .then(response => {
          response.data == 'ok' ? this.isDuplicated = true : this.isDuplicated = false
          // console.log(response.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    compareTwoPassword() {
      this.password1 === this.password2 ? this.isMatched = false : this.isMatched = true
    },
    nameValidCheck() {
      /* TODO */
      /* 공백 검사, 특수문자, 숫자 들어가면 안됨 */
    },
    passwordValidCheck() {
      /* TODO */
      /* 패스워드 패턴 체크 - 숫자, 문자, 특수문자 포함 8자리 이상 32자리 이하 */
      const numPattern = /[0-9]/
      const strPattern = /[a-zA-z]/
      const spcPattern = /[~!@#$%^&*()_+|<>?:{}]/

      if (!numPattern.test(this.password1)) {
        this.passwordOnlyNum = true
      } else {
        this.passwordOnlyNum = false
      }
    }
  }
}

</script>

<style src="@/assets/styles/style.css">
/* @import '../../../../frontend/src/assets/styles/style.css'; */
</style>