export default defineNuxtPlugin((Nuxtapp) => {
  const memberStore = useMemberStore();
  //1. 새로고침시 로그인 정보를 로컬 스토리지에서 로드

  try {
    memberStore.loadUserFromStorage();
  } catch (e) {
    console.log("loadUserFromStorage error = ", e);
  }
});
