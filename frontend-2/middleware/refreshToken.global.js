export default defineNuxtRouteMiddleware((to, from) => {
  const memberStore = useMemberStore();
  const beingLogin = !memberStore.isAnonymous();
  //기존 토큰 획득
  const refreshToken = useCookie("_ggogit_accessToken");

  //로그인 중이라면
  //토큰 최신화
  if (beingLogin) {
    const refreshTokens = async () => {
      const response = await useAuthDataFetch("members/refresh", {
        method: "POST",
        baseURL: useRuntimeConfig().public.apiBase,
        credentials: "include",
        headers: {
          Cookie: `refreshToken=${refreshToken.value}`,
        },
      });
      memberStore.setAuthWithToken(response.accessToken);
    };
    refreshTokens();
  }
});
