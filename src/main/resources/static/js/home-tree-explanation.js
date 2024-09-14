export const bookExRemoveNone = (selectedElement) => {
  const id = selectedElement.classList[1];
  console.log(id);
  document.querySelectorAll(".book-tree-explanation-item").forEach((item) => {
    if (item.classList.contains(id)) {
      item.classList.remove("none");
    } else {
      item.classList.add("none");
    }
  });
};

