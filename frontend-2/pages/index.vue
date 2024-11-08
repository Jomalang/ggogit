<script setup lang="ts">

onMounted(() => {

  // 화면이 900px 이하인 경우 스크롤 이벤트 적용 박스에 mobile-phone-side
  if (window.innerWidth <= 900) {
    window.addEventListener('scroll', () => {
      const sideBar = document.querySelectorAll('.mobile-phone-side');
    });
  }

});

</script>

<template>

  <header>
    <div class="nav-box">
      <NuxtLink to="/" class="logo-title">GGogit</NuxtLink>
    </div>
  </header>

  <main>
    <section class="section-1">
      <div class="main-title">
        <p>기록의 즐거움</p>
        <p>꼬깃과 함께</p>
        <p>메모의 여정을 떠나세요</p>
        <NuxtLink to="/home">
          <div class="start-btn">GGogit 시작하기</div>
        </NuxtLink>
      </div>
      <img src="/svg/home-down-arrow.svg">
    </section>

    <section class="section-2">
      <div class="sub-title">
        <p>기록, 이제는 시간 순서대로</p>
        <p>정리된 브랜치로</p>
        <p>체계적으로 관리하세요.</p>
      </div>
    </section>

    <section class="section-3">
      <div class="center-box">
        <div class="middle-title">
          <p>일상 속에서 떠오르는</p>
          <p>생각과 영감을 나만의 지식</p>
          <p>트리로 만들어 보세요!</p>
        </div>

        <img class="img-1 phone" src="/png/1.png" alt="1">
        <img class="img-2 phone" src="/png/2.png" alt="2">
        <img class="img-3 phone" src="/png/3.png" alt="3">

        <div class="small-title">
          <p>떠오른 생각과 영감을 트리 형태로</p>
          <p>확장할 수 있는 특별한 경험을 제공합니다.</p>
          <p>시간이 흐를수록 풍서해지는 지식의 숲을 만들어보세요!</p>
        </div>
      </div>
    </section>

    <section class="section-4">
      <div class="center-box">
        <div class="middle-title">
          <p>원하는 도서를 선택하고</p>
          <p>그 안에 숨겨진 지식을</p>
          <p>트리로 표현해 보세요!</p>
        </div>

        <img class="img-3 phone" src="/png/4.png" alt="3">
        <img class="img-4 phone" src="/png/5.png" alt="4">

        <div class="small-title">
          <p>떠오른 생각과 영감을 트리 형태로</p>
          <p>확장할 수 있는 특별한 경험을 제공합니다.</p>
          <p>시간이 흐를수록 풍서해지는 지식의 숲을 만들어보세요!</p>
        </div>
      </div>
    </section>

    <section class="section-3 mobile-second-section-3">
      <div class="center-box">
        <div class="middle-title">
          <p>일상 속에서 떠오르는</p>
          <p>생각과 영감을 나만의 지식</p>
          <p>트리로 만들어 보세요!</p>
        </div>

        <img class="img-1 phone" src="/png/6.png" alt="1">
        <img class="img-2 phone" src="/png/7_1.png" alt="7">

        <div class="small-title-2">
          <p>떠오른 생각과 영감을 트리 형태로</p>
          <p>확장할 수 있는 특별한 경험을 제공합니다.</p>
          <p>시간이 흐를수록 풍서해지는</p>
          <p>지식의 숲을 만들어보세요!</p>
        </div>
      </div>
    </section>

    <section class="section-4">
      <div class="center-box">
        <div class="middle-title">
          <p>도서를 완독하여</p>
          <p>자신만의 회고록을</p>
          <p>작성해보세요!</p>
        </div>

        <img class="img-3 phone" src="/png/8.png" alt="8">
        <img class="img-4 phone" src="/png/9.png" alt="9">

        <div class="small-title">
          <p>떠오른 생각과 영감을 트리 형태로</p>
          <p>확장할 수 있는 특별한 경험을 제공합니다.</p>
          <p>시간이 흐를수록 풍서해지는 지식의 숲을 만들어보세요!</p>
        </div>
      </div>
    </section>


  </main>

</template>

<style src="./index.css"></style>