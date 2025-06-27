# 订单生成器

可用来批量生成订单信息

```txt
订单号 用户名 商品类型     商品名      金额       订单创建时间     订单状态  支付源 支付类型 购买数量
1, 雪国列车, 数码电子, 极光无线蓝牙耳机, 299.0, 2024-12-17 10:22:03, 待收货, 小程序, 银行卡, 1
2, 🎴𝓒𝓻𝓸𝓼𝓼𝓲𝓷𝓰 𝓣𝓱𝓮 𝓢𝓽𝓪𝓻𝓼𝓲𝓽 𝓑𝓻𝓲𝓭𝓰𝓮🎴, 实用商品, 便携折叠水杯, 29.0, 2026-10-17 10:22:03, 待付款, APP, 支付宝, 5
3, 迷路的蝙蝠@哥哥2455, 母婴儿童, 积木拼装玩具, 129.0, 2024-10-28 10:22:03, 已完成, APP, 支付宝, 5
4, 呐喊的老虎-王0401, 办公用品, 会议投影翻页笔, 89.0, 2025-12-20 10:22:03, 已完成, 小程序, 微信, 1
5, 薄荷糖的夏天, 珠宝首饰, 祖母绿复古胸针, 399.0, 2024-10-10 10:22:03, 待收货, APP, 支付宝, 3```
```

**使用方法**

`getFullOrders(Int): List[Order]`：生成指定数量的订单数据，返回一个Order列表

```scala
def method(): Unit = {
    //创建Generator对象调用getFullOrders方法
    Generator().getFullOrders(2000)
}
```

```java
public void method() {
    new Generator().getFullOrders(200);
}
```

### 可根据现有的订单字段选择是否启用

```scala
def method(): Unit = {
    //创建GeneratorBuilder对象调用withXXX方法
    GeneratorBuilder()
        //选择订单字段
        .withOrderId()
        .withUserName()
        .withProductName()
        .withPayType()
        .withQuantity()
        .build()
        //通过getOrdersAsString方法获取订单数据
        .getOrdersAsString(1000)
}
```

```java
public void method() {
    new GeneratorBuilder()
            .withOrderId()
            .withUserName()
            .withProductName()
            .build()
            .getOrdersAsString(10);
}
```

`getOrdersAsString(Int): List[String]`：生成指定数量的订单数据，返回一个String列表

```txt
1, Blade~7463🎮, 解压捏捏乐玩具, 微信, 1
2, Storm_4652🦊, 维生素C泡腾片, 微信, 4
3, ⚡𝓢𝓽𝓸𝓻𝓶 𝓓𝓻𝓲𝓯𝓽𝓮𝓻⚡, 3D立体拼图, 微信, 1
4, 疯狂的刺猬～大人9180, 挂烫机（便携）, 支付宝, 1
5, Wolf.4114🐺, 记忆棉护颈枕头, 银行卡, 1
6, Blade_7414😼, 书法练习水写布, 银行卡, 1
7, Moon_4185🦊, 防晒速干冲锋衣, 微信, 1
...
```