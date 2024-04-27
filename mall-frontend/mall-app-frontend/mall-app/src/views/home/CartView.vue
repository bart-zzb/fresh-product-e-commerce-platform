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
  <div style="padding-bottom: 80px;padding-top: 150px;z-index: -1;" v-show="emptyShow">
    <van-empty description="亲，当前购物车没有任何商品" />
  </div>

  <!--<van-checkbox-group v-model="checked">-->
  <div style="padding-bottom: 80px;padding-top: 150px;z-index: -1;">
    <div v-for="(products, index) in cartProducts" :key="index" style="margin-left:10px;">
      <van-checkbox :name="products.id" :label="products.id" v-model="products.tbProductChecked"
                    @change="changeChecked(products.tbProductSpecId, products.tbProductChecked)"
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
                         @change="changeAmount(products.tbProductSpecId, products.amount)"
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
                  button-text="删除" @submit="del()">
    <van-checkbox style="margin-right: 180px;width: 100px;" v-model="allChecked" @toggle="selectAll(allChecked)">
      全选({{ selectCount }})
    </van-checkbox>
  </van-submit-bar>

</template>

<script setup>
import {onMounted, ref} from "vue";
import router from "@/router";
import axios from "@/utils/request";
import {showToast} from "vant";

const show = ref(false);

const cartProducts = ref([])

//购物车总价
const totalPrice = ref(0.00);
//商品数量
const selectCount = ref(0);
//是否选择全部商品
const allChecked = ref(false);

const emptyShow = ref(false);

const loadContents = () => {
  axios.get("mall/cart/get").then((response) => {
    if (response.data.state == 20000) {
      cartProducts.value = response.data.data;
      //判断是否展示空购物车页面
      if(cartProducts.value.length==0){
        emptyShow.value=true;
      }else{
        emptyShow.value=false
      }
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
}

//商品选择状态改变
const changeChecked = (tbProductSpecId, tbProductChecked) => {
  let checked = 0
  if (tbProductChecked == true) {
    checked = 1
  }
  axios.post("mall/cart/modify_checked/" + tbProductSpecId + "/" + checked).then((response) => {
    if (response.data.state == 20000) {
      loadContents();
      loadDetails(true);
    }
  })
}

//商品数量改变
const changeAmount = (tbProductSpecId, amount) => {
  axios.post("mall/cart/modify_amount/" + tbProductSpecId + "/" + amount).then((response) => {
    if (response.data.state == 20000) {
      loadContents();
      loadDetails(true);
    }
  })
}

//删除按钮
const del = () => {
  axios.post("mall/cart/delete").then((response) => {
    if (response.data.state == 20000) {
      loadContents();
      loadDetails(true);
    }
  })
}

const onSubmit = () => {
  axios.get("mall/cart/get/allChecked").then((response) => {
    if (response.data.state == 20000) {
      //获取选中商品购物车
      let currentCartList = response.data.data;
      // 查看是否选中任何商品
      if (currentCartList.length == 0) {
        showToast({
          message: '<div style="font-size: 20px;margin: 20px;">' +
              '<div style="margin: 10px auto;text-align: center;"><span class="van-icon van-icon-fail" style="color:#13DEA5;"></span></div>' +
              '<div style="text-align: center;">未选择任何商品</div></div>',
          type: 'html',
          overlay: true,
          duration: 1500,
          'close-on-click-overlay': true
        })
      } else {
        //删除选中购物车商品
        axios.post("mall/cart/delete").then((response) => {
          if (response.data.state == 20000) {
            //封装orderItemsAddDTOS
            let orderItemsAddDTOS = [];
            for (let i = 0; i < currentCartList.length; i++) {
              orderItemsAddDTOS.push({
                tbProductSpecId: currentCartList[i].tbProductSpecId,
                amount: currentCartList[i].amount
              })
            }
            //发送请求
            axios({
              method: "post",
              headers: {
                'Content-Type': 'application/json',
              },
              dataType: "json",
              data: JSON.stringify(orderItemsAddDTOS),
              url: "mall/order/add"
            }).then((response) => {
              if (response.data.state == 20000) {
                //获取订单编号
                let orderNo = response.data.data.orderNo;
                router.push("/payment?orderNo=" + orderNo)
              }
            });
          }
        })
      }
    }
  })

}

</script>

<style scoped>

</style>
