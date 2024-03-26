-- 创建数据库 e_mall
DROP DATABASE IF EXISTS e_mall;
CREATE DATABASE e_mall;
USE e_mall;

-- 创建用户表 tb_user
DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user
(
    id              BIGINT          UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    create_time     DATETIME        NOT NULL COMMENT '创建时间',
    modified_time   DATETIME        NOT NULL COMMENT '修改时间',
    user_type       TINYINT(3)      UNSIGNED NOT NULL COMMENT '用户类型, 1:个人 2:企业',
    profile_img     VARCHAR(50)     DEFAULT '' COMMENT '头像',
    nickname        VARCHAR(50)     DEFAULT '' COMMENT '昵称',
    district        VARCHAR(50)     DEFAULT '' COMMENT '省(冗余)',
    city            VARCHAR(50)     DEFAULT '' COMMENT '市(冗余)',
    province        VARCHAR(50)     DEFAULT '' COMMENT '区(冗余)',
    address_detail  VARCHAR(50)     DEFAULT '' COMMENT '详细地址(冗余)',
    account         VARCHAR(50)     NOT NULL COMMENT '用户账号',
    login_password  VARCHAR(50)     NOT NULL COMMENT '登录密码',
    contact_phone   VARCHAR(50)     DEFAULT '' COMMENT '用户手机号',
    user_point      INT(10)         UNSIGNED DEFAULT 0 COMMENT '用户积分',
    user_balance    DECIMAL(10,2)   UNSIGNED DEFAULT 0.00 COMMENT '用户余额'
)DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';


