const _treeFormData = ref({});
const _isTreeFormActivated = ref(false);

export default function useTreeFormData() {

    function postInit() {
        _isTreeFormActivated.value = false;
        init();
    }

    function init() {

        if (_isTreeFormActivated.value) {
            return // 이미 초기화 되었으면 종료
        }

        _isTreeFormActivated.value = true;
        _treeFormData.value = {
            // 트리 씨앗 정보
            seedId: 1,

            // 도서 정보
            bookTitle: "",
            bookTitleValid: true,

            // 지은이 정보
            author: "",
            authorValid: true,

            // 출판일 정보
            publishDate: "",
            publishDateValid: true,

            // 출판사 정보
            publisher: "",
            publisherValid: true,

            // 총 페이지 정보
            totalPage: "",
            totalPageValid: true,

            // 도서 카테고리 정보
            bookCategoryId: null,
            bookCategoryIdValid: true,

            // 도서 카테고리 이름 정보
            bookCategoryName: null,
            bookCategorySelected: true,

            // 트리 정보
            treeTitle: "",
            treeTitleValid: true,

            // 트리 설명 정보
            description: "",
            descriptionValid: true,

            // 공개 여부 정보
            visibility: false,
            visibilityValid: true,

            // 이미지 정보
            imageData: "",

            // 트리 생성 경로
            createUrl: ``,
        }
    }

    function setCreateUrl(url) {
        _treeFormData.value.createUrl = url;
    }

    function setSeedId(seedId) {
        _treeFormData.value.seedId = seedId;
    }

    return {
        treeFormData: _treeFormData,
        postInit,
        init,
        setCreateUrl,
        setSeedId
    }
}