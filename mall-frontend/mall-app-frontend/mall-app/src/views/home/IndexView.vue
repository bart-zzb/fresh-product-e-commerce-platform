<template>
  <van-row>
    <van-col span="6">
      <div style="line-height: 50px;font-weight: bold;font-size: 22px;color: #1989FA;margin-left: 8px;">同城生鲜</div>
    </van-col>
    <van-col span="18">
      <van-search v-model="value" placeholder="请输入搜索关键词"/>
    </van-col>
  </van-row>
  <van-row>
    <van-col span="24">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item v-for="item in carouselList"><img :src=item width="390px;"></van-swipe-item>
      </van-swipe>
    </van-col>
  </van-row>
  <van-row>
    <van-col v-for="item in labelList" span="6">
      <img :src="item.imgUrl">
      <div>{{item.name}}</div>
    </van-col>
  </van-row>

</template>

<script setup>
import {ref} from "vue";

const carouselList = ref([
    "imgs/carousel1.png",
    "imgs/carousel2.png",
    "imgs/carousel3.png",
    "imgs/carousel4.png",
    "imgs/carousel5.png",
    "imgs/carousel6.png"
]);

const labelList = ref([
  {imgUrl:"/imgs/label/label1.png",name:"猪肉"},
  {imgUrl:"/imgs/label/label2.png",name:"牛肉"},
  {imgUrl:"/imgs/label/label3.png",name:"猪骨"},
  {imgUrl:"/imgs/label/label4.png",name:"加工产品"},
  {imgUrl:"/imgs/label/label5.png",name:"供港蔬菜"},
  {imgUrl:"/imgs/label/label6.png",name:"本周秒杀"},
  {imgUrl:"/imgs/label/label7.png",name:"高州特产"},
  {imgUrl:"/imgs/label/label8.png",name:"优惠券领取"}
]);
</script>

<style scoped>

</style>