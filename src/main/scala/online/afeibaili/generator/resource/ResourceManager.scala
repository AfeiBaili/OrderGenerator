package online.afeibaili.generator.resource

import online.afeibaili.generator.{CloseableOps, InputStreamOps, NullCheck, OutStreamOps}

import java.io.{File, FileInputStream, FileNotFoundException, FileOutputStream}
import scala.collection.JavaConverters.asScalaIteratorConverter

object ResourceManager {
    def readListAsResource(resourceName: String): List[String] = {
        val stream = this.getClass.getResourceAsStream(resourceName)
        stream.checkNull()
        stream.buffered().use { reader =>
            reader.lines().iterator().asScala.toList
        }
    }

    def readListAsFile(path: String): List[String] = {
        new FileInputStream(path).buffered().use { reader =>
            reader.lines().iterator().asScala.toList
        }
    }

    def writeListToFile(list: List[String], path: String = "data/nick.txt"): Unit = {
        new FileOutputStream(path).buffered().use { writer =>
            list.foreach { line =>
                writer.write(s"$line\n")
            }
        }
        println(s"写入文件成功: $path")
    }

    private def loadFile(filePath: String): File = {
        val file = new File(filePath)
        if (file.exists()) file
        else throw new FileNotFoundException(s"不是一个文件: $filePath")
    }
}
