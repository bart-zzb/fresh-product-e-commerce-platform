---

---

# 同城生鲜APP

## 1.项目描述
- 同城生鲜APP前台
- 单体式架构
- 该手机端APP旨在打造一个社区化购物的平台，旨在为客户提供最新鲜、最优质的鲜肉类型的食材，满足用户对新鲜食品的需求。该系统采用同城仓库库存管理，鲜肉类采取统一屠宰，统一销售，统一配送，尽其量降低各中间环节的成本，从而达到成本最小，新鲜至优的原则为用户提供优秀的购买体验
## 2.功能描述

- 搭建手机端操作平台给用户展示生鲜商品，提供更加生动，清晰的商品浏览页面与简单的购买支付，让用户轻松方便地操作使用系统
- 透过搭建优惠信息，成长经验，积分累积等内容方式，提升用户活跃度，培养用户忠诚度
- 提供简单的管理页面，管理员可以通过简单的方式来管理页面相关内容，实现随时的数据更新与维护
- 使用简单JWT认证功能，识别用户，确保安全性  
- 使用Redis缓存技术，方便购物车数据读写操作，实现访问购物车数据的高性能
- 通过连接支付包沙箱网页支付接口功能，模拟实现支付宝支付功能和异步通知回调

## 3.需求分析

- #### https://k0gvly5pumi.feishu.cn/wiki/BDHRwBjkviLebdkT2Mmc7P0Inhe

## 4.数据库设计

- #### https://k0gvly5pumi.feishu.cn/wiki/XAGZwivodiZ49Hk1OTAclVhenZb

## 5.技术选型
- 后端：Spring Boot + Spring SSM + MySQL + Redis
- 前端：HTML/CSS + JavaScript + Vue + Vant + ElementsPlus

## 6.开发技术

- `spring-boot-starter-web`
- `spring-boot-starter-validation`
- `spring-boot-starter-data-redis`
- `spring-boot-starter-security`
- `spring-security-crypto`
- `spring-boot-starter-test`
- `mybatis-spring-boot-starter`
- `mybatis-plus-boot-starter`
- `pagehelper-spring-boot-starter`
- `druid-spring-boot-starter`
- `knife4j-spring-boot-starter`
- `lombok`
- `jjwt`
- `fastjson`
- `jackson-datatype-jsr310`
- `alipay-sdk-java`
- `hutool-all`

## 7.本地启动项目

### 7.1 项目架构说明

- 后端项目为主目录下 `mall`，其中包含三个模块：
  - 通用模块：`mall-common`
  - 控制层模块：`mall-front`
  - 业务层模块：`mall-service`
- 后端主启动项在`mall-front`
- 前端项目在`mall-frontend/mall-app-frontend/mall-app`

### 7.2 项目分层出参入参流转说明

|              | 读               | 读                | 写       | 写             |
| ------------ | ---------------- | ----------------- | -------- | -------------- |
|              | 入参(读)         | 出参(读)          | 入参(写) | 出参(写)       |
| controller： | 基本数据类型/DTO | VO/PageData< VO > | DTO      | 基本数据类型   |
| service：    | 基本数据类型/DTO | BO                | DTO      | 写入生成的ID值 |
| repository： | 基本数据类型/DTO | BO                | DTO      | 写入生成的ID值 |
| mapper：     | 基本数据类型/DTO | PO                | PO       | 写入生成的ID值 |

### 7.3 安装软件

- 在启动项目之前，你需要安装：

  - MySQL（或Maria DB） 5.5或以上版本
  - Redis
  - Node.js
  - npm

  并且，保证以上软件的处于可用状态。

- 在前端项目主目录下 `mall-frontend/mall-app-frontend/mall-app` 运行 `npm install`安装项目依赖

### 7.4 关于软件配置

本项目连接各数据库均使用默认配置，如下：

| 软件            | 主机      | 端口 | 用户名 | 密码 |
| --------------- | --------- | ---- | ------ | ---- |
| MySQL / MariaDB | localhost | 3306 | root   | root |
| Redis           | localhost | 6379 | <无>   | <无> |

### 7.5 创建数据库与数据表

在主目录下的执行 `mall/sql/create_table/create_table_mall.sql` 创建数据库 e_mall;

### 7.6 插入测试数据

在主目录下的执行 `mall/sql/insert_data/insert_data_mall.sql` 插入测试数据；

### 7.7 配值访问静态资源路径

- 本项目的图片静态资源默认访问路径默认为： `d:/files/e_mall/imgs`
- 本项目已经将所有需要的静态资源放在主目录下的 `mall-frontend/mall-app-frontend/mall-app/public/imgs`，启动项目时可以将所需的静态资源拷贝到上面的默认访问路径 `d:/files/e_mall/imgs`
- 或者在`mall/mall-front/src/main/resources/application.yml` 中的 `file-path`属性自定义访问路径

### 7.8 支付宝沙箱功能

- 若要体验支付宝支付功能，请先登录注册支付宝沙箱账号 https://open.alipay.com/
- 在`mall/mall-front/src/main/resources/application.yml`的配置文件的`alipay`属性中，根据自己的支付宝沙箱账号配置四个属性：
  - appId：应用Id
  - appPrivateKey：应用私钥
  - alipayPublicKey：支付宝公钥
  - notifyUrl：异步回调路径，设置为`后端项目路径`，测试时必须为公网地址

### 7.9 启动项目

| 页面     | 主机      | 端口 |
| -------- | --------- | ---- |
| mall前端 | localhost | 9090 |
| mall后端 | localhost | 8080 |

- mall 后端启动项: `mall/mall-front/src/main/java/cn/tedu/mall/front/MallFrontApplication`
- mall 后端测试页面: http://localhost:8080/doc.html#/home
- mall 前端启动项：在`mall-frontend/mall-app-frontend/mall-app`运行`npm run serve`
- mall 前端访问页面: http://localhost:9090
- mall 前端推荐测试手机端版本：iPhone 12 Pro

