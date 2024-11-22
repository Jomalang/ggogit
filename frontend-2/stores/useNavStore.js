import { defineStore } from "pinia";

export const useNavStore = defineStore("navStore", () => {
  const myId = useMemberStore()._id;
  const currentSpot = ref("_home"); //_home, _search, _community, _mypage로 현재 위치 표현

  const home = ref("/home");
  const search = ref("/search/book");
  const community = ref("/community");
  const mypage = ref(`/member/${myId}`);

  //logout시 사용
  function initStore() {
    currentSpot.value = "_home";

    home.value = "/home";
    search.value = "/search/book";
    community.value = "/community";
    mypage.value = `/member/${myId}`;

    localStorage.setItem("_ggogit_path_home", home.value);
    localStorage.setItem("_ggogit_path_search", search.value);
    localStorage.setItem("_ggogit_path_community", community.value);
    localStorage.setItem("_ggogit_path_mypage", mypage.value);
  }

  //새로고침 마다 사용
  function loadFromStorage() {
    if (!import.meta.env.SSR) {
      home.value = localStorage.getItem("_ggogit_path_home") || "/home";
      search.value =
        localStorage.getItem("_ggogit_path_search") || "/search/book";
      community.value =
        localStorage.getItem("_ggogit_path_community") || "/community";
      mypage.value =
        localStorage.getItem("_ggogit_path_mypage") || `/member/${myId}`;
    }
  }

  //nav컴포넌트에서 버튼 클릭시 사용, 현재 위치 표현
  function setCurrentSpot(spotNum) {
    if (spotNum === 1) {
      currentSpot.value = "_home";
    } else if (spotNum === 2) {
      currentSpot.value = "_search";
    } else if (spotNum === 3) {
      currentSpot.value = "_community";
    } else if (spotNum === 4) {
      currentSpot.value = "_mypage";
    } else {
      console.error("잘못된 path입니다.");
      return;
    }
  }

  //페이지 전환때마다 미들웨어에서 사용
  function setHome(value) {
    home.value = value;
    localStorage.setItem("_ggogit_path_home", home.value);
  }

  function setSearch(value) {
    search.value = value;
    localStorage.setItem("_ggogit_path_search", search.value);
  }

  function setCommunity(value) {
    community.value = value;
    localStorage.setItem("_ggogit_path_community", community.value);
  }

  function setMypage(value) {
    mypage.value = value;
    localStorage.setItem("_ggogit_path_mypage", mypage.value);
  }

  return {
    currentSpot,
    home,
    search,
    community,
    mypage,
    initStore,
    loadFromStorage,
    setCurrentSpot,
    setHome,
    setSearch,
    setCommunity,
    setMypage,
  };
});
