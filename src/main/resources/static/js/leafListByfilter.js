//===============================================================
//                         TEMPLATE
//===============================================================
function template(value){
    const formattedDate = new Date(value.updateTime).toISOString().split('T')[0];
    return `
    <div class="card-branch__list-frame" id="card-branch__list-frame">
    <a class="branch-info-frame" href="#">
        <div class="branch-img-frame">
            <img src="${value.bookMark ? '/svg/card-bookmark-icon.svg' : '/svg/card-branch-represent-icon.svg' }" alt="브랜치 이미지">
        </div>
        <div class="branch-detail-info">
            <p class="branch-detail-info--name">${value.title}</p>
        </div>
    </a>
    <div class="branch-card-bottom-info-frame">
        <div class="branch-card-bottom-info">
            <span>리프 <p>${value.viewCount}</p></span>
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

//===============================================================
//                     BRANCH LIST 생성
//===============================================================

const urlParam = new URL(location.href).searchParams;
const treeId = urlParam.get('treeId');

const branchListFrame = document.getElementById("card-branch__list-frame");
const sortBtn = document.getElementById("branchSortFilter");
const filterFrame = document.getElementById("filter-bg-blur");

let filter, sort;


function updateFilter() {
    filter = filterFrame.querySelector(" input[type='radio'][name='filter']:checked").value;
    sort = filterFrame.querySelector(" input[type='radio'][name='sort']:checked").value;

    console.log("filter : " + filter);
    console.log("sort : " + sort);
}

function generateBranchList(list){
    if (list.length === 0)
        return nullTemplate();

    return list.map(value => {
        return template(value);
    });
}

function listload(){
    updateFilter();
    fetch(`/api/v1/filter?treeId=${treeId}&filter=${filter}&sort=${sort}`)
        .then(response => response.json())
        .then(list =>{
            branchListFrame.innerHTML = generateBranchList(list).join('');
        });
}

//===============================================================
//                    페이지 로딩 시 실행 AJAX
//===============================================================

window.onload = function (){
listload();
}


//===============================================================
//                     AJAX
//===============================================================

sortBtn.addEventListener("click",(e) =>{
    if(!e.target.checked) return

    updateFilter();
    const isLeaf = e.target.dataset.id;

    if (isLeaf === null)
    fetch(`api/v1/filter?treeId=${treeId}&filter=${filter}&sort=${sort}`)
        .then(response => response.json())
        .then(list => {
            branchListFrame.innerHTML = generateBranchList(list).join('');
        })
        .catch(error => console.error('Error: ', error));
    else
        fetch(`api/v1/filter?treeId=${treeId}&isLeaf=${isLeaf}&filter=${filter}&sort=${sort}`)
            .then(response => response.json())
            .then(list => {
                branchListFrame.innerHTML = generateBranchList(list).join('');
            })
            .catch(error => console.error('Error: ', error));

})