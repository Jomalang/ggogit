
// 북 카드 템플릿
function bookCard(value){
    return`
    <div class="card-tree-details">
                                <a class="card-tree-detail" href="/tree/detail/${value.treeId}">
                                    <div class="card-tree__img-frame">
                                        <img class="card-tree__book-cover" src="/img/home/${value.coverImageName}" alt="treeCover" />
                                    </div>
                                    <div class="card-tree-detail__box">
                                        <div class="card-tree-detail__tags">
                                            <span class="card-tree-detail__tag">${value.seedKorName}</span>
                                            <span class="card-tree-detail__tag">${value.bookCategory}</span>
                                        </div>
                                        <div class="card-tree-detail__slot">
                                            <p class="card-tree-detail__name">${value.bookTitle}</p>
                                            ${value.bookComplete ? '<div class="card-tree-detail__complete-icon"></div>' : ''}
                                        </div>
                                        <p class="card-tree-detail__explanation">${value.title}</p>
                                        <div class="card-tree-detail__info">
                                            <span class="card-tree-detail__info">${value.bookPublishedYear}</span>
                                            <span class="card-tree-detail__info">/</span>
                                            <span class="card-tree-detail__info">${value.bookAuthor}</span>
                                            <span class="card-tree-detail__info">/</span>
                                            <span class="card-tree-detail__info" th:if="*{bookTranslator != null}">${value.bookTranslator}</span>
                                            <span class="card-tree-detail__info" th:if="*{bookTranslator != null}">/</span>
                                            <span class="card-tree-detail__info">${value.bookPublisher}</span>
                                        </div>
                                        <span class="card-tree-detail__info-created-date">${new Date(value.leafCreatedAt).toISOString().split('T')[0]}</span>
                                    </div>
                                </a>
                            </div>
    `;
}
// ETC 카드 템플릿
function etcCard(value){
return`
                            <div class="card-tree-details">
                                <a class="card-tree-detail" href="/tree/detail/${value.treeId}">
                                    <div class="card-tree__img-frame">
                                        <img class="card-tree__book-cover" src="/img/home/${value.coverImageName}" alt="treeCover" />
                                    </div>
                                    <div class="card-tree-detail__box">
                                        <div class="card-tree-detail__tags">
                                            <span class="card-tree-detail__tag">${value.seedKorName}</span>
                                        </div>
                                        <p class="card-tree-detail__explanation">${value.title}</p>
                                        <div class="card-tree-detail__info">
                                            <span class="card-tree-detail__info">${value.bookAuthor}</span>
                                        </div>
                                        <span class="card-tree-detail__info-created-date">${new Date(value.leafCreatedAt).toISOString().split('T')[0]}</span>
                                    </div>
                                </a>
                            </div>`;
}
// NULL 카드 템플릿
function nullCard(){
    return`                 <div class="card-tree__img-frame">
                                        <img class="card-tree__book-cover" src="/img/home/no-tree-mid-book.png" alt="treeCover" />
                                    </div>
                                    <div class="card-tree-detail__box">
                                        <div class="card-tree-detail__tags">
                                            <span class="card-tree-detail__tag"></span>
                                            <span class="card-tree-detail__tag"></span>
                                        </div>
                                        <div class="card-tree-detail__slot">
                                            <p class="card-tree-detail__name">조회된 트리가 없습니다.</p>
                                        </div>
                                        <p class="card-tree-detail__explanation"></p>
                                        <div class="card-tree-detail__info">
                                            <span class="card-tree-detail__info"></span>
                                            <span class="card-tree-detail__info"></span>
                                            <span class="card-tree-detail__info"></span>
                                            <span class="card-tree-detail__info"></span>
                                            <span class="card-tree-detail__info" th:if="*{bookTranslator != null}"></span>
                                            <span class="card-tree-detail__info" th:if="*{bookTranslator != null}"></span>
                                            <span class="card-tree-detail__info"></span>
                                        </div>
                                        <span class="card-tree-detail__info-created-date"></span>
                                    </div>`;
}

// 홈페이지 트리 전체보기 AJAX 요청 함수
function listLoad(){
    fetch(`/api/tree/list?seed=0`)
        .then(response => response.json())
        .then(list => {
            treeCardListFrame.innerHTML = list.map(value => {
                if (value.seedId < 2) {
                    return bookCard(value);
                } else {
                    return etcCard(value);
                }
            }).join('');
        })
        .catch(error => console.error('Error:', error));
}
// 홈페이지 처음 로딩 시 AJAX 요청
window.onload = function (){
    listLoad();
}

// --------------------------------------------------------------------------------------
//                          홈페이지 시드 별 트리 요청 AJAX
//---------------------------------------------------------------------------------------
const seedMenu = document.getElementById("seed-filter");
const treeCardListFrame = document.getElementById("tree-card-list");


seedMenu.addEventListener("change", (e) => {
    if(!e.target.checked) return;

    const seedId = e.target.dataset.id;

fetch(`/api/tree/list?seed=${seedId}`)
    .then(response => response.json())
    .then(list => {
        console.log(list);
        if (list.length === 0 )
            return treeCardListFrame.innerHTML = nullCard();

        treeCardListFrame.innerHTML = list.map(value => {
            if (value.seedId < 2) {
                return bookCard(value);
            } else {
                return etcCard(value);
            }
        }).join('');
    })
    .catch(error => console.error('Error:', error));
});