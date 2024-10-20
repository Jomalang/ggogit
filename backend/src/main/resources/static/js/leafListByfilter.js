//전역 변수
const pathname = window.location.pathname;
const lastSegments = (pathname) => pathname.split('/').pop();
const treeId = lastSegments(pathname);
let bookMark;

//필터 이름 변경 함수
function updateFilterName(standard, sort, filterTabBtn) {
    filterTabBtn.innerHTML = `
            <input
                class="bar-search-current__detail-input"
                type="checkbox"
            />
            <label
                class="bar-search-current__detail"
                for="bar-search-current__detail"
            >${standard || ''} ${sort || ''}</label>
        `;
}

function setupFilter(filterTabBtn) {
    let filter, sort, filterName, sortName, tmpName;
    filter = document.querySelector("input[type='radio'][name='filter']:checked");
    tmpName = filter.closest('label');
    if (tmpName)
        filterName = tmpName.textContent.replace(filter.outerHTML, '').trim();

    sort = document.querySelector("input[type='radio'][name='sort']:checked");
    tmpName = sort.closest('label');
    if (tmpName)
        sortName = tmpName.textContent.replace(sort.outerHTML, '').trim();

    updateFilterName(filterName, sortName, filterTabBtn);
}

function updateBookmark(sortBtn) {
    return  sortBtn.querySelector(" input[type='radio'][name='bookMark']:checked").value;
}

function updateFilter(filterFrame) {
    return  filterFrame.querySelector(" input[type='radio'][name='filter']:checked").value;
}
function updateSort(filterFrame) {
    return filterFrame.querySelector(" input[type='radio'][name='sort']:checked").value;
}

function generateBranchList(list){
    return list.map(value => {
        return template(value);
    });
}

function listLoad(filterFrame, branchListFrame, filterCount){
    let filter, sort;
    filter = updateFilter(filterFrame);
    sort = updateSort(filterFrame);
    fetch(`/api/v1/filter?treeId=${treeId}&filter=${filter}&sort=${sort}`)
        .then(response => response.json())
        .then(list =>{
            if (branchListFrame) {
                if(list.length === 0)
                    branchListFrame.innerHTML = nullTemplate();
                else {
                    branchListFrame.innerHTML = generateBranchList(list).join('');
                    filterCount.innerHTML = branchCount(list);
                }
            } else {
                console.error("branchListFrame element not found");
            }
        });
}


function branchListByFilter(bookMark, filterFrame, sortBtn, branchListFrame, filterCount) {

    let filter, sort;
    filter = updateFilter(filterFrame);
    sort = updateSort(filterFrame);

    branchListFrame.innerHTML = ``;

    if (bookMark != null)
        fetch(`/api/v1/filter?treeId=${treeId}&bookMark=${bookMark}&filter=${filter}&sort=${sort}`)
            .then(response => response.json())
            .then(list => {
                if (branchListFrame) {
                    if(list.length === 0)
                        branchListFrame.innerHTML = nullTemplate();
                    else {
                        branchListFrame.innerHTML = generateBranchList(list).join('');
                        filterCount.innerHTML = branchCount(list);
                    }
                } else {
                    console.error("branchListFrame element not found");
                }
            })
            .catch(error => console.error('Error: ', error));
    else
        fetch(`/api/v1/filter?treeId=${treeId}&filter=${filter}&sort=${sort}`)
            .then(response => response.json())
            .then(list => {
                if (branchListFrame) {
                    if(list.length === 0)
                        branchListFrame.innerHTML = nullTemplate();
                    else {
                        branchListFrame.innerHTML = generateBranchList(list).join('');
                        filterCount.innerHTML = branchCount(list);
                    }
                } else {
                    console.error("branchListFrame element not found");
                }
            })
            .catch(error => console.error('Error: ', error));

}

function updateBookMark(e){
    return e.target.dataset.id;
}


function branchListByBookmark(e,bookMark,filterFrame, branchListFrame, filterCount) {

    let filter, sort;
    filter = updateFilter(filterFrame);
    sort = updateSort(filterFrame);

    bookMark = e.target.dataset.id;
    branchListFrame.innerHTML = ``;

    if (bookMark != null)
        fetch(`/api/v1/filter?treeId=${treeId}&bookMark=${bookMark}&filter=${filter}&sort=${sort}`)
            .then(response => response.json())
            .then(list => {
                if (branchListFrame) {
                    branchListFrame.innerHTML = generateBranchList(list).join('');
                    filterCount.innerHTML = branchCount(list);
                } else {
                    console.error("branchListFrame element not found");
                }
            })
            .catch(error => console.error('Error: ', error));
    else
        fetch(`/api/v1/filter?treeId=${treeId}&filter=${filter}&sort=${sort}`)
            .then(response => response.json())
            .then(list => {
                if (branchListFrame) {
                    branchListFrame.innerHTML = generateBranchList(list).join('');
                    filterCount.innerHTML = branchCount(list);
                } else {
                    console.error("branchListFrame element not found");
                }
            })
            .catch(error => console.error('Error: ', error));

}
//===============================================================
//                         TEMPLATE
//===============================================================
function template(value){
    const formattedDate = new Date(value.updateTime).toISOString().split('T')[0];
    return `
    <div class="card-branch__list-frame" >
    <div class="card-branch__list-frame-flex">
        <a class="branch-info-frame" href="#">
            <div class="branch-img-frame">
                <img src="${value.bookMark ? '/svg/card-bookmark-icon.svg' : '/svg/card-branch-represent-icon.svg' }" alt="브랜치 이미지">
            </div>
            <div class="branch-detail-info">
                <p class="branch-detail-info--name">${value.title}</p>
            </div>
        </a>
        <div class="branch-img-frame-lock">
                <img src="${value.visibility ? '' : '/svg/lock.svg' }" >
        </div>
    </div>
    <div class="branch-card-bottom-info-frame">
        <div class="branch-card-bottom-info">
            <span>리프 <p>${value.leafCount}</p></span>
            <span>조회수 <p>${value.viewCount}</p></span></div>
        <div class="branch-card-bottom-info">
            <p class="branch-detail-info--regdate">${formattedDate}</p>
        </div>
    </div>
    </div>
`;
}
function nullTemplate(){
    return `
    <div class="card-branch__list-frame">
        <div class="branch-detail-info">
            <p class="branch-detail-info--name">조회된 리프가 없습니다.</p>
        </div>
</div>
`;
}

function branchCount(list) {
    return`
    <span
              class="text-main-side-text"
              id="branch-count">
              ${list.length} 개
      </span>
    `;
    
}




//==========================================================================================
//                           DOM로드 후 실행 이벤트 리스너
//==========================================================================================
document.addEventListener("DOMContentLoaded", () => {
    const filterTabBtn = document.getElementById("filter-tree-leaf__card-sort");
    const filterTabCloseBtn = document.getElementById("filter-tab-close-btn");
    const filterBg = document.getElementById("filter-bg-blur");
    const filterTab = document.getElementById("filter-tab");
    const filterCount = document.getElementById("branch-count");


    filterTabBtn.addEventListener("click", () => {
        filterBg.classList.remove("none");
        setTimeout(() => filterTab.classList.add("up"), 10);
    });

    filterTabCloseBtn.addEventListener("click", () => {
        filterTab.classList.remove("up");
        filterTab.addEventListener("transitionend", function handler() {
            setupFilter(filterTabBtn);
            filterBg.classList.add("none");
            filterTab.removeEventListener("transitionend", handler);
        });
    });

    // 초기 설정
    setupFilter(filterTabBtn);


//===============================================================
//                     BRANCH LIST 생성
//===============================================================


    const branchListFrame = document.getElementById("card-branch__list-frame");
    if (!branchListFrame)
        console.error("id : " + branchListFrame)
    const sortBtn = document.getElementById("branchSortFilter");
    const filterFrame = document.getElementById("filter-bg-blur");
    const selectFilter = document.getElementById("filter-tab__list1");
    const selectSort = document.getElementById("filter-tab__list2");

//===============================================================
//                     AJAX 구현
//===============================================================

    //초기 필터값 브랜치 list
    listLoad(filterFrame, branchListFrame, filterCount);

    //버튼 필터값 브랜치 list (전체, 북마크, 리프)
    sortBtn.addEventListener("click", (e) => {
        if (!e.target.checked) return
        branchListByBookmark(e,bookMark,filterFrame, branchListFrame, filterCount);
        bookMark = updateBookMark(e);
    });

    //팝업 필터값 브랜치1 list (최신 수정, 기준,...)
    selectFilter.addEventListener("click", (e) => {
        if (!e.target.checked) return
        branchListByFilter(bookMark, filterFrame, sortBtn, branchListFrame, filterCount);
    });

    //팝업 필터값 브랜치2 list (오름순, 내림순)
    selectSort.addEventListener("click", (e) => {
        if (!e.target.checked) return
        branchListByFilter(bookMark, filterFrame, sortBtn, branchListFrame, filterCount);
    });
});
