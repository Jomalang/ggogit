export default defineNuxtRouteMiddleware((to, from) => {
  if (!import.meta.env.SSR) {
    const memberStore = useMemberStore();
    const beingLogin = !memberStore.isAnonymous();
    const protectedPaths = ["/home", "/tree", "/leaf", "/memoir"];

    //미인증 사용자 로그인 페이지로 보내기
    if (
      protectedPaths.some((path) => to.path.startsWith(path)) &&
      !beingLogin
    ) {
      return navigateTo("/member/login?returnUrl=" + to.fullPath);
    }
  }
});
