<script setup>

const { data } = defineProps(['data'])
/*
tree: string;
branch: string;
leaf: string;
*/
</script>

<template>
  <!-- ==========================================
      FRAGMENT: 리프 경로 바 (tree, branch)
      ========================================== -->
<div class="log-path-box">
    <p class="log-path-box__frame">
        <span class="log-path-box__tree-name" >{{data.tree}}</span>
        /
        <span class="log-path-box__branch-name" >{{data.branch}}</span>
        /
      <span class="log-path-box__branch-name" >{{data.leaf}}</span>
    </p>
</div>
</template>

<style>
/*  ==========================================
    FRAGMENT: 리프 경로 바
    ========================================== */
    .log-path-box {
  width: 100%;
  border-top: 1px solid var(--gray, #d9d9d9);
  padding: 13px 0;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  background-color: var(--main2, #e5eddb);
  font-size: 14px;
  font-weight: var(--bold, 700);
}
</style>
