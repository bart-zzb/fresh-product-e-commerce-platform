<template>

  <div style="background-color:#F5F5F5;height:100%;width:100%;position: absolute;top:0;">
    <!--顶部栏-->
    <div style="background-color:#D54431;height: 100px;position: absolute;top:0;width: 100%;">
      <van-nav-bar class="nav" style="margin:55px auto;background-color:#D54431;"
                   title="提交订单"
                   left-text="返回"
                   left-arrow
                   @click-left="onClickLeft"/>
    </div>

    <!--配送地址-->
    <div
        style="background:linear-gradient(90deg, #fff,#D54431);height: 140px;margin-top: 100px;padding:10px;border-bottom-left-radius: 15px;border-bottom-right-radius: 15px;">
      <van-tabs v-model:active="active" style="margin-top:10px;">
        <van-tab title="快递配送">
          <van-cell title="设置地址"
                    style="text-align: left;
                    background-color: #fff;
                    border-bottom-left-radius: 15px;
                    border-bottom-right-radius: 15px;
                    --van-cell-line-height:55px;
                    --van-cell-font-size:18px;
                    --van-cell-horizontal-padding:30px;
                    --van-cell-icon-size:20px;"
                    is-link/>
        </van-tab>
        <van-tab title="自提">
          <van-cell title="暂时不支持自提"
                    style="text-align: left;
                    background-color: #fff;
                    border-bottom-left-radius: 15px;
                    border-bottom-right-radius: 15px;
                    --van-cell-line-height:55px;
                    --van-cell-font-size:18px;
                    --van-cell-horizontal-padding:30px;
                    --van-cell-icon-size:20px;"
          />
        </van-tab>
      </van-tabs>
    </div>

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
        <van-row gutter="0">
          <van-col span="2" style="margin: auto 0px;padding-left:10px; ">
            <van-checkbox v-model="item.checked" @change="change(index)"></van-checkbox>
          </van-col>
          <van-col span="22" style="margin: auto 0">
            <van-card
                :num="item.productCount"
                :price="item.productUnitPrice"
                :desc="item.productDescription"
                :title="item.productName"
                class="goods-card"
                :thumb="item.productImgUrl"
            >
              <template #footer>
                <van-stepper v-model="item.productCount" theme="round" button-size="22" @change="change(index)"
                             disable-input/>
              </template>
            </van-card>
          </van-col>
        </van-row>

        <template #right>
          <van-button square text="删除" type="danger" class="delete-button" @click="del(index)"/>
        </template>
      </van-swipe-cell>

    </div>

    <!-- 优惠券模块 -->
    <div style="margin-top: 10px;background-color: #fff;border-top-left-radius: 15px;border-top-right-radius: 15px;padding-top: 4px;">
      <van-cell title="优惠券"
                style="text-align: left;
                --van-cell-font-size:18px;
                --van-cell-horizontal-padding:30px;
                --van-cell-icon-size:20px;"
                is-link/>

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

    </div>
  </div>


  <!-- 底部栏 -->
  <div
      style="position: absolute;bottom: 0;width: 100%;height: 80px;font-size: 22px;margin: auto;background-color: #fff;">
    <van-row gutter="10" style="margin: 10px 20px;">
      <van-col span="12">
        <p style="margin: 16px auto;">
          <span style="font-weight: bold;">合计:</span>
          <span style="color:#D54431;"> ¥ {{ order.totalPrice }}</span>
        </p>
      </van-col>
      <van-col span="12" style="margin:8px auto;">
        <van-button color="#D54431" style="width:100%;height: 45px;font-size: 20px;border-radius: 50px;">立即结算
        </van-button>
      </van-col>
    </van-row>
  </div>

</template>

<script setup>
import {ref} from "vue";

const onClickLeft = ref();

const order = ref({
  productCount: 2, totalPrice: 75.8 * 3 + 89.8 * 2, productList: [
    {
      checked: true,
      productName: "肥牛",
      productImgUrl: "/imgs/product/product6.png",
      productDescription: "500g/份",
      productUnitPrice: 75.8,
      productCount: 3,
      productTotalPrice: 75.8 * 3
    },
    {
      checked: true,
      productName: "吊龙",
      productImgUrl: "/imgs/product/product8.png",
      productDescription: "500g/份",
      productUnitPrice: 89.8,
      productCount: 2,
      productTotalPrice: 98.8 * 2
    },
  ]
})

const del = (index) => {
  order.value.productList[index].checked = false;
  order.value.productCount = order.value.productCount - 1;
  change(index);
  order.value.productList.splice(index, 1);
}

const change = (index) => {
  order.value.totalPrice = 0;
  for (let i = 0; i < order.value.productList.length; i++) {
    if (order.value.productList[i].checked == true) {
      order.value.totalPrice += order.value.productList[i].productUnitPrice * order.value.productList[i].productCount;
    }
  }
}

</script>

<style scoped>
* {
  text-align: center;
  --van-action-bar-height: 100px;
  --van-action-bar-icon-font-size: 20px;
  --van-tab-font-size: 18px;
  --van-tab-line-height: 30px;
}

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
    ::v-deep .van-cell__title{
    font-size: 18px;
  }

.delete-button {
  height: 100%;
}
</style>