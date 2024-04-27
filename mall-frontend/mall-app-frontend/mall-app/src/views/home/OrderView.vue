<template>
  <!--顶部栏-->
  <div style="position: fixed; top:0px;width: 100%;">
    <van-nav-bar class="nav" style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;background-color:#D54431;"
                 title="我的订单"
                 left-text="返回"
                 left-arrow
                 @click-left="onBack"/>
  </div>
  <div style="margin-top:65px;padding:10px;">
    <div style="margin-left: 20px;">共{{orderList.length}}条订单</div>
      <van-collapse v-model="activeNames" v-for="(order,index) in orderList" >
        <van-collapse-item :title="'订单尾号:'+order.orderNo.substring(23,36) + '     '+'¥'+ order.orderAmountTotal" :name="index" :value="order.orderStatus" style="margin-top: 10px;--van-collapse-item-content-background: #f5f5f5;">
          ¥{{order.orderAmountTotal}}
        </van-collapse-item>
      </van-collapse>

<!--    <van-cell-group inset style="margin-top: 10px;" v-for="order in orderList">-->
<!--      <van-cell :title="'订单号:'+order.orderNo" :value="'¥'+ order.orderAmountTotal" :label="order.orderStatus" style="&#45;&#45;van-cell-background:#f5f5f5;" />-->
<!--    </van-cell-group>-->
  </div>
  <div style="margin: auto">  <van-pagination style="position: fixed;bottom:80px;width: 100%;"
                                              v-model="currentPage"
                                              :total-items="125"
                                              :show-page-size="6"
                                              force-ellipses
  /></div>

</template>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import axios from "@/utils/request";

const orderList = ref([])
const activeNames = ref(['1']);
const currentPage = ref();
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
      console.log(orderList.value);

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