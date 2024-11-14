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
      console.log("loadUserFromStorage error = ", e);
      initAuth();
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
    setEmail,
  };
});
