<script setup>

const { data } = defineProps(['data']);
const emit = defineEmits(['submit']);

const closeModal = () => {
  console.log('close');
  emit('close');
};

const submitModal = () => {
  console.log('submit');
  emit('submit');
};

</script>

<template>
  <div v-if="data.isOpen" class="modal-container">
    <div class="modal-content">
      <p class="modal-message">{{ data.message }}</p>

      <div class="modal-delete-box">
        <p class="modal-delete-info-message">"삭제합니다"를 입력한 후</p>
        <p class="modal-delete-info-message">확인 버튼을 눌러주세요.</p>
      </div>

      <input class="confirm-input-box" type="text" placeholder="삭제합니다" />

      <div class="button-box">
        <span class="modal-button close-button" @click="closeModal">{{ data.cancelText || '취소' }}</span>
        <span class="modal-button submit-button" @click="submitModal">{{ data.confirmText || '확인' }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-container {
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;

  width: 100%;
  height: 100%;
  backdrop-filter: blur(8px); /* 배경 블러 처리 */
  background-color: rgba(0, 0, 0, 0.5); /* 약간 어두운 투명 배경 */

  .modal-delete-box {
    display: flex;
    flex-direction: column;
  }

  .modal-content {
    width: 250px;
    height: auto;
    background-color: white;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

    .modal-message {
      margin-top: 10px;
      padding-bottom: 20px;
      font-size: 20px;
      font-weight: var(--bold);
      text-align: center;
    }

    .modal-delete-info-message {
      text-align: center;
      font-size: 16px;
    }

    .confirm-input-box {
      height: 56px;
      display: flex;
      border: none;
      border-radius: 12px;
      font-size: 16px;
      font-weight: var(--regular);
      color: var(--text-sub);
      padding-left: 16px;
      //background-color: var(--main3, #e5eddb);
      background-color: red;
    }

    .button-box {
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 10px;

      .modal-button {
        background-color: var(--main1);
        color: white;
        padding: 12px 24px;
        border-radius: 8px;
        font-size: 18px;
        font-weight: var(--bold);
      }

      .close-button { /* 취소 버튼 */
        background-color: #F48C8D;
      }
    }
  }
}

</style>