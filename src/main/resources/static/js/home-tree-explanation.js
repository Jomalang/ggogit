const bookExRemoveNone = () => {
  const bookShowing = document.querySelector(".selected");
  console.log(bookShowing);
  let id = 0;
  if (bookShowing !== null) {
    id = bookShowing.classList[1];
  }

  console.log(id);

  document.querySelectorAll(".book-tree-explanation-item").forEach((item) => {
    if (item.classList.contains(id)) {
      item.classList.remove("none");
    } else {
      item.classList.add("none");
    }
  });
};
window.addEventListener("pageshow", bookExRemoveNone);
carouselList.addEventListener("touchend", bookExRemoveNone);
carouselList.addEventListener("mousedown", bookExRemoveNone);
