export const useBackStore = defineStore("backStore", () => {
  //nav스토어 객체는 현재 사용자가 위치한 영역을 나타내는데 사용됩니다.
  const navStore = useNavStore();

  //뒤로가기를 눌렀는지 확인합니다.
  const isBack = ref(false);

  //각 영역에 대한 스택을 생성합니다.
  const backStackForHome = ref([]);
  const backStackForSearch = ref([]);
  const backStackForCommunity = ref([]);
  const backStackForMyPage = ref([]);

  //쿠키 객체
  const backCookieHome = useCookie("_ggogit_backStack_home", {
    path: "/",
    sameSite: "strict",
    httpOnly: true,
  });
  const backCookieSearch = useCookie("_ggogit_backStack_search", {
    path: "/",
    sameSite: "strict",
    httpOnly: true,
  });
  const backCookieCommunity = useCookie("_ggogit_backStack_community", {
    path: "/",
    sameSite: "strict",
    httpOnly: true,
  });
  const backCookieMyPage = useCookie("_ggogit_backStack_mypage", {
    paht: "/",
    sameSite: "strict",
    httpOnly: true,
  });

  //각 영역을 조건문으로 다룰 반응형 객체입니다.
  const backNavigationStack = ref(backStackForHome.value);

  //뒤로 가기 버튼을 눌렀는지 판단합니다.(미들웨어)
  function checkIsBack() {
    return isBack.value;
  }

  function IsBackToTrue() {
    isBack.value = true;
  }

  function IsBackToFalse() {
    isBack.value = false;
  }

  //현재 영역을 판단하고 사용할 스택을 설정합니다. (미들웨어)
  function changeStack() {
    console.log(navStore.currentSpot);
    if (navStore.currentSpot === "_home") {
      backNavigationStack.value = backStackForHome.value;
    } else if (navStore.currentSpot === "_search") {
      backNavigationStack.value = backStackForSearch.value;
    } else if (navStore.currentSpot === "_community") {
      backNavigationStack.value = backStackForCommunity.value;
    } else if (navStore.currentSpot === "_mypage") {
      backNavigationStack.value = backStackForMyPage.value;
    }
  }
  //미들웨어에서 사용
  function addPageToStack(page) {
    if (backNavigationStack.value.length >= 20) {
      backNavigationStack.value.shift(page);
    }
    backNavigationStack.value.push(page);
  }

  //각 컴포넌트에서 사용
  function popPageFromStack() {
    if (backNavigationStack.value.length >= 1) {
      const returnPage = backNavigationStack.value.pop();
      return returnPage;
    } else {
      return useRoute().fullPath;
    }
  }

  function getLastPage() {
    return backNavigationStack.value.length > 1
      ? backNavigationStack[backNavigationStack.value.length - 2]
      : "/home";
  }

  //쿠키와 백스택을 초기화합니다.
  //로그인 시
  function initStackAndCookie() {
    backStackForHome.value.splice(0, backStackForHome.length);
    backStackForSearch.value.splice(0, backStackForSearch.length);
    backStackForCommunity.value.splice(0, backStackForCommunity.length);
    backStackForMyPage.value.splice(0, backStackForMyPage.length);
    backCookieHome.value = "";
    backCookieSearch.value = "";
    backCookieCommunity.value = "";
    backCookieMyPage.value = "";
  }

  //각 영역의 백스택을 쿠키에 문자열로 저장합니다.
  //미들웨어
  function setStackToCookie() {
    backCookieHome.value = backStackForHome.value.join(",");
    backCookieSearch.value = backStackForSearch.value.join(",");
    backCookieCommunity.value = backStackForCommunity.value.join(",");
    backCookieMyPage.value = backStackForMyPage.value.join(",");
  }

  //쿠키에서 백스택을 불러옵니다.
  //플러그인
  function getStackFromCookie() {
    backStackForHome.value = backCookieHome.value.split(",");
    backStackForSearch.value = backCookieSearch.value.split(",");
    backStackForCommunity.value = backCookieCommunity.value.split(",");
    backStackForMyPage.value = backCookieMyPage.value.split(",");
  }

  return {
    isBack,
    backStackForHome,
    backStackForSearch,
    backStackForCommunity,
    backStackForMyPage,
    backNavigationStack,
    addPageToStack,
    IsBackToFalse,
    IsBackToTrue,
    popPageFromStack,
    getLastPage,
    setStackToCookie,
    getStackFromCookie,
    initStackAndCookie,
    changeStack,
    checkIsBack,
  };
});
