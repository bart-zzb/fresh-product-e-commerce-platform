<template>
  <van-row>
    <van-col span="4"></van-col>
    <van-col span="4">
      <div style="line-height: 50px;">同城生鲜</div>
    </van-col>
    <van-col span="16">
      <van-search v-model="value" placeholder="请输入搜索关键词"/>
    </van-col>
  </van-row>
  <van-row>
    <van-col span="24">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item><img src="/imgs/carsoual1.png" width="390px;"></van-swipe-item>
        <van-swipe-item><img src="/imgs/carsoual1.png" width="390px"></van-swipe-item>
        <van-swipe-item><img src="/imgs/carsoual1.png" width="390px"></van-swipe-item>
        <van-swipe-item><img src="/imgs/carsoual1.png" width="390px"></van-swipe-item>
      </van-swipe>
    </van-col>
  </van-row>

</template>

<script setup>

</script>

<style scoped>

</style>