import MemoirIndex from "./MemoirIndex.vue";
import MemoirRegister from "./MemoirRegister.vue";
import MemoirEdit from "./MemoirEdit.vue";

export default {
  path: "/memoir",
  children: [
    { path: ":id", component: MemoirIndex },
    { path: "reg", component: MemoirRegister },
    { path: "edit", component: MemoirEdit },
  ],
};
