package online.afeibaili.generator

import online.afeibaili.generator.Generator._
import online.afeibaili.generator.pojo.{Order, Product}
import online.afeibaili.generator.resource.ResourceManager

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Random


class Generator {
    private val random = new Random()
    private var config: Generator.Config = Generator.Config()

    def this(config: Generator.Config) = {
        this()
        this.config = config
    }

    def getOneOrder: Order = {
        Order(
            random.nextInt(9999999),
            name(),
            product(),
            createdTime(),
            orderStatus(),
            orderSource(),
            payType(),
            quantity()
        )
    }

    def getFullOrders(count: Int): List[Order] = {
        (1 to count).map(i => {
            getOrderById(i)
        }).toList
    }

    def getOrdersAsString(count: Int): List[String] = {
        (1 to count).map(i => {
            getOrderById(i).toString(config)
        }).toList
    }

    private def getOrderById(id: Int): Order = {
        Order(
            id,
            name(),
            product(),
            createdTime(),
            orderStatus(),
            orderSource(),
            payType(),
            quantity()
        )
    }
}

object Generator {
    private type JavaRandom = java.util.Random

    private val random = new JavaRandom()
    private val nameList = ResourceManager.readListAsResource("/nick.txt")
    private val productList = ResourceManager.readListAsResource("/product.txt")
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val orderStatusList = List(
        ("待付款", 0),
        ("待发货", 1),
        ("待收货", 4),
        ("待评价", 6),
        ("已完成", 9)
    )
    private val orderStatusWeight = orderStatusList.last._2 + 1
    private val orderSourceList = List("PC", "APP", "小程序")
    private val payTypeList = List("支付宝", "微信", "银行卡")

    def apply(): Generator = new Generator()

    def name(): String = nameList(random.nextInt(nameList.size))

    def product(): Product = {
        val product = productList(random.nextInt(productList.size)).split("-")
        Product(product(1), product(2).toDouble, product(0))
    }

    def createdTime(): String = LocalDateTime.now()
        .minusDays(random.nextInt(365 << 1))
        .plusDays(random.nextInt(365 << 1))
        .format(formatter)

    def orderStatus(): String = {
        val randomWeight = random.nextInt(orderStatusWeight)
        orderStatusList.filter(x => (0 to x._2).contains(randomWeight)).head._1
    }

    def orderSource(): String = orderSourceList(random.nextInt(orderSourceList.size))

    def payType(): String = payTypeList(random.nextInt(payTypeList.size))

    def quantity(): Int = {
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                return random.nextInt(1, 10)
            }
            return 1
        }
        1
    }

    def apply(config: Config): Generator = new Generator(config)


    class Config(
                    var hasOrderId: Boolean = true,
                    var hasUserName: Boolean = true,
                    var hasProductName: Boolean = true,
                    var hasProductAmount: Boolean = true,
                    var hasProductCategory: Boolean = true,
                    var hasCreatedTime: Boolean = true,
                    var hasOrderStatus: Boolean = true,
                    var hasOrderSource: Boolean = true,
                    var hasPayType: Boolean = true,
                    var hasQuantity: Boolean = true,
                ) {

        def setAllForFalse(): Config = {
            hasOrderId = false
            hasUserName = false
            hasProductName = false
            hasProductAmount = false
            hasProductCategory = false
            hasCreatedTime = false
            hasOrderStatus = false
            hasOrderSource = false
            hasPayType = false
            hasQuantity = false
            this
        }

        override def toString: String =
            s"""Config(
               |  hasOrderId = $hasOrderId,
               |  hasUserName = $hasUserName,
               |  hasProductName = $hasProductName,
               |  hasProductAmount = $hasProductAmount,
               |  hasProductCategory = $hasProductCategory,
               |  hasCreatedTime = $hasCreatedTime,
               |  hasOrderStatus = $hasOrderStatus,
               |  hasOrderSource = $hasOrderSource,
               |  hasPayType = $hasPayType,
               |  hasQuantity = $hasQuantity
               |)""".stripMargin
    }

    object Config {
        def apply() = new Config()
    }
}
