const bookExRemoveNone = () => {
  const bookShowing = document.querySelector(".selected");
  const id = bookShowing.classList[1];

  document.querySelectorAll(".book-tree-explanation-item").forEach((item) => {
    if (item.classList.contains(id)) {
      item.classList.remove("none");
    } else {
      item.classList.add("none");
    }
  });
};
window.addEventListener("load", bookExRemoveNone);
carouselList.addEventListener("touchend", bookExRemoveNone);
carouselList.addEventListener("mousedown", bookExRemoveNone);
