<template>
  <h2>直播</h2>
  <div v-for="item in liveCardList">
    <img :src="BASE_URL+item.imgUrl" width="390px;">
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "@/utils/request";

const liveCardList = ref([]);
onMounted(()=>{
  axios.get("mall/live/index").then((response)=>{
    if(response.data.state==20000){
      liveCardList.value = response.data.data;
    }
  })
})
</script>

<style scoped>
*{
  text-align: center;
}
</style>