<template>
  <!--标题和搜索栏-->
  <div style="height: 55px;position: fixed;top:0;width: 100%;z-index: 1;background-color: #fff;">
    <van-row>
      <van-col span="6">
        <div style="line-height: 50px;font-weight: bold;font-size: 22px;color: #1989FA;margin-left: 8px;">同城生鲜</div>
      </van-col>
      <van-col span="18">
        <van-search v-model="value" placeholder="请输入搜索关键词"/>
      </van-col>
    </van-row>
  </div>


  <!--轮播图管理-->
  <van-row style="padding-top: 55px;">
    <van-col span="24">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item v-for="item in carouselList"><img :src="BASE_URL + item.imgUrl" width="390px;"></van-swipe-item>
      </van-swipe>
    </van-col>
  </van-row>

  <!--用户轮播信息-->
  <van-swipe style="height: 24px;" :autoplay="2500" :show-indicators="false" vertical>
    <van-swipe-item v-for="item in orderList" style="height: 24px;margin:0px;">
      <p style="margin:0px 12px;"><span
          style="color: red;border: 1px solid red;border-radius: 8px;">&nbsp{{ item.time }}&nbsp</span>
        {{ item.phone }} 购买了 {{ item.amount }} 份 {{ item.productName }} {{ item.specName }}</p>
    </van-swipe-item>
  </van-swipe>

  <!--图标分类-->
  <van-row>
    <van-col v-for="item in labelList" span="6" style="margin: 10px auto;">
      <router-link class="rl" :to="'/list?id='+item.id">
        <img :src="BASE_URL + item.imgUrl" style="width: 45px;">
        <div>{{ item.name }}</div>
      </router-link>
    </van-col>
  </van-row>

  <van-divider style="margin: 6px auto;"/>

  <!--平台轮播信息-->
  <van-row>
    <van-col span="7">
      <div style="font-weight: bold;font-size: 18px;margin: 0px auto;height: 24px;color: red"><i>平台公告</i></div>
    </van-col>
    <van-col span="2">
      <van-divider vertical :hairline="false" style="height: 26px;"/>
    </van-col>
    <van-col span="15">
      <van-swipe style="height: 24px;" :autoplay="2500" :show-indicators="false" vertical>
        <van-swipe-item v-for="item in messageList">
          <p style="text-align:left;line-height: 26px;margin: 0px;"><span
              style="color: red;border: 1px solid red;border-radius: 8px;">&nbsp最新&nbsp</span>
            {{ item.message }}
          </p>
        </van-swipe-item>
      </van-swipe>
    </van-col>
  </van-row>

  <!--横幅1-->
  <van-row>
    <van-col span="24">
      <img :src="BASE_URL + banner1.imgUrl" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示1-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList1">
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <router-link class="rl" :to="'/detail?id='+item.id">
            <van-image :src="BASE_URL + item.imgUrl"></van-image>
            <p style="margin: 2px;">{{ item.specsName }}</p>
            <van-row>
              <van-col span="14">
                <div style="color: #f00;margin:0;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                <div style="margin: 0;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
              </van-col>
              <van-col span="10">
                <van-icon name="cart-o" color="#f00" size="30"/>
              </van-col>
            </van-row>
          </router-link>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>


  <!--横幅2-->
  <van-row>
    <van-col span="24">
      <img :src="BASE_URL + banner2.imgUrl" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示2-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList2">
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <router-link class="rl" :to="'/detail?id='+item.id">
            <van-image :src="BASE_URL + item.imgUrl"></van-image>
            <p style="margin: 2px;">{{ item.specsName }}</p>
            <van-row>
              <van-col span="14">
                <div style="color: #f00;margin:0;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                <div style="margin: 0;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
              </van-col>
              <van-col span="10" style="margin-top: 10px;">
                <van-icon name="cart-o" color="#f00" size="30"/>
              </van-col>
            </van-row>
          </router-link>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>

  <!--横幅3-->
  <van-row>
    <van-col span="24">
      <img :src="BASE_URL + banner3.imgUrl" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示3-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList3">
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <router-link class="rl" :to="'/detail?id='+item.id">
            <van-image :src="BASE_URL + item.imgUrl"></van-image>
            <p style="margin: 2px;">{{ item.specsName }}</p>
            <van-row>
              <van-col span="14">
                <div style="color: #f00;margin:0;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                <div style="margin: 0;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
              </van-col>
              <van-col span="10">
                <van-icon name="cart-o" color="#f00" size="30"/>
              </van-col>
            </van-row>
          </router-link>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>

  <!--横幅4-->
  <van-row>
    <van-col span="24">
      <img :src="BASE_URL + banner4.imgUrl" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示4-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList4">
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <router-link class="rl" :to="'/detail?id='+item.id">
            <van-image :src="BASE_URL + item.imgUrl"></van-image>
            <p style="margin: 2px;">{{ item.specsName }}</p>
            <van-row>
              <van-col span="14">
                <div style="color: #f00;margin:0;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                <div style="margin: 0;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
              </van-col>
              <van-col span="10">
                <van-icon name="cart-o" color="#f00" size="30"/>
              </van-col>
            </van-row>
          </router-link>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>

  <!--横幅5-->
  <van-row>
    <van-col span="24">
      <img :src="BASE_URL + banner5.imgUrl" style="width:390px;">
    </van-col>
  </van-row>
  <!--商品展示5-->
  <van-swipe :show-indicators="false">
    <van-swipe-item v-for="list in productList5">
      <van-row gutter="4" style="margin: 2px 4px 20px;">
        <van-col span="8" v-for="item in list" style="height: 200px;">
          <!--商品卡片-->
          <router-link class="rl" :to="'/detail?id='+item.id">
            <van-image :src="BASE_URL + item.imgUrl"></van-image>
            <p style="margin: 2px;">{{ item.specsName }}</p>
            <van-row>
              <van-col span="14">
                <div style="color: #f00;margin:0;">{{ item.currentPrice }}&nbsp;/&nbsp;{{ item.unit }}</div>
                <div style="margin: 0;"><s>{{ item.originalPrice }}&nbsp;/&nbsp;{{ item.unit }}</s></div>
              </van-col>
              <van-col span="10">
                <van-icon name="cart-o" color="#f00" size="30"/>
              </van-col>
            </van-row>
          </router-link>
        </van-col>
      </van-row>
    </van-swipe-item>
  </van-swipe>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "@/utils/request";

const carouselList = ref([]);
const labelList = ref([]);
const bannerList = ref([]);

const banner1 = ref({imgUrl: ""});
const banner2 = ref({imgUrl: ""});
const banner3 = ref({imgUrl: ""});
const banner4 = ref({imgUrl: ""});
const banner5 = ref({imgUrl: ""});

const productList1 = ref([]);
const productList2 = ref([]);
const productList3 = ref([]);
const productList4 = ref([]);
const productList5 = ref([]);

onMounted(() => {
  axios.get("mall/carousel/index").then((response) => {
    if (response.data.state == 20000) {
      carouselList.value = response.data.data;
    }
  })

  axios.get("mall/label/index").then((response) => {
    if (response.data.state == 20000) {
      labelList.value = response.data.data;
    }
  })

  axios.get("mall/banner/index").then((response) => {
    if (response.data.state == 20000) {
      bannerList.value = response.data.data;
      banner1.value = bannerList.value[0];
      banner2.value = bannerList.value[1];
      banner3.value = bannerList.value[2];
      banner4.value = bannerList.value[3];
      banner5.value = bannerList.value[4];
    }
  })

  loadContents(7, productList1, 1, 3);
  loadContents(8, productList1, 1, 6);
  loadContents(19, productList2, 1, 9);
  loadContents(22, productList3, 1, 6);
  loadContents(9, productList4, 1, 3);
  loadContents(10, productList5, 1, 3);
  loadContents(11, productList5, 1, 3);

})

const orderList = ref([
  {time: "3时20分06秒", phone: "134****0318", amount: "1", productName: "猪扒(里脊)", specName: "300g/份"},
  {time: "0时31分32秒", phone: "137****173", amount: "2", productName: "牛肉", specName: "300g/份"},
  {time: "2时40分16秒", phone: "135****0345", amount: "4", productName: "牛展", specName: "500g/份"}
]);

const messageList = ref([
  {message: "购买须知"},
  {message: "下单流程"},
  {message: "每日截单时间为23:00, 23点前的订单,会于次日安排配送"},
  {message: "用户打开手机位置操作指引"}
]);

const loadContents = (categoryId, productList, pageNum, pageSize) => {
  axios.get("mall/product_specs/category/" + categoryId + "?pageNum=" + pageNum + "&pageSize=" + pageSize).then((response) => {
    if (response.data.state == 20000) {
      //重新分组
      let list = chunkArray(response.data.data.list, 3);
      //加载数据到数组
      regroupList(pageSize, 3, list, productList)
    }
  })
}

const regroupList = (pageSize, gap, list, targetList) => {
  let count = 0
  while (pageSize > 0) {
    targetList.value.push(list[count]);
    pageSize -= gap;
    count++;
  }
}

const chunkArray = (array, size) => {
  if (array.length === 0) {
    return [];
  }

  return [array.slice(0, size)]
      .concat(chunkArray(array.slice(size, array.length), size));
}

</script>

<style scoped>
/*单行文字超出的部分替换为省略号*/
p {
  /*单行显示*/
  white-space: nowrap;
  /*超出部分隐藏*/
  overflow: hidden;
  /*超出部分替换成...*/
  text-overflow: ellipsis;
}

* {
  text-align: center;
}

.rl {
  text-decoration: none;
  color: inherit; /* 可以保持原始文字颜色 */
}
</style>