<template>
  <!--搜索框-->
  <van-search v-model="value" placeholder="请输入搜索关键词" />

  <!--轮播-->
  <van-swipe :autoplay="3000" lazy-render>
    <van-swipe-item  v-for="image in images" :key="image">
      <img :src="image" style="height: 200px"/>
    </van-swipe-item>
  </van-swipe>
  <!--列表-->
  <van-grid :column-num="3">
    <van-grid-item v-for="value in 6" :key="value" icon="photo-o" text="文字" />
  </van-grid>
  <!--商品列表-->
  <van-card
      num="2"
      price="2.00"
      desc="描述信息"
      title="商品标题"
      thumb="https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg"
  />

  <van-card
      num="2"
      price="2.00"
      desc="描述信息"
      title="商品标题"
      thumb="https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg"
  />
  <van-card
      num="2"
      price="2.00"
      desc="描述信息"
      title="商品标题"
      thumb="https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg"
  />

  <!--导航菜单-->
  <div>
    <router-view></router-view>
    <van-tabbar active-color="#ee0a24" inactive-color="#000">
      <van-tabbar-item icon="wap-home-o">首页</van-tabbar-item>
      <van-tabbar-item icon="apps-o">分类页</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart-o">购物车</van-tabbar-item>
      <van-tabbar-item icon="user-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>

</template>

<script setup>
const images = [
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg',
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-2.jpeg',
];
</script>

<style scoped>

</style>
