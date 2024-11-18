export default defineNuxtPlugin((Nuxtapp) => {
  const navStore = useNavStore();
  const backStore = useBackStore();
  try {
    navStore.loadFromStorage();
    backStore.getStackFromCookie();
  } catch (e) {
    console.log("loadFromStroage error = ", e);
  }
});
