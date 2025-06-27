import online.afeibaili.generator.{Generator, Printer}

object TestGenerator extends App {
    Generator().getOrdersAsString(10).print()
}
