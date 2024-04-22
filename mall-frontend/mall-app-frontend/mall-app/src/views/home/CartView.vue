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
      <van-row>
        <van-col span="19">
          购物数量 {{ selectCount }}
        </van-col>
        <van-col span="5">
          <button v-show="!show" @click="show=true">管理</button>
          <button v-show="show" @click="show=false">取消</button>
        </van-col>
      </van-row>
    </div>
  </div>


  <!--<van-checkbox-group v-model="checked">-->
  <div style="padding-bottom: 80px;padding-top: 150px;z-index: -1;">
    <div v-for="(products, index) in cartProducts" :key="index" style="margin-left:10px;">
      <van-checkbox :name="products.id" :label="products.id" v-model="products.tbProductChecked"
                    @change="change(products.tbProductSpecId, products.tbProductChecked)"
                    style="margin-bottom: 4px;" label-disabled>
        <van-card
            style="width: 330px;"
            :num="products.amount"
            :price="products.price"
            :desc="products.tbProductName"
            :title="products.specsName"
            :thumb="BASE_URL + products.imgUrl">
          <template #footer>
            <van-stepper v-model="products.amount" theme="round" button-size="22"
                         @change="change(products.tbProductSpecId, products.tbProductChecked)"
                         disable-input/>
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
const selectCount = ref(0);
//是否选择全部商品
const allChecked = ref();

const loadContents = () => {
  axios.get("mall/cart/get").then((response) => {
    if (response.data.state == 20000) {
      cartProducts.value = response.data.data;
    }
  })
};

const loadDetails = (showAllChecked) => {
  axios.get("mall/cart/total").then((response) => {
    if (response.data.state == 20000) {
      totalPrice.value = response.data.data.totalPrice * 100;
      selectCount.value = response.data.data.totalAmount;
      if (showAllChecked) {
        allChecked.value = response.data.data.allChecked;
      }
    }
  })
}

onMounted(() => {
  loadContents();
  loadDetails(true);
})

//是否选择全部商品
const selectAll = (signal) => {
  axios.get("mall/cart/allChecked/" + signal).then((response) => {
    if (response.data.state == 20000) {
      loadContents();
      loadDetails(false);
    }
  })

  // if (signal == true) {
  //   for (let i = 0; i < cartProducts.value.length; i++) {
  //     cartProducts.value[i].tbProductChecked = true;
  //   }
  // } else {
  //   for (let i = 0; i < cartProducts.value.length; i++) {
  //     cartProducts.value[i].tbProductChecked = false;
  //   }
  // }
}

//商品选择状态改变
const change = (tbProductSpecId, tbProductChecked) => {

  axios.post("mall/cart/modify_checked/" + tbProductSpecId + "/" + tbProductChecked).then((response) => {
    if (response.data.state == 20000) {
      loadContents();
      loadDetails(true);
    }
  })
  // totalPrice.value = 0;
  // selectCount.value = 0;
  // allChecked.value = true;
  // for (let i = 0; i < cartProducts.value.length; i++) {
  //   if (cartProducts.value[i].tbProductChecked == true) {
  //     totalPrice.value += cartProducts.value[i].price * cartProducts.value[i].amount * 100;
  //     selectCount.value += cartProducts.value[i].amount;
  //   } else {
  //     allChecked.value = false;
  //   }
  // }
}

//删除按钮
const del = () => {
  let saveList = [];
  for (let i = 0; i < cartProducts.value.length; i++) {
    if (cartProducts.value[i].tbProductChecked == false) {
      saveList.push(cartProducts.value[i])
    }
  }
  if (saveList != cartProducts.value) {
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
