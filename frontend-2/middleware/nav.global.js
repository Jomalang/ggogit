export default defineNuxtRouteMiddleware((to, from) => {
  if (!import.meta.env.SSR) {
    const navStore = useNavStore();

    const { currentSpot } = storeToRefs(navStore);

    if (currentSpot.value === "_home") {
      navStore.setHome(to.fullPath);
    } else if (currentSpot.value === "_search") {
      navStore.setSearch(to.fullPath);
    } else if (currentSpot.value === "_community") {
      navStore.setCommunity(to.fullPath);
    } else if (currentSpot.value === "_mypage") {
      navStore.setMypage(to.fullPath);
    }
  }
});
