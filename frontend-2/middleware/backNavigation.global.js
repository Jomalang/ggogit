import useBackNavigation from "~/composables/useBackNavigation.js";

export default defineNuxtRouteMiddleware((to, from) => {
    const { addPageToStack, popPageFromStack } = useBackNavigation();

    // console.log('to', to.fullPath);
    // console.log('from', from.fullPath);

    // 리프 리스트에서 리프 상세로 이동하는 경우
    if (from.fullPath.includes('/leaf?leafId=') && to.fullPath.includes('/leaf/')) {
        const leafId = to.fullPath.split('/').pop();
        if (Number.isInteger(Number(leafId))) { // 정수 검사
            popPageFromStack();
            addPageToStack(`/leaf?leafId=${leafId}`);
        }
    }
    addPageToStack(to.fullPath);
});
