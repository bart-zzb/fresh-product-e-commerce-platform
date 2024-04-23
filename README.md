# 同城生鲜APP

## 1.项目描述
- 同城生鲜APP管理系统
- 该手机端APP旨在打造一个社区化购物的平台，旨在为客户提供最新鲜、最优质的鲜肉类型的食材，满足用户对新鲜食品的需求。该系统采用同城仓库库存管理，鲜肉类采取统一屠宰，统一销售，统一配送，尽其量降低各中间环节的成本，从而达到成本最小，新鲜至优的原则为用户提供优秀的购买体验
## 2.技术选型
- 后端：Spring Boot + Spring SSM + MySQL + Redis
- 前端：HTML/CSS + JavaScript + Vue + Vant + ElementsPlus

## 2.功能描述
- 搭建手机端操作平台给用户展示生鲜商品，提供更加生动，清晰的商品浏览页面与简单的购买支付，让用户轻松方便地操作使用系统
- 透过搭建优惠信息，成长经验，积分累积等内容方式，提升用户活跃度，培养用户忠诚度
- 提供简单的管理页面，管理员可以通过简单的方式来管理页面相关内容，实现随时的数据更新与维护
- 使用简单JWT认证功能，识别用户，确保安全性  
- 使用Redis缓存技术，方便购物车数据读写操作，实现访问购物车数据的高性能
- 提供语音识别功能，让用户可以在搜索框自动识别语音识别翻译成文字，从而提供更便捷的方式进行操作

## 3.需求分析
- #### https://k0gvly5pumi.feishu.cn/wiki/BDHRwBjkviLebdkT2Mmc7P0Inhe

## 4.数据库设计
- #### https://k0gvly5pumi.feishu.cn/wiki/XAGZwivodiZ49Hk1OTAclVhenZb

## 5.运行环境说明

- MySql 默认端口: 3306
- Redis 默认端口: 6379
- sql 测试表创建: mall/sql/create_table/create_table_mall.sql
- sql 测试表数据: mall/sql/insert_data/insert_data_mall.sql
- 默认前端静态资源路径: d:/files/e_mall/imgs
- 前端静态资源说明: 需要将 mall-frontend/mall-app-frontend/mall-app/public/imgs 内所有图片复制到默认前端静态资源路径，或者在application.yml自定义
- mall 后端启动项: mall/mall-front/src/main/java/cn/tedu/mall/front/MallFrontApplication
- mall 后端测试页面: http://localhost:8080/doc.html#/home
- mall 前端访问页面: http://localhost:9090