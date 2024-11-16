const _leafFormData = ref({});
const _isActivated = ref(false);

export default function useLeafFormData() {

    function init() {
        if (_isActivated.value) {
            return; // 이미 초기화 되었으면 종료
        }

        _isActivated.value = true;
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
        }
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
        init
    }
}