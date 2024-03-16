<template>
  <div class="about">
    <van-date-picker
        v-model="currentDate"
        title="选择日期"
        :min-date="minDate"
        :max-date="maxDate"
    />

  </div>
</template>
