export default async function useAuthDataFetch(url, options = {}) {
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
    console.log("CSR");
    _accessToken = localStorage.getItem("_ggogit_accessToken");
  }

  // 2. 획득한 token을 헤더에 담기
  console.log("...options.headers", options.headers);
  options.headers = {
    ...options.headers,
    Authorization: `Bearer ${_accessToken}`,
  };
  options = {
    ...options,
    immediate: true,
  };

  // 3. 사용자가 요청한 url과 option에, 획득한 token을 담아서 fetch 요청하기
  const response = await $fetch(url, options);

  // 4. fetch의 data, error, status 등을 반환
  console.log("DataFetch response = ", response);
  return { ...response };
}
