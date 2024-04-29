<template>
  <!--顶部栏-->
  <div style="position: fixed; top:0px;width: 100%;z-index: 1;">
    <van-nav-bar class="nav" style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;background-color:#D54431;"
                 title="我的订单"
                 left-text="返回"
                 left-arrow
                 @click-left="onBack"/>
  </div>
  <div style="margin-top:65px;padding:10px;">
    <div style="margin-left: 20px;">共{{orderList.length}}条订单</div>
    <van-empty description="亲，当前购物车没有任何商品" v-show="emptyShow"/>
      <van-collapse v-model="activeNames" v-for="(order,index) in orderList" >
        <van-collapse-item :title="'订单尾号:'+order.orderNo.substring(23,36) + '     '+'总金额: ¥'+ order.orderAmountTotal" :name="index+1" :value="order.orderStatus" style="margin-top: 10px;--van-collapse-item-content-background: #f5f5f5;">
          <van-swipe-cell v-for="(item,index) in order.orderItemsVOS">
            <van-card
                :num="item.amount"
                :price="item.price"
                :desc="item.tbProductName"
                :title="item.specsName"
                class="goods-card"
                :thumb="BASE_URL + item.imgUrl"
            />
          </van-swipe-cell>
        </van-collapse-item>
      </van-collapse>

<!--    <van-cell-group inset style="margin-top: 10px;" v-for="order in orderList">-->
<!--      <van-cell :title="'订单号:'+order.orderNo" :value="'¥'+ order.orderAmountTotal" :label="order.orderStatus" style="&#45;&#45;van-cell-background:#f5f5f5;" />-->
<!--    </van-cell-group>-->
  </div>
<!--  <div style="margin: auto">  <van-pagination style="position: fixed;bottom:80px;width: 100%;"-->
<!--                                              v-model="currentPage"-->
<!--                                              :total-items="125"-->
<!--                                              :show-page-size="6"-->
<!--                                              force-ellipses-->
<!--  /></div>-->

</template>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import axios from "@/utils/request";

const orderList = ref([])
const activeNames = ref([1]);
const emptyShow = ref(true);
// const currentPage = ref();
const onBack =()=>{
  let redirectPath = localStorage.getItem('redirectPath');
  //跳转上一页
  if (redirectPath) {
    localStorage.removeItem('redirectPath');
    router.push(redirectPath);
  }
}

onMounted(()=>{
  axios.get("mall/order/select/all").then((response)=>{
    if(response.data.state == 20000){
      orderList.value = response.data.data;
      if(orderList.value.length!=0){
        emptyShow.value = false;
      }
    }
  })
})
</script>

<style scoped>
/*设置顶部栏样式*/
.nav {
  --van-nav-bar-title-text-color: #fff;
  --van-nav-bar-icon-color: #fff;
  --van-nav-bar-text-color: #fff;
  --van-nav-bar-title-font-size: 22px;
  --van-nav-bar-arrow-size: 20px;
}
</style>