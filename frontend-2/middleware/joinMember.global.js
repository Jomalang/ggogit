export default defineNuxtRouteMiddleware(async (to, from) => {


    if (to.path === '/member/new') { // /member/new 경로로 가는 경우

        // key쿼리 스트링이 존재해야함
        if (!to.query.key) { // key쿼리 스트링이 없으면 로그인 페이지로 이동
            return navigateTo('/member/login', {replace: true});
        }
        const config = useRuntimeConfig();
        // 키 유효성 확인
        const response = await $fetch(`members/join/check-token`, {
            method: 'POST',
            baseURL: `${config.public.apiBase}`,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: {
                key: to.query.key
            }
        });

        // 유효하지 않는 경우 로그인 페이지로 이동
        if (!response.valid) {
            return navigateTo('/member/login', {replace: true});
        }
    }
});