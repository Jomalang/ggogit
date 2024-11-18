const _leafFormData = ref({});
const _isLeafFormActivated = ref(false);

export default function useLeafFormData() {

    function postInit() {
        _isLeafFormActivated.value = false;
        console.log('post 초기화');
        init();
    }

    function init() {
        if (_isLeafFormActivated.value) {
            return; // 이미 초기화 되었으면 종료
        }

        console.log('init 초기화');
        _isLeafFormActivated.value = true;
        _leafFormData.value = {
            // 페이지 번호
            startPage: undefined,
            startPageValidation: true,
            endPage: undefined,
            endPageValidation: true,

            // 태그 데이터
            tagIds: [],
            selectedTags: [],
            tagSelected: true,

            // 제목
            title: undefined,
            titleValidation: true,

            // 내용
            content: undefined,

            // 공개성
            visibility: true,

            // 생성 화면 경로
            createUrl: '',

            // 수정 데이터 조회 여부
            isLoaded: false
        }
        console.log('수정 후 초기화 부분', _leafFormData.value);
    }

    function getSelectedTags() {
        return _leafFormData.value.selectedTags;
    }

    function setCreateUrl(url) {
        _leafFormData.value.createUrl = url;
    }

    function getCreateUrl() {
        return _leafFormData.value.createUrl;
    }

    return {
        leafFormData: _leafFormData,
        getSelectedTags,
        getCreateUrl,
        setCreateUrl,
        postInit,
        init
    }
}