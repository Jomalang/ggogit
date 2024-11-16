import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";

export const useMemberStore = defineStore("memberStore", () => {
  const _id = ref("");
  const _username = ref("");
  const _nickname = ref("");
  const _email = ref("");
  const _roles = ref([]);
  const _accessToken = ref("");

  function isAnonymous() {
    return _email.value === "";
  }

  function setAuthWithToken(accessToken) {
    _accessToken.value = accessToken;
    const memberInfo = jwtDecode(_accessToken.value);
    setAuth(memberInfo);
  }

  function getAuthToken() {
    return _accessToken.value;
  }

  function setAuth(loginInfo) {
    _id.value = loginInfo.id;
    _username.value = loginInfo.username;
    _nickname.value = loginInfo.nickname;
    _email.value = loginInfo.email;
    _roles.value = loginInfo.roles;

    if (!import.meta.env.SSR) {
      localStorage.setItem("_ggogit_id", _id.value);
      localStorage.setItem("_ggogit_username", _username.value);
      localStorage.setItem("_ggogit_nickname", _nickname.value);
      localStorage.setItem("_ggogit_email", _email.value);
      localStorage.setItem("_ggogit_roles", JSON.stringify(_roles.value));
      localStorage.setItem("_ggogit_accessToken", _accessToken.value);
      // CSR일때 _accessToken을 쿠키에 저장
      document.cookie = `_ggogit_accessToken=${_accessToken.value}; path=/;`;
    }
    // SSR일때 _accessToken을 쿠키에 저장
    if (import.meta.env.SSR) {
      const event = useRequestEvent();
      if (event) {
        event.node.res.setHeader(
          "Set-Cookie",
          `_ggogit_accessToken=${_accessToken.value}; Path=/; HttpOnly; Secure; SameSite=Strict`
        );
      }
    }
  }
  function setLocal(id, name, email, picture, role, accessToken) {
    _id.value = id;
    _username.value = name;
    _nickname.value = name;
    _email.value = email;
    _roles.value = role;
    _accessToken.value = accessToken;
    if (!import.meta.env.SSR) {
      localStorage.setItem("_ggogit_id", _id.value);
      localStorage.setItem("_ggogit_username", _username.value);
      localStorage.setItem("_ggogit_nickname", _nickname.value);
      localStorage.setItem("_ggogit_email", _email.value);
      localStorage.setItem("_ggogit_roles", JSON.stringify(_roles.value));
      localStorage.setItem("_ggogit_accessToken", _accessToken.value);
    }
  }

  function loadUserFromStorage() {
    try {
      if (!import.meta.env.SSR) {
        if (localStorage.getItem("_ggogit_accessToken") === null) return;

        _id.value = localStorage.getItem("_ggogit_id");
        _username.value = localStorage.getItem("_ggogit_username");
        _nickname.value = localStorage.getItem("_ggogit_nickname");
        _email.value = localStorage.getItem("_ggogit_email");
        _roles.value = JSON.parse(localStorage.getItem("_ggogit_roles"));
        _accessToken.value = localStorage.getItem("_ggogit_accessToken");
      }
    } catch (e) {
      // console.log("loadUserFromStorage error = ", e);
      useNavStore().initStore();
    }
  }

  function initAuth() {
    _id.value = 0;
    _username.value = "";
    _nickname.value = "";
    _email.value = "";
    _roles.value = [];
    _accessToken.value = "";
    if (!import.meta.env.SSR) {
      localStorage.setItem("_ggogit_id", _id.value);
      localStorage.setItem("_ggogit_username", _username.value);
      localStorage.setItem("_ggogit_nickname", _nickname.value);
      localStorage.setItem("_ggogit_email", _email.value);
      localStorage.setItem("_ggogit_roles", JSON.stringify(_roles.value));
      localStorage.setItem("_ggogit_accessToken", _accessToken.value);
      localStorage.setItem("_email", "");
      localStorage.setItem("_profile", "");
      localStorage.setItem("_username", "");
      // CSR일때 쿠키 초기화
      document.cookie = `_ggogit_accessToken=${""}; path=/;`;
      // localStroage의 nav경로 초기화
      useNavStore().initStore();
    }
    // SSR일때 쿠키 초기화
    if (import.meta.env.SSR) {
      const event = useRequestEvent();
      if (event) {
        event.node.res.setHeader(
          "Set-Cookie",
          `_ggogit_accessToken=${""}; Path=/; HttpOnly; Secure; SameSite=Strict`
        );
      }
    }
  }

  function hasRole(role) {
    return _roles.value.includes(role);
  }
  function setEmail(email) {
    _email.value = email;
  }

  return {
    _id,
    _username,
    _nickname,
    _email,
    _roles,
    _accessToken,
    setAuthWithToken,
    getAuthToken,
    isAnonymous,
    hasRole,
    setAuth,
    loadUserFromStorage,
    initAuth,
    setLocal,
    setEmail,
  };
});
