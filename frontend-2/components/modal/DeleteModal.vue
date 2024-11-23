<script setup>

const { data } = defineProps(['data']);
const emit = defineEmits(['submit']);
const isDelete = ref(false);
const inputMsg = ref('');
const validateMessage = ref('');

const closeModal = () => {
  console.log('close');
  emit('close');
};

const submitModal = () => {

  inputMsg.value === '삭제합니다' ? isDelete.value = true : isDelete.value = false;

  if (!isDelete.value) {
    validateMessage.value = '입력값이 일치하지 않습니다.';
    return false;
  }
  emit('submit');
};


</script>

<template>
  <div v-if="data.isOpen" class="modal-container">
    <div class="modal-content">
      <p class="modal-message">{{ data.title }}</p>

      <div class="modal-delete-box">
        <p class="modal-delete-info-message">{{data.message}}</p>
        <p class="modal-delete-info-message">"삭제합니다"를 입력한 후</p>
        <p class="modal-delete-info-message">확인 버튼을 눌러주세요.</p>
        <p class="modal-delete-info-message-validate">{{validateMessage}}</p>
      </div>

      <input class="confirm-input-box" type="text" placeholder="삭제합니다" v-model="inputMsg" @keyup.enter="submitModal" autofocus/>

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
  top: 50%;
  left: 50%;
  transform: translate(-50%, -47%);
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 300%;
  backdrop-filter: blur(8px); /* 배경 블러 처리 */
  background-color: rgba(0, 0, 0, 0.5); /* 약간 어두운 투명 배경 */

  .modal-delete-box {

    display: flex;
    flex-direction: column;
  }

  .modal-content {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 300px;
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
      line-height: var(--line-height-main);
      font-family: "Pretendard", sans-serif;
      text-align: center;
      font-size: 16px;
      font-weight: var(--medium);
    }
    .modal-delete-info-message-validate{
      line-height: var(--line-height-main);
      font-family: "Pretendard", sans-serif;
      color: var(--warning, #ba0c0c);
      text-align: center;
      font-size: 16px;
      font-weight: var(--regular);
    }

    .confirm-input-box {
      margin: 20px auto;
      width: 200px;
      height: 50px;
      display: flex;
      border: none;
      border-radius: 12px;
      font-size: 16px;
      font-weight: var(--bold);
      color: var(--main1, #323a27);
      padding-left: 16px;
      background-color: var(--main2, #f5f8f1);
    }

    .button-box {
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 10px;

      .modal-button {
        width: 55px;
        background-color: var(--main1);
        color: white;
        padding: 12px 24px;
        border-radius: 8px;
        text-align: center;
        font-size: 18px;
        font-weight: var(--bold);
      }

      .close-button { /* 취소 버튼 */
        width: 55px;
        background-color: #F48C8D;
      }
    }
  }
}

</style>