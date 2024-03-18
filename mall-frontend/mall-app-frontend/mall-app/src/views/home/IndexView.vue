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
  <img :src="banner1" style="width:390px;">
  <!--横幅2-->
  <img :src="banner2" style="width:390px;">
  <!--横幅3-->
  <img :src="banner3" style="width:390px;">
  <!--横幅4-->
  <img :src="banner4" style="width:390px;">
  <!--横幅5-->
  <img :src="banner5" style="width:390px;">
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
  {message: "每日截单时间为23:00,23点前的订单,会于次日安排配送"}
]);

const banner1 = ref("/imgs/banner/banner1.png");
const banner2 = ref("/imgs/banner/banner2.png");
const banner3 = ref("/imgs/banner/banner3.png");
const banner4 = ref("/imgs/banner/banner4.png");
const banner5 = ref("/imgs/banner/banner5.png");

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