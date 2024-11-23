export default async function useAuthFetch(url, options = {}) {
  // 1. 쿠키에서 토큰을 가져오기
  const cookies = useRequestHeaders(["cookie"]);
  let _accessToken = null;
  if (import.meta.env.SSR) {
    // console.log("SSR");
    _accessToken = cookies.cookie
      ? cookies.cookie
          .split("; ")
          .find((row) => row.startsWith("_ggogit_accessToken="))
          ?.split("=")[1]
      : null;
  }

  if (!import.meta.env.SSR) {
    _accessToken = localStorage.getItem("_ggogit_accessToken");
  }

  if (!_accessToken) {
    navigateTo("/member/login");
  }

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

  //4. 사용자가 브라우저 종료 후 접속한 경우,
  //쿠키가 없기 때문에 localStorage의 토큰을 이용해 CSR에서 한번 더 요청
  if (!import.meta.env.SSR && data.value == null) {
    console.log("refresh");
    refresh();
  }

  // 5. fetch의 data, error, status 등을 반환(useFetch와 유사한 형태)
  return { data, error, status, refresh };
}
