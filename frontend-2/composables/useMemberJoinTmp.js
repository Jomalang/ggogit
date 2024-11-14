import { defineStore } from "pinia";

export const useMemberJoinTmp = defineStore("ggogitMemberJoinTmp", () => {
    const _username = ref("");
    const _email = ref("");
    const _profile = ref("");

    function setJoinInfo(joinInfo) {

        _username.value = joinInfo.name;
        _email.value = joinInfo.email;
        _profile.value = joinInfo.picture;

        localStorage.setItem("_username",joinInfo.name);
        localStorage.setItem("_email",joinInfo.email);
        localStorage.setItem("_profile",joinInfo.picture);
    }

    function getJoinInfo() {

        if (_username.value === "" || _email.value === "" || _profile.value === "") {
            if (import.meta.env.SSR) { return; }
            try {
                _username.value = localStorage.getItem("_username");
                _email.value = localStorage.getItem("_email");
                _profile.value = localStorage.getItem("_profile");
            } catch (e) {
                console.log(e);
                return;
            }
        }

        return {
            name: _username.value,
            email: _email.value,
            picture: _profile.value
        };
    }

    return {
        setJoinInfo,
        getJoinInfo
    };
});