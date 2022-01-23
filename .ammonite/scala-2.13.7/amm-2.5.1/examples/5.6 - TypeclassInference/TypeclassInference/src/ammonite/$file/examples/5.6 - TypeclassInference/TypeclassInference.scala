
package ammonite
package $file.examples.`5.6 - TypeclassInference`
import _root_.ammonite.interp.api.InterpBridge.{
  value => interp
}
import _root_.ammonite.interp.api.InterpBridge.value.{
  exit,
  scalaVersion
}
import _root_.ammonite.interp.api.IvyConstructor.{
  ArtifactIdExt,
  GroupIdExt
}
import _root_.ammonite.compiler.CompilerExtensions.{
  CompilerInterpAPIExtensions,
  CompilerReplAPIExtensions
}
import _root_.ammonite.runtime.tools.{
  browse,
  grep,
  time,
  tail
}
import _root_.ammonite.compiler.tools.{
  desugar,
  source
}
import _root_.mainargs.{
  arg,
  main
}
import _root_.ammonite.repl.tools.Util.{
  PathRead
}


object TypeclassInference{
/*<script>*/trait StrParser[T]{ def parse(s: String): T }
object StrParser{
  implicit object ParseInt extends StrParser[Int]{
    def parse(s: String) = s.toInt
  }
  implicit object ParseBoolean extends StrParser[Boolean]{
    def parse(s: String) = s.toBoolean
  }
  implicit object ParseDouble extends StrParser[Double]{
    def parse(s: String) = s.toDouble
  }

  implicit def ParseSeq[T](implicit p: StrParser[T]) = new StrParser[Seq[T]]{
    def parse(s: String) = s.split(',').toSeq.map(p.parse)
  }

  implicit def ParseTuple[T, V](implicit p1: StrParser[T], p2: StrParser[V]) =
    new StrParser[(T, V)]{
      def parse(s: String) = {
        val Array(left, right) = s.split('=')
        (p1.parse(left), p2.parse(right))
      }
    }
}

def parseFromString[T](s: String)(implicit parser: StrParser[T]) = {
  parser.parse(s)
}

def parseFromConsole[T](implicit parser: StrParser[T]) = {
  parser.parse(scala.Console.in.readLine())
}
/*</script>*/ /*<generated>*/
def $main() = { scala.Iterator[String]() }
  override def toString = "TypeclassInference"
  /*</generated>*/
}
