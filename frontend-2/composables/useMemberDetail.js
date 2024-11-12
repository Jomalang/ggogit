import { defineStore } from "pinia";
import {jwtDecode} from "jwt-decode";

export const useMemberDetail = defineStore("ggogitMember", () => {
  const _id = ref("");
  const _username = ref("");
  const _email = ref("");
  const _roles = ref([]);
  const _token = ref("");
  const _accessToken = ref("");
  const _refreshToken = ref("");

  function isAnonymous() {
    return _email.value === "";
  }

  function setAuthWithToken(accessToken, refreshToken) {
    _accessToken.value = accessToken;
    _refreshToken.value = refreshToken;
    console.log('accessToken', _accessToken.value);
    const memberInfo = jwtDecode(_accessToken.value);
    console.log('memberInfo', memberInfo);
    setAuth(memberInfo);
  }

  function setAuth(loginInfo) {
    _id.value = loginInfo.id;
    _username.value = loginInfo.username;
    _email.value = loginInfo.email;
    _roles.value = loginInfo.roles;
    _token.value = loginInfo.token;

    if (!import.meta.env.SSR) {
      localStorage.setItem("_ggogit_id", _id.value);
      localStorage.setItem("_ggogit_username", _username.value);
      localStorage.setItem("_ggogit_email", _email.value);
      localStorage.setItem("_ggogit_roles", JSON.stringify(_roles.value));
      localStorage.setItem("_ggogit_token", _token.value);
    }
  }

  const loadUserFromStorage = () => {
    try {
      if (!import.meta.env.SSR) {
        _id.value = localStorage.getItem("_ggogit_id");
        _username.value = localStorage.getItem("_ggogit_username");
        _email.value = localStorage.getItem("_ggogit_email");
        _roles.value = JSON.parse(localStorage.getItem("_ggogit_roles"));
        _token.value = localStorage.getItem("_ggogit_token");
      }
    } catch (e) {
      initAuth();
    }
  };

  function initAuth() {
    _id.value = 0;
    _username.value = "";
    _email.value = "";
    _roles.value = [];
    _token.value = "";
    _accessToken.value = "";
    _refreshToken.value = "";
  }

  function hasRole(role) {
    return _roles.value.includes(role);
  }

  return {
    setAuthWithToken,
    isAnonymous,
    hasRole,
    setAuth,
    initAuth,
    loadUserFromStorage
  };
});
