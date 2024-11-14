import { useMemberDetail } from "#imports";
import useBackNavigation from "~/composables/useBackNavigation.js";

export default defineNuxtRouteMiddleware((to) => {
    const { addPageToStack } = useBackNavigation();
    addPageToStack(to.fullPath);
});
