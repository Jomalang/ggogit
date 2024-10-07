
    function commentTabActive() {
    const commentTab = document.getElementById("comment-filter-tab-id");

    commentTab.classList.add("book-detail-comment-tab-container--active");

    document.body.classList.add("no-scroll");
    window.scrollTo({ behavior: "smooth"});
}

    function commentTabInactive() {
    const commentTab = document.getElementById("comment-filter-tab-id");

    commentTab.classList.remove("book-detail-comment-tab-container--active");
    document.body.classList.remove("no-scroll");
}

    document.getElementById("bar-comment-id").addEventListener("click", commentTabActive);
    document.getElementById("top-bar-comment__back-icon-box-id").addEventListener("click", commentTabInactive);
    document.getElementById("top-bar-comment__line-box").addEventListener("click", commentTabInactive);

    document.getElementById("input-comment-input__input-id").addEventListener("focus", function () {
    const submitBtn = document.getElementById("input-comment-input__submit-id");
    setTimeout(() => {
    submitBtn.classList.add("input-comment-input__submit--active")
}, 100);
});

    document.getElementById("input-comment-input__input-id").addEventListener("focusout", function () {
    const submitBtn = document.getElementById("input-comment-input__submit-id");
    submitBtn.classList.remove("input-comment-input__submit--active");
});
