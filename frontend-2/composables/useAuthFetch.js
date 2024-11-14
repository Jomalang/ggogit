export default async function useAuthFetch(url, options = {}) {
  // 1. MemberDetail에서 token을 획득
  const _accessToken = useMemberStore().getAuthToken() || "";

  // 2. 획득한 token을 헤더에 담기
  options.headers = {
    ...options.headers,
    Authorization: `Bearer ${_accessToken}`,
  };

  options = {
    ...options,
    immediate: true,
  };

  // 3. 사용자가 요청한 url과 option에, 획득한 token을 담아서 fetch 요청하기
  const { data, status, error, refresh } = await useFetch(url, options);

  //4. CSR에서 한번 더 요청하도록 실행(새로고침 대비)
  if (!import.meta.env.SSR) {
    refresh();
  }

  // 5. fetch의 data, error, status 등을 반환(useFetch와 유사한 형태)
  return { data, error, status, refresh };
}
