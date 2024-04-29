<template>
  <!--顶部栏-->
  <div style="position: fixed; top:0px;width: 100%;">
    <van-nav-bar class="nav" style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;background-color:darkorange;"
                 title="收货地址"
                 left-text="返回"
                 left-arrow
                 @click-left="onBack"/>
  </div>
  <van-address-list style="margin-top: 60px;"
      v-model="chosenAddressId"
      :list="list"
      default-tag-text="默认"
      @add="onAdd"
      @edit="onEdit"
  />

</template>

<script setup>
import router from "@/router";
import {ref} from "vue";
import {showToast} from "vant";

const onBack = ()=>{
  let redirectPath = localStorage.getItem("redirectPath");
  if(redirectPath){
    router.push(redirectPath);
    localStorage.removeItem("redirectPath");
  }else{
    router.push("/personal");
  }
}

const chosenAddressId = ref('1');
const list = [
  {
    id: '1',
    name: '张三',
    tel: '13000000000',
    address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
    isDefault: true,
  },
  {
    id: '2',
    name: '李四',
    tel: '1310000000',
    address: '浙江省杭州市拱墅区莫干山路 50 号',
  },
];


const onAdd = () => showToast('新增地址');
const onEdit = (item, index) => showToast('编辑地址:' + index);

</script>

<style>
/*设置顶部栏样式*/
.nav {
  --van-nav-bar-title-text-color: #fff;
  --van-nav-bar-icon-color: #fff;
  --van-nav-bar-text-color: #fff;
  --van-nav-bar-title-font-size: 22px;
  --van-nav-bar-arrow-size: 20px;
}
</style>