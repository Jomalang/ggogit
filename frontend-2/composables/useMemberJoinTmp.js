import { defineStore } from "pinia";

export const useMemberJoinTmp = defineStore("ggogitMemberJoinTmp", () => {
    const _username = ref("");
    const _email = ref("");
    const _profile = ref("");

    function setJoinInfo(joinInfo) {
        console.log('joinInfo', joinInfo.email, joinInfo.name, joinInfo.picture);
        localStorage.setItem("_username",joinInfo.name);
        localStorage.setItem("_email",joinInfo.email);
        localStorage.setItem("_profile",joinInfo.picture);
    }
    return {
        setJoinInfo
    };
});