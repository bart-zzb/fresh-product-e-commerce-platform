-- 创建数据库 e_mall
DROP DATABASE IF EXISTS e_mall;
CREATE DATABASE e_mall CHARSET = utf8mb4;
USE e_mall;

-- 创建用户表 tb_user
DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user
(
    id              BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time     DATETIME                NOT NULL COMMENT '创建时间',
    modified_time   DATETIME                NOT NULL COMMENT '修改时间',
    user_type       TINYINT(3) UNSIGNED     NOT NULL COMMENT '用户类型, 1:个人 2:企业',
    profile_img     VARCHAR(50)             DEFAULT '' COMMENT '头像',
    nickname        VARCHAR(50)             DEFAULT '' COMMENT '昵称',
    district        VARCHAR(50)             DEFAULT '' COMMENT '省(冗余)',
    city            VARCHAR(50)             DEFAULT '' COMMENT '市(冗余)',
    province        VARCHAR(50)             DEFAULT '' COMMENT '区(冗余)',
    address_detail  VARCHAR(50)             DEFAULT '' COMMENT '详细地址(冗余)',
    username        VARCHAR(50)             NOT NULL COMMENT '用户名',
    password        VARCHAR(200)             NOT NULL COMMENT '登录密码',
    contact_phone   VARCHAR(50)             DEFAULT '' COMMENT '用户手机号',
    user_point      INT(10) UNSIGNED        DEFAULT 0 COMMENT '用户积分',
    user_balance    DECIMAL(10,2) UNSIGNED  DEFAULT 0.00 COMMENT '用户余额',
    coupon_count    INT(10) UNSIGNED        DEFAULT 0 COMMENT '优惠券数量'
)DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- 创建商品分类列表 tb_category
DROP TABLE IF EXISTS tb_category;
CREATE TABLE tb_category
(
    id              BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time     DATETIME                NOT NULL COMMENT '创建时间',
    modified_time   DATETIME                NOT NULL COMMENT '修改时间',
    parent_id       BIGINT  UNSIGNED        NOT NULL COMMENT '父级id(顶级父级id为0)',
    level           TINYINT(3) UNSIGNED     NOT NULL COMMENT '级别类型 (1: 一级分类) (2: 二级分类)...',
    is_parent       TINYINT(3) UNSIGNED     NOT NULL COMMENT '是否为父类 (1: 为父类, 它有子类) (0: 不为父类, 没有子类)',
    sort            TINYINT(3) UNSIGNED     NOT NULL COMMENT '排序 (1:优先级最高)',
    category_name   VARCHAR(50)             NOT NULL COMMENT '分类名称',
    enable          TINYINT(3) UNSIGNED     NOT NULL COMMENT '其否启用 (0: 不启用) (1: 启用)',
    is_display      TINYINT(3) UNSIGNED     NOT NULL COMMENT '是否启用首页展示 (0: 不启用展示) (1: 启用展示)'
)DEFAULT CHARSET = utf8mb4 COMMENT ='商品分类列表';

-- 创建商品表[SPU] tb_product
DROP TABLE IF EXISTS tb_product;
CREATE TABLE tb_product
(
    id              BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time     DATETIME                NOT NULL COMMENT '创建时间',
    modified_time   DATETIME                NOT NULL COMMENT '修改时间',
    product_name    VARCHAR(50)             NOT NULL COMMENT '商品名称',
    attribute_list  JSON                    DEFAULT '{}' COMMENT '商品属性集合(便于前端解析)',
    tb_category_id  BIGINT UNSIGNED         NOT NULL COMMENT '商品分类列表id',
    tb_brand_id     BIGINT UNSIGNED         NOT NULL COMMENT '品牌表id',
    sales           INT(10) UNSIGNED        DEFAULT 0 COMMENT '销售量',
    status          TINYINT(3) UNSIGNED     NOT NULL COMMENT '启用状态 (0: 未启用) (1: 启用)',
    info            VARCHAR(50)             DEFAULT '' COMMENT '备注信息'
)DEFAULT CHARSET = utf8mb4 COMMENT ='商品表';

-- 创建商品规格表[SKU] tb_product_specs
DROP TABLE IF EXISTS tb_product_specs;
CREATE TABLE tb_product_specs
(
    id              BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time     DATETIME                NOT NULL COMMENT '创建时间',
    modified_time   DATETIME                NOT NULL COMMENT '修改时间',
    tb_product_id   BIGINT UNSIGNED         NOT NULL COMMENT '商品表id',
    specs_name      VARCHAR(50)             DEFAULT '' COMMENT '规格名称',
    img_url         VARCHAR(50)             DEFAULT '' COMMENT '图片地址',
    product_specs   JSON                    DEFAULT '{}' COMMENT '规格属性集合',
    current_price   DECIMAL(10, 2)   DEFAULT 0.00 COMMENT '现价',
    original_price  DECIMAL(10, 2)   DEFAULT 0.00 COMMENT '原价',
    amount          INT(10) UNSIGNED        DEFAULT 0 COMMENT '数量',
    sales           INT(10) UNSIGNED        DEFAULT 0 COMMENT '销售量',
    status          TINYINT(3) UNSIGNED     NOT NULL COMMENT '启用状态 (0: 未启用) (1: 启用)',
    unit            VARCHAR(50)             DEFAULT '' COMMENT '单位',
    info            VARCHAR(50)             DEFAULT '' COMMENT '备注信息'
)DEFAULT CHARSET = utf8mb4 COMMENT ='商品规格表[SKU]';

-- 创建品牌表 tb_brand
DROP TABLE IF EXISTS tb_brand;
CREATE TABLE tb_brand
(
    id              BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    create_time     DATETIME                NOT NULL COMMENT '创建时间',
    modified_time   DATETIME                NOT NULL COMMENT '修改时间',
    name            VARCHAR(50)             NOT NULL COMMENT '品牌名称',
    status          TINYINT(3) UNSIGNED     NOT NULL COMMENT '启用状态 (0: 未启用) (1: 启用)',
    info            VARCHAR(50)             DEFAULT '' COMMENT '备注信息'
)DEFAULT CHARSET = utf8mb4 COMMENT ='商品规格表[SKU]';

-- 创建属性key表 tb_attribute_key
DROP TABLE IF EXISTS tb_attribute_key;
CREATE TABLE tb_attribute_key
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    attribute_name          VARCHAR(50)             NOT NULL COMMENT '属性名字',
    priority                TINYINT(3) UNSIGNED     DEFAULT 1 COMMENT '优先级 (9最大，1最小)',
    tb_product_category_id  TINYINT(3) UNSIGNED     NOT NULL COMMENT '商品表id'
)DEFAULT CHARSET = utf8mb4 COMMENT ='属性key表';

-- 创建属性value表 tb_attribute_value
DROP TABLE IF EXISTS tb_attribute_value;
CREATE TABLE tb_attribute_value
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    attribute_value         VARCHAR(50)             NOT NULL COMMENT '属性值',
    priority                TINYINT(3) UNSIGNED     DEFAULT 1 COMMENT '优先级 (9最大，1最小)',
    tb_attribute_key_id     TINYINT(3) UNSIGNED     NOT NULL COMMENT '属性key表id'
)DEFAULT CHARSET = utf8mb4 COMMENT ='属性key表';

-- 创建收货地址表 tb_address
DROP TABLE IF EXISTS tb_address;
CREATE TABLE tb_address
(
    id              BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time     DATETIME                NOT NULL COMMENT '创建时间',
    modified_time   DATETIME                NOT NULL COMMENT '修改时间',
    contact_phone   VARCHAR(50)             NOT NULL COMMENT '用户手机号(冗余)',
    account         VARCHAR(50)             DEFAULT '' COMMENT '用户账号(冗余)',
    province        VARCHAR(50)             DEFAULT '' COMMENT '省(冗余)',
    city            VARCHAR(50)             DEFAULT '' COMMENT '市(冗余)',
    district        VARCHAR(50)             DEFAULT '' COMMENT '区(冗余)',
    address_detail  VARCHAR(50)             DEFAULT '' COMMENT '详细地址(冗余)',
    tb_user_id      BIGINT UNSIGNED         NOT NULL COMMENT '用户表id',
    address_name    VARCHAR(50)             NOT NULL COMMENT '地址名称',
    is_default      TINYINT(3) UNSIGNED     DEFAULT 0 COMMENT '是否默认地址',
    receiver        VARCHAR(50)             DEFAULT '' COMMENT '收货人'
)DEFAULT CHARSET = utf8mb4 COMMENT = '收货地址表';

-- 创建订单表 tb_order
DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    order_no                VARCHAR(50)             NOT NULL COMMENT '订单编号',
    status                  TINYINT(3) UNSIGNED     NOT NULL COMMENT '订单状态订单状态
                                                                        (0 : 待支付)
                                                                        (1 : 已支付，待发货)
                                                                        (2 : 已发货/待收货)
                                                                        (3 : 确认收货/已完成)
                                                                        (4 : 用户关闭)
                                                                        (5 : 平台关闭(商家))
                                                                        (6:  系统调度关闭)',
    product_map             JSON                   DEFAULT '{}' COMMENT '商品详情集合',
    product_amount_total    DECIMAL(10,2)          DEFAULT 0.00 COMMENT '商品总价',
    order_amount_total      DECIMAL(10,2)          DEFAULT 0.00 COMMENT '实付付款金额',
    freight                 DECIMAL(10,2)          DEFAULT 0.00 COMMENT '配送费用',
    integration             INT(10) UNSIGNED       DEFAULT 0 COMMENT '可获得积分',
    integration_amount      DECIMAL(10,2)          DEFAULT 0.00 COMMENT '积分抵扣金额',
    tb_user_id              bigint unsigned        NOT NULL COMMENT '用户id',
    tb_address_id           bigint unsigned        NOT NULL COMMENT '收货地址id',
    consignee               VARCHAR(50)            DEFAULT '' COMMENT '收货人(冗余)',
    consignee_phone         VARCHAR(50)            DEFAULT '' COMMENT '收货人手机(冗余)',
    consignee_address       VARCHAR(50)            DEFAULT '' COMMENT '收货人地址(冗余)',
    pay_channel             TINYINT(3) UNSIGNED    NOT NULL COMMENT '支付类型
                                                                    (0 : 未指定)
                                                                    (1 : 微信支付)
                                                                    (2 : 支付宝支付)
                                                                    (3 : 银行支付)',
    out_trade_no            VARCHAR(50)            DEFAULT '' COMMENT '第三方支付流水号',
    pay_time                DATETIME               DEFAULT '1000-01-01 00:00:00' COMMENT '付款时间',
    delivery_time           DATETIME               DEFAULT '1000-01-01 00:00:00' COMMENT '发货时间',
    receive_delivery_time   DATETIME               DEFAULT '1000-01-01 00:00:00' COMMENT '收货时间',
    comment_time           DATETIME                 DEFAULT '1000-01-01 00:00:00' COMMENT '评价时间',
    cancel_time             DATETIME               DEFAULT '1000-01-01 00:00:00' COMMENT '取消时间',
    tb_user_notes           VARCHAR(50)            DEFAULT '' COMMENT '用户备注'
)DEFAULT CHARSET = utf8mb4 COMMENT = '订单表';

-- 创建订单项目表 tb_order_items
DROP TABLE IF EXISTS tb_order_items;
CREATE TABLE tb_order_items
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    tb_order_id             BIGINT UNSIGNED         NOT NULL COMMENT '订单表ID',
    tb_product_id           BIGINT UNSIGNED         NOT NULL COMMENT '商品表(SPU)id',
    tb_product_name         VARCHAR(50)             NOT NULL COMMENT '商品名称(SPU)',
    tb_product_spec_id      BIGINT UNSIGNED         NOT NULL COMMENT '商品规格表(SKU)id',
    specs_name               VARCHAR(50)             NOT NULL COMMENT '(SKU)规格名称',
    img_url                 VARCHAR(50)             NOT NULL COMMENT '(SKU)图片地址',
    price                   DECIMAL(10,2)           NOT NULL COMMENT '(SKU)价格',
    amount                  INT(10) UNSIGNED        NOT NULL COMMENT '(SKU)数量',
    tb_category_id          BIGINT UNSIGNED         NOT NULL COMMENT '商品分类列表id',
    tb_category_name        VARCHAR(50)             NOT NULL COMMENT '商品分类名称',
    tb_brand_id             BIGINT UNSIGNED         NOT NULL COMMENT '品牌id',
    tb_brand_name           VARCHAR(50)             NOT NULL COMMENT '品牌名称',
    ift_integration         INT(10) UNSIGNED        NOT NULL COMMENT '赠送积分',
    total_price             DECIMAL(10,2)           NOT NULL COMMENT '商品项目总价'
)DEFAULT CHARSET = utf8mb4 COMMENT = '订单项目表';

-- 创建购物车表 tb_shopping_cart
DROP TABLE IF EXISTS tb_shopping_cart;
CREATE TABLE tb_shopping_cart
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    tb_user_id              BIGINT UNSIGNED         NOT NULL COMMENT '用户id',
    tb_product_id           BIGINT UNSIGNED         NOT NULL COMMENT '商品表(SPU)id',
    tb_product_name         VARCHAR(50)             NOT NULL COMMENT '商品名称(SPU)',
    tb_product_checked      TINYINT(3) UNSIGNED     DEFAULT 1 COMMENT '商品选中状态(0:未选中,1:选中)',
    tb_product_spec_id      BIGINT UNSIGNED         NOT NULL COMMENT '商品规格表(SKU)id',
    spec_name               VARCHAR(50)             NOT NULL COMMENT '(SKU)规格名称',
    img_url                 VARCHAR(50)             NOT NULL COMMENT '(SKU)图片地址',
    price                   DECIMAL(10,2)           NOT NULL COMMENT '(SKU)价格',
    amount                  INT(10) UNSIGNED        NOT NULL COMMENT '(SKU)数量',
    total_price             DECIMAL(10,2)           NOT NULL COMMENT '订单总价'
)DEFAULT CHARSET = utf8mb4 COMMENT = '购物车表';

-- 创建首页轮播图表 sys_index_carousel
DROP TABLE IF EXISTS sys_index_carousel;
CREATE TABLE sys_index_carousel
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    img_url                 VARCHAR(50)             NOT NULL COMMENT '图片地址',
    sort                    TINYINT(3) UNSIGNED     NOT NULL COMMENT '排序 (1:优先级最高)'
)DEFAULT CHARSET = utf8mb4 COMMENT = '首页轮播图表';

-- 创建首页横幅表 sys_index_banner
DROP TABLE IF EXISTS sys_index_banner;
CREATE TABLE sys_index_banner
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    img_url                 VARCHAR(50)             NOT NULL COMMENT '图片地址',
    name                    VARCHAR(50)             DEFAULT '' COMMENT '名称',
    sort                    TINYINT(3) UNSIGNED     NOT NULL COMMENT '排序 (1:优先级最高)'
)DEFAULT CHARSET = utf8mb4 COMMENT = '首页横幅表';

-- 创建直播卡片表 sys_live_card
DROP TABLE IF EXISTS sys_live_card;
CREATE TABLE sys_live_card
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    img_url                 VARCHAR(50)             NOT NULL COMMENT '图片地址',
    sort                    TINYINT(3) UNSIGNED     NOT NULL COMMENT '排序 (1:优先级最高)'
)DEFAULT CHARSET = utf8mb4 COMMENT = '直播卡片表';

-- 创建首页标签表 sys_index_label
DROP TABLE IF EXISTS sys_index_label;
CREATE TABLE sys_index_label
(
    id                      BIGINT UNSIGNED         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    create_time             DATETIME                NOT NULL COMMENT '创建时间',
    modified_time           DATETIME                NOT NULL COMMENT '修改时间',
    img_url                 VARCHAR(50)             NOT NULL COMMENT '图片地址',
    name                    VARCHAR(50)             DEFAULT '' COMMENT '图片名称',
    sort                    TINYINT(3) UNSIGNED     NOT NULL COMMENT '排序 (1:优先级最高)'
)DEFAULT CHARSET = utf8mb4 COMMENT = '首页标签表';


