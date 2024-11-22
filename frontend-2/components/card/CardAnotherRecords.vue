<script setup lang="ts">
import { CardType } from "@/types/types";
import type { CardItemProps } from "@/types/types";

const config = useRuntimeConfig();
//TODO : 알라딘 책 이미지일 경우 알라딘 이미지 api로 요청해야 함.

const props = defineProps<{
  item: CardItemProps;
}>();
const item = props.item;

function modifyCount(count: number) {
  return count > 999 ? "999+" : String(count);
}

const link = () => {
  if (item.cardType === CardType.TREE) {
    return `/tree/${item.treeId}`;
  } else if (item.cardType === CardType.MEMOIR) {
    return `/memoir/${item.memoirId}`;
  } else if (item.cardType === CardType.LEAF) {
    if (item.bookTitle) {
      return `/leaf/book/${item.leafId}`;
    } else {
      return `/leaf/etc/${item.leafId}`;
    }
  }
};
</script>

<template>
  <!-- (item, type) -->
  <div class="card-another-records-box">
    <div class="card-another-records__top-box">
      <NuxtLink class="card-another-records__top-anker" :to="link()">
        <!-- 표지 -->
        <div v-if="item.cardType === CardType.TREE">
          <img
            class="card-another-records__top-cover-box"
            :src="useGetImageUrl(item.cardImage, 'tree')"
            alt="트리 이미지"
          />
        </div>
        <div v-else-if="item.cardType === CardType.MEMOIR">
          <img
            class="card-another-records__top-cover-box"
            :src="useGetImageUrl(item.cardImage, 'memoir')"
            alt="회고록 이미지"
          />
        </div>
        <div v-else-if="item.cardType === CardType.LEAF">
          <img
            class="card-another-records__top-cover-box"
            :src="
              item.cardImage
                ? useGetImageUrl(item.cardImage, 'tree')
                : useGetImageUrl(item.treeImage, 'tree')
            "
            alt="리프 이미지"
          />
        </div>

        <div class="card-another-records__mid-box">
          <div class="card-another-records__mid-seed-frame">
            <span class="card-another-records__mid-seed">{{
              item.bookCategory
            }}</span>
          </div>

          <p
            class="card-another-records__mid-another--title"
            v-if="item.cardType === CardType.TREE"
          >
            {{ item.treeTitle }}
          </p>
          <p
            class="card-another-records__mid-another--title"
            v-else-if="item.cardType === CardType.MEMOIR"
          >
            {{ item.memoirTitle }}
          </p>
          <p
            class="card-another-records__mid-another--title--sub"
            v-else-if="item.cardType === CardType.LEAF"
          >
            {{ item.treeTitle }}
          </p>
          <p class="card-another-records__mid-work-title">
            {{ item.bookTitle }}
          </p>

          <div v-if="item.bookPublishedYear" class="card-another-records__info">
            <span class="card-another-records__info">{{
              item.bookPublishedYear
            }}</span>
            <span class="card-another-records__info"> / </span>
            <span class="card-another-records__info">{{
              item.bookAuthor
            }}</span>
            <span class="card-another-records__info"> / </span>
            <span class="card-another-records__info">{{
              item.bookPublisher
            }}</span>
          </div>
        </div>
      </NuxtLink>
      <div class="card-another-records__icon-box">
        <div class="card-another-records__share-icon-box">
          <img src="~/assets/png/comment.png" alt="댓글 아이콘" />
        </div>
        <div class="card-another-records__like-icon-box">
          <input
            class="card-another-records__like-bold-input"
            type="checkbox"
            id="like"
          />
          <label class="card-another-records__like-bold" for="like"></label>
        </div>
      </div>
    </div>

    <div class="card-another-records__bot-box">
      <div
        class="card-another-records__bot-nickname-box"
        v-if="item.cardType === CardType.LEAF"
      >
        <a href="">
          <div class="card-another-records__log-text-box">
            <q class="card-another-records-log-title">{{ item.leafTitle }}</q>
            <q class="card-another-records-log-content">{{
              item.leafContent
            }}</q>
          </div>
        </a>
      </div>
      <div class="card-another-records__bot-box-etc-info">
        <div class="card-another-records__bot-box-etc-date">
          {{ item.updateTime }}
        </div>
        <div class="card-another-records__bot-statistics-box">
          <a href="">
            <p
              v-if="item.cardType === CardType.TREE"
              class="card-another-records__bot-leaf-text"
            >
              <span class="card-another-records__bot-leaf-count">{{
                modifyCount(item.leafCount)
              }}</span>
              리프
            </p>
            <p
              v-if="item.cardType === CardType.MEMOIR"
              class="card-another-records__bot-leaf-text"
            >
              <span class="card-another-records__bot-leaf-count">{{
                modifyCount(item.leafCount)
              }}</span>
              리프
            </p>
            <p class="card-another-records__bot-view-text">
              <span class="card-another-records__bot-view-count">{{
                modifyCount(item.viewCount)
              }}</span>
              조회수
            </p>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-another-records-box {
  margin-right: 30px;
  width: 332px;
  border-radius: 16px;
  background: var(--white);
  padding: 16px;
  box-shadow: var(--shadow-active);
}

.card-another-records__top-anker {
  width: 100%;
  display: flex;
  flex: 1 0 auto;
  gap: 8px;
}

.card-another-records__top-box {
  margin-bottom: 12px;
  width: 100%;
  height: 120px;
  display: flex;
  flex-direction: column-reverse;
  justify-content: center;
}

.card-another-records__top-cover-box {
  width: 80px;
  height: 120px;
  object-fit: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
}

.card-another-records__mid-box {
  width: 100%;
  align-items: flex-start;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-another-records__mid-seed-frame {
  gap: 4px;
}

.card-another-records__mid-seed {
  color: var(--text-sub);
  font-size: 10px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__mid-another--title {
  color: var(--text-main);
  font-size: 18px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-another-records__mid-another--title--sub {
  color: var(--text-sub);
  font-size: 18px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-another-records__mid-work-title {
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-another-records__info {
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-another-records__icon-box {
  padding-top: 10px;
  height: 21px;
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
  gap: 8px;
}
.card-another-records__like-icon-box {
  width: 21px;
  height: 21px;
}

.card-another-records__like-bold-input {
  display: none;
  cursor: none;
}

.card-another-records__like-bold {
  cursor: pointer;
}

.card-another-records__like-bold::after {
  width: 21px;
  height: 21px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/png/like.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.card-another-records__like-bold-content {
  width: 100%;
  display: none;
}

.card-another-records__like-bold-input:checked
  + .card-another-records__like-bold::after {
  width: 21px;
  height: 21px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/png/like-fill.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.card-another-records__bot-nickname-box {
  width: 100%;
}

.card-another-records__bot-box {
  width: 100%;
  height: auto;
}

.card-another-records-log-title {
  width: 100%;
  margin-top: 10px;
  color: var(--text-main);
  font-size: 18px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-another-records-log-content {
  width: 100%;
  padding-top: 10px;
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--regular);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-another-records__bot-box-etc-info {
  width: 100%;
  margin-top: 15px;
  display: flex;
  justify-content: space-between;
}

.card-another-records__bot-box-etc-date {
  display: flex;
  color: var(--text-sub);
  align-items: flex-end;
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__bot-statistics-box {
  display: flex;
}

.card-another-records__bot-leaf-text,
.card-another-records__bot-view-text {
  color: var(--text-sub);
  font-size: 8px;
  font-weight: var(--regular);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__bot-leaf-count,
.card-another-records__bot-view-count {
  color: var(--text-main);
  font-size: 12px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-another-records__log-text-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.text-memoir-content {
  color: var(--text-main);
  font-size: 14px;
  font-weight: var(--regular);
  line-height: var(--line-height-high);
  letter-spacing: var(--letter-spacing-main);
}
</style>
