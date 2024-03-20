<template>
  <h1>产品分类</h1>
  <van-search v-model="searchValue" placeholder="请输入搜索关键词"/>
  <van-tabs v-model:active="active">
    <van-tab v-for="cat in categoryList" :title="cat.name">
      <van-tree-select v-model:main-active-index="activeIndex" height="auto" :items="cat.catList">
        <template #content>
          <div v-if="activeIndex == 0 && cat.catList.length>=1">
            <!--商品卡片-->
            <van-row v-for="item in cat.catList[activeIndex].productList">
              <van-col span="10">
                <van-image :src="item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 2px 4px;">{{ item.title }}</p>
                <van-row >
                  <van-col span="18" style="position: absolute;bottom: 2px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.origPrice }}</s></div>
                  </van-col>
                  <van-col span="6" style="position: absolute;bottom: 10px;right: 10px;">
                    <van-icon name="add-o" color="#f00" size="25" style="margin-top: 5px;"/>
                  </van-col>
                </van-row>
              </van-col>
            </van-row>
          </div>
          <div v-if="activeIndex == 1 && cat.catList.length>=2">
            <!--商品卡片-->
            <van-row v-for="item in cat.catList[activeIndex].productList">
              <van-col span="10">
                <van-image :src="item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 2px 4px;">{{ item.title }}</p>
                <van-row >
                  <van-col span="18" style="position: absolute;bottom: 2px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.origPrice }}</s></div>
                  </van-col>
                  <van-col span="6" style="position: absolute;bottom: 10px;right: 10px;">
                    <van-icon name="add-o" color="#f00" size="25" style="margin-top: 5px;"/>
                  </van-col>
                </van-row>
              </van-col>
            </van-row>
          </div>
          <div v-if="activeIndex == 2 && cat.catList.length>=3" >
            <!--商品卡片-->
            <van-row v-for="item in cat.catList[activeIndex].productList">
              <van-col span="10">
                <van-image :src="item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 2px 4px;">{{ item.title }}</p>
                <van-row >
                  <van-col span="18" style="position: absolute;bottom: 2px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.origPrice }}</s></div>
                  </van-col>
                  <van-col span="6" style="position: absolute;bottom: 10px;right: 10px;">
                    <van-icon name="add-o" color="#f00" size="25" style="margin-top: 5px;"/>
                  </van-col>
                </van-row>
              </van-col>
            </van-row>
          </div>
        </template>
      </van-tree-select>
    </van-tab>
  </van-tabs>
</template>

<script setup>
import {ref} from "vue";

const searchValue = ref();
const activeIndex = ref(0);
const categoryList = ref([
  {
    name: "本周优选", catList: [
      {
        text: "供澳蔬菜", productList: [
          {
            imgUrl: "/imgs/product/product1.png",
            title: "天润喀拉峻高端纯牛奶(蛋白质>3.6%) 250g*12盒",
            currentPrice: "76.8/箱",
            origPrice: "98/箱"
          },
          {imgUrl: "/imgs/product/product3.png", title: "虾丸 250g", currentPrice: "18.8/份", origPrice: "23.8/份"},
        ]
      },
      {
        text: "粤港风味", productList: [
          {imgUrl: "/imgs/product/product7.png", title: "大连慢炖即食用海参 95g*6瓶", currentPrice: "499/份", origPrice: "699/份"}
        ]
      },
    ]
  },
  {
    name: "猪肉", catList: [
      {
        text: "猪肉类", productList: [
          {imgUrl: "/imgs/product/product4.png", title: "腌制猪扒 400g/份", currentPrice: "23.9/份", origPrice: "35.9/份"}
        ]
      },
      {text: "猪骨类",},
      {text: "猪副产品类",},
    ]
  },
  {
    name: "牛肉类", catList: [
      {
        text: "牛肉类", productList: [
          {imgUrl: "/imgs/product/product5.png", title: "腌制牛扒 400g/份", currentPrice: "58.8/份", origPrice: "65/份"},
          {imgUrl: "/imgs/product/product6.png", title: "肥牛 500g/份", currentPrice: "75.8/份", origPrice: "83.6/份"},
          {imgUrl: "/imgs/product/product8.png", title: "吊龙 500g/份", currentPrice: "89.8/份", origPrice: "98.8/份"},
        ]
      },
      {text: "进口牛肉"},
    ]
  },
  {
    name: "其他产品", catList: [
      {text: "牛肉丸",},
      {text: "猪肉丸",},
      {
        text: "闸坡鱼丸", productList: [
          {imgUrl: "/imgs/product/product2.png", title: "闸坡鱼羹 250g", currentPrice: "19.8/份", origPrice: "24.8/份"},
        ]
      },
    ]
  }
]);


</script>

<style scoped>

</style>