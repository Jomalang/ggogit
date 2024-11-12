export default defineNuxtRouteMiddleware((to, from) => {
  const userDetails = useMemberDetail();
  //to.path는 사용자가 이동하려는(요청한) 경로를 나타낸다.
  if (to.path.startsWith("/home, /tree, /leaf, /memoir")) {
    // 로그인 여부를 확인하고 로그인하지 않은 사용자는 로그인 페이지로 이동시킨다.
    if (userDetails.isAnonymous()) {
      //리턴 URL을 포함시켜준다.
      return navigateTo("/member/login?returnURL=" + `${to.fullPath}`);
    }
  }
});
