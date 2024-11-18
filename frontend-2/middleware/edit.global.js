export default defineNuxtRouteMiddleware((to, from) => {

    // 수정 화면에서 다른 화면으로 이동한경우
    if (from.path.includes('edit')) {
        // 태그 수정인 경우 PASS
        if (to.path.startsWith('/leaf/tag')) { return; }

        // 도서 카테고리 수정인 경우 PASS
        if (to.path.startsWith('/leaf/tag')) { return; }

        useLeafTagList().postInit();
        useLeafFormData().postInit();
    }
});