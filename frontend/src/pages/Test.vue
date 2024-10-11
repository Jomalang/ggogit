<script setup lang="ts">

import FilterTabGet from "@/components/tab-filter/FilterTabGet.vue";
import {SeedFilterProps, SeedFilterTabProps} from "@/types/types";

const cc: SeedFilterTabProps = {
  filterName: '씨앗필터',
  filterItems: [
    {
      korName: '씨앗정보1',
      engName: 'seed1'
    },
    {
      korName: '씨앗정보2',
      engName: 'seed2',
    },
    {
      korName: '씨앗정보3',
      engName: 'seed3',
    }
  ]
}

</script>

<template>
  <FilterTabGet :data="cc"/>
</template>

<style scoped></style>