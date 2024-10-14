<script setup lang="ts">

interface Props{
  likeLink: string;
  shareLink: string;
}

const props = defineProps<Props>();
</script>

<template>
  <!-- ==========================================
       FRAGMENT: 좋아요 및 공유 (likeLink, shareLink)
       ==========================================-->
  <div id="bar-like-share-id" class="bar-like-share-box">
      <div class="bar-like-share__like-frame">
          <div class="bar-like-share__like-link" :href="`${props.likeLink}`">
              <img class="bar-like-share__like-icon" src="/public/svg/detail-like.svg" alt="좋아요 아이콘" />
          </div>
      </div>
      <div class="bar-like-share__share-frame">
          <a class="bar-like-share__share-link" :href="`${props.shareLink}`">
              <img class="bar-like-share__share-icon" src="/public/svg/detail-share.svg" alt="공유 아이콘" />
          </a>
      </div>
  </div>  
</template>

<style>

/*  ========================================== /
     FRAGMENT: 좋아요 및 공유
/   ========================================== */
.bar-like-share-box {
  padding: 0 24px;
  display: flex;
  justify-content: flex-end;
  background-color: black;
}

.bar-like-share-box--none {
  display: none;
}
.bar-like-share__like-frame,
.bar-like-share__share-frame {
  display: flex;
  width: 36px;
  height: 36px;
  justify-content: flex-end;
  align-items: center;
}
</style>
