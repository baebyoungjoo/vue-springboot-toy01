<template>
  <div>
    <!-- TODO -->
    <div class="paging_step">
      <span class="ico_join ico_paging"></span>
      <span class="ico_join ico_paging on"></span>
    </div>
    가입 정보 입력<br>
    로그인 정보 및 가입 정보를 입력하세요.

    <div class="form-group">
      <div>
        <dl>
          <dt><label>아이디</label></dt>
          <dd>
            <div>
              <input type="text" class="form-control" id="userId" required v-model="userId" placeholder="아이디를 입력해 주세요" name="userId" @focus="idDuplicateChk = false; idLengthChk = false; idOnlyNumChk = false; idValidChk = false;" @blur="duplicateIdCheckHandler">
            </div>
            <div v-if="idDuplicateChk">
              <p class="txt_message">이미 사용된 아이디여서 또 사용할 수 없어요. 다른 아이디를 입력해 주세요.</p>
            </div>
            <div v-else-if="idValidChk">
              <p class="txt_message">아이디는 영문 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)만 사용할 수 있어요.
                (단, 마침표(.)는 처음과 끝에 또는 연속으로 사용 불가)</p>
            </div>
            <div v-else-if="idLengthChk">
              <p class="txt_message">조금 더! 아이디는 3자 이상이에요.</p>
            </div>
            <div v-else-if="idOnlyNumChk">
              <p class="txt_message">숫자로 된 아이디는 사용할 수 없어요. 영문 소문자를 추가해서 다시 입력해 주세요.</p>
            </div>
          </dd>
        </dl>
        <dl>
          <dt><label>비밀번호</label></dt>
          <dd>
            <div>
              <input type="password" class="form-control" id="password1" required v-model="password1" maxlength="32" placeholder="비밀번호(8자 이상)" name="password1" @focus="passwordOnlyNum = false" @blur="passwordValidCheck">
            </div>
            <div v-if="passwordOnlyNum">
              <p class="txt_message">숫자로 된 비밀번호는 사용할 수 없어요! 영문자, 특수문자를 함께 입력해 주세요.</p>
            </div>
          </dd>
          <dt><label>비밀번호 재확인</label></dt>
          <dd>
            <div>
              <input type="password" class="form-control" id="password2" required v-model="password2" maxlength="32" placeholder="비밀번호 재확인" name="password2" @focus="isMatchedPwd = false" @blur="compareTwoPassword">
            </div>
            <div v-if="isMatchedPwd">
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
              <input type="text" class="form-control" id="userName" required v-model="userName" placeholder="이름" maxlength="32" name="userName"  @focus="nameOnlyStr = false; nameKoLengthChk = false; nameEnLengthChk = false;" @blur="nameValidCheck">
            </div>
            <div v-if="nameOnlyStr">
              <p class="txt_message">사용할 수 없는 문자가 있어요. 한글(성과 이름을 공백없이 입력) 또는 영문만 입력해 주세요.</p>
            </div>
            <div v-else-if="nameKoLengthChk">
              <p class="txt_message">성과 이름(2자 또는 3자 이상)을 함께 입력해 주세요.</p>
            </div>
            <div v-else-if="nameEnLengthChk">
              <p class="txt_message">영어 이름은 2~30자 까지 입력할 수 있어요.</p>
            </div>
          </dd>
        </dl>
        <dl>
          <dt><label>이메일 주소</label></dt>
          <dd>
            <div>
              <input type="text" class="form-control" id="email" required v-model="email" placeholder="이메일" name="email" @focus="emailValidChk = false;" @blur="emailValidCheck">
            </div>
            <div v-if="emailValidChk">
              <p class="txt_message">이메일 주소 형식이 아닙니다. 본인확인이 가능한 이메일 주소를 입력해 주세요.</p>
            </div>
          </dd>
        </dl>
      </div>
    </div>

    <router-link class="btn btn-sm btn-outline-warning" to="/join/joinTerms">이전으로</router-link>
    <!-- TODO 회원가입 완료 submit -->
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
      /* id check value */
      idDuplicateChk: false,
      idValidChk: false,
      idLengthChk: false,
      idOnlyNumChk: false,
      /* password check value */
      isMatchedPwd: false,
      passwordOnlyNum: false,
      /* name check value */
      nameOnlyStr: false,
      nameKoLengthChk: false,
      nameEnLengthChk: false,
      /* email check value */
      emailValidChk: false,
    }
  },
  methods: {
    duplicateIdCheckHandler() {
      /* id check - 숫자만 x, 영문 소문자, 빼기 (-), 밑줄 (_), 마침표 (.) 사용 가능. 마침표 (.) 는 앞, 뒤, 연속 사용 x */
      const numPattern = /^\d+$/
      const spcPattern = /[`~!@#$%^&*()=+\\|;:'",<>/?[\]{}]/
      const dotPattern = /^\.{1,}|[\.]$|\.{2,}/

      axiosInstanceMember
        .post("/check/" + this.userId)
        .then(response => {
          response.data == "EXIST" ? this.idDuplicateChk = true : this.idDuplicateChk = false
        })
        .catch(error => {
          console.log(error)
        })
      this.userId.length < 3 ? this.idLengthChk = true : this.idLengthChk = false
      numPattern.test(this.userId) ? this.idOnlyNumChk = true : this.idOnlyNumChk = false
      spcPattern.test(this.userId) || dotPattern.test(this.userId) ? this.idValidChk = true : this.idValidChk = false
    },
    compareTwoPassword() {
      this.password1 === this.password2 ? this.isMatchedPwd = false : this.isMatchedPwd = true
    },
    passwordValidCheck() {
      /* password check - 숫자만 x */
      const numPattern = /^\d+$/

      numPattern.test(this.password1) == true ? this.passwordOnlyNum = true : this.passwordOnlyNum = false
    },
    nameValidCheck() {
      /* 이름은 한글 및 영문 - 공백, 특수문자, 숫자 x */
      const numPattern = /[0-9]/
      const spcPattern = /[`~!@#$%^&*()\-_=+\\|;:'",<.>/?[\]{}]/
      const emptyPattern = /\s/

      numPattern.test(this.userName) || spcPattern.test(this.userName) || emptyPattern.test(this.userName) ? 
        this.nameOnlyStr = true : this.nameOnlyStr = false

      /* 한글 이름 2-3자 이상, 영문 이름 2-30자 */
      const koPattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/
      const enPattern = /[a-zA-z]/

      if (koPattern.test(this.userName) && this.userName.length < 2) this.nameKoLengthChk = true
      if (enPattern.test(this.userName) && this.userName.length < 2) this.nameEnLengthChk = true
    },
    emailValidCheck() {
      const emailPattern = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}/

      emailPattern.test(this.email) ? this.emailValidChk = false : this.emailValidChk = true
    },
  }
}
</script>

<style src="@/assets/styles/style.css">
/* @import '../../../../frontend/src/assets/styles/style.css'; */
</style>