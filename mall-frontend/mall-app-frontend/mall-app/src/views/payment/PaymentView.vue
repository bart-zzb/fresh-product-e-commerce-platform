<template>
  <!--顶部栏-->
  <div style="background-color:#D54431;height: 60px;position: fixed;top:0;width: 100%;z-index: 1">
    <van-nav-bar class="nav" style="padding-top: 10px;background-color:#D54431;"
                 title="提交订单"
                 left-text="返回"
                 left-arrow
                 @click-left="onBack"/>
  </div>

  <!--配送地址-->
  <div
      style="background:linear-gradient(90deg, #fff,#D54431);height: 140px;margin-top:60px;padding:10px;border-bottom-left-radius: 15px;border-bottom-right-radius: 15px;">
    <van-tabs v-model:active="active" style="margin-top:10px;">
      <van-tab title="快递配送">
        <div style="height: 50px;background-color:#ffffff;padding-top: 25px;text-align: left; border-bottom-left-radius: 15px; border-bottom-right-radius: 15px">
          <van-row gutter="0" style="width: 100%;" @click="toAddress">
            <van-col span="20"><input type="text" placeholder="选择具体收货地址" :value="addressValue.addressDetail" style="border:none;outline: none;width: 100%;font-size: 16px;"></input></van-col>
            <van-col span="4" style="padding-top:2px;"><van-icon name="arrow"/></van-col>
          </van-row>
        </div>
<!--        <van-field-->
<!--            style="height: 75px;padding-top: 25px;text-align: left;-->
<!--                    border-bottom-left-radius: 15px;-->
<!--                   border-bottom-right-radius: 15px"-->
<!--            v-model="addressValue"-->
<!--            is-link-->
<!--            readonly-->
<!--            label="设置地址"-->
<!--            placeholder="选择具体收货地址"-->
<!--            @click="toAddress"-->
<!--        />-->

      </van-tab>
      <van-tab title="自提">
        <van-cell title="暂时不支持自提"
                  style="text-align: left;
                    background-color: #fff;
                    border-bottom-left-radius: 15px;
                    border-bottom-right-radius: 15px;
                    --van-cell-line-height:55px;
                    --van-cell-font-size:16px;
                    --van-cell-horizontal-padding:30px;
                    --van-cell-icon-size:16px;"
        />
      </van-tab>
    </van-tabs>
  </div>

  <div style="background-color:#F5F5F5;height:100%;width:100%;padding-bottom: 80px;">

    <!-- 商品信息 -->
    <div
        style="margin-top: 10px;background-color: #fff;border-top-left-radius: 15px;border-top-right-radius: 15px;padding-top: 4px;">
      <van-cell :title="'共'+ order.productCount+'件商品'"
                style="text-align: left;
                background-color: #fff;
                border-bottom-left-radius: 15px;
                border-bottom-right-radius: 15px;
                --van-cell-line-height:18px;
                --van-cell-font-size:18px;
                --van-cell-horizontal-padding:30px;
                --van-cell-icon-size:20px;"
      />

      <!--分割线 -->
      <van-divider
          :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0', margin:'0' }"
      />

      <van-swipe-cell v-for="(item,index) in order.productList">
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

    <!-- 优惠券模块 -->
    <div
        style="margin-top: 10px;background-color: #fff;border-top-left-radius: 15px;border-top-right-radius: 15px;padding-top: 4px;">
      <van-cell title="优惠券"
                style="text-align: left;
                --van-cell-font-size:18px;
                --van-cell-horizontal-padding:30px;
                --van-cell-icon-size:20px;"
                is-link/>

      <!-- 分割线 -->
      <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0', margin:'0' }"/>

      <!-- 快递费用 -->
      <van-cell title="快递费用"
                value="免运费"
                style="text-align: left;
                --van-cell-font-size:18px;
                --van-cell-horizontal-padding:30px;
                --van-cell-icon-size:20px;"
      />

      <!-- 分割线 -->
      <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0', margin:'0' }"/>

      <!-- 备注模块 -->
      <van-cell-group inset>
        <van-field
            v-model="message"
            rows="2"
            autosize
            label="备注"
            type="textarea"
            maxlength="50"
            placeholder="请输入备注"
            show-word-limit
        />
      </van-cell-group>

      <!-- 分割线 -->
      <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0', margin:'0' }"/>

      <van-cell title="支付方式"
                style="text-align: left;
                --van-cell-font-size:18px;
                --van-cell-horizontal-padding:30px;
                --van-cell-icon-size:20px;"
      />

      <!-- 支付模块 -->
      <van-radio-group v-model="payChecked" direction="horizontal" style="margin-left: 50px;padding-bottom: 10px;">
        <van-radio name="alipay">支付宝</van-radio>
        <van-radio name="wechatpay">微信支付</van-radio>
        <van-radio name="bankcard">余额:{{ balance }}</van-radio>
      </van-radio-group>

    </div>
  </div>


  <!-- 底部栏 -->
  <div
      style="position: fixed;bottom: 0;width: 100%;height: 80px;font-size: 22px;margin: auto;background-color: #fff;">
    <van-row gutter="10" style="margin: 10px 20px;">
      <van-col span="12">
        <p style="margin: 16px auto;">
          <span style="font-weight: bold;">合计:</span>
          <span style="color:#D54431;"> ¥ {{ order.totalPrice }}</span>
        </p>
      </van-col>
      <van-col span="12" style="margin:8px auto;">
        <van-button color="#D54431" style="width:100%;height: 45px;font-size: 20px;border-radius: 50px;"
                    @click="submit()">立即结算
        </van-button>
      </van-col>
    </van-row>
  </div>

</template>

<script setup>
import {onMounted, ref} from "vue";
import router from "@/router";
import axios from "@/utils/request";
import {showConfirmDialog, showFailToast, showSuccessToast} from "vant";
import qs from "qs";

const active = ref();
const columns = ref([]);
const addressValue = ref({});
const message = ref();

const order = ref({
  productCount: 0, totalPrice: 0, productList: []
})

onMounted(() => {
  let orderNo = new URLSearchParams(location.search).get('orderNo');
  axios.get("mall/order/select?orderNo=" + orderNo).then((response) => {
    if (response.data.state == 20000) {
      order.value.productList = response.data.data.orderItemsVOS;
      order.value.totalPrice = response.data.data.orderAmountTotal;
      order.value.productCount = order.value.productList.length;
    }
  })

  //获取当前用户默认地址表
  axios.get("admin/userAddress/get/defaultAddress").then((response) => {
    if (response.data.state == 20000) {
      if(response.data.data!=null){
        addressValue.value = response.data.data;
      }
    }
  })

  let userInfo = JSON.parse(localStorage.getItem("userInfo"));
  balance.value = userInfo.userBalance;
})

const onBack = () => {
  showConfirmDialog({
    title: '提示',
    message:
        '您确认退出订单支付页面吗？',
  })
      .then(() => {
        router.push("/cart");
      })
      .catch(() => {
        // on cancel
      });
}

const payChecked = ref("wechatpay");

const balance = ref();

const isEmpty = (value) => {
  switch (typeof value) {
    case 'string':
      return value.trim() === '';
    case 'undefined':
      return true;
    case 'object':
      if (Array.isArray(value)) {
        return value.length === 0;
      } else if (value === null) {
        return true;
      } else {
        for (let key in value) {
          if (value.hasOwnProperty(key)) {
            return false;
          }
        }
        return true;
      }
    default:
      return false;
  }
}

//提交订单按钮
const submit = () => {
  if (isEmpty(addressValue.value)) {
    showFailToast('未选择收货地址');
  } else {
    console.log(addressValue);
    let orderNo = new URLSearchParams(location.search).get('orderNo');
    //更新订单地址
    let orderUpdateConsigneeInfoDTO = {
      orderNo:orderNo,
      consignee:addressValue.value.receiver,
      consigneePhone:addressValue.value.contactPhone,
      consigneeAddress:addressValue.value.addressDetail,
    };
    let data = qs.stringify(orderUpdateConsigneeInfoDTO);
    axios.post("mall/order/update/consignee_info", data).then((response)=>{
      if (response.data.state==20000){
        //如果选择支付宝支付, 会在接口层异步调用时把订单状态和更新库存,更新销量实现
        if(payChecked.value=='alipay'){
        //支付宝沙箱支付
        window.open(BASE_URL+"alipay/pay?orderNo="+orderNo);
        router.push('/personal');
      }else{
        //更新订单状态
        let orderUpdatePaidDTO = {
          orderNo:orderNo,
          payChannel:1
        };
        let data = qs.stringify(orderUpdatePaidDTO);
        axios.post("mall/order/update/pay", data).then((response)=>{
          if (response.data.state==20000){
            //更新库存，更新销量
            // let productSpecDeleteDTOS = [];
            // for (let i = 0; i < order.value.productList.length; i++) {
            //   productSpecDeleteDTOS.push({
            //     tbProductSpecId: order.value.productList[i].tbProductSpecId,
            //     amount: order.value.productList[i].amount
            //   })
            // }
            // axios({
            //   method: "post",
            //   headers: {
            //     'Content-Type': 'application/json',
            //   },
            //   dataType: "json",
            //   data: JSON.stringify(productSpecDeleteDTOS),
            //   url: "mall/product_specs/modify"
            // }).then((response) => {
            //   if (response.data.state == 20000) {
                showSuccessToast('支付成功');
                setTimeout(() => {router.push('/personal');}, 1000);
          //     }
          //   })
          }
        })
      }
      }
    })
  }
}

const toAddress = () => {
  let redirectPath = localStorage.setItem('redirectPath', router.currentRoute.value.fullPath);
  router.push("/address");
}

</script>

<style scoped>
* {
  text-align: center;
  /*设置快递配送和自提的按钮样式*/
  --van-tab-font-size: 18px;
}

/*设置顶部栏样式*/
.nav {
  --van-nav-bar-title-text-color: #fff;
  --van-nav-bar-icon-color: #fff;
  --van-nav-bar-text-color: #fff;
  --van-nav-bar-title-font-size: 22px;
  --van-nav-bar-arrow-size: 20px;
}

.goods-card {
  margin: 0;
  background-color: #fff;
  text-align: left;
  --van-card-thumb-size: 100px;
  --van-card-price-integer-font-size: 18px;
  --van-card-price-font-size: 16px;
  --van-card-font-size: 16px;
  --van-card-price-color: #D54431;
  --van-card-title-line-height: 25px;
}

/*修改cell所有label的font-size*/
.van-cell
::v-deep .van-cell__title {
  font-size: 18px;
}

</style>