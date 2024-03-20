<template>

  <h2 style="text-align: center">购物车</h2>
  <van-row style="color: darkgrey; font-size: 14px;margin-top: 10px; text-align: center">
    <van-col :span="8"><van-icon name="passed"/>100%正品保证</van-col>
    <van-col :span="10"><van-icon name="passed"/>所有商品精挑细选</van-col>
    <van-col :span="6"><van-icon name="passed"/>售后无忧</van-col>
  </van-row>
  <div style="margin: 20px 0 0 20px;">
    购物数量 0
    <button style="margin-left: 220px;">管理</button>
  </div>
  <van-card
      num="2"
      price="2.00"
      desc="描述信息"
      title="商品标题"
      thumb="{{ imageURL }}"
  />
</template>

<script setup>

import {ref} from "vue";

const cartGoods = ref([]);

const goods = ref({
   num:2,
   price:2.00,
   desc:'描述信息',
   title:'商品标题',
   imageUrl:'/imgs/detail/detail1.jpg'
 });

</script>

<style scoped>

</style>
