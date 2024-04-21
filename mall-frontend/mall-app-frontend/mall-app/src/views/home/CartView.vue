<template>
  <div style="height: 150px;position: fixed;top:0;width: 100%;z-index: 1;background-color: #fff;">
    <h2 style="text-align: center">购物车</h2>
    <van-row style="color: darkgrey; font-size: 14px;margin-top: 10px; text-align: center">
      <van-col :span="8">
        <van-icon name="passed"/>
        100%正品保证
      </van-col>
      <van-col :span="10">
        <van-icon name="passed"/>
        所有商品精挑细选
      </van-col>
      <van-col :span="6">
        <van-icon name="passed"/>
        售后无忧
      </van-col>
    </van-row>
    <div style="margin: 20px 0 20px 20px;">
    购物数量 {{ selectCount }}
    <button v-show="!show" style="margin-left: 220px;" @click="show=true">管理</button>
    <button v-show="show" style="margin-left: 220px;" @click="show=false">取消</button>
    </div>
  </div>


  <!--<van-checkbox-group v-model="checked">-->
  <div style="padding-bottom: 80px;padding-top: 150px;z-index: -1;">
    <div v-for="(products, index) in cartProducts" :key="index" style="margin-left:10px;">
      <van-checkbox :name="products.id" :label="products.id" v-model="products.tbProductChecked" @change="change(index)"
                    style="margin-bottom: 4px;" label-disabled>
        <van-card
            style="width: 330px;"
            :num="products.amount"
            :price="products.price"
            :desc="products.tbProductName"
            :title="products.specsName"
            :thumb="BASE_URL + products.imgUrl">
          <template #footer>
            <van-stepper v-model="products.amount" theme="round" button-size="22" @change="change(index)" disable-input/>
          </template>
        </van-card>
      </van-checkbox>
    </div>
  </div>

  <!-- </van-checkbox-group>-->

  <van-submit-bar style="margin-bottom: 80px;"
                  :price="totalPrice"
                  button-text="提交订单" @submit="onSubmit">
    <van-checkbox v-model="allChecked" @toggle="selectAll(allChecked)">全选({{ selectCount }})</van-checkbox>
  </van-submit-bar>
  <van-submit-bar v-show="show"
                  style="margin-bottom: 80px;"
                  button-text="删除" @submit="del">
    <van-checkbox style="margin-right: 180px;width: 100px;" v-model="allChecked" @toggle="selectAll(allChecked)">
      全选({{ selectCount }})
    </van-checkbox>
  </van-submit-bar>

</template>

<script setup>
import {onMounted, ref} from "vue";
import router from "@/router";
import axios from "@/utils/request";

const show = ref(false);

const cartProducts = ref([])
// const cartProducts = ref([{
//   id: 1,
//   num: 2,
//   price: 13.9,
//   desc: '规格:300g/2份起售',
//   title: '五花肉 300g/份',
//   imageUrl: '/imgs/detail/detail1.jpg',
//   checked: false
// },
//   {
//     id: 2,
//     num: 3,
//     price: 49.6,
//     desc: '规格:500g/2份起售',
//     title: '牛扒 500g/份',
//     imageUrl: '/imgs/product/product5.png',
//     checked: false
//   },
//   {
//     id: 3,
//     num: 2,
//     price: 13.9,
//     desc: '规格:300g/2份起售',
//     title: '五花肉 300g/份',
//     imageUrl: '/imgs/detail/detail1.jpg',
//     checked: false
//   }]);
//购物车总价
const totalPrice = ref(0.00);
//商品数量
const selectCount = ref(7);

onMounted(()=>{
  axios.get("mall/cart/get").then((response)=>{
    if(response.data.state==20000){
      cartProducts.value = response.data.data;
    }
  })

  axios.get("mall/cart/totalPrice").then((response)=>{
    if(response.data.state==20000){
      totalPrice.value = response.data.data.productAmountTotal*100;
    }
  })
})


//是否选择全部商品
const allChecked = ref(false);

//是否选择全部商品
const selectAll = (signal) => {
  if (signal == true) {
    for (let i = 0; i < cartProducts.value.length; i++) {
      cartProducts.value[i].tbProductChecked = true;
    }
  } else {
    for (let i = 0; i < cartProducts.value.length; i++) {
      cartProducts.value[i].tbProductChecked = false;
    }
  }
}

//商品选择状态改变
const change = () => {
  totalPrice.value = 0;
  selectCount.value = 0;
  allChecked.value = true;
  for (let i = 0; i < cartProducts.value.length; i++) {
    if (cartProducts.value[i].tbProductChecked == true) {
      totalPrice.value += cartProducts.value[i].price * cartProducts.value[i].amount * 100;
      selectCount.value += cartProducts.value[i].amount;
    } else {
      allChecked.value = false;
    }
  }
}

//删除按钮
const del = () => {
  let saveList = [];
  for (let i = 0; i < cartProducts.value.length; i++) {
    if (cartProducts.value[i].tbProductChecked == false) {
      saveList.push(cartProducts.value[i])
    }
  }
  if (saveList != cartProducts.value){
    cartProducts.value = saveList;
    change();
  }
}

const onSubmit = () => {
  router.push("/payment");
}

</script>

<style scoped>

</style>
