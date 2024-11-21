<script setup>
const props = defineProps({
  edit: "",
  editBtnActive: {
    type: Boolean,
    default: true,
  },
});
</script>

<template>
  <!--top-bar__transparent(edit)-->
  <div class="top-bar__transparent-frame">
    <div
      class="top-bar__transparent-back-btn"
      @click.prevent="useGoBack()"
    ></div>
    <div class="top-bar__transparent-btns" v-if="editBtnActive">
      <NuxtLink
        class="top-bar__transparent-setting-btn"
        :to="props.edit"
      ></NuxtLink>
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
