import { defineStore } from "pinia";

export const useMemberDetail = defineStore("ggogitMember", () => {
  const id = ref("");
  const username = ref("");
  const email = ref("");
  const roles = ref([]);
  const token = ref("");

  function isAnonymous() {
    return email.value === "";
  }

  function setAuth(loginInfo) {
    id.value = loginInfo.id;
    username.value = loginInfo.username;
    email.value = loginInfo.email;
    roles.value = loginInfo.roles;
    token.value = loginInfo.token;

    if (!import.meta.env.SSR) {
      localStorage.setItem("_ggogit_id", id.value);
      localStorage.setItem("_ggogit_username", username.value);
      localStorage.setItem("_ggogit_email", email.value);
      localStorage.setItem("_ggogit_roles", JSON.stringify(roles.value));
      localStorage.setItem("_ggogit_token", token.value);
    }
  }

  const loadUserFromStorage = () => {
    if (!import.meta.env.SSR) {
      if (!localStorage.getItem("_ggogit_id")) return;

      id.value = localStorage.getItem("_ggogit_id");
      username.value = localStorage.getItem("_ggogit_username");
      email.value = localStorage.getItem("_ggogit_email");
      roles.value = JSON.parse(localStorage.getItem("_ggogit_roles"));
      token.value = localStorage.getItem("_ggogit_token");
    }
  };

  function initAuth() {
    id.value = 0;
    username.value = "";
    email.value = "";
    roles.value = [];
    token.value = "";
  }

  function hasRole(role) {
    return roles.value.includes(role);
  }

  return {
    id,
    username,
    email,
    roles,
    token,
    isAnonymous,
    hasRole,
    setAuth,
    initAuth,
    loadUserFromStorage,
  };
});
