<script setup lang="ts">
import { RouterLink, RouterView, useRouter } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import BasicLayout from '@/layouts/BasicLayout.vue'
//import { healthUsingGet } from '@/api/mainController.ts'
//import { useLoginUserStore } from '@/stores/useLoginUserStore.ts'


//const loginUserStore = useLoginUserStore()
//loginUserStore.fetchLoginUser()


//healthUsingGet().then((res) => {
//  console.log(res)
//})
</script>

<template>
  <div id="app">
    <BasicLayout />
  </div>
</template>

<style scoped></style>
