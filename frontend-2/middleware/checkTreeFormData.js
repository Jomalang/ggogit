export default defineNuxtRouteMiddleware((to, from) => {

    // console.log('checkTreeFormData middleware');

    // /leafs/book/new 경로로 가는 경우
    if (to.path === '/leafs/book/new') {
        const treeFormData = useState('treeFormData'); // treeFormData데이터가 없으면 트리 선택 페이지로 이동
        if (!treeFormData) {
            return navigateTo('/tree/book/search', { replace: true });
        }
    }
});