export default function useGoBack() {
  const backStore = useBackStore();
  const prevPath = ref("");
  //뒤로가기 버튼임을 알립니다.
  backStore.IsBackToTrue();

  prevPath.value = backStore.popPageFromStack();
  navigateTo(prevPath.value);
}
