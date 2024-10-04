function template(value){
    return `
    <div class="card-branch__list-frame" th:fragment="card-branch__list(lists)" th:each="list : ${lists}">
    <a class="branch-info-frame" href="#">
        <div class="branch-img-frame">
            <img th:src="@{${list.bookMark} ? '/svg/card-bookmark-icon.svg' : '/svg/card-branch-represent-icon.svg' }" alt="브랜치 이미지">
        </div>
        <div class="branch-detail-info">
            <p class="branch-detail-info--name" th:text="${list.title}"></p>
        </div>
    </a>
    <div class="branch-card-bottom-info-frame">
        <div class="branch-card-bottom-info">
            <span>리프 <p th:text="${list.leafCount}"></p></span>
            <span>조회수 <p th:text="${list.viewCount}"></p></span></div>
        <div class="branch-card-bottom-info">
            <p class="branch-detail-info--regdate" th:text="${#temporals.format(list.updateTime, 'yyyy-MM-dd')}"></p>
        </div>
    </div>
</div>
`;
}

function listload(){
    fetch(`/api/v1/filter?treeId=${}&filter`)
        .then(response => response.json())
        .then(list =>{

        });
}