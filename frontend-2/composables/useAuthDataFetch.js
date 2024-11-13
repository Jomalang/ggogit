import { useMemberDetail } from "./useMemberDetail";

export default async function useAuthFetch(url, options = {}) {
  // 1. MemberDetail에서 token을 획득
  const { token = {} } = useMemberDetail();

  // 2. 획득한 token을 헤더에 담기
  options.headers = {
    ...options.headers,
    Authorization: `Bearer ${token}`,
  };

  // 3. 사용자가 요청한 url과 option에, 획득한 token을 담아서 fetch 요청하기
  const { data, status, error } = await useFetch(url, options);

  // 4. fetch의 data, error, status 등을 반환(useFetch와 유사한 형태)
  console.log(data);
  console.log(status);
  console.log(error);

  return { data, error, status };
}
