const seedMenu = document.getElementById("seed-filter");
const treeCardListFrame = document.getElementById("tree-card-list");

function listLoad(){
    fetch(`/api/tree/list?seed=0`)
        .then(response => response.json())
        .then(list => {
            treeCardListFrame.innerHTML = list.map(value => {
                if (value.seedId < 2) {
                    return `
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
                } else {
                    return `
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
                            </div>
                        `;
                }
            }).join('');
        })
        .catch(error => console.error('Error:', error));
}
window.onload = function (){
    listLoad();
}


seedMenu.addEventListener("change", (e) => {
    if(!e.target.checked) return;

    const seedId = e.target.dataset.id;

    if (seedId >= 0) {
        fetch(`/api/tree/list?seed=${seedId}`)
            .then(response => response.json())
            .then(list => {
                treeCardListFrame.innerHTML = list.map(value => {
                    if (value.seedId < 2) {
                        return `
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
                    } else {
                        return `
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
                            </div>
                        `;
                    }
                }).join('');
            })
            .catch(error => console.error('Error:', error));
    }
});