export default defineNuxtRouteMiddleware((to, from) => {
  if (!import.meta.env.SSR) {
    const memberStore = useMemberStore();
    const beingLogin = !memberStore.isAnonymous();
    const protectedPaths = ["/home", "/tree", "/leaf", "/memoir"];

    if (
      protectedPaths.some((path) => to.path.startsWith(path)) &&
      !beingLogin
    ) {
      return navigateTo("/member/login?returnUrl=" + to.fullPath);
    }
    console.log("로그인 중입니다.");
  }
});
