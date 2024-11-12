export default defineNuxtPlugin((Nuxtapp) => {
  const memberDetail = useMemberDetail();

  // 로컬 스토리지에 저장된 사용자 정보 로드
  if (!import.meta.env.SSR) {
    memberDetail.loadUserFromStorage();
  }
});
