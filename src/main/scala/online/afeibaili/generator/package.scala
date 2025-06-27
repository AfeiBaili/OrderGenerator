package online.afeibaili

import java.io._

package object generator {
    implicit class NullCheck(private val it: Any) extends AnyVal {
        def checkNull(): Unit = if (it == null) throw new NullPointerException("it is null")
    }

    implicit class InputStreamOps(private val stream: InputStream) {
        def buffered(): BufferedReader = {
            new BufferedReader(reader())
        }

        def reader(): Reader = {
            new InputStreamReader(stream, "utf-8")
        }
    }

    implicit class OutStreamOps(private val stream: OutputStream) {
        def buffered(): BufferedWriter = {
            new BufferedWriter(writer())
        }

        def writer(): Writer = {
            new OutputStreamWriter(stream, "utf-8")
        }
    }

    implicit class CloseableOps[A <: Closeable](private val input: A) {
        def use[R](method: A => R): R = {
            try {
                method(input)
            } finally {
                input.close()
            }
        }
    }

    implicit class Printer(private val any: Any) extends AnyVal {
        def print(): Unit = any match {
            case seq: Seq[Any] => seq.foreach(println)
            case map: Map[Any, Any] => map.foreach(println)
            case _ => println(any)
        }
    }

    implicit class KotlinOps[T](private val it: T) extends AnyVal {
        def also(f: T => Unit): T = {
            f(it)
            it
        }

        def let[R](f: T => R): R = f(it)
    }
}
