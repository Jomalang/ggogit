
import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';

import App from './App.vue';
import BookDetail from "@/pages/app/book/BookDetail.vue";
import BookIndex from "@/pages/app/book/BookIndex.vue";
import Index from "@/pages/Index.vue";
import HomeIndex from "@/pages/app/home/HomeIndex.vue";
import MemberLogin from "@/pages/app/member/MemberLogin.vue";
import MemberJoinInput from "@/pages/app/member/MemberJoinInput.vue";
import MemberJoin from "@/pages/app/member/MemberJoin.vue";
import MemberPasswordReset from "@/pages/app/member/MemberPasswordReset.vue";
import MemoirIndex from "@/pages/app/memoir/MemoirIndex.vue";
import MemoirRegister from "@/pages/app/memoir/MemoirRegister.vue";
import MemoirEdit from "@/pages/app/memoir/MemoirEdit.vue";
import LeafFirstRegister from "@/pages/app/leaf/LeafFirstRegister.vue";
import LeafRegister from "@/pages/app/leaf/LeafRegister.vue";
import LeafEdit from "@/pages/app/leaf/LeafEdit.vue";
import LeafList from "@/pages/app/leaf/LeafList.vue";
import LeafDetail from "@/pages/app/leaf/LeafDetail.vue";
import TagList from "@/pages/app/leaf/TagList.vue";
import TagEdit from "@/pages/app/leaf/TagEdit.vue";
import SeedIndex from "@/pages/app/tree/SeedIndex.vue";
import TreeSearch from "@/pages/app/tree/TreeSearch.vue";
import TreeSearchResult from "@/pages/app/tree/TreeSearchResult.vue";
import TreeBookRegister from "@/pages/app/tree/TreeBookRegister.vue";
import TreeEtcRegister from "@/pages/app/tree/TreeEtcRegister.vue";
import TreeList from "@/pages/app/tree/TreeList.vue";
import TreeBookSearch from "@/pages/app/tree/TreeBookSearch.vue";
import TreeDetail from "@/pages/app/tree/TreeDetail.vue";
import TreeMemoirRegisterIndex from "@/pages/app/tree/TreeMemoirRegisterIndex.vue";
import MemberIndex from "@/pages/app/member/MemberIndex.vue";
import LeafIndex from "@/pages/app/leaf/LeafIndex.vue";
import TagIndex from "@/pages/app/leaf/TagIndex.vue";
import TreeIndex from "@/pages/app/tree/TreeIndex.vue";
import Test from "@/pages/Test.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/test',
            component: Test
        },
        {
            path: '/', component: Index
        },
        {
            path: '/home', component: HomeIndex
        },
        {
            path: '/member', children: [
                { path: 'login', component: MemberLogin },
                { path: 'join-input', component: MemberJoinInput },
                { path: 'join', component: MemberJoin },
                { path: 'password-reset', component: MemberPasswordReset },
            ],
            component: MemberIndex
        },
        {
            path: '/book', children: [
                { path: 'detail', component: BookDetail }
            ],
            component: BookIndex
        },
        {
            path: '/memoir', children: [
                { path: 'reg', component: MemoirRegister },
                { path: 'edit', component: MemoirEdit },
            ],
            component: MemoirIndex
        },
        {
            path: '/leaf', children: [
                { path: 'first/reg', component: LeafFirstRegister },
                { path: 'reg', component: LeafRegister },
                { path: 'edit', component: LeafEdit },
                { path: 'list', component: LeafList },
                { path: 'detail', component: LeafDetail }
            ],
            component: LeafIndex
        },
        {
            path: '/tag', children: [
                { path: 'list', component: TagList },
                { path: 'edit', component: TagEdit }
            ],
            component: TagIndex
        },
        {
            path: '/seed', component: SeedIndex
        },
        {
            path: '/tree', children: [
                { path: 'search', component: TreeSearch },
                { path: 'search/result:treeSearchText', component: TreeSearchResult },
                { path: 'book/reg', component: TreeBookRegister },
                { path: 'etc/reg', component: TreeEtcRegister },
                { path: 'list', component: TreeList },
                { path: 'book/search', component: TreeBookSearch },
                { path: 'detail:treeId', component: TreeDetail },
                { path: 'memoir/register/index', component: TreeMemoirRegisterIndex }
            ],
            component: TreeIndex
        },
    ]
});

createApp(App)
    .use(router)
    .mount('#app');
