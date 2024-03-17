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

  <!--轮播信息-->
  <van-swipe style="height: 24px;" :autoplay="2500" :show-indicators="false" vertical>
    <van-swipe-item v-for="item in orderList" style="height: 24px;margin:0px;">
      <p style="margin:0px 12px;"><span style="color: red;border: 1px solid red;border-radius: 8px;">&nbsp{{item.time}}&nbsp</span>
        {{item.phone}} 购买了 {{item.amount}} 份 {{item.productName}} {{item.specName}}</p>
    </van-swipe-item>
  </van-swipe>

  <!--图标分类-->
  <van-row>
    <van-col v-for="item in labelList" span="6" style="margin: 10px auto;">
      <img :src="item.imgUrl" style="width: 45px;">
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

const orderList = ref([
  {time:"3时20分06秒",phone:"134****0318",amount:"1",productName:"猪扒(里脊)",specName:"300g/份"},
  {time:"0时31分32秒",phone:"137****173",amount:"2",productName:"牛肉",specName:"300g/份"},
  {time:"2时40分16秒",phone:"135****0345",amount:"4",productName:"牛展",specName:"500g/份"}
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
  text-overflow:ellipsis;
}
</style>