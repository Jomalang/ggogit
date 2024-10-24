//------------------style------------------
import "@/assets/css/common/variables.css";
import "@/assets/css/layout.css";

//------------------script------------------
import { createApp } from "vue";
import { createRouter, createWebHistory } from "vue-router";

import App from "./App.vue";
import BookDetail from "@/pages/app/book/BookDetail.vue";
import BookIndex from "@/pages/app/book/BookIndex.vue";
import Index from "@/pages/Index.vue";
import HomeIndex from "@/pages/app/home/HomeIndex.vue";
import NoTree from "@/pages/app/home/NoTree.vue";
import MemberLogin from "@/pages/app/member/MemberLogin.vue";
import MemberJoinInput from "@/pages/app/member/MemberJoinInput.vue";
import MemberJoin from "@/pages/app/member/MemberJoin.vue";
import MemberPasswordReset from "@/pages/app/member/MemberPasswordReset.vue";
import MemoirIndex from "@/pages/app/memoir/MemoirIndex.vue";
import MemoirRegister from "@/pages/app/memoir/MemoirRegister.vue";
import MemoirEdit from "@/pages/app/memoir/MemoirEdit.vue";
import LeafEdit from "@/pages/app/leaf/LeafBookEdit.vue";
import LeafList from "@/pages/app/leaf/LeafList.vue";
import LeafDetail from "@/pages/app/leaf/LeafDetail.vue";
import TagList from "@/pages/app/leaf/TagList.vue";
import TagEdit from "@/pages/app/leaf/TagEdit.vue";
import SeedIndex from "@/pages/app/tree/SeedIndex.vue";
import MemberIndex from "@/pages/app/member/MemberIndex.vue";
import LeafIndex from "@/pages/app/leaf/LeafIndex.vue";
import TagIndex from "@/pages/app/leaf/TagIndex.vue";
import Test from "@/pages/Test.vue";
import LeafBookFirstRegister from "@/pages/app/leaf/LeafBookFirstRegister.vue";
import LeafEtcFirstRegister from "@/pages/app/leaf/LeafEtcFirstRegister.vue";
import LeafBookRegister from "@/pages/app/leaf/LeafBookRegister.vue";
import LeafEtcRegister from "@/pages/app/leaf/LeafEtcRegister.vue";
import BookCategoryList from "@/pages/app/book/BookCategoryList.vue";
import TreeBookAutoRegister from "@/pages/app/tree/TreeBookAutoRegister.vue";

//----------------------Router----------------------
import treeRouter from "@/pages/app/tree/Router.js";
import memoirRouter from "@/pages/app/memoir/Router.js";

let router;

router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/test",
      component: Test,
    },
    {
      path: "/",
      component: Index,
    },
    {
      path: "/home",
      children: [
        { path: "index", component: HomeIndex },
        { path: "no-tree", component: NoTree },
      ],
    },
    {
      path: "/member",
      children: [
        { path: "login", component: MemberLogin },
        { path: "join-input", component: MemberJoinInput },
        { path: "join", component: MemberJoin },
        { path: "password-reset", component: MemberPasswordReset },
      ],
      component: MemberIndex,
    },
    {
      path: "/book",
      children: [{ path: "detail", component: BookDetail }],
      component: BookIndex,
    },
    memoirRouter,
    {
      path: "/leaf",
      children: [
        { path: "book/first/reg", component: LeafBookFirstRegister },
        { path: "etc/first/reg", component: LeafEtcFirstRegister },
        { path: "book/reg", component: LeafBookRegister },
        { path: "etc/reg", component: LeafEtcRegister },
        { path: "book/edit", component: LeafEdit },
        { path: "book/edit", component: LeafEdit },
        { path: "list", component: LeafList },
        { path: "detail", component: LeafDetail },
      ],
      component: LeafIndex,
    },
    {
      path: "/tag",
      children: [
        { path: "list", component: TagList },
        { path: "edit", component: TagEdit },
      ],
      component: TagIndex,
    },
    {
      path: "/seed",
      component: SeedIndex,
    },
    treeRouter,
  ],
});

createApp(App).use(router).mount("#app");
