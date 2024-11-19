export default defineNuxtRouteMiddleware((to, from) => {
  const useStore = useBackStore();
  const { isBack } = storeToRefs(useStore);
  //페이지 이동시마다 스택 선택
  useStore.changeStack();
  console.log("backNavStack", useStore.backNavigationStack);
  //뒤로가기 제외 확인 후 페이지 이동시마다 스택에 페이지 추가
  if (!isBack.value) useStore.addPageToStack(from.fullPath);

  //페이지 이동시마다 스택을 쿠키에 저장(새로고침 시에도 유지)
  useStore.setStackToCookie();
  //뒤로가기 체크 변수 초기화
  useStore.IsBackToFalse();

  // 리프 리스트에서 리프 상세로 이동하는 경우
  // if (
  //   from.fullPath.includes("/leaf?leafId=") &&
  //   to.fullPath.includes("/leaf/")
  // ) {
  //   const leafId = to.fullPath.split("/").pop();
  //   if (Number.isInteger(Number(leafId))) {
  //     // 정수 검사
  //     useStore.popPageFromStack();
  //     useStore.addPageToStack(`/leaf?leafId=${leafId}`);
  //   }
  // }
});
