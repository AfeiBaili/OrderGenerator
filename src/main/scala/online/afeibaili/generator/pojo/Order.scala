package online.afeibaili.generator.pojo

import online.afeibaili.generator.Generator

case class Order(
                    orderId: Int,
                    userName: String,
                    product: Product,
                    createdTime: String,
                    orderStatus: String,
                    orderSource: String,
                    payType: String,
                    quantity: Int
                ) {
    override def toString: String = {
        s"$orderId, $userName, ${product.category}, ${product.name}, ${product.amount}, $createdTime, $orderStatus, $orderSource, $payType, $quantity"
    }

    def toString(config: Generator.Config): String = {
        val str = StringBuilder
            .newBuilder
            .append(if (config.hasOrderId) s"$orderId, " else "")
            .append(if (config.hasUserName) s"$userName, " else "")
            .append(if (config.hasProductCategory) s"${product.category}, " else "")
            .append(if (config.hasProductName) s"${product.name}, " else "")
            .append(if (config.hasProductAmount) s"${product.amount}, " else "")
            .append(if (config.hasCreatedTime) s"$createdTime, " else "")
            .append(if (config.hasOrderStatus) s"$orderStatus, " else "")
            .append(if (config.hasOrderSource) s"$orderSource, " else "")
            .append(if (config.hasPayType) s"$payType, " else "")
            .append(if (config.hasQuantity) s"$quantity, " else "")
            .toString()
        str.substring(0, str.length - 2)
    }
}