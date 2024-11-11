export default function useGoBack() {
  return () => {
    window.history.back();
  };
}
