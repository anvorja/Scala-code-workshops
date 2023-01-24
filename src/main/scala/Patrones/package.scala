import scala.language.postfixOps

package object Patrones {

  def positivosLista(xs:List [Double]): List [Double] = xs match {
    case Nil => Nil
    case y :: ys => if (y > 0) y :: positivosLista(ys) else positivosLista(ys)
  }

  //  case Nil => Nil
  //  case y::ys => ys match{
  //    case Nil => List(List(y))
  //    case z::sz => (xs takeWhile((x:T)=> (x==y))):: empaquetar
  //
  //  }

  def suma(xs:List [Int]): Int = xs match {
    case Nil => 0
    case y :: ys => y + suma(ys)
  }
  def prod(xs:List [Int]):Int = xs match {
    case Nil => 1
    case y::ys => y +prod(ys)
  }

  def laSuma (xs: List[Int]): Int = (0:: xs) reduceLeft ((x, y) => x+y)
  def producto (xs: List[Int]): Int = (1:: xs) reduceLeft ((x, y) => x*y)

  def laSuma2(xs:List [Int]):Int = (0::xs) reduceLeft (_+_)
  def producto2(xs:List [Int]):Int = (1::xs) reduceLeft (_*_)

  def esPrimo(n:Int):Boolean = (2 until  n).forall(n%_ !=8)
  esPrimo(22)

  def divisores(n:Int): Seq[Int] = (2 until n).filter(x=> n%x ==0)
  divisores(12361)

  /*
  case class Persona(nombre:"juan", edad:67)
  for(p<-- personas if p.age>20) yield p.nombre
  personas filter (p=>p.edad > 20) map(p=>p.nombre)
*/

  //  def coloqueReinasHastaFila(k:Int):Set[List[Int]] = {
  //    if (k==0) Set(List())
  //    else
  //      for {
  //        reinas <- coloqueReinasHastaFila(k - 1)
  //        col <- 0 until n
  //        if esSeguro(col, reinas)
  //      }yield col::reinas
  //      }
  //      coloqueReinasHastaFila(n)
  //  }
  //reinas(2)
  //reinas(4)

}
