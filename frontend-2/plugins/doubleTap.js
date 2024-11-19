export default defineNuxtPlugin((nuxtApp) => {
  nuxtApp.vueApp.directive("double-action", {
    //el은 디렉티브가 연결된 DOM객체, binding은 디렉티브에 전달된 값을 래핑한 객체이다.
    mounted(el, binding) {
      let lastTouchTime = 0;

      // 더블클릭 이벤트 (데스크톱)
      const handleDoubleClick = (event) => {
        if (typeof binding.value === "function") {
          binding.value(event, "dblclick");
        }
      };

      // 더블터치 이벤트 (모바일)
      const handleTouchEnd = (event) => {
        const currentTime = new Date().getTime();
        const timeDifference = currentTime - lastTouchTime;

        if (timeDifference < 300 && timeDifference > 0) {
          if (typeof binding.value === "function") {
            binding.value(event, "doubletap");
          }
        }

        lastTouchTime = currentTime;
      };

      // 이벤트 리스너 추가
      el.addEventListener("dblclick", handleDoubleClick);
      el.addEventListener("touchend", handleTouchEnd);

      // 클린업 - 컴포넌트가 제거되면 이벤트 리스너도 DOM에서 제거해줘야 한다.
      // 그렇지 않으면 메모리 누수, 컴포넌트 재 등록시마다 이벤트 리스너 반복 등록등이 일어난다.
      el._doubleActionCleanup = () => {
        el.removeEventListener("dblclick", handleDoubleClick);
        el.removeEventListener("touchend", handleTouchEnd);
      };
    },
    unmounted(el) {
      // 등록된 이벤트 리스너 제거
      el._doubleActionCleanup && el._doubleActionCleanup();
    },
  });
});
