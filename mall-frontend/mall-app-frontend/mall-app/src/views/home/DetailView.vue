<template>
  <!--  顶部栏-->
  <div>
    <van-row style="position: fixed;width: 390px;background-color: white;">
      <van-col span="3">
        <van-icon name="arrow-left" size="25" @click="router.back()" style="margin-top: 13px;"/>
      </van-col>
      <van-col span="19">
        <div style="line-height: 50px;font-weight: bold;font-size: 18px;margin-left: 8px;">{{ content.specsName }}</div>
      </van-col>
      <!--      <van-col span="3">-->
      <!--        <van-icon name="guide-o"  size="25" style="margin-top: 13px;"/>-->
      <!--      </van-col>-->
      <!--      <van-col span="2">-->
      <!--        <van-icon name="circle" size="25" style="margin-top: 13px;"/>-->
      <!--      </van-col>-->
    </van-row>
    <img :src="BASE_URL+content.imgUrl" style="width:390px;">
    <van-row>
      <!--    价格-->
      <van-col span="17">
        <div style="background-color: darkseagreen;color: azure; line-height: 50px;">
          <b style="font-size: 25px; margin-left: 20px;">{{ content.currentPrice }}</b><s> 零售价：￥{{
            content.originalPrice
          }}</s>
        </div>
      </van-col>
      <van-col span="7">
        <div style="padding: 5px 0 0 10px;">
          <p style="font-size: 10px;padding:0 0 5px 15px;">据结束仅剩</p>
          <van-count-down :time="discountsTime">
            <template #default="timeData">
              <span class="block">{{ timeData.hours }}</span>
              <span class="colon">:</span>
              <span class="block">{{ timeData.minutes }}</span>
              <span class="colon">:</span>
              <span class="block">{{ timeData.seconds }}</span>
            </template>
          </van-count-down>
        </div>
      </van-col>
    </van-row>
    <!--标题-->
    <van-row style="margin: 10px 0 0 15px;">
      <van-col :span="21">
        <p style="line-height: 30px;font-size: 21px;">{{ content.specsName }}</p>
      </van-col>
      <van-col :span="3">
        <van-icon name="share-o" size="25"/>
      </van-col>
    </van-row>
    <!---->
    <van-row style="color: darkgrey; font-size: 16px;margin: 10px 0 0 15px;">
      <van-col :span="8">货号:{{ content.id }}</van-col>
      <van-col :span="8">库存：{{ content.amount }}</van-col>
      <van-col :span="8">销量：{{ content.sales }}</van-col>
    </van-row>

    <div style="margin-left: 15px;">
      <!-- 取货时间-->
      <van-row style="margin-top: 30px; line-height:100%;color: darkgrey">
        <van-col :span="6">取货时间</van-col>
        <van-col :span="18">
          <p style="width: 225px;font-size: 10px;border: 1px solid red;color: firebrick">佛山市区内次日送达，市外以物流配送时间为准。</p>
        </van-col>
      </van-row>

      <!-- 优惠券单元格 -->
      <van-coupon-cell
          :coupons="coupons"
          :chosen-coupon="chosenCoupon"
          @click="showList=true"
          style="margin-top: 30px;color: darkgrey;font-size: 17px;"
      />
      <!-- 优惠券列表 -->
      <van-popup v-model:show="showList"
                 round
                 position="bottom"
                 style="height: 70%; padding-top: 4px;">
        <van-coupon-list
            :coupons="coupons"
            :chosen-coupon="chosenCoupon"
            :disabled-coupons="disabledCoupons"
            @change="onChange"
            @exchange="onExchange"
        />
      </van-popup>

      <!-- 已选择：-->
      <van-row style="color: darkgrey; font-size: 16px;margin-top: 30px;">
        <van-col :span="6">已选择：</van-col>
        <van-col :span="16">1{{ content.unit }}起售
          <b style="font-weight: lighter;margin-left: 130px;color: #2c3e50;">{{ num }}{{ content.unit }}</b></van-col>
        <van-col :span="2">
          <van-cell is-link @click="show = true"/>
        </van-col>
      </van-row>
      <!-- 选择购物车弹出框-->
      <van-popup v-model:show="showCart" position="bottom" style="height: 40%;">
        <van-row style=" margin-left: 15px;">
          <van-col :span="6">
            <img :src="BASE_URL + content.imgUrl" style="width:80px;margin-top: 10px;"></van-col>
          <van-col :span="18">
            <p style="margin: 20px 0 20px; font-weight: bold;font-size: 18px;">{{ content.specsName }}</p>
            <b style="color: #ff2221;font-size: 20px;">￥{{ content.currentPrice }} </b> 库存{{ content.amount }}
          </van-col>
        </van-row>
        <div style="width: 80px;margin: 20px 0 0 15px;">
          规格<br>
          <van-button type="danger" style="width: 60px;">{{ content.unit }}</van-button>
        </div>
        <div style="width: 80px;margin: 20px 0 0 15px;">
          数量<br>
          <table style="width: 100px;">
            <tr>
              <td>
                <button style="font-size: large; width: 25px;" @click="subtract">-</button>
              </td>
              <td>{{ num }}</td>
              <td>
                <button style="font-size: large; width: 25px;" @click="add">+</button>
              </td>
            </tr>
          </table>
        </div>
        <div style="margin: 15px 20px 0px 20px;">
          <van-button color="#D54431" style="width: 100%;height: 35px;font-size: 18px;border-radius: 50px;"
                      @click="addCart()">加入购物车
          </van-button>
        </div>

        <!--展示立即购买弹出框-->
      </van-popup>
      <van-popup v-model:show="showBuy" position="bottom" style="height: 40%;">
        <van-row style=" margin-left: 15px;">
          <van-col :span="6">
            <img :src="BASE_URL + content.imgUrl" style="width:80px;margin-top: 10px;"></van-col>
          <van-col :span="18">
            <p style="margin: 20px 0 20px; font-weight: bold;font-size: 18px;">{{ content.specsName }}</p>
            <b style="color: #ff2221;font-size: 20px;">￥{{ content.currentPrice }} </b> 库存{{ content.amount }}
          </van-col>
        </van-row>
        <div style="width: 80px;margin: 20px 0 0 15px;">
          规格<br>
          <van-button type="danger" style="width: 60px;">{{ content.unit }}</van-button>
        </div>
        <div style="width: 80px;margin: 20px 0 0 15px;">
          数量<br>
          <table style="width: 100px;">
            <tr>
              <td>
                <button style="font-size: large; width: 25px;" @click="subtract">-</button>
              </td>
              <td>{{ num }}</td>
              <td>
                <button style="font-size: large; width: 25px;" @click="add">+</button>
              </td>
            </tr>
          </table>
        </div>
        <div style="margin: 15px 20px 0px 20px;">
          <van-button color="#D54431" style="width: 100%;height: 35px;font-size: 18px;border-radius: 50px;">立即购买
          </van-button>
        </div>
      </van-popup>
    </div>

    <!--  商品介绍  -->
    <div style="margin-top:30px;height: 30px;line-height: 30px;background-color: darkseagreen;text-align: center;">
      商品介绍
    </div>
    <!--    <img :src="content.detailImgUrl" style="width:395px;">-->

  </div>

  <div>
    <van-tabbar route>
      <!--  底部栏-->
      <van-action-bar position="bottom" style="height: 80px;">
        <van-action-bar-icon icon="chat-o" text="客服"/>
        <van-action-bar-icon icon="cart-o" text="购物车" @click="router.push('/cart')"/>
        <van-action-bar-icon icon="shop-o" text="店铺"/>
        <van-action-bar-button type="warning" text="加入购物车" @click="showCart=true"/>
        <van-action-bar-button type="danger" text="立即购买" @click="showBuy=true"/>
      </van-action-bar>
    </van-tabbar>
  </div>


</template>

<script setup>
import {onMounted, ref} from "vue";
import router from "@/router";
import axios from "@/utils/request";
import {showToast} from "vant";

const content = ref({imgUrl: ''});
const discountsTime = ref(30 * 60 * 60 * 1000);
// const content = ref({
//   title: '五花肉 300g/份', imgUrl: '/imgs/detail/detail1.jpg',
//   newPrice: 13.9, oldPrice: 18.2, goodsId: '0169',
//   count: 24, salesVolume: 1812, discountsTime: 30 * 60 * 60 * 1000,
//   detailImgUrl: "/imgs/detail/detail1-1.jpg"
// });

const num = ref(1);
const subtract = () => {
  if (num.value > 1) {
    num.value = num.value - 1;
  }
}
const add = () => {
  num.value = num.value + 1;
}

const coupon = {
  available: 1,
  condition: '无门槛\n最多优惠12元',
  reason: '',
  value: 150,
  name: '优惠券名称',
  startAt: 1489104000,
  endAt: 1514592000,
  valueDesc: '1.5',
  unitDesc: '元',
};
const coupons = ref([coupon]);
const showList = ref(false);
const chosenCoupon = ref(-1);
const onChange = (index) => {
  showList.value = false;
  chosenCoupon.value = index;
};
const onExchange = (code) => {
  coupons.value.push(coupon);
};


const showCart = ref(false);
const showBuy = ref(false);


onMounted(() => {
  let id = new URLSearchParams(location.search).get('id');

  axios.get("/mall/product_specs/" + id).then((response) => {
    if (response.data.state == 20000) {
      content.value = response.data.data;
    }
  })
})

const addCart = () => {
  showToast({
    message: '<div style="font-size: 20px;margin: 20px;">' +
        '<div style="margin: 10px auto;text-align: center;"><span class="van-icon van-icon-passed" style="color:#13DEA5;"></span></div>' +
        '<div style="text-align: center;">加入购物车成功</div></div>',
    type: 'html',
    overlay: true,
    duration: 1500,
    'close-on-click-overlay': true
  })
  setTimeout(() => {
    showCart.value = false;
  }, 100);
}

</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.colon {
  display: inline-block;
  margin: 3px 4px;
  color: darkseagreen;
}

.block {
  display: inline-block;
  width: 22px;
  color: #fff;
  font-size: 12px;
  text-align: center;
  background-color: darkseagreen;
}

</style>
