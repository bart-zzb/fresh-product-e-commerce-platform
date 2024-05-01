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
    <div style="margin-left: 20px;">共{{ orderList.length }}条订单</div>
    <van-empty description="亲，当前购物车没有任何商品" v-show="emptyShow"/>
    <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="onLoad"
    >
      <van-collapse v-model="activeNames" v-for="(order,index) in orderList">
        <van-collapse-item :name="index+1" value="订单详情"
                           style="margin-top: 10px;--van-collapse-item-content-background: #f5f5f5;white-space: pre-wrap">
          <template #icon>
            <van-icon name="balance-list-o" color="orange"/>
          </template>
          <template #title>
            <div style="color: #1989FA;">{{ order.orderStatus }}</div>
            <div>订单时间:</div>
            <div>总金额: ¥{{ order.orderAmountTotal }}</div>
            <div>商品数量: {{ order.amount }}</div>
          </template>
          <div style="margin-left: 25px;">
            <span style="margin-left: 15px;font-size: 12px;">订单编号:{{ order.orderNo }}</span>
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
    </van-list>

  </div>

</template>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import axios from "@/utils/request";

const orderList = ref([])
const activeNames = ref([1]);
const emptyShow = ref(true);

const onBack = () => {
  let redirectPath = localStorage.getItem('redirectPath');
  //跳转上一页
  if (redirectPath) {
    localStorage.removeItem('redirectPath');
    router.push(redirectPath);
  }
}

onMounted(() => {
  axios.get("mall/order/select/all").then((response) => {
    if (response.data.state == 20000) {
      orderList.value = response.data.data;
      if (orderList.value.length != 0) {
        emptyShow.value = false;
      }
    }
  })
})

const list = ref([]);
const loading = ref(false);
const finished = ref(false);
const onLoad = () => {
  // 异步更新数据
  // setTimeout 仅做示例，真实场景中一般为 ajax 请求
  setTimeout(() => {
    for (let i = 0; i < 10; i++) {
      list.value.push(list.value.length + 1);
    }

    // 加载状态结束
    loading.value = false;

    // 数据全部加载完成
    if (list.value.length >= 40) {
      finished.value = true;
    }
  }, 1000);
}
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