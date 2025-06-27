package online.afeibaili.generator

class GeneratorBuilder {
    private val config: Generator.Config = Generator.Config().setAllForFalse()

    def withOrderId(): GeneratorBuilder = this.also {
        _.config.hasOrderId = true
    }

    def withUserName(): GeneratorBuilder = this.also {
        _.config.hasUserName = true
    }

    def withProductName(): GeneratorBuilder = this.also {
        _.config.hasProductName = true
    }

    def withProductAmount(): GeneratorBuilder = this.also {
        _.config.hasProductAmount = true
    }

    def withProductCategory(): GeneratorBuilder = this.also {
        _.config.hasProductCategory = true
    }

    def withCreatedTime(): GeneratorBuilder = this.also {
        _.config.hasCreatedTime = true
    }

    def withOrderStatus(): GeneratorBuilder = this.also {
        _.config.hasOrderStatus = true
    }

    def withOrderSource(): GeneratorBuilder = this.also {
        _.config.hasOrderSource = true
    }

    def withPayType(): GeneratorBuilder = this.also {
        _.config.hasPayType = true
    }

    def withQuantity(): GeneratorBuilder = this.also {
        _.config.hasQuantity = true
    }

    def build(): Generator = Generator(config)
}

object GeneratorBuilder {
    def apply(): GeneratorBuilder = new GeneratorBuilder()
}
