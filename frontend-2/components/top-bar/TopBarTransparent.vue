<script setup>
const props = defineProps({
  edit: "",
  delete: "",
});

const deleteResource = () => {
  // console.log(props.delete);
  if (confirm("정말 삭제하시겠습니까?")) {
    const { data } = $fetch(props.delete, {
      baseURL: import.meta.env.VITE_API_BASE_URL,
      method: "DELETE",
    });
    navigateTo("/home");
  }
};
</script>

<template>
  <!--top-bar__transparent(edit)-->
  <div class="top-bar__transparent-frame">
    <a
      class="top-bar__transparent-back-btn"
      href="javascript:history.back()"
    ></a>
    <div class="top-bar__transparent-btns">
      <a class="top-bar__transparent-setting-btn" :href="edit"></a>
      <button
        class="top-bar__transparent-delete-btn"
        @click="deleteResource"
      ></button>
    </div>
  </div>
</template>

<style>
.top-bar__transparent-frame {
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: transparent;
}

.top-bar__transparent-btns {
  display: flex;
  gap: 15px;
}
.top-bar__transparent-back-btn {
  width: 11px;
  height: 23px;
  display: flex;
  background: transparent url("/svg/back-icon--white.svg") no-repeat center;
  background-size: contain;
}

.top-bar__transparent-setting-btn {
  width: 25px;
  height: 25px;
  display: flex;
  background: transparent url("/svg/edit.svg") no-repeat center;
  background-size: contain;
}
.top-bar__transparent-delete-btn {
  width: 25px;
  height: 25px;
  display: flex;
  background: transparent url("/svg/delete.svg") no-repeat center;
  background-size: contain;
  padding: 0;
  border: none;
}
</style>
