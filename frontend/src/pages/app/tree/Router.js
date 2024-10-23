import TreeIndex from "./TreeIndex.vue";
import TreeSearch from "./TreeSearch.vue";
import TreeSearchResult from "./TreeSearchResult.vue";
import TreeEtcRegister from "./TreeEtcRegister.vue";
import TreeList from "./TreeList.vue";
import TreeBookSearch from "./TreeBookSearch.vue";
import TreeBookRegister from "./TreeBookRegister.vue";
import TreeDetail from "./TreeDetail.vue";
import TreeMemoirRegisterIndex from "./TreeMemoirRegisterIndex.vue";

export default {
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
    },
    { path: "detail:treeId", component: TreeDetail },
    { path: "memoir/register/index", component: TreeMemoirRegisterIndex },
  ],
  component: TreeIndex,
};
