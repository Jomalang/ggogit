export default defineNuxtPlugin((Nuxtapp) => {
  const memberStore = useMemberStore();
  //1. 새로고침시 로그인 정보를 로컬 스토리지에서 로드
  try {
    memberStore.loadUserFromStorage();
    const tokenCookie = useCookie("_ggogit_accessToken");
    //쿠키가 없을 경우 리로딩
    if (tokenCookie.value === undefined) {
      tokenCookie.value = memberStore.getAuthToken();
      console.log("tokenCookie.value = ", tokenCookie.value);
    }
  } catch (e) {
    console.log("loadUserFromStorage error = ", e);
  }
});
