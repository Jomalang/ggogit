<script setup lang="ts">
import axios from "axios";

// export default {
//   data() {
//     return {
//       newPassword: "",
//       checkPassword: "",
//       message: "",
//     };
//   },
//   methods: {
//     resetPassword() {
//       const payload = {
//         newPassword: this.newPassword,
//         checkPassword: this.checkPassword,
//       };

//       axios
//         .put("/api/v1/member/resetPassword", payload)
//         .then((response) => {
//           this.message = "비밀번호가 성공적으로 변경되었습니다.";
//         })
//         .catch((error) => {
//           this.message = "비밀번호 변경에 실패했습니다.";
//           console.error("오류:", error);
//         });
//     },
//   },
// };
</script>

<template>
  <div class="login-member__join-page-container">
    <TextJoinPageInfo
      label="비밀번호 변경"
      info-text="변경할 비밀번호를 입력해 주세요"
    />

    <div class="input-text__bar">
      <label class="input-text__label">
        <span class="input-text__label-text">{{ label }}</span>
        <input
          type="password"
          class="input-text__input"
          :name="newPassword"
          :placeholder="placeholder"
          v-model="newPassword"
          autocomplete="off"
        />
      </label>
    </div>
    <div class="input-text__bar">
      <label class="input-text__label">
        <span class="input-text__label-text">{{ label }}</span>
        <input
          type="password"
          class="input-text__input"
          :name="checkPassword"
          :placeholder="placeholder"
          v-model="checkPassword"
          autocomplete="off"
        />
      </label>
    </div>

    <SubmitBtnFullBar text="비밀번호 변경" />
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<style scoped>
.login-member__join-page-container {
  display: flex;
  flex-direction: column;
  width: auto;
  padding: 50px 24px 0 24px;
  gap: 60px;
}
</style>
