<template>
  <!--标题和搜索栏-->
  <van-row>
    <van-col span="6">
      <div style="line-height: 50px;font-weight: bold;font-size: 22px;color: #1989FA;margin-left: 8px;">同城生鲜</div>
    </van-col>
    <van-col span="18">
      <van-search v-model="value" placeholder="请输入搜索关键词"/>
    </van-col>
  </van-row>

  <!--轮播图管理-->
  <van-row>
    <van-col span="24">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item v-for="item in carouselList"><img :src=item width="390px;"></van-swipe-item>
      </van-swipe>
    </van-col>
  </van-row>

  <!--用户轮播信息-->
  <van-swipe style="height: 24px;" :autoplay="2500" :show-indicators="false" vertical>
    <van-swipe-item v-for="item in orderList" style="height: 24px;margin:0px;">
      <p style="margin:0px 12px;"><span
          style="color: red;border: 1px solid red;border-radius: 8px;">&nbsp{{ item.time }}&nbsp</span>
        {{ item.phone }} 购买了 {{ item.amount }} 份 {{ item.productName }} {{ item.specName }}</p>
    </van-swipe-item>
  </van-swipe>

  <!--图标分类-->
  <van-row>
    <van-col v-for="item in labelList" span="6" style="margin: 10px auto;">
      <img :src="item.imgUrl" style="width: 45px;">
      <div>{{ item.name }}</div>
    </van-col>
  </van-row>

  <van-divider style="margin: 6px auto;"/>

  <!--平台轮播信息-->
  <van-row>
    <van-col span="7">
      <div style="font-weight: bold;font-size: 18px;margin: 0px auto;height: 24px;color: red"><i>平台公告</i></div>
    </van-col>
    <van-col span="2">
      <van-divider vertical :hairline="false" style="height: 26px;"/>
    </van-col>
    <van-col span="15">
      <van-swipe style="height: 24px;" :autoplay="2500" :show-indicators="false" vertical>
        <van-swipe-item v-for="item in messageList">
          <p style="text-align:left;line-height: 26px;margin: 0px;"><span
              style="color: red;border: 1px solid red;border-radius: 8px;">&nbsp最新&nbsp</span>
            {{ item.message }}
          </p>
        </van-swipe-item>
      </van-swipe>
    </van-col>
  </van-row>

  <!--横幅1-->
  <van-row>
    <van-col span="24">
      <img :src="banner1" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示1-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList1" >
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <van-image :src="item.imgUrl"></van-image>
          <p style="margin: 2px;">{{ item.title }}</p>
          <van-row>
            <van-col span="14">
              <div style="color: #f00;margin:0;">{{ item.currentPrice }}</div>
              <div style="margin: 0;"><s>{{ item.origPrice }}</s></div>
            </van-col>
            <van-col span="10">
              <van-icon name="cart-o" color="#f00" size="30"/>
            </van-col>
          </van-row>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>

  <!--横幅2-->
  <van-row>
    <van-col span="24">
      <img :src="banner2" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示2-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList1" >
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <van-image :src="item.imgUrl"></van-image>
          <p style="margin: 2px;">{{ item.title }}</p>
          <van-row>
            <van-col span="14">
              <div style="color: #f00;margin:0;">{{ item.currentPrice }}</div>
              <div style="margin: 0;"><s>{{ item.origPrice }}</s></div>
            </van-col>
            <van-col span="10">
              <van-icon name="cart-o" color="#f00" size="30"/>
            </van-col>
          </van-row>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>

  <!--横幅3-->
  <van-row>
    <van-col span="24">
      <img :src="banner3" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示3-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList1" >
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <van-image :src="item.imgUrl"></van-image>
          <p style="margin: 2px;">{{ item.title }}</p>
          <van-row>
            <van-col span="14">
              <div style="color: #f00;margin:0;">{{ item.currentPrice }}</div>
              <div style="margin: 0;"><s>{{ item.origPrice }}</s></div>
            </van-col>
            <van-col span="10">
              <van-icon name="cart-o" color="#f00" size="30"/>
            </van-col>
          </van-row>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>

  <!--横幅4-->
  <van-row>
    <van-col span="24">
      <img :src="banner4" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示4-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList1" >
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <van-image :src="item.imgUrl"></van-image>
          <p style="margin: 2px;">{{ item.title }}</p>
          <van-row>
            <van-col span="14">
              <div style="color: #f00;margin:0;">{{ item.currentPrice }}</div>
              <div style="margin: 0;"><s>{{ item.origPrice }}</s></div>
            </van-col>
            <van-col span="10">
              <van-icon name="cart-o" color="#f00" size="30"/>
            </van-col>
          </van-row>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>

  <!--横幅5-->
  <van-row>
    <van-col span="24">
      <img :src="banner5" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示5-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList1" >
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <van-image :src="item.imgUrl"></van-image>
          <p style="margin: 2px;">{{ item.title }}</p>
          <van-row>
            <van-col span="14">
              <div style="color: #f00;margin:0;">{{ item.currentPrice }}</div>
              <div style="margin: 0;"><s>{{ item.origPrice }}</s></div>
            </van-col>
            <van-col span="10">
              <van-icon name="cart-o" color="#f00" size="30"/>
            </van-col>
          </van-row>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>


</template>

<script setup>
import {ref} from "vue";

const carouselList = ref([
  "imgs/carousel/carousel1.png",
  "imgs/carousel/carousel2.png",
  "imgs/carousel/carousel3.png",
  "imgs/carousel/carousel4.png",
  "imgs/carousel/carousel5.png",
  "imgs/carousel/carousel6.png"
]);

const labelList = ref([
  {imgUrl: "/imgs/label/label1.png", name: "猪肉"},
  {imgUrl: "/imgs/label/label2.png", name: "牛肉"},
  {imgUrl: "/imgs/label/label3.png", name: "猪骨"},
  {imgUrl: "/imgs/label/label4.png", name: "加工产品"},
  {imgUrl: "/imgs/label/label5.png", name: "供港蔬菜"},
  {imgUrl: "/imgs/label/label6.png", name: "本周秒杀"},
  {imgUrl: "/imgs/label/label7.png", name: "高州特产"},
  {imgUrl: "/imgs/label/label8.png", name: "优惠券领取"}
]);

const orderList = ref([
  {time: "3时20分06秒", phone: "134****0318", amount: "1", productName: "猪扒(里脊)", specName: "300g/份"},
  {time: "0时31分32秒", phone: "137****173", amount: "2", productName: "牛肉", specName: "300g/份"},
  {time: "2时40分16秒", phone: "135****0345", amount: "4", productName: "牛展", specName: "500g/份"}
]);

const messageList = ref([
  {message: "购买须知"},
  {message: "下单流程"},
  {message: "每日截单时间为23:00, 23点前的订单,会于次日安排配送"},
  {message: "用户打开手机位置操作指引"}
]);

const banner1 = ref("/imgs/banner/banner1.png");
const banner2 = ref("/imgs/banner/banner2.png");
const banner3 = ref("/imgs/banner/banner3.png");
const banner4 = ref("/imgs/banner/banner4.png");
const banner5 = ref("/imgs/banner/banner5.png");

const productList1 = ref([
  [
    {imgUrl: "/imgs/product/product1.png", title: "天润喀拉峻高端纯牛奶(蛋白质>3.6%) 250g*12盒", currentPrice: "76.8/箱", origPrice: "98/箱"},
    {imgUrl: "/imgs/product/product2.png", title: "闸坡鱼羹 250g", currentPrice: "19.8/份", origPrice: "24.8/份"},
    {imgUrl: "/imgs/product/product3.png", title: "虾丸 250g", currentPrice: "18.8/份", origPrice: "23.8/份"}
  ],
  [
    {imgUrl: "/imgs/product/product4.png", title: "腌制猪扒 400g/份", currentPrice: "23.9/份", origPrice: "35.9/份"},
    {imgUrl: "/imgs/product/product5.png", title: "腌制牛扒 400g/份", currentPrice: "58.8/份", origPrice: "65/份"},
    {imgUrl: "/imgs/product/product6.png", title: "肥牛 500g/份", currentPrice: "75.8/份", origPrice: "83.6/份"}
  ],
  [
    {imgUrl: "/imgs/product/product7.png", title: "大连慢炖即食用海参 95g*6瓶", currentPrice: "499/份", origPrice: "699/份"},
    {imgUrl: "/imgs/product/product8.png", title: "吊龙 500g/份", currentPrice: "89.8/份", origPrice: "98.8/份"}
  ],
]);
</script>

<style scoped>
/*单行文字超出的部分替换为省略号*/
p {
  /*单行显示*/
  white-space: nowrap;
  /*超出部分隐藏*/
  overflow: hidden;
  /*超出部分替换成...*/
  text-overflow: ellipsis;
}
</style>