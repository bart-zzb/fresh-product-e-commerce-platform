<template>
  <div style="background-color: #F7F8FA;height: 100%;width:100%;position: absolute;top:0;">
    <!--顶部栏-->
    <div style="position: fixed; top:0px;width: 100%;z-index: 1;">
      <van-nav-bar class="nav"
                   style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;background-color:darkorange;"
                   title="地址编辑"
                   left-text="返回"
                   left-arrow
                   @click-left="onBack"/>
    </div>
    <div style="padding-top: 60px;">
      <van-address-edit
          :area-list="areaList"
          :address-info="addressEditInfo"
          show-delete
          show-search-result
          :search-result="searchResult"
          :area-columns-placeholder="['请选择', '请选择', '请选择']"
          @save="onSave"
          @delete="onDelete"
      />
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import {showConfirmDialog, showSuccessToast, showToast} from "vant";
import {areaList} from "@vant/area-data";
import axios from '@/utils/request';
import qs from "qs";

const onBack = () => {
  router.push("/address");
}
const addressEditInfo = ref({});

const searchResult = ref([]);

//保存编辑地址
const onSave = (currentAddressEditInfo) => {
  console.log(currentAddressEditInfo)
  //封装userAddressDTO
  let data = {
    id: currentAddressEditInfo.id,
    contactPhone: currentAddressEditInfo.tel,
    district: currentAddressEditInfo.county,
    city: currentAddressEditInfo.city,
    province: currentAddressEditInfo.province,
    addressName: currentAddressEditInfo.addressDetail,
    receiver: currentAddressEditInfo.name
  };
  let params = qs.stringify(data);

  if (data.id) {
    //修改地址
    axios.post("/admin/userAddress/update", params).then((response) => {
      if (response.data.state == 20000) {
        showSuccessToast('保存成功');
        router.push("/address")
      }
    })
  } else {
    //新增地址
    axios.post("/admin/userAddress/add", params).then((response) => {
      if (response.data.state == 20000) {
        showSuccessToast('新增成功');
        router.push("/address")
      }
    })
  }

}

const onDelete = () => {
  showConfirmDialog({
    title: '提示',
    message:
        '您确认要删除该地址吗',
  })
      .then(() => {
        let id = router.currentRoute.value.query.addressId;
        if (id) {
          axios.post("/admin/userAddress/delete?id=" + id).then((response) => {
            if (response.data.state == 20000) {
              showSuccessToast("删除成功");
              router.push("/address");
            }
          })
        }
      })
      .catch(() => {
        // on cancel
      });
};

onMounted(() => {
  let id = router.currentRoute.value.query.addressId;
  if (id) {
    axios.get("/admin/userAddress/get/current?id=" + id).then((response) => {
      if (response.data.state == 20000) {
        let temp = response.data.data;
        addressEditInfo.value.id = temp.id;
        addressEditInfo.value.name = temp.receiver;
        addressEditInfo.value.tel = temp.contactPhone;
        addressEditInfo.value.province = temp.province;
        addressEditInfo.value.city = temp.city;
        addressEditInfo.value.county = temp.district;
        addressEditInfo.value.addressDetail = temp.addressName;
      }
    })
  }
})
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