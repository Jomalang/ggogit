<script setup>

import { defineProps, defineEmits, onMounted, onUnmounted } from 'vue';

const props = defineProps({
  items: {
    type: Array,
    default: () => []
  }
})
const formatDate = (date) => {
  const options = {
    year: '2-digit', // '24' 형식으로 출력
    month: '2-digit', // '10' 형식으로 출력
    day: '2-digit', // '01' 형식으로 출력
    hour: 'numeric', // 시간 출력 (24시간제)
    minute: 'numeric', // 분 출력
  };
  return new Date(date).toLocaleString('ko-KR', options);
};

// 무한 스크롤

const emit = defineEmits(['loadMore']);
const scrollContainer = ref(null);
const itemRefs = ref([]);
const isLoaded = ref(false);

let scrollNume = 5;

// 스크롤 이벤트 핸들러
const handleScroll = () => {
  const container = scrollContainer.value;

  // 스크롤 위치 계산
  const { scrollTop, clientHeight } = container;

  // 6번째 항목이 화면 하단에 도달했는지 확인
  const sixthItem = itemRefs.value[scrollNume]; // 인덱스는 0부터 시작하므로 6번째는 index=5
  if (sixthItem) {
    const sixthItemRect = sixthItem.getBoundingClientRect();
    const containerRect = container.getBoundingClientRect();

    // sixthItem의 하단이 컨테이너의 하단과 같거나 지나갔는지 확인
    if (sixthItemRect.bottom <= containerRect.bottom && !isLoaded.value) {
      let newItems = itemRefs.value[scrollNume];
      if(sixthItem === newItems) {
        scrollNume += 10;
        isLoaded.value = true;
        emit('loadMore'); // 부모에게 알림
      }
    }
  }
};

// 각 리스트 항목에 대한 ref 설정
const setItemRef = (index) => (el) => {
  if (el) {
    itemRefs.value[index] = el;
  }
};

// 마운트 시 스크롤 이벤트 등록 (passive: true)
onMounted(() => {
  if (scrollContainer.value) {
    scrollContainer.value.addEventListener('scroll', handleScroll, { passive: true });
  }
});

// 언마운트 시 스크롤 이벤트 제거
onUnmounted(() => {
  if (scrollContainer.value) {
    scrollContainer.value.removeEventListener('scroll', handleScroll);
  }
});


</script>

<template>
  <div ref="scrollContainer" class="scroll-container">
    <NuxtLink class="card-branch__list-frame"
      v-for="(item, index) in props.items"
      :key="item.id"
      :to="`/leaf/${item.id}`"
    >
      <div class="branch-info-frame" :ref="setItemRef(index)">
        <div class="branch-img-frame">
          <img v-if="item.bookMark" src="/public/svg/card-bookmark-icon.svg" alt="브랜치 이미지">
          <img v-else src="/public/svg/card-branch-represent-icon.svg" alt="브랜치 이미지" >
        </div>
        <div class="branch-detail-info">
          <p class="branch-detail-info--name">{{ item.title }}</p>
        </div>
      </div>
      <div class="branch-card-bottom-info-frame">
        <div class="branch-card-bottom-info">
          <span>리프 <p>{{ item.leafCount }}</p></span>
          <span>조회수 <p>{{ item.viewCount }}</p></span></div>
        <div class="branch-card-bottom-info">
          <p class="branch-detail-info--regdate">{{formatDate(item.updateTime)}}</p>
        </div>
      </div>
    </NuxtLink>
  </div>
</template>

<style scoped>
.scroll-container{
  overflow-y: auto;
  height: 480px;
}
.card-branch__list-frame {
  padding: 14px 16px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  gap: 4px;
  margin-top: 10px;
  margin-bottom: 10px;
  border-radius: 8px;
  background: var(--main2--opacity40);
}
.card-branch__list-frame-flex {
  display: flex;
  justify-content: space-between;
}
.branch-info-frame {
  display: flex;
  gap: 4px;
}
.branch-img-frame {
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
  border-radius: 4px;
  background: var(--main1);
}
.branch-img-frame-lock {
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: end;
  align-items: start;
}
.branch-detail-info {
  padding-top: 1px;
  padding-bottom: 1px;
  display: flex;
  flex-grow: 1;
  min-width: 0;
  align-items: center;
  text-align: left;
}
.branch-detail-info--regdate {
  font-size: 12px;
  font-weight: var(--regular);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}
.branch-detail-info--name {
  font-size: 18px;
  font-weight: var(--bold);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.branch-card-bottom-info-frame {
  display: flex;
  justify-content: space-between;
}
.branch-card-bottom-info {
  display: flex;
  align-items: end;
  gap: 10px;
}
.branch-card-bottom-info span {
  display: flex;
  align-items: end;
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--regular);
}
.branch-card-bottom-info span > p {
  margin-left: 2px;
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--bold);
}
.branch-card-bottom__like-frame {
  width: 24px;
  height: 24px;
  display: flex;
}
.branch-card-bottom__like-input {
  display: none;
  cursor: none;
}

.branch-card-bottom__like {
  cursor: pointer;
}

.branch-card-bottom__like::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.branch-card-bottom__like-content {
  width: 100%;
  display: none;
}

.branch-card-bottom__like-input:checked + .branch-card-bottom__like::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like-fill.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.branch-card-bottom__like-input:checked ~ .branch-card-bottom__like-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>