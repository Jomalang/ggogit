const seedMenu = document.getElementById("my-tree-list");
const seed = seedMenu.querySelector("input");
const treeCardListFrame = document.getElementById("\"tree-card-list\"");
const cardList = treeCardListFrame.querySelector("> div");

seed.onclick = function (e){
    if(!e.target.classList.contains("filter-tree-list-btn"))
        return;

    let requst = new XMLHttpRequest();

    let seedId = e.target.dataset.id;

    if(seedId != null){
        requst.open("GET", `/api/tree/list?seed=${seedId}`,false);
        requst.send(null);


        let list = JSON.parse(requst.responseText);

        cardList.innerHTML = "";

        if(seedId == 1){
        list.forEach(value=>{
            let template = `

    <div class="card-tree-details">
      <a class="card-tree-detail" th:href="@{/tree/detail/{id}(id=${value.treeId})}">
          <div class="card-tree__img-frame">
            <img
              class="card-tree__book-cover"
              src="/svg/card-felt__cover.svg"
              th:src="@{/img/home/{img}(img=${value.coverImageName})}"
              alt="treeCover"
            />
          </div>
        <div class="card-tree-detail__box">
          <!--반복문으로 넣어야 할듯..-->
          <div class="card-tree-detail__tags">
            <span class="card-tree-detail__tag" id="data-XXX" th:text="${value.bookCategory}">도서</span>
<!--            <span class="card-tree-detail__tag" id="data-XXX">정치비평</span>-->
          </div>
          <!---->
            <div class="card-tree-detail__slot">
                <p class="card-tree-detail__name" id="data-XXX" th:text="${value.bookTitle}">
                    운명이란 무엇인가?
                </p>
                <div th:if="${value.bookComplete}" class="card-tree-detail__complete-icon" ></div>
            </div>
          <p
            class="card-tree-detail__explanation"
            id="data-XXX"
            th:text="${value.title}"
          >
            그의 운명에 대한 아주 개인적인 생각
          </p>
          <div class="card-tree-detail__info">
            <span
                    class="card-tree-detail__info"
                    id="data-XXX"
                    th:text="${value.bookPublishedYear}"
            >2024</span
            >
                    <span class="card-tree-detail__info">/</span>
                    <span
                            class="card-tree-detail__info"
                            id="data-XXX"
                            th:text="${value.bookAuthor}"
                    >유시민</span
                    >
                    <span class="card-tree-detail__info">/</span>
                    <span
                            class="card-tree-detail__info"
                            id="data-XXX"
                            th:text="${value.bookPublisher}"
                    >생각의 길</span
                    >
                </div>
                <span
                        class="card-tree-detail__info-created-date"
                        id="data-XXX"
                        th:text="${#dates.format(value.leafCreatedAt, 'yyyy-MM-dd')}"
                >2024-07-15</span
                >
            </div>
        </a>
    </div>`;
            list.insertAdjacentHTML("beforeend", template) //입력 위치, 입력 정보 형식이며 위치는 총 4가지 https://developer.mozilla.org/ko/docs/Web/API/Element/insertAdjacentHTML
        })

        }
    }
    list.forEach({

    })
}