<template>

  <h2 style="text-align: center">购物车</h2>
  <van-row style="color: darkgrey; font-size: 14px;margin-top: 10px; text-align: center">
    <van-col :span="8"><van-icon name="passed"/>100%正品保证</van-col>
    <van-col :span="10"><van-icon name="passed"/>所有商品精挑细选</van-col>
    <van-col :span="6"><van-icon name="passed"/>售后无忧</van-col>
  </van-row>
  <div style="margin: 20px 0 20px 20px;">
    购物数量 0
    <button v-show="!show" style="margin-left: 220px;" @click="show=true">管理</button>
    <button v-show="show" style="margin-left: 220px;" @click="show=false">取消</button>
  </div>
  <van-checkbox-group v-model="checked">
    <div v-for="(goods, index) in cartGoods" :key="index" style="margin-left:20px;">
      <van-checkbox :name="goods.id" :label="goods.id">
        <van-card
            style="width: 330px;"
            :num="goods.num"
            :price="goods.price"
            :desc="goods.desc"
            :title="goods.title"
            :thumb="goods.imageUrl"/>
      </van-checkbox>
    </div>
  </van-checkbox-group>

  <van-submit-bar style="margin-bottom: 80px;"
                  :price="3050"
                  button-text="提交订单" @submit="onSubmit">
    <van-checkbox>全选(0)</van-checkbox>
  </van-submit-bar>
  <van-submit-bar v-show="show"
                  style="margin-bottom: 80px;"
                  button-text="删除" @submit="onSubmit">
    <van-checkbox style="margin-right: 180px;width: 100px;">全选(0)</van-checkbox>
  </van-submit-bar>

</template>

<script setup>
import {ref} from "vue";
import router from "@/router";
import {showToast} from "vant";

const show = ref(false);

const checked = ref([false,false,false]);
const cartGoods = ref([{
    id:1,
    num:2,
    price:13.9,
    desc:'规格:300g/2份起售',
    title:'五花肉 300g/份',
    imageUrl:'/imgs/detail/detail1.jpg'
  },
  {
    id:2,
    num:2,
    price:13.9,
    desc:'规格:300g/2份起售',
    title:'五花肉 300g/份',
    imageUrl:'/imgs/detail/detail1.jpg'
  },
  {
    id:3,
    num:2,
    price:13.9,
    desc:'规格:300g/2份起售',
    title:'五花肉 300g/份',
    imageUrl:'/imgs/detail/detail1.jpg'
  }]);


</script>

<style scoped>

</style>
