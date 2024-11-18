export default defineNuxtPlugin((Nuxtapp) => {
  const navStore = useNavStore();
  try {
    navStore.loadFromStorage();
  } catch (e) {
    console.log("loadFromStroage error = ", e);
  }
});
