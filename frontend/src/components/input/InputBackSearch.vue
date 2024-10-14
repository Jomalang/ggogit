<script setup lang="ts">
const props = defineProps<{
  placeholder: string;
  href: string;
  method: string;
  name: string;
  action: string;
}>();
</script>

<template>
  <!-- input-back-search(placeholder, href, method, name) -->
  <div class="search__form">
    <div>
      <a :href="href">
        <img src="/public/svg/back.svg" alt="back button" />
      </a>
    </div>
    <form class="search-bar" :action="action" :method="method">
      <label class="search-bar--label">
        <input
          class="search-bar--input"
          type="text"
          :name="name"
          :id="name"
          :placeholder="placeholder"
          autocomplete="off"
        />
        <button class="search-bar--close" type="reset">
          <img src="/public/svg/close-button.svg" alt="close-btn" />
        </button>
      </label>
      <button type="submit">
        <img src="/public/svg/lens.svg" alt="lens" />
      </button>
    </form>
  </div>
</template>

<style scoped>
/* =================================
      input-back-search, input-search 
      검색버튼 뒤로가기버튼 있음. 없음.
   ===================================
 */
a {
  text-decoration: none;
}

button {
  border: none;
  background-color: transparent;
  cursor: pointer;
}

.search__form {
  display: flex;
  align-items: center;
}

.search-bar {
  background: var(--main2);
  border-radius: 20px;
  padding: 8px 16px 8px 16px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-grow: 1;
}

.search-bar--label {
  display: flex;
  flex-grow: 1;
  font-size: 14px;
}

.search-bar--input {
  width: 100%;
  font-weight: var(--semi-bold);
  font-size: 14px;
  background-color: var(--main2);
  border: none;
}

.search-bar--input:focus {
  outline: none;
}

/*  */
.search-bar--close {
  display: none;
}

.search-bar--input:not(:placeholder-shown) {
  font-weight: var(--semi-bold);
  font-size: 14px;
  background-color: var(--main2);
}

/*  */
.search-bar--input:not(:placeholder-shown) + .search-bar--close {
  display: inline;
  height: 18px;
}

/*  */
.search-bar-img {
  height: 18px;
}
</style>
