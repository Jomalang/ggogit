const _tags = ref([]);
const _selectedTags = ref([]);
const _isLeafTagListActivated = ref(false);

export default function useLeafTagList() {

    function postInit() {
        _isLeafTagListActivated.value = false;
        init();
    }

    function init() {
        if (_isLeafTagListActivated.value) {
            return; // 이미 초기화 되었으면 종
        }

        _isLeafTagListActivated.value = true;
        _tags.value = [];
        _selectedTags.value = [];
    }

    // 태그 리스트 데이터 초기화
    function initTags(tags) {
        // console.log('태그 초기화');
        // console.log(tags);
        // console.log(_selectedTags.value);

        for (let tag of tags) {
            let isSelected = _selectedTags.value.some(selectedTag => tag.id === selectedTag.id);
            if (!isSelected) {
                _tags.value.push(tag);
            }
        }
    }

    // 태그 리스트 조회
    function getTags() {
        return _tags.value;
    }

    // 선택 가능 확인
    function canSelectTag(tag) {
        // 선택된 태그가 3개 이상이면 선택 불가
        if (_selectedTags.value.length >= 3) {
            return false;
        }

        // 이미 선택된 태그인지 확인
        return !_selectedTags.value.includes(tag);
    }

    // 태그 선택
    function selectTag(tag) {

        if (_selectedTags.value.length >= 3) { // 태그 3개 이상은 선택 불가
            console.error("선택 테그는 3개까지만 가능합니다.");
        }

        _selectedTags.value.push(tag); // 선택하면 선택된 태그 리스트에 추가

        const index = _tags.value.indexOf(tag);
        if (index === -1) { // 선택하면 태그 리스트에서 제거
            console.error("제거할 태그가 없습니다.");
        }
        _tags.value.splice(index, 1);

        return true;
    }

    // 태그 선택 해제
    function deselectTag(tag) {
        const index = _selectedTags.value.indexOf(tag);
        if (index === -1) {
            console.error("deselectTag: tag not found");
            return;
        }
        _selectedTags.value.splice(index, 1);
        _tags.value.push(tag);
    }

    // 선택된 태그 리스트 조회
    function getSelectedTags() {
        return _selectedTags.value;
    }

    function setSelectedTags(tags) {
        // console.log('setSelectedTags', tags);
        _selectedTags.value = tags;
        // console.log('setSelectedTags', _selectedTags.value);
    }

    // 선택된 태그 리스트 초기화
    function clearSelectedTags() {
        _selectedTags.value = [];
    }

    function addTag(tags) {
        _tags.value = _tags.value.concat(tags);
    }

    return {
        initTags,
        canSelectTag,
        getTags,
        selectTag,
        deselectTag,
        getSelectedTags,
        setSelectedTags,
        addTag,
        clearSelectedTags,
        init,
        postInit
    };
}