const backNavigationStack = reactive([]);
const isBackEvent = ref(false);

export default function useBackNavigation() {

    function addPageToStack(page) {
        // console.log('페이지 데이터 삽입', page);
        // console.log('데이터 정보', backNavigationStack);
        if (!backNavigationStack.includes(page)) {
            if (backNavigationStack.length >= 20) {
                backNavigationStack.shift();
            }
            backNavigationStack.push(page);
        }
    }

    function popPageFromStack() {
        backNavigationStack.pop();
    }

    function getLastPage() {
        return backNavigationStack.length > 1 ?
            backNavigationStack[backNavigationStack.length - 2] : '/home';
    }

    return {
        backNavigationStack,
        addPageToStack,
        popPageFromStack,
        getLastPage,
    };
}