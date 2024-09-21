import {carouselList, carouselItems} from "./home-tree-index-carousel.js";

export const bookExRemoveNone = (selectedElement) => {
  const id = selectedElement.classList[1];

  carouselList.querySelectorAll(".mid__item").forEach(item => {
    if(item === selectedElement){
      item.classList.add("center__item");
    } else{
      item.classList.remove("center__item");
    }
  })

  document.querySelectorAll(".textbox-recent-tree-info__frame").forEach(item => {
    if (item.classList.contains(id)) {
      item.classList.remove("none");
    } else {
      item.classList.add("none");
    }
 })

  document.querySelectorAll(".book-tree-explanation-item").forEach((item) => {
    if (item.classList.contains(id)) {
      item.classList.remove("none");
    } else {
      item.classList.add("none");
    }
  });
};

export const calcMidTree = () =>{
  let ids = [];
  let ret = null;
  carouselList.querySelectorAll(".selected").forEach(item => { ids.push
  (item.classList[1]);
  });
  const midId = String(ids[1]);
  carouselList.querySelectorAll(".selected").forEach(item => {
    if(item.classList[1] === midId) {
      console.log(item);
      ret = item;
    }
  });
  return ret;
}

