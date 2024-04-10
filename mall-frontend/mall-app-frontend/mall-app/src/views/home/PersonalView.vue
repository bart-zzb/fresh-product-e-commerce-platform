<template>
  <div style="background-color: #F5F5F5;height: 100%;width:100%;position: absolute;top:0;">
    <div style="background-color: #D54431; height: 200px; border-bottom-left-radius: 30px;border-bottom-right-radius: 30px;">
      <h2 style="margin: 0;line-height: 80px;color:#fff;">个人中心</h2>
      <!--logo id名 功能-->
      <van-row gutter="2">
        <van-col span="6">
          <img src="imgs/logo/logo.png" height="60px;" style="margin-top:20px;">
        </van-col>
        <van-col span="18" style="margin-top:25px;text-align: left;">
          <span style="color:#fff;font-size: 18px;">你好,{{user.nickname}} {{user.memberType}}</span>
          <van-icon name="setting-o" style="float: right;right: 30px;color:#fff;" size="30px"/>
          <p style="color:#F4ADA7;margin-top:6px;font-size: 16px;text-align: left;">ID: {{user.id}} {{user.phone}}</p>
        </van-col>
      </van-row>

      <!--余额 优惠券-->
      <van-grid direction="horizontal" :column-num="2" style="margin: 10px 20px;">
        <van-grid-item>
          <div>
            <p style="color:#AAAAAA;">我的余额</p>
            <p >{{user.balance}}</p>
          </div>
        </van-grid-item>
        <van-grid-item>
          <div>
            <p style="color:#AAAAAA;">我的优惠券</p>
            <p>{{user.couponsCount}}</p>
          </div>
        </van-grid-item>
      </van-grid>

      <!--余额 优惠券-->
      <div style="background-color: #fff; border-radius: 10px;margin: 15px 20px;">
        <van-row >
          <van-col span="12">
            <div style="margin: 10px 15px 8px;float: left;font-size: 18px;">我的订单</div>
          </van-col>
          <van-col span="12">
            <div style="margin: 10px 15px 8px;float: right;font-size: 18px;">
              <span>全部订单</span>
              <van-icon name="arrow" />
            </div>
          </van-col>
        </van-row>
        <!--分割线-->
        <hr>
        <!-订单功能-->
        <van-row>
          <van-col span="6">
            <van-icon name="cash-back-record-o" size="35" color="#D54431"/>
            <p class="label">待付款</p>
          </van-col>
          <van-col span="6">
            <van-icon name="todo-list-o" size="35" color="#D54431"/>
            <p class="label">待发货</p>
          </van-col>
          <van-col span="6">
            <van-icon name="completed-o" size="35" color="#D54431"/>
            <p class="label">待收货</p>
          </van-col>
          <van-col span="6">
            <van-icon name="comment-o" size="35" color="#D54431"/>
            <p class="label">待评价</p>
          </van-col>
        </van-row>
      </div>

      <!--我的服务-->
      <div style="background-color: #fff; border-radius: 10px;margin: 15px 20px;">
        <div style="margin: 10px 15px 8px;font-size: 18px;width: 100%;height: 42px;line-height: 42px;text-align: left;">我的服务</div>
        <!--分割线-->
        <hr>
        <!--服务功能-->
        <van-row style="margin: 12px auto;">
          <van-col span="6">
            <van-icon name="service-o" size="35" color="#D54431"/>
            <p class="label">申请团长</p>
          </van-col>
          <van-col span="6">
            <van-icon name="comment-o" size="35" color="#D54431"/>
            <p class="label">优惠券</p>
          </van-col>
          <van-col span="6">
            <van-icon name="star-o" size="35" color="#D54431"/>
            <p class="label">我的收藏</p>
          </van-col>
          <van-col span="6">
            <van-icon name="location-o" size="35" color="#D54431"/>
            <p class="label">收获地址</p>
          </van-col>
        </van-row>
        <van-row style="margin: 12px auto;">
          <van-col span="6">
            <van-icon name="newspaper" size="35" color="#D54431"/>
            <p class="label">积分订单</p>
          </van-col>
          <van-col span="6">
            <van-icon name="shop-o" size="35" color="#D54431"/>
            <p class="label">积分商场</p>
          </van-col>
          <van-col span="6">
            <van-icon name="balance-o" size="35" color="#D54431"/>
            <p class="label">鲜肉卡充值</p>
          </van-col>
          <van-col span="6">
            <van-icon name="award-o" size="35" color="#D54431"/>
            <p class="label">优待申请</p>
          </van-col>
        </van-row>
        <van-row style="margin: 12px auto;">
          <van-col span="6">
            <van-icon name="service-o" size="35" color="#D54431"/>
            <p class="label">联系客服</p>
          </van-col>
        </van-row>
      </div>
    </div>
  </div>

</template>

<script setup>
import {ref} from "vue";

const user = ref(
    {nickname:"五里雾", memberType:"超级会员",id:"20451",phone:"绑定手机",balance:"153.65",couponsCount:"2"}
)
</script>

<style scoped>
  p {
    margin: 6px 0px;
  }

  p.label{
    font-size: 14px;
  }

  *{
    text-align: center;
  }
</style>
