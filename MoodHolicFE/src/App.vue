<template>
  <header><HeaderComponent/></header>
  <main>
    <router-view/>
  </main>
</template>

<script setup>

import LogIn from "@/components/MainPage/Login/LogIn.vue";
import HeaderComponent from "@/components/Header/Header.vue";
</script>
