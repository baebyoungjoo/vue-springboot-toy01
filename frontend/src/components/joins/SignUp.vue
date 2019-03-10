<template>
  <div>
    <!-- TODO -->
    <div class="paging_step">
      <span class="ico_join ico_paging"></span>
      <span class="ico_join ico_paging on"></span>
    </div>
    가입 정보 입력<br>
    로그인 정보 및 가입 정보를 입력하세요.

    <div class="form-group row justify-content-md-center">
      <div>
        <dl>
          <dt><label>아이디</label></dt>
          <dd>
            <div>
              <!-- <input type="text" class="form-control" id="userId" required v-model="$v.userId.$model" placeholder="아이디를 입력해 주세요" name="userId" @focus="statusChange('id')" @blur="duplicateIdCheckHandler"> -->
              <input type="text" class="form-control" id="userId" required v-model="$v.userId.$model" placeholder="아이디를 입력해 주세요" name="userId" >
            </div>
            <div v-if="!$v.userId.required">
              <p class="txt_message">아이디를 입력해 주세요.</p>
            </div>
            <!-- <div v-if="idDuplicateChk"> -->
            <div v-else-if="!$v.userId.isUnique">
              <p class="txt_message">이미 사용된 아이디여서 또 사용할 수 없어요. 다른 아이디를 입력해 주세요.</p>
            </div>
            <!-- <div v-else-if="idLengthChk"> -->
            <div v-else-if="!$v.userId.minLength">
              <p class="txt_message">조금 더! 아이디는 3자 이상이에요.</p>
            </div>
            <!-- <div v-else-if="idOnlyNumChk"> -->
            <div v-else-if="!$v.userId.numeric">
              <p class="txt_message">숫자로 된 아이디는 사용할 수 없어요. 영문 소문자를 추가해서 다시 입력해 주세요.</p>
            </div>
            <!-- <div v-else-if="idValidChk"> -->
            <!-- <div v-else-if="!$v.userId.spcHelpers || !$v.userId.dotHelpers"> -->
            <div v-else-if="!$v.userId.helper">
              <p class="txt_message">아이디는 영문 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)만 사용할 수 있어요.
                (단, 마침표(.)는 처음과 끝에 또는 연속으로 사용 불가)</p>
            </div>
            <div v-else-if="!$v.userId.bigHelper">
              <p class="txt_message">영문 대문자는 사용할 수 없어요. 소문자로 다시 입력해 주세요.</p>
            </div>
          </dd>
        </dl>
        <dl>
          <dt><label>비밀번호</label></dt>
          <dd>
            <div>
              <!-- <input type="password" class="form-control" id="password1" required v-model="$v.password1.$model" maxlength="32" placeholder="비밀번호(8자 이상)" name="password1" @focus="statusChange('password1')" @blur="passwordValidCheck"> -->
              <input type="password" class="form-control" id="password1" required v-model="$v.password1.$model" maxlength="32" placeholder="비밀번호(8자 이상)" name="password1">
            </div>
            <!-- <div v-if="passwordOnlyNum"> -->
            <div v-if="!$v.password1.numeric">
              <p class="txt_message">숫자로 된 비밀번호는 사용할 수 없어요! 영문자, 특수문자를 함께 입력해 주세요.</p>
            </div>
          </dd>
          <dt><label>비밀번호 재확인</label></dt>
          <dd>
            <div>
              <!-- <input type="password" class="form-control" id="password2" required v-model="$v.password2.$model" maxlength="32" placeholder="비밀번호 재확인" name="password2" @focus="statusChange('password2')" @blur="compareTwoPassword"> -->
              <input type="password" class="form-control" id="password2" required v-model="$v.password2.$model" maxlength="32" placeholder="비밀번호 재확인" name="password2">
            </div>
            <!-- <div v-if="isMatchedPwd"> -->
            <div v-if="!$v.password2.sameAsPassword">
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
              <!-- <input type="text" class="form-control" id="userName" required v-model="$v.userName.$model" placeholder="이름" maxlength="32" name="userName"  @focus="statusChange('name')" @blur="nameValidCheck"> -->
              <input type="text" class="form-control" id="userName" required v-model="$v.userName.$model" placeholder="이름" maxlength="32" name="userName">
            </div>
            <div v-if="!$v.userName.required">
              <p class="txt_message">이름을 입력해 주세요.</p>
            </div>
            <!-- <div v-if="nameOnlyStr"> -->
            <div v-else-if="!$v.userName.helpers"> 
              <p class="txt_message">사용할 수 없는 문자가 있어요. 한글(성과 이름을 공백없이 입력) 또는 영문만 입력해 주세요.</p>
            </div>
            <!-- <div v-else-if="nameKoLengthChk"> -->
            <div v-else-if="!$v.userName.isKorean">
              <p class="txt_message">성과 이름(2자 또는 3자 이상)을 함께 입력해 주세요.</p>
            </div>
            <!-- <div v-else-if="nameEnLengthChk"> -->
            <div v-else-if="!$v.userName.isAlpha">
              <p class="txt_message">영어 이름은 2~30자 까지 입력할 수 있어요.</p>
            </div>
          </dd>
        </dl>
        <dl>
          <dt><label>이메일 주소</label></dt>
          <dd>
            <div>
              <!-- <input type="text" class="form-control" id="email" required v-model="$v.email.$model" placeholder="이메일" name="email" @focus="statusChange('email')" @blur="emailValidCheck"> -->
              <input type="text" class="form-control" id="email" required v-model="$v.email.$model" placeholder="이메일" name="email">
            </div>
            <!-- <div v-if="emailValidChk"> -->
            <div v-if="!$v.email.email">
              <p class="txt_message">이메일 주소 형식이 아닙니다. 본인확인이 가능한 이메일 주소를 입력해 주세요.</p>
            </div>
          </dd>
        </dl>
      </div>

      <!-- TODO captcha key, image 회원가입 진입 시 갱신 -->
      <div>
        <dl>
          <dt>
          <button @click="getCaptchaKey">captchaKey</button>
          <button @click="getCaptchaImage">captchaImage</button>
          <input type="text" v-model="captchaValue">
          <button @click="captchaValidCheck">captchaValidCheck</button>
          </dt>
        </dl>
      </div>
    </div>
    <!-- <pre>{{$v.$invalid}}</pre> -->
    <router-link class="btn btn-sm btn-outline-warning" to="/join/joinTerms">이전으로</router-link>
    <!-- TODO 회원가입 완료 -> 어디로 리다이렉션? 보통은 메인화면으로..?? 로그인페이지..?-->
    <router-link to="/">
      <button @click="saveMember" :disabled="invalidChecker == 1" class="btn btn-sm btn-info">회원 가입</button>
    </router-link>
  </div>
</template>

<script>
/* TODO */
import { axiosInstanceMember, axiosInstanceCaptcha } from '../../http-common'
import { required, minLength, sameAs, helpers, email, numeric, not, async} from 'vuelidate/lib/validators'

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
      responsedata: '',
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
      captchaKey: '',
      captchaValue: '',
    }
  },
  computed: {
    invalidChecker() {
      if(this.$v.$invalid) return 1
      else return -1
    }
  },
  validations: {
    userId: {
      required,
      isUnique(userId) {
        // if (userId === '') return true
        return axiosInstanceMember
          .post("/check/" + this.userId)
          .then(response => {
            if (response.data == "EXIST")  return false
            else return true
          })
          .catch(error => {
            console.log(error)
          })
      },
      minLength: minLength(3),
      numeric: not(numeric),
      helper: not(helpers.regex('userId', /[`~!@#$%^&*()=+\\|;:'",<>/?[\]{}]|^\.{1,}|[\.]$|\.{2,}/)),
      bigHelper: not(helpers.regex('userId', /[A-Z]/))
    },
    password1: {
      numeric: not(numeric)
    },
    password2: {
      sameAsPassword: sameAs('password1')
    },
    userName: {
      required,
      helpers: not(helpers.regex('userName', /[0-9]|[`~!@#$%^&*()\-_=+\\|;:'",<.>/?[\]{}]|\s/)),
      isKorean(userName){
        if (/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(userName) && userName.length < 2) return false
        else return true
      },
      isAlpha(userName) {
        if (/[a-zA-Z]/.test(userName) && userName.length < 2) return false
        else return true
      }
    },
    email: {
      email
    }
  },
  methods: {
    saveMember() {
      var memberData = {
        userId: this.userId,
        name: this.userName,
        password: this.password1,
        email: this.email,
      }
      
      this.$v.$touch()
      if (!this.$v.$invalid) {
        axiosInstanceMember
        .put("/join", memberData)
        .then(response => {
          this.$noty.success("회원 가입 성공!", {
            theme: 'semanticui',
            timeout: 3000
          })
        })
        .catch(e => {
          console.log(e);
        });
      }
    },
    /* TODO captcha */
    getCaptchaKey() {
      axiosInstanceCaptcha
      .get("/key")
      .then(response => {
        this.captchaKey = response.data.key
        console.log(response.data.key)
      })
      .catch(e => {
        console.log("e" + e)
      })
    },
    getCaptchaImage() {
      axiosInstanceCaptcha
      .get("/image/" + this.captchaKey)
      .then(response => {
        console.log(response)
      })
      .catch(e => {
        console.log(e)
      })
    },
    captchaValidCheck() {
      axiosInstanceCaptcha
      .get("/check/" + this.captchaKey + "/" + this.captchaValue)
      .then(response => {
        console.log(response.data.result)
      })
      .catch(e => {
        console.log(e)
      })
    },
  }
}
</script>

<style src="@/assets/styles/style.css">
/* @import '../../../../frontend/src/assets/styles/style.css'; */
</style>