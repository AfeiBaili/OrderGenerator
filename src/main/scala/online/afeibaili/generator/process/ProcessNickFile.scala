package online.afeibaili.generator.process

import online.afeibaili.generator.resource.ResourceManager

object ProcessNickFile {

    def main(args: Array[String]): Unit = {
    }

    def processFile(): Unit = {
        val list = ResourceManager.readListAsResource("/product.txt")

        val newList = list
            .filter(_.trim.nonEmpty)
            .map(x => {
                val strings = x.split("\\s")
                s"${strings(0)}-${strings(1)}-${strings(2)}"
            }
            )
            .distinct

        ResourceManager.writeListToFile(newList, "data/product.txt")

    }
}
