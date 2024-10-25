<script setup lang="ts">
const props = defineProps<{
  text: string;
}>();
</script>

<template>
  <section class="social-login-container" >
    <h3 class="none">회원가입 이용약관</h3>
    <div class="join-policy-agreement">
      <div class="checkbox">
        <label>
          <input type="checkbox" class="checkbox__input" value="true" name="policyAgreement" th:field="*{policyAgreement}"/>
          <span class="checkbox__check"></span>
        </label>
      </div>
      <p><a class="text--underline" href="#">이용약관</a>과 <a class="text--underline" href="#">개인정보 취급 방침</a>에 동의합니다.</p>
      <div class = "text--errors" th:errors="*{policyAgreement}"></div>
    </div>
  </section>

</template>

<style scoped>
.checkbox {
  /** height값은 컴포넌트 제작 당시엔 없었음. align center 정렬을 위해 추가함.**/
  height: 24px;
  position: relative;
  font-size: 24px;
  font-weight: var(--bold);
  color: var(--main1);
  cursor: pointer;
}
.checkbox__input {
  position: absolute;
  opacity: 0;
}
.checkbox__check {
  position: relative;
  display: inline-block;
  width: 24px;
  height: 24px;
  border: 1px solid var(--main1);
  border-radius: 4px;
}
.checkbox__input:checked ~ .checkbox__check {
  background: var(--main1);
  border-color: var(--main1);
}
.checkbox__check::before {
  content: " ";
  position: absolute;
  top: 4px;
  left: 2px;
  width: 15px;
  height: 6px;
  border-bottom: 4px solid var(--white);
  border-left: 4px solid var(--white);
  transform: scale(0) rotate(-45deg);
}
.checkbox__input:checked ~ .checkbox__check::before {
  transform: scale(0.9) rotate(-45deg);
}

.text--underline{
  text-decoration: underline;
  font-weight: var(--bold);
}

.text--errors{
  padding-top: 5px;
  font-size: 16px;
  color: var(--warning);
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.join-policy-agreement{
  display: flex;
  align-items: center;
  gap: 12px;
}
</style>