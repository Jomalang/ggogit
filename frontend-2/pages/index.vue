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

<style scoped>
@media (min-width: 901px) {
  .phone {
    width: 600px;
  }

  main {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  header {
    display: flex;
    justify-content: center;
    background-color: var(--main2, #e5eddb);
    height: 60px;
    width: 100%;

    .nav-box {
      width: 1140px;
      display: flex;
      align-items: center;
      .logo-title {
        margin: 20px;
        font-size: 20px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
      }
    }
  }

  .section-1 {
    background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('/webp/bg.webp');
    background-size: cover;
    background-position: center;
    width: 100vw;
    height: calc(100vh - 60px);
    position: relative;

    .start-btn {
      text-align: center;
      padding: 20px;
      margin-top: 40px;
      border-radius: 40px;
      width: 370px;
      height: 20px;
      background-color: var(--main2, #e5eddb);
      font-weight: bold;
    }

    .main-title {
      position: absolute;
      top: 20%;
      width: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      p {
        margin: 20px;
        font-size: 66px;
        font-weight: bold;
        color: var(--white, #fff);
      }
    }

    img {
      position: absolute;
      bottom: 20px;
      left: 50%;
      transform: translateX(-50%);
      animation: moveUpDown 2s infinite alternate;
    }
  }

  @keyframes moveUpDown {
    0% {
      bottom: 20px;
    }
    100% {
      bottom: 40px;
    }
  }

  .section-2 {
    background-color: var(--main2, #e5eddb);
    width: 100vw;
    height: 1000px;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;

    .sub-title {
      position: absolute;
      top: 260px;
      width: 100%;
      color: var(--main1, #3e3e3e);
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      p {
        margin: 20px;
        font-size: 55px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
      }
    }
  }

  .section-3 {
    width: 100vw;
    height: 2000px;
    display: flex;
    justify-content: center;
    align-items: center;

    .center-box {
      position: relative;
      width: 1140px;
      height: 100%;

      .middle-title {
        position: absolute;
        left: 70px;
        top: 310px;
        font-size: 50px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }

      .img-1 {
        position: absolute;
        top: 300px;
        left: 65%;
        transform: translateX(-50%);
      }

      .img-2 {
        position: absolute;
        top: 600px;
        left: 25%;
        transform: translateX(-50%);
      }

      .img-3 {
        position: absolute;
        top: 1100px;
        left: 60%;
        transform: translateX(-50%);
      }

      .small-title {
        position: absolute;
        bottom: 400px;
        left: 80px;
        font-size: 23px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }
    }
  }

  .section-4 {
    width: 100vw;
    height: 1300px;
    background-color: var(--main2, #e5eddb);
    display: flex;
    justify-content: center;
    align-items: center;

    .center-box {
      position: relative;
      width: 1140px;
      height: 100%;

      .middle-title {
        text-align: right;
        position: absolute;
        top: 200px;
        right: 40px;
        width: 100%;
        font-size: 50px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }

      .img-3 {
        position: absolute;
        top: 200px;
        left: 25%;
        transform: translateX(-50%);
      }

      .img-4 {
        position: absolute;
        top: 400px;
        left: 60%;
        transform: translateX(-50%);
      }

      .small-title {
        position: absolute;
        bottom: 150px;
        left: 80px;
        font-size: 23px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }
    }
  }

  .small-title-2 {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    bottom: 400px;
    text-align: center;
    font-size: 23px;
    font-weight: bold;
    color: var(--main1, #3e3e3e);
    p {
      margin: 20px;
    }
  }
}

/* 스크린 900 미만 */
@media (max-width: 900px) {

  .phone {
    width: 400px;
  }

  header {
    display: flex;
    justify-content: center;
    background-color: var(--main2, #e5eddb);
    height: 60px;
    width: 100%;

    .nav-box {
      width: 1140px;
      display: flex;
      align-items: center;
      .logo-title {
        margin: 20px;
        font-size: 20px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
      }
    }
  }

  .section-1 {
    background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('/webp/bg.webp');
    background-size: cover;
    background-position: center;
    width: 100vw;
    height: calc(100vh - 60px);
    position: relative;

    .start-btn {
      text-align: center;
      padding: 20px;
      margin-top: 40px;
      border-radius: 40px;
      width: 200px;
      height: 20px;
      background-color: var(--main2, #e5eddb);
      font-weight: bold;
    }

    .main-title {
      position: absolute;
      top: 20%;
      width: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      p {
        margin: 10px;
        font-size: 33px;
        font-weight: bold;
        color: var(--white, #fff);
      }
    }

    img {
      width: 30px;
      position: absolute;
      bottom: 20px;
      left: 50%;
      transform: translateX(-50%);
      animation: moveUpDown 2s infinite alternate;
    }
  }

  @keyframes moveUpDown {
    0% {
      bottom: 20px;
    }
    100% {
      bottom: 40px;
    }
  }

  .section-2 {
    background-color: var(--main2, #e5eddb);
    width: 100vw;
    height: 700px;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;

    .sub-title {
      position: absolute;
      top: 260px;
      width: 100%;
      color: var(--main1, #3e3e3e);
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      p {
        margin: 10px;
        font-size: 28px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
      }
    }
  }

  .section-3 {
    width: 100vw;
    height: 1300px;
    display: flex;
    justify-content: center;
    align-items: center;

    .center-box {
      position: relative;
      width: 1140px;
      height: 100%;

      .middle-title {
        margin-top: 100px;
        text-align: center;
        font-size: 28px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }

      .img-1 {
        position: absolute;
        top: 200px;
        left: 50%;
        transform: translateX(-50%);
      }

      .img-2 {
        position: absolute;
        top: 300px;
        left: 25%;
        transform: translateX(-50%);
      }

      .img-3 {
        position: absolute;
        top: 500px;
        left: 40%;
        transform: translateX(-50%);
      }

      .small-title {
        margin-top: 850px;
        text-align: center;
        font-size: 18px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }
    }
  }

  .mobile-second-section-3 {
    height: 1100px;
  }

  .section-4 {
    width: 100vw;
    height: 1100px;
    background-color: var(--main2, #e5eddb);
    display: flex;
    justify-content: center;
    align-items: center;

    .center-box {
      position: relative;
      width: 1140px;
      height: 100%;

      .middle-title {
        text-align: center;
        width: 100%;
        margin-top: 100px;
        font-size: 28px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }

      .img-3 {
        position: absolute;
        top: 200px;
        left: 50%;
        transform: translateX(-50%);
      }

      .img-4 {
        position: absolute;
        top: 300px;
        left: 25%;
        transform: translateX(-50%);
      }

      .small-title {
        margin-top: 650px;
        text-align: center;
        font-size: 18px;
        font-weight: bold;
        color: var(--main1, #3e3e3e);
        p {
          margin: 20px;
        }
      }
    }
  }

  .small-title-2 {
    margin-top: 650px;
    text-align: center;
    font-size: 18px;
    font-weight: bold;
    color: var(--main1, #3e3e3e);
    p {
      margin: 20px;
    }
  }
}
</style>