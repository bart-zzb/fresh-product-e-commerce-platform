<template>
  <!--顶部栏-->
  <div style="position: fixed; top:0px;width: 100%;z-index: 1;">
    <van-nav-bar class="nav"
                 style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;background-color:#D54431;"
                 title="我的订单"
                 left-text="返回"
                 left-arrow
                 @click-left="onBack"/>
  </div>
  <div style="margin-top:65px;padding:10px;">

    <van-tabs v-model:active="active" swipeable @change="tabChange">
      <van-tab v-for="orderItems in orderStatusList" :title="orderItems.status">
        <div style="margin-left: 20px;">共{{ orderItems.orderList.length }}条订单</div>
        <van-empty :description="'亲，当前没有'+orderItems.status+'的订单'" v-show="orderItems.emptyShow"/>
        <van-collapse v-model="orderItems.activeNames" v-for="(order,index) in orderItems.orderList">
          <van-collapse-item :name="index+1" value="订单详情"
                             style="margin-top: 10px;--van-collapse-item-content-background: #f5f5f5;white-space: pre-wrap">
            <template #icon>
              <van-icon name="balance-list-o" color="orange"/>
            </template>
            <template #title>
              <div style="color: #1989FA;font-size: 15px;">{{ order.orderStatus }}</div>
              <div style="font-size:12px;">时间: {{ order.modifiedTime }}</div>
              <div style="font-size: 12px;">总金额: ¥{{ order.orderAmountTotal }}</div>
              <div style="font-size: 12px;">商品数量: {{ order.orderItemsVOS.length }}</div>
            </template>
            <div style="margin-left: 25px;">
              <div style="margin-left: 15px;font-size: 12px;">订单编号:{{ order.orderNo }}</div>
              <div style="margin-left: 15px;font-size: 12px;">收货人:{{ order.consignee }}</div>
              <div style="margin-left: 15px;font-size: 12px;">收货人手机号:{{ order.consigneePhone }}</div>
              <div style="margin-left: 15px;font-size: 12px;">收货人地址:{{ order.consigneeAddress }}</div>
              <div style="margin-left: 15px;font-size: 12px;">商品详情:</div>
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
            </div>

          </van-collapse-item>
        </van-collapse>
      </van-tab>
    </van-tabs>
  </div>

</template>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import axios from "@/utils/request";

const active = ref(0);

const orderStatusList = ref([
  //activeNames表示当前默认展开哪个订单详情
  {status: '全部', orderList: [], emptyShow: true, activeNames: [1]},
  {status: '已支付', orderList: [], emptyShow: true, activeNames: [1]},
  {status: '已发货', orderList: [], emptyShow: true, activeNames: [1]},
  {status: '待收货', orderList: [], emptyShow: true, activeNames: [1]},
  {status: '待评价', orderList: [], emptyShow: true, activeNames: [1]},
  {status: '已完成', orderList: [], emptyShow: true, activeNames: [1]}]);

const tabChange = () => {
  if (active.value == 0) {
    loadAllContent();
  }
  loadContent(active.value);
}

const onBack = () => {
  let redirectPath = localStorage.getItem('redirectPath');
  //跳转上一页
  if (redirectPath) {
    localStorage.removeItem('redirectPath');
    router.push(redirectPath);
  }
}

const loadAllContent = () => {
  axios.get("mall/order/select/all").then((response) => {
    if (response.data.state == 20000) {
      orderStatusList.value[0].orderList = response.data.data;
      //判断是否展示空页面状态
      if (orderStatusList.value[0].orderList.length != 0) {
        orderStatusList.value[0].emptyShow = false;
      }
    }
  })
}

const loadContent = (index) => {
  axios.get("mall/order/select/order_status?status=" + index).then((response) => {
    if (response.data.state == 20000) {
      orderStatusList.value[index].orderList = response.data.data;
      //判断是否展示空页面状态
      if (orderStatusList.value[index].orderList.length != 0) {
        orderStatusList.value[index].emptyShow = false;
      }
    }
  })
}

onMounted(() => {
  //展示全部订单页面
  if (active.value == 0) {
    loadAllContent()
    //展示其他订单状态的页面
  } else {
    loadContent(active.value);
  }
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

.van-icon {
  font-size: 30px;
}
</style>