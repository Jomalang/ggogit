<script setup lang="ts">

</script>

<template>
  <header>
    <h1 class="none">사용자 트리 세부정보</h1>
    <section  class="reg-book-search-container">
        <h2 class="none">트리 검색</h2>
        <!-- 컴포넌트 start -->
        <div
                <!-- th:replace="~{fragments/input::input-back-search-text(placeholder='검색할 트리를 입력해주세요', action='', name='treeSearchText', href='javascript:history.back()')}" -->
        ></div>
    </section>
</header>
<main>
    <section class="user-tree-info__container">
        <h2 class="none">트리 정보</h2>
        <div th:replace="~{fragments/card :: card-tree-info-cover(src=${treeInfoView.coverImageName},treetitle=${treeInfoView.title},booktitle=${treeInfoView.bookTitle})}">
        </div>
    </section>
    <section class="branch-tree-detail-container"
             th:with="rPage=${treeInfoView.readingPage ?: 0}, totalPage=${treeInfoView.bookTotalPage ?: 1}">
        <h2 class="none">트리 상세 설명</h2>
        <div th:replace="~{fragments/card :: card-hidden-info(
        hiddentext='자세히',
        authors=${treeInfoView.bookAuthor},
        translators=${treeInfoView.bookTranslator},
        publisher=${treeInfoView.bookPublisher},
        page=${totalPage},
        seed=${treeInfoView.seedId},
        treedescription=${treeInfoView.description},
        readpage=${rPage},
        progress=${#numbers.formatDecimal((rPage * 100.0 / totalPage), 1, 1)},
        fullpage=${treeInfoView.bookTotalPage},
        leaf=${treeInfoView.treeLeafCnt},
        like=${treeInfoView.treeLikeCnt},
        view=${treeInfoView.treeViewCnt}
    )}"></div>
    </section>

    <section class="branch-list__container">
        <h2 th:replace="~{fragments/text :: text-main-title__listCount(title='브랜치 목록', number=${#lists.size(leafList)})}"></h2>
        <!-- 컴포넌트 -->
        <section class="branch-filter-container">
            <h3 class="none">브랜치 필터</h3>
            <!-- 컴포넌트 -->
            <div th:replace="~{fragments/filter :: filter-tree-leaf__card()}"></div>
        </section>

        <section class="branch-filter-result-list__container">
            <h3 class="none">브랜치 리스트</h3>
            <!-- 컴포넌트 -->
            <div id="card-branch__list-frame" >
<!--            <div th:replace="~{fragments/card :: card-branch__list(${leafList})}"></div>-->
            </div>
        </section>
    </section>
    <section class="filter-tab-container--30 none" id="filter-bg-blur">
        <h2 class="none">정렬 선택</h2>
        <div
                id="filter-tab"
                class="filter-tab__box--30"
        >
            <div class="filter-tab__header">
                <button
                        id="filter-tab-close-btn"
                        class="filter-tab__btn--back"
                        type="button"
                >
                    <img th:src="@{/svg/tab-back.svg}" alt="뒤로가기 버튼" />
                </button>
                <h1 class="filter-tab__header--title" >정렬 선택</h1>
            </div>

            <div class="filter-attribute__bg">
                <h2 class="filter-attribute__title">정렬 기준</h2>
                <ul class="filter-tab__list" id="filter-tab__list1">
                    <li  class="filter-tab__item">
                        <label class="filter-tab__item-label">
                            <input class="filter-tab__item-radio" type="radio" name="filter" value="10" checked>
                            <span class="filter-tab__item--label-text">최근 수정</span>
                            <div class="filter-tab__icon-box">
                                <img class="filter-tab__icon-img" src="/svg/tab-check-btn.svg" alt="필터 버튼">
                            </div>
                        </label>
                    </li>
                    <li  class="filter-tab__item">
                        <label class="filter-tab__item-label">
                            <input class="filter-tab__item-radio" type="radio" name="filter" value="11">
                            <span class="filter-tab__item--label-text">제목</span>
                            <div class="filter-tab__icon-box">
                                <img class="filter-tab__icon-img" src="/svg/tab-check-btn.svg" alt="필터 버튼">
                            </div>
                        </label>
                    </li>
                    <li  class="filter-tab__item">
                        <label class="filter-tab__item-label">
                            <input class="filter-tab__item-radio" type="radio" name="filter" value="12">
                            <span class="filter-tab__item--label-text">리프 수</span>
                            <div class="filter-tab__icon-box">
                                <img class="filter-tab__icon-img" src="/svg/tab-check-btn.svg" alt="필터 버튼">
                            </div>
                        </label>
                    </li>
                    <li  class="filter-tab__item">
                        <label class="filter-tab__item-label">
                            <input class="filter-tab__item-radio" type="radio" name="filter" value="13">
                            <span class="filter-tab__item--label-text">조회 수</span>
                            <div class="filter-tab__icon-box">
                                <img class="filter-tab__icon-img" src="/svg/tab-check-btn.svg" alt="필터 버튼">
                            </div>
                        </label>
                    </li>
                </ul>
            </div>
            <div class="filter-attribute__bg">
                <h2 class="filter-attribute__title">정렬 순서</h2>
                <ul class="filter-tab__list" id="filter-tab__list2">
                    <li  class="filter-tab__item">
                        <label class="filter-tab__item-label">
                            <input class="filter-tab__item-radio" type="radio" name="sort" value="1" checked>
                            <span class="filter-tab__item--label-text">내림차순</span>
                            <div class="filter-tab__icon-box">
                                <img class="filter-tab__icon-img" src="/svg/tab-check-btn.svg" alt="필터 버튼">
                            </div>
                        </label>
                    </li>
                    <li  class="filter-tab__item">
                        <label class="filter-tab__item-label">
                            <input class="filter-tab__item-radio" type="radio" name="sort" value="0">
                            <span class="filter-tab__item--label-text">오름차순</span>
                            <div class="filter-tab__icon-box">
                                <img class="filter-tab__icon-img" src="/svg/tab-check-btn.svg" alt="필터 버튼">
                            </div>
                        </label>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</main>

<section class="nav-back-container">
    <h2 class="none">네비바 뒤 공백</h2>
</section>
<aside>
    <section class="nav-container">
        <h2 class="none">네비게이션</h2>
        <!-- 트리 생성 언더바  -->
        <div th:replace="~{fragments/nav :: navigation-bar(active='home')}"></div>
    </section>
</aside>

</template>

<style scoped>

</style>