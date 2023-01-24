import scala.collection.convert.ImplicitConversions.`seq AsJavaList`

package object Recursion {

  /**
   * Ejercicio 1
   */
  def pascal(columna: Int, fila: Int): Int = {
    if (columna == 0 || fila == columna) 1
    else pascal(columna - 1, fila - 1) + pascal(columna, fila - 1)
  }
  /**
   * Ejercicio 2
   */
  def balanceado(chars: List[Char]): Boolean = {
    def auxBalanceado(chars: List[Char], contador: Int): Boolean = {
      if (chars.isEmpty) contador == 0
      else if (contador < 0) false
      else if (chars.head == '(') auxBalanceado(chars.tail, contador + 1)
      else if (chars.head == ')') auxBalanceado(chars.tail, contador - 1)
      else auxBalanceado(chars.tail, contador)

    }

    auxBalanceado(chars, 0)
  }

  /**
   * Ejercicio 3
   */
  def cambioMonedas(total: Int, denominacion: List[Int]): Int = {
    if (total == 0) 1
    else if (total > 0 && denominacion.nonEmpty) cambioMonedas(total - denominacion.head, denominacion) + cambioMonedas(total, denominacion.tail)
    else 0
  }

}