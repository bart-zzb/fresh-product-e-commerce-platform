-- 插入用户表测试数据
USE e_mall;
TRUNCATE tb_user;
INSERT INTO tb_user(id,
                    create_time,
                    modified_time,
                    user_type,
                    nickname,
                    username,
                    password,
                    contact_phone,
                    user_balance,
                    coupon_count)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 'XR', 'bart', '$2a$10$NPsifrclTHOnnuligxSYL.tW4gg6JoDYc1FCPY.WARXjQDnQW9xf2', 13700888022,156.82, 3),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 'xiaoy', 'xiaoy', '$2a$10$nILbHwVQOsk9mtqNOm0ndumTFT5aHXG3NPBr28hL.0akCzbNbNVE2',13766687022, 34.82, 4);

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


-- 插入商品表[SPU] tb_product
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
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '翡翠豆苗 约200g', '{}', 9, 1, 56, 1),
       (7, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '风味腊肠', '{}', 10, 1, 15, 1),
       (8, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '【高州助农】桂康袋装桂味荔枝干360g', '{}', 11, 2, 1, 1),
       (9, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '精选火锅套餐 750g', '{}', 16, 1, 3, 1),
       (10, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '猪扒 (里脊)', '{}', 19, 1, 73, 1),
       (11, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '水晶肉', '{}', 19, 1, 65, 1),
       (12, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '腌制猪扒', '{}', 19, 1, 65, 1),
       (13, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '五花肉', '{}', 19, 1, 43, 1),
       (14, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '梅头肉', '{}', 19, 1, 84, 1),
       (15, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '排骨', '{}', 20, 1, 53, 1),
       (16, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '牛肉', '{}', 22, 1, 23, 1),
       (17, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '牛碎腩', '{}', 22, 1, 15, 1),
       (18, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '牛肉丸', '{}', 24, 1, 10, 1);


-- 插入商品规格表[SKU] tb_product_specs
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
                             unit,
                             status)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, '烧乳猪 4-5斤', 'product/product1.png', '{}', 498, 568, 100, 2, '只', 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, '烧中猪 7-8斤', 'product/product2.png', '{}', 688, 768, 120, 0, '只', 1),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, '烧金猪 10-11斤', 'product/product3.png', '{}', 888, 968, 120,  0,'只',1),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '玉米鲜肉馅', 'product/product4.png', '{}', 27.5, 36, 250, 3,'袋', 1),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '茴香鲜肉馅', 'product/product5.png', '{}', 27.5, 36, 120, 2,'袋', 1),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '韭菜鲜肉馅', 'product/product6.png', '{}', 27.5, 36, 280, 8,'袋', 1),
       (7, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, '芹菜鲜肉馅', 'product/product7.png', '{}', 27.5, 36, 213, 2,'袋', 1),
       (8, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '流沙包', 'product/product8.png', '{}', 27.5, 36, 145, 10,'袋', 1),
       (9, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '核桃包', 'product/product9.png', '{}', 27.5, 36, 123, 6,'袋', 1),
       (10, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '叉烧包', 'product/product10.png', '{}', 27.5, 36, 117, 1,'袋', 1),
       (11, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 3, '鲜肉包', 'product/product11.png', '{}', 27.5, 36, 213, 0,'袋', 1),
       (12, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 4, '果蔬馒头', 'product/product12.png', '{}', 25.5, 35, 84, 5,'袋', 1),
       (13, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 4, '大枣红糖馒头', 'product/product13.png', '{}', 25.5, 35, 48, 3,'袋', 1),
       (14, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 5, '云南上海青', 'product/product14.png', '{}', 4.2, 6.2, 100, 95, '份',1),
       (15, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 6, '连州翡翠豆苗', 'product/product15.png', '{}', 4.2, 8.3, 120, 56,'份', 1),
       (16, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 7, '广式腊肠 500g', 'product/product16.png', '{}', 42, 53.8, 120, 15,'袋', 1),
       (17, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 8, '桂味荔枝干', 'product/product17.png', '{}', 60, 76, 120, 1, '袋',1),
       (18, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 9, '嫩牛肉+吊龙+牛肉丸', 'product/product18.png', '{}', 128, 168, 120, 3,'份',
        1),
       (19, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 10, '猪扒 300g/份', 'product/product19.png', '{}', 13.9, 20, 120, 50,'份',
        1),
       (20, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 10, '猪扒 500g/份', 'product/product20.png', '{}', 20.9, 30.9, 120,
        23,'份', 1),
       (21, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 11, '水晶肉 350g/份', 'product/product21.png', '{}', 24.9, 30.9, 100,
        40,'份',1),
       (22, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 11, '水晶肉 500g/份', 'product/product22.png', '{}', 32.9, 38.9, 100,
        25,'份', 1),
       (23, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 12, '腌制猪扒 300g/份', 'product/product23.png', '{}', 23.9, 35.9, 100,
        44,'份', 1),
       (24, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 12, '腌制猪扒 500g/份', 'product/product24.png', '{}', 33.9, 39.9, 100,
        21,'份', 1),
       (25, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 13, '五花肉 300g/份', 'product/product25.png', '{}', 13.9, 18.2, 100,
        35,'份', 1),
       (26, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 13, '五花肉 500g/份', 'product/product26.png', '{}', 19.9, 24.9, 100,
        8, '份',1),
       (27, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 14, '梅头肉 (带皮) 300g/份', 'product/product27.png', '{}', 13.9, 19.28,
        100,
        14,'份', 1),
       (28, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 14, '梅头肉 (带皮) 500g/份', 'product/product28.png', '{}', 19.9, 28.9,
        100, 38,'份',
        1),
       (29, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 14, '梅头肉 (不带皮) 300g/份', 'product/product29.png', '{}', 14.9,
        '20.28', 100,
        12, '份',1),
       (30, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 14, '梅头肉 (不带皮) 500g/份', 'product/product30.png', '{}', 20.9, 29.9,
        100,
        20,'份', 1),
       (31, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 15, '前排 500g/份', 'product/product31.png', '{}', 33.9, 45.9, 100,
        20, '份',1),
       (32, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 15, '肋排 500g/份', 'product/product32.png', '{}', 38.9, 48.9, 100,
        33, '份',1),
       (33, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 16, '牛肉 300g/份', 'product/product33.png', '{}', 36.8, 48.2, 100,
        20, '份',1),
       (34, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 16, '牛肉 500g/份', 'product/product34.png', '{}', 55.8, 65.8, 100,
        2, '份',1),
       (35, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 17, '牛碎腩 300g/份', 'product/product35.png', '{}', 28.8, 35.8, 100,
        8, '份',1),
       (36, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 17, '牛碎腩 500g/份', 'product/product36.png', '{}', 38.6, 42.8, 100,
        7, '份',1),
       (37, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 18, '牛肉丸 250g/份', 'product/product37.png', '{}', 32, 35, 100, 10,'份',
        1);

-- 插入品牌表 tb_brand
TRUNCATE tb_brand;
INSERT INTO tb_brand(id,
                     create_time,
                     modified_time,
                     name,
                     status)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '同城自营', 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '高州助农', 1);


-- 插入属性key表 tb_attribute_key
TRUNCATE tb_attribute_key;
INSERT INTO tb_attribute_key(id,
                             create_time,
                             modified_time,
                             attribute_name,
                             priority,
                             tb_product_category_id)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '熟食属性', 1, 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '销售标签属性', 1, 1);

-- 插入属性value表 tb_attribute_value
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

-- 插入tb_shopping_cart数据
TRUNCATE tb_shopping_cart;
INSERT INTO tb_shopping_cart(id,
                             create_time,
                             modified_time,
                             tb_user_id,
                             tb_product_id,
                             tb_product_name,
                             tb_product_checked,
                             tb_product_spec_id,
                             spec_name,
                             img_url,
                             price,
                             amount,
                             total_price)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, '纯手工鲜肉水饺 约300g (15个装)', 1, 6, '韭菜鲜肉馅', 'product/product6.png', 27.50, 3,
        82.50),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, '纯手工鲜肉水饺 约300g (15个装)', 1, 7, '芹菜鲜肉馅', 'product/product7.png', 27.50, 2,
        55.00),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, '纯手工鲜肉水饺 约300g (15个装)', 0, 4, '玉米鲜肉馅', 'product/product4.png', 27.50, 2,
        55.00),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 2, '纯手工鲜肉水饺 约300g (15个装)', 0, 4, '茴香鲜肉馅', 'product/product5.png', 27.50, 2,
        55.00),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 10, '猪扒 (里脊)', 1, 20, '猪扒 500g/份', 'product/product25.png', 20.9, 2, 41.80),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 13, '五花肉', 1, 25, '五花肉 300g/份', '1.png', 13.90, 2, 27.80);

-- 插入tb_order数据
TRUNCATE tb_order;
INSERT INTO tb_order(id,
                     create_time,
                     modified_time,
                     order_no,
                     status,
                     product_amount_total,
                     order_amount_total,
                     integration,
                     integration_amount,
                     tb_user_id,
                     tb_address_id,
                     consignee,
                     consignee_phone,
                     consignee_address,
                     pay_channel,
                     out_trade_no)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'e58fcc37-f139-475a-8715-324c90a15a83', 2, 68.90, 13.90, 13,
        0.00, 2, 1, 'xiaoy', '13456789121', '广东省广州天河区体育中心一号', 1, ''),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'a6c2f3d3-38ec-4ef1-a43f-318bd4c295ee', 3, 41.80, 41.80, 41,
        0.00, 2, 1, 'xiaoy', '13456789121', '广东省佛山禅城区百花广场11楼', 2, '');

-- 插入tb_order_items数据
TRUNCATE tb_order_items;
INSERT INTO tb_order_items(id,
                           create_time,
                           modified_time,
                           tb_order_id,
                           tb_product_id,
                           tb_product_name,
                           tb_product_spec_id,
                           specs_name,
                           img_url,
                           price,
                           amount,
                           tb_category_id,
                           tb_category_name,
                           tb_brand_id,
                           tb_brand_name,
                           ift_integration,
                           total_price)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 2, '纯手工鲜肉水饺 约300g (15个装)', 6, '韭菜鲜肉馅', 'product/product6.png', 27.50, 2, 8,
        '手工面点', 1, '同城自营', 55, 55),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 1, 13, '五花肉', 25, '五花肉 300g/份', 'product/product25.png', 13.9, 1, 19, '猪肉类', 1,
        '同城自营', 13, 13.9),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 2, 10, '猪扒 (里脊)', 20, '猪扒 500g/份', 'product/product20.png', 20.90, 2, 19,
        '猪肉类', 1, '同城自营', 41, 41.80);

-- 插入tb_order_items数据
TRUNCATE tb_address;
INSERT INTO tb_address(id, create_time, modified_time, contact_phone, account, province, city, district, address_detail,
                       tb_user_id, address_name, is_default, receiver)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '13456789121', '0001', '广东省', '广州市', '天河区', '广东省广州市天河区体育中心一号', 2,
        '体育中心一号', 1, 'xiaoy'),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', '13456789121', '0001', '广东省', '佛山市', '禅城区', '广东省佛山市禅城区百花广场11楼', 2,
        '百花广场11楼', 0, 'xiaoy');

-- 插入sys_index_carousel
TRUNCATE sys_index_carousel;
INSERT INTO sys_index_carousel(id, create_time, modified_time, img_url, sort)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'carousel/carousel1.png', 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'carousel/carousel2.png', 2),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'carousel/carousel3.png', 3),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'carousel/carousel4.png', 4),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'carousel/carousel5.png', 5),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'carousel/carousel6.png', 6);

-- 插入sys_index_banner
TRUNCATE sys_index_banner;
INSERT INTO sys_index_banner(id, create_time, modified_time, img_url, name, sort)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'banner/banner1.png', '新品上线', 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'banner/banner2.png', '猪肉产品', 2),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'banner/banner3.png', '牛肉产品', 3),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'banner/banner4.png', '特色农产', 4),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'banner/banner5.png', '其他产品', 5);

-- 插入sys_live_card
TRUNCATE sys_live_card;
INSERT INTO sys_live_card(id, create_time, modified_time, img_url, sort)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'live_card/live_card1.png', 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'live_card/live_card2.png', 2),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'live_card/live_card3.png', 3),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'live_card/live_card4.png', 4);

-- 插入sys_index_label
TRUNCATE sys_index_label;
INSERT INTO sys_index_label(id, create_time, modified_time, img_url, name, sort)
VALUES (1, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label1.png', '猪肉', 1),
       (2, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label2.png', '牛肉', 2),
       (3, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label3.png', '猪骨', 3),
       (4, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label4.png', '加工产品', 4),
       (5, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label5.png', '供港蔬菜', 5),
       (6, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label6.png', '本周秒杀', 6),
       (7, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label7.png', '高州特产', 7),
       (8, '2024-03-27 15:00:00', '2024-03-27 15:00:00', 'label/label8.png', '优惠券领取', 8);
