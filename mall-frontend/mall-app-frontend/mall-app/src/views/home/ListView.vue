<template>
  <h2>产品分类</h2>
  <van-search v-model="searchValue" placeholder="请输入搜索关键词"/>
  <van-tabs v-model:active="active">
    <van-tab v-for="cat in categoryList" :title="cat.text">
      <van-tree-select v-model:main-active-index="activeList[active].subActiveIndex[activeIndex]" height="auto" :items="cat.children">
        <template #content>
          <div v-if="activeList[active].subActiveIndex[activeIndex] == 0 && cat.children.length>=1">
            <!--商品卡片-->
            <van-row v-for="item in cat.children[activeList[active].subActiveIndex[activeIndex]].productSpecsList">
              <van-col span="10">
                <van-image :src="BASE_URL+item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 10px 4px;">{{ item.specsName }}</p>
                <van-row>
                  <van-col span="18" style="position: absolute;bottom: 6px;left:6px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
                  </van-col>
                  <van-col span="6" style="position: absolute;bottom: 10px;right: 10px;">
                    <van-icon name="add-o" color="#f00" size="25" style="margin-top: 5px;"/>
                  </van-col>
                </van-row>
              </van-col>
            </van-row>
          </div>
          <div v-if="activeList[active].subActiveIndex[activeIndex] == 1 && cat.children.length>=2">
            <!--商品卡片-->
            <van-row v-for="item in cat.children[activeList[active].subActiveIndex[activeIndex]].productSpecsList">
              <van-col span="10">
                <van-image :src="BASE_URL+item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 2px 4px;">{{ item.specsName }}</p>
                <van-row>
                  <van-col span="18" style="position: absolute;bottom: 2px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
                  </van-col>
                  <van-col span="6" style="position: absolute;bottom: 10px;right: 10px;">
                    <van-icon name="add-o" color="#f00" size="25" style="margin-top: 5px;"/>
                  </van-col>
                </van-row>
              </van-col>
            </van-row>
          </div>
          <div v-if="activeList[active].subActiveIndex[activeIndex] == 2 && cat.children.length>=3">
            <!--商品卡片-->
            <van-row v-for="item in cat.children[activeList[active].subActiveIndex[activeIndex]].productSpecsList">
              <van-col span="10">
                <van-image :src="BASE_URL+item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 2px 4px;">{{ item.specsName }}</p>
                <van-row>
                  <van-col span="18" style="position: absolute;bottom: 2px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
                  </van-col>
                  <van-col span="6" style="position: absolute;bottom: 10px;right: 10px;">
                    <van-icon name="add-o" color="#f00" size="25" style="margin-top: 5px;"/>
                  </van-col>
                </van-row>
              </van-col>
            </van-row>
          </div>
          <div v-if="activeList[active].subActiveIndex[activeIndex] == 3 && cat.children.length>=4">
            <!--商品卡片-->
            <van-row v-for="item in cat.children[activeList[active].subActiveIndex[activeIndex]].productSpecsList">
              <van-col span="10">
                <van-image :src="BASE_URL+item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 2px 4px;">{{ item.specsName }}</p>
                <van-row>
                  <van-col span="18" style="position: absolute;bottom: 2px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
                  </van-col>
                  <van-col span="6" style="position: absolute;bottom: 10px;right: 10px;">
                    <van-icon name="add-o" color="#f00" size="25" style="margin-top: 5px;"/>
                  </van-col>
                </van-row>
              </van-col>
            </van-row>
          </div>
          <div v-if="activeList[active].subActiveIndex[activeIndex] == 4 && cat.children.length>=5">
            <!--商品卡片-->
            <van-row v-for="item in cat.children[activeList[active].subActiveIndex[activeIndex]].productSpecsList">
              <van-col span="10">
                <van-image :src="BASE_URL+item.imgUrl"></van-image>
              </van-col>
              <van-col span="14" style="position: relative;">
                <p style="margin: 2px 4px;">{{ item.specsName }}</p>
                <van-row>
                  <van-col span="18" style="position: absolute;bottom: 2px;">
                    <div style="color: #f00;margin-left:10px;text-align: left;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                    <div style="margin-left:10px;text-align: left;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
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
import {onMounted, ref} from "vue";
import axios from "@/utils/request";

const active=ref(0);
const activeIndex = ref(0);
const activeList = ref([])

const searchValue = ref();
const categoryList = ref([]);

onMounted(() => {
  let id = new URLSearchParams(location.search).get('id');
  axios.get("mall/product_specs/tree").then((response) => {
    if (response.data.state == 20000) {
      categoryList.value = response.data.data;
      loadActiveList();
    }
  })
  if(id==1){
    active.value=3;
    activeIndex.value=0;
  }else if(id==2){
    active.value=4;
    activeIndex.value=0;
  }else if(id==3){
    active.value=3
    activeIndex.value=1;
  }else if(id==4){
    active.value=0;
    activeIndex.value=1;
  }else if(id==5){
    active.value=0;
    activeIndex.value=2;
  }else if(id==6){
    active.value=0;
    activeIndex.value=0;
  }else if(id==7){
    active.value=1;
    activeIndex.value=0;
  }else if(id==8){
    active.value=2;
    activeIndex.value=0;
  }
})

const loadActiveList =()=>{
  for (let i = 0; i < categoryList.value.length; i++) {
    let temp = {};
    temp.activeIndex = i;
    temp.subActiveIndex = [];
    for (let j = 0; j < categoryList.value[i].children.length; j++) {
      temp.subActiveIndex.push(j);
    }
    activeList.value.push(temp);
  }
}
// const categoryList = ref([
//   {
//     name: "本周优选", catList: [
//       {
//         text: "供澳蔬菜", productList: [
//           {
//             imgUrl: "/imgs/product/product1.png",
//             title: "天润喀拉峻高端纯牛奶(蛋白质>3.6%) 250g*12盒",
//             currentPrice: "76.8/箱",
//             origPrice: "98/箱"
//           },
//           {imgUrl: "/imgs/product/product3.png", title: "虾丸 250g", currentPrice: "18.8/份", origPrice: "23.8/份"},
//         ]
//       },
//       {
//         text: "粤港风味", productList: [
//           {imgUrl: "/imgs/product/product7.png", title: "大连慢炖即食用海参 95g*6瓶", currentPrice: "499/份", origPrice: "699/份"}
//         ]
//       },
//     ]
//   },
//   {
//     name: "猪肉", catList: [
//       {
//         text: "猪肉类", productList: [
//           {imgUrl: "/imgs/product/product4.png", title: "腌制猪扒 400g/份", currentPrice: "23.9/份", origPrice: "35.9/份"},
//           {imgUrl: "/imgs/product/product9.png", title: "猪扒(里脊) 300g/份", currentPrice: "13.9/份", origPrice: "20/份"},
//           {imgUrl: "/imgs/product/product10.png", title: "五花肉 300g/份", currentPrice: "13.9/份", origPrice: "18.2/份"}
//         ]
//       },
//       {text: "猪骨类",},
//       {text: "猪副产品类",},
//     ]
//   },
//   {
//     name: "牛肉类", catList: [
//       {
//         text: "牛肉类", productList: [
//           {imgUrl: "/imgs/product/product5.png", title: "腌制牛扒 400g/份", currentPrice: "58.8/份", origPrice: "65/份"},
//           {imgUrl: "/imgs/product/product6.png", title: "肥牛 500g/份", currentPrice: "75.8/份", origPrice: "83.6/份"},
//           {imgUrl: "/imgs/product/product8.png", title: "吊龙 500g/份", currentPrice: "89.8/份", origPrice: "98.8/份"},
//         ]
//       },
//       {text: "进口牛肉"},
//     ]
//   },
//   {
//     name: "其他产品", catList: [
//       {text: "牛肉丸",},
//       {text: "猪肉丸",},
//       {
//         text: "闸坡鱼丸", productList: [
//           {imgUrl: "/imgs/product/product2.png", title: "闸坡鱼羹 250g", currentPrice: "19.8/份", origPrice: "24.8/份"},
//         ]
//       },
//     ]
//   }
// ]);


</script>

<style scoped>
* {
  text-align: center;
}
</style>