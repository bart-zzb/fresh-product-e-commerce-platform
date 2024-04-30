<template>
  <div style="background-color: #F7F8FA;height: 100%;width:100%;position: absolute;top:0;">
    <!--顶部栏-->
    <div style="position: fixed; top:0px;width: 100%;z-index: 1;">
      <van-nav-bar class="nav"
                   style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;background-color:darkorange;"
                   title="收货地址"
                   left-text="返回"
                   left-arrow
                   @click-left="onBack"/>
    </div>
    <van-empty description="暂无地址信息, 赶快添加新地址吧！" style="padding-top: 100px;" v-show="showEmpty"/>
    <div style="padding-top: 60px;" >
      <van-address-list style="margin-top: 15px;"
                        v-model="chosenAddressId"
                        :list="list"
                        default-tag-text="默认"
                        @add="onAdd"
                        @edit="onEdit"
                        @click-item="clickAddress"
      />
    </div>
  </div>

</template>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import axios from '@/utils/request';
import {AddressList, showToast} from 'vant';

const showEmpty = ref(false);
const loadContents =()=>{
  axios.get("/admin/userAddress/get/all").then((response) => {
    if (response.data.state == 20000) {
      list.value=[];
      let tempAddressList = response.data.data;
      //判断是否展示空背景
      if(tempAddressList.length==0){
        showEmpty.value=true;
      }else{
        showEmpty.value=false
      }

      //list格式是固定的
      for (let item of tempAddressList) {
        if (item.isDefault=='1') {
          //默认选中
          chosenAddressId.value = item.id
          list.value.push({
            id: item.id,
            name: item.receiver,
            tel: item.contactPhone,
            address: item.addressDetail,
            isDefault: true
          })
        } else {
          list.value.push({
            id: item.id,
            name: item.receiver,
            tel: item.contactPhone,
            address: item.addressDetail
          })
        }
      }
    }
  })
}

onMounted(() => {
  loadContents();
})

const onBack = () => {
  let redirectPath = localStorage.getItem("redirectPath");
  if (redirectPath) {
    router.push(redirectPath);
    localStorage.removeItem("redirectPath");
  } else {
    router.push("/personal");
  }
}

const chosenAddressId = ref(0);
const list = ref([]);

const clickAddress = () =>{
  axios.post("/admin/userAddress/change?id="+chosenAddressId.value).then((response)=>{
    if(response.data.state==20000){
      loadContents();
    }
  })
}

const onAdd = () => {
  router.push("/addressAdd");
};
const onEdit = (item, index) => {
  router.push("/addressAdd?addressId="+item.id);
};

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