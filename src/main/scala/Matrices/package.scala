import common._
import scala.util.Random

package object Matrices {

  val random = new Random ( )
  type Matriz = Vector [Vector[Int]]

  def matrizAlAzar (long: Int, vals: Int): Matriz = {
    //Crea una matriz de enteros cuadrada de long x long,
    // con valores aleatorios entre 0 y vals
    val v = Vector.fill (long, long){random.nextInt(vals)}
    v
  }

  def prodEscalar ( v1: Vector[Int], v2: Vector[Int] ): Int ={
    ( v1 zip v2 ).map({ case (i,j) => (i*j) }).sum
  }
  def transpuesta (m: Matriz ) : Matriz = {
    val l= m.length
    Vector.tabulate (l,l) ((i,j) => m (j) (i) )
  }

  /*  def multMatriz (m1: Matriz , m2: Matriz ): Matriz = {
      val trans = transpuesta(m2)
      val l= m1.length
      Vector.tabulate(l,l){()=>}

    }*/

}
