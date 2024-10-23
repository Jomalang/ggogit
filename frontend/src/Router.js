import TextInfo from "./components/text/TextInfo.vue";

export default [
  {
    path: "/tree",
    children: [
      { path: "search", component: TreeSearch },
      { path: "search/result:treeSearchText", component: TreeSearchResult },
      { path: "etc/reg", component: TreeEtcRegister },
      { path: "list", component: TreeList },
      {
        path: "book",
        children: [
          { path: "search", component: TreeBookSearch },
          { path: "reg", component: TreeBookRegister },
        ],
        component: "",
      },
      { path: "detail:treeId", component: TreeDetail },
      { path: "memoir/register/index", component: TreeMemoirRegisterIndex },
    ],
    component: TreeIndex,
  },
];
