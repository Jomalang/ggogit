function makeTagElement(tagId, tagName) {
    const li = document.createElement('li');
    li.classList.add('input-tag-select__tag');
    li.innerHTML = `
                <label class="input-tag-select__tag-label">
                      <span class="input-tag-select__tag-text">${tagName}</span>
                      <button class="input-tag-select__tag-delete-btn" type="button">
                        <img src="/svg/x-button.svg" alt="next-button" />
                      </button>
                      <input class="none input-tag-select__input" name="tagIds" value="${tagId}" />
                </label>
            `;
    return li;
}

function removeTagEvent(liTag) {

    const tagList = document.querySelector('.input-tag-select__bg');
    tagList.addEventListener('click', (event) => {
        event.preventDefault(); // 라벨 클릭 이벤트 제거
    });

    const label = liTag.querySelector('.input-tag-select__tag-label');
    label.addEventListener('click', (event) => {
        event.preventDefault(); // 라벨 클릭 이벤트 제거
    });

    const input = label.querySelector('.input-tag-select__input');
    const deleteBtn = liTag.querySelector('.input-tag-select__tag-delete-btn');
    deleteBtn.addEventListener('click', () => {
        // 세션 스토리지에서 삭제한다.
        const sessionStorageTagList = sessionStorage.getItem('selectedTagList');
        if (!sessionStorageTagList) { return; } // 저장된 데이터가 없다면 종료한다.
        const tagInfos = JSON.parse(sessionStorageTagList);
        const newTagInfos = tagInfos.filter(ti => ti.id !== parseInt(input.value));
        sessionStorage.setItem('selectedTagList', JSON.stringify(newTagInfos));

        // HTML 요소를 삭제한다.
        liTag.remove();

        // 태그가 0개인 경우 placeholder를 추가
        const tagList = document.querySelector('.input-tag-select__tags');
        if (tagList.children.length === 0) {
            const placeHolder = document.createElement('li');
            placeHolder.classList.add('input-tag-select__placeholder');
            placeHolder.textContent = '태그를 선택해주세요.';
            tagList.insertAdjacentElement('beforeend', placeHolder);
        }
    });
}

function loadTagData() {
    // 세션 스토리지에서 불러온다.
    const tagList = sessionStorage.getItem('selectedTagList');
    if (!tagList) { return; } // 저장된 데이터가 없다면 종료한다.
    if (tagList === '[]') { return; } // 저장된 데이터가 없다면 종료한다.

    const placeHolder = document.querySelector('.input-tag-select__placeholder');
    placeHolder.remove();

    const tagInfos = JSON.parse(tagList);
    tagInfos.forEach(ti => {
        const liTag = makeTagElement(ti.id, ti.name);
        removeTagEvent(liTag);
        const tagList = document.querySelector('.input-tag-select__tags');
        tagList.insertAdjacentElement('beforeend', liTag);
    });
}

function saveInputData() {
    // 세션 스토리지에 저장한다.
    const form = document.getElementById('input-leaf-register-form-id');
    const formData = new FormData(form);
    const formJson = JSON.stringify(Object.fromEntries(formData.entries()));
    console.log(formJson);
    sessionStorage.setItem('input-leaf-register-form', formJson);
}

function loadInputData() {
    // 세션 스토리지에서 불러온다.
    const formJson = sessionStorage.getItem('input-leaf-register-form');
    if (!formJson) { return; } // 저장된 데이터가 없다면 종료한다.

    const formObj = JSON.parse(formJson);
    const form = document.getElementById('input-leaf-register-form-id');
    for (const key in formObj) {
        if (key === 'tagIds') { continue; } // 태그는 제외한다.

        // 공개성 input
        if (key === 'visibility') {
            const isPublic = formObj[key] === '1'; // 1: 공개, 0: 비공개
            document.getElementById('public').checked = isPublic;
            document.getElementById('private').checked = !isPublic;
            continue;
        }

        // 나머지 input
        const input = form.querySelector(`[name="${key}"]`);
        if (input) {
            input.value = formObj[key];
        }
    }
}

function getUrlPath() {
    return window.location.pathname + window.location.search;
}

function loadPrevPage() {
    const prevPage = {
        path: getUrlPath()
    }
    sessionStorage.setItem('prevPage', JSON.stringify(prevPage));
}

function tagSelectedBtnEvent() {
    const tagSelectBtn = document.getElementById('input-tag-select__button-id');
    tagSelectBtn.addEventListener('click', () => {
        saveInputData();
        location.href = '/tag/list';
    });
}

function submitBtnEvent() {
    const submitBtn = document.getElementById('book-tree-input-form-id');
    submitBtn.addEventListener('click', () => {
        const form = document.getElementById('input-leaf-register-form-id');
        sessionStorage.removeItem('input-leaf-register-form');
        sessionStorage.removeItem('selectedTagList');
        form.submit();
    });
}

document.addEventListener('DOMContentLoaded', () => {
    loadPrevPage();
    loadInputData();
    loadTagData();
    tagSelectedBtnEvent();
    submitBtnEvent();

    setTimeout(() => {
        document.querySelector('.first-log-box')
            .classList.add('first-log-box--show');
    }, 500); // 0.5초 뒤에 이벤트를 등록한다.
});