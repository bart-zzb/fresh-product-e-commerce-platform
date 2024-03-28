-- 插入用户表测试数据
USE e_mall;
TRUNCATE tb_user;
INSERT INTO tb_user(id,
                    create_time,
                    modified_time,
                    user_type,
                    nickname,
                    account,
                    login_password,
                    user_balance)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, '五里雾', '0001', '123456', '156.82'),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 'xiaoy', '0002', '123456', '34.82');

-- 插入商品分类列表测设数据 tb_category
TRUNCATE tb_category;
INSERT INTO tb_category(id,
                        create_time,
                        modified_time,
                        parent_id,
                        level,
                        is_parent,
                        sort,
                        category_name,
                        enable,
                        is_display)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 0, 1, 1, 1, '本周优选', 1, 0),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 0, 1, 1, 2, '地方特产', 1, 0),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 0, 1, 1, 3, '优惠套餐', 1, 0),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 0, 1, 1, 4, '猪肉', 1, 0),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 0, 1, 1, 5, '牛肉', 1, 0),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 0, 1, 1, 6, '其他产品', 1, 0),
       (7, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 2, 0, 1, '发财金猪', 1, 0),
       (8, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 2, 0, 2, '手工面点', 1, 0),
       (9, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 2, 0, 3, '供澳蔬菜', 1, 1),
       (10, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 2, 0, 4, '粤海腊肠', 1, 1),
       (11, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, 0, 1, '高州特产', 1, 0),
       (12, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, 0, 2, '东北大米', 1, 0),
       (13, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, 0, 3, '白蕉海鲈', 1, 0),
       (14, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, 0, 4, '大连海参', 1, 0),
       (15, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, 0, 5, '新疆特产', 1, 0),
       (16, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, 2, 0, 1, '火锅套餐', 1, 0),
       (17, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, 2, 0, 2, '露营套餐', 1, 0),
       (18, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, 2, 0, 3, '本周菜谱', 1, 0),
       (19, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 4, 2, 0, 1, '猪肉类', 1, 1),
       (20, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 4, 2, 0, 2, '猪骨类', 1, 1),
       (21, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 4, 2, 0, 3, '猪副产品类', 1, 0),
       (22, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 5, 2, 0, 1, '牛肉类', 1, 1),
       (23, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 5, 2, 0, 2, '进口牛肉', 1, 0),
       (24, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 6, 2, 0, 1, '牛肉丸', 1, 1),
       (25, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 6, 2, 0, 2, '猪肉丸', 1, 0),
       (26, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 6, 2, 0, 3, '闸波鱼丸', 1, 0);


-- 创建商品表[SPU] tb_product
TRUNCATE tb_product;
INSERT INTO tb_product(id,
                       create_time,
                       modified_time,
                       product_name,
                       attribute_list,
                       tb_category_id,
                       tb_brand_id,
                       sales,
                       status)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '脆皮金猪', '{}', 7, 1, 2, 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '纯手工鲜肉水饺 约300g (15个装)', '{}', 8, 1, 15, 1),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '纯手工包子 约300g (5个装)', '{}', 8, 1, 17, 1),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '纯手工馒头 约400g (5个装)', '{}', 8, 1, 8, 1),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '上海青 约450g', '{}', 9, 1, 95, 1),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '翡翠豆苗 约200g', '{}', 9, 1, 56, 1);
#(1,'2024-03-27 15:00:00', '2024-03-27 15:00:00','【高州助农】龙小荔盒装龙眼饼','{}',11,1),

-- 创建商品规格表[SKU] tb_product_specs
TRUNCATE tb_product_specs;
INSERT INTO tb_product_specs(id,
                             create_time,
                             modified_time,
                             tb_product_id,
                             specs_name,
                             img_url,
                             product_specs,
                             current_price,
                             original_price,
                             amount,
                             sales,
                             status)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, '4-5斤', '1.png', '{}', '498', '568', 100, 2, 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, '7-8斤', '1.png', '{}', '688', '768', 120, 0, 1),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, '10-11斤', '1.png', '{}', '888', '968', 120, 0, 1),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '玉米鲜肉馅', '1.png', '{}', '27.5', '36', 250, 3, 1),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '茴香鲜肉馅', '1.png', '{}', '27.5', '36', 120, 2, 1),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '韭菜鲜肉馅', '1.png', '{}', '27.5', '36', 280, 8, 1),
       (7, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '芹菜鲜肉馅', '1.png', '{}', '27.5', '36', 213, 2, 1),
       (8, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '流沙包', '1.png', '{}', '27.5', '36', 145, 10, 1),
       (9, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '核桃包', '1.png', '{}', '27.5', '36', 123, 6, 1),
       (10, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '叉烧包', '1.png', '{}', '27.5', '36', 117, 1, 1),
       (11, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '玉米包', '1.png', '{}', '27.5', '36', 213, 0, 1),
       (12, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 4, '果蔬馒头', '1.png', '{}', '25.5', '35', 84, 5, 1),
       (13, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 4, '大枣红糖馒头', '1.png', '{}', '25.5', '35', 48, 3, 1),
       (14, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 5, '云南上海青', '1.png', '{}', '4.2', '6.2', 100, 95, 1),
       (15, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 6, '连州翡翠豆苗', '1.png', '{}', '4.2', '8.3', 120, 56, 1);

-- 创建品牌表 tb_brand
TRUNCATE tb_brand;
INSERT INTO tb_brand(id,
                     create_time,
                     modified_time,
                     name,
                     status)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '同城自营', 1);


-- 创建属性key表 tb_attribute_key
TRUNCATE tb_attribute_key;
INSERT INTO tb_attribute_key(id,
                             create_time,
                             modified_time,
                             attribute_name,
                             priority,
                             tb_product_category_id)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '熟食属性', 1, 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '销售标签属性', 1, 1);

-- 创建属性value表 tb_attribute_value
TRUNCATE tb_attribute_value;
INSERT INTO tb_attribute_value(id,
                               create_time,
                               modified_time,
                               attribute_value,
                               priority,
                               tb_attribute_key_id)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '烧味', 9, 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '冷盘', 8, 1),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '冷冻熟肉', 7, 1),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '加工肉类', 6, 1),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '热销top1', 9, 2),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '热销top2', 8, 2),
       (7, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '热销top3', 7, 2),
       (8, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '近期热卖', 6, 2);
