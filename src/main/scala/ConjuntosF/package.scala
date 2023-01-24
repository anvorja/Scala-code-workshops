import scala.annotation.tailrec

/**
 * This package contains functions regarding sets workshop
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.0.0 date: 11/05/2022
 */
package object ConjuntosF
{

  type Conjunto = Int => Boolean

  /**
   * Verifica si un elemento pertenece o no a un conjunto
   */
  def pertenece(elemento: Int, s:Conjunto ):Boolean = s(elemento)

  /**
   * Devuelve el conjunto con un solo elemento
   * @param elemento
   * @return
   */
  def conjuntoUnitario(elemento: Int):Conjunto = (a:Int) => a == elemento

  /**
   *
   * @param elemento cualquiera
   * @return el conjunto vacio
   */
  def conjuntoVacio(elemento: Int):Conjunto = (a:Int) => a != elemento && a == elemento

  def conjuntoUviversal(elemento: Int):Conjunto = (a:Int) => a != elemento

  /**
   * Devuelve la union de dos conjuntos
   * @param s
   * @param t
   * @return el conjunto de todos los elementos que están en ‘s‘ o en ‘t‘
   */
  def union(s: Conjunto, t: Conjunto): Conjunto = i => s(i) || t(i)

  /**
   * Devuelve la intersección de dos conjuntos
   * @param s
   * @param t
   * @return el conjunto de todos los elementos que están en ‘s‘ y en ‘t‘
   */
  def intersect(s: Conjunto, t: Conjunto): Conjunto = i => s(i) && t(i)

  /**
   * Devuelve la diferencia de dos conjuntos
   * @param s
   * @param t
   * @return el conjunto de todos los elementos de ‘s‘ que no están en ‘t‘ .
   */
  def dif(s: Conjunto, t: Conjunto): Conjunto = i => s(i) && !t(i)

  /**
   * Devuelve el sub conjunto de elementos de ‘s‘ para los cuales ‘p‘ se cumple
   * @param s
   * @param p
   * @return
   */
  def filtrar(s:Conjunto, p: Int => Boolean):Conjunto = (i:Int) => s(i) && p(i)

  /**
   * Calcula si todos los elementos de ‘s‘ que están en los limites satisfacen ‘p‘
   * @param s
   * @param p
   * @return
   */
  def forall(s:Conjunto, p: Int => Boolean ):Boolean = {
    @tailrec
    def auxVerificaForall(elemento: Int): Boolean = {
      if (elemento > 1000) true
      else if (s(elemento) && !p(elemento)) false
      else auxVerificaForall(elemento+1)
    }
    auxVerificaForall(-1000)
  }

  /**
   * Calcula si existe algún elemento de ‘s‘ dentro de los limites
   * que satisfaga 'p'
   * @param s
   * @param p
   * @return boolean
   */
  def exists(s:Conjunto, p:Int => Boolean): Boolean = !forall(s, (elemento:Int) => !pertenece(elemento, p))

  /**
   * Devuelve el conjunto transformado aplicando ‘f‘ a cada elemento de ‘s‘ .
   * @param s
   * @param f
   * @return
   */
  def mapear(s: Conjunto , f: Int => Int): Conjunto ={
    def prueba(e1: Int):Boolean = {
      exists(s, (e2:Int) =>  f(e2) == e1)
    }
    prueba
  }


}