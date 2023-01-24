import scala.annotation.tailrec
import scala.language.postfixOps

/**
 * This package contains functions regarding Huffman tree building
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.0.0 date: 21/05/2022
 */
package object Huffman {

  /**
   * Representación de un árbol de Huffman
   */
  abstract class ArbolH
  case class Nodo(izq: ArbolH, der: ArbolH,
                  cars: List[Char], peso: Int) extends ArbolH
  case class Hoja(car: Char, peso: Int) extends ArbolH

  /**
   * Dado un árbol de Huffman devuelve su peso
   * @param arbol:ArbolH
   * @return w:Int
   */
  def peso(arbol:ArbolH): Int = arbol match
  {
    case Nodo(_, _, _, w) => w
    case Hoja(_, w) => w
  }

  /**
   * Dado un árbol de Huffman devuelve la lista de caracteres que codifica
   * @param arbol:ArbolH
   * @return List[Char]
   */
  def cars(arbol:ArbolH): List[Char] = arbol match
  {
    case Nodo(_, _, cs, _) => cs
    case Hoja(c, _) => List(c)
  }

  /**
   * Dados dos subárboles de un árbol de Huffman,
   * permite construir el árbol de Huffman correspondiente
   * @param izq: ArbolH
   * @param der: ArbolH
   * @return árbol de Huffman
   */
  def hacerNodoArbolH(izq:ArbolH, der:ArbolH): Nodo = Nodo(izq, der, cars(izq):::cars(der), peso(izq) + peso(der))

  /*
   * A continuación, usaremos las siguientes funciones para construir
   * un árbol de Huffman en el sentido de que la codificación de ese texto
   * será de longitud mínima y que nos permitirá decodificar y recuperar
   * el texto original sin errores y sin pérdida de información.
   */

  /**
   * Función que recibe un texto en forma de lista de caracteres List[Char]
   * y devuelve la lista con la frecuencia en que cada caracter aparece en el texto.
   * @param cars:List[Char]
   * @return List[(Char, Int)]
   */
  def ocurrencias(cars:List[Char]):List[(Char, Int)] =
  {
    (Map[Char, Int]() /: cars) {
      (m, c) =>
        m + (c -> (m.get(c) map {
          _ + 1
        } getOrElse 1))
    } toList
  }

  /**
   * Función que recibe una lista de frecuencias producida por la función ocurrencias
   * y devuelve la lista de hojas del árbol de Huffman correspondiente,
   * está ordenada ascendentemente por la frecuencia de cada carácter
   * @param frecs:List[(Char, Int)]
   * @return List[Hoja]
   */
  def listaDeHojasOrdenadas(frecs:List[(Char, Int)]):List[Hoja] =
  {
    frecs map { a => Hoja(a._1, a._2) } sortBy
      {
        peso
      }
  }

  /**
   * Función que recibe una lista de árboles de Huffman (sean árboles o nodos)
   * y retorna true si solo hay un árbol en la lista, y false en caso contrario
   * @param arboles:List[ArbolH]
   * @return Boolean
   */
  def listaUnitaria(arboles:List[ArbolH]):Boolean = arboles.size == 1

  /*
  Recibe una lista de árboles de Huffman ordenada ascendentemente por el peso de cada árbol, toma los dos primeros
  (los de menor peso) si los hay, y devuelve una lista de árboles de Huffman ordenada ascendentemente con los mismos
  árboles originales, salvo los dos primeros.
   */

  /**
   * Función que recibe una lista de árboles de Huffman ordenada ascendentemente por el peso de cada árbol,
   * toma los dos primeros (los de menor peso) si los hay,
   * y devuelve una lista de árboles de Huffman ordenada ascendentemente con los mismos árboles originales, salvo los dos primeros.
   * @param arboles:List[ArbolH]
   * @return arboles:List[ArbolH]
   */
  def combinar(arboles:List[ArbolH]):List[ArbolH] = arboles match
  {
    case Nil | _::Nil => arboles
    case x::y::ts => hacerNodoArbolH(x, y)::ts sortBy {
      peso
    }
  }

  /**
   * Función currificada que recibe una pareja condición y acción (cond : List[ArbolH] => Boolean, mezclar : List[ArbolH] => List[ArbolH]),
   * luego una lista ordenada de árboles de Huffman,
   * y devuelve una lista de árboles de Huffman correspondiente a aplicar la acción mezclar repetidamente sobre la lista original y sus resultados
   * @param cond:List[ArbolH]
   * @param mezclar:List[ArbolH] => List[ArbolH]
   * @param listaOrdenadaArboles:List[ArbolH]
   * @return List[ArbolH]
   */
  @tailrec
  def hastaQue(cond:List[ArbolH] => Boolean, mezclar:List[ArbolH] => List[ArbolH])
              (listaOrdenadaArboles: List[ArbolH]): List[ArbolH] =
  {
    if (cond(listaOrdenadaArboles)) listaOrdenadaArboles else hastaQue(cond, mezclar)(mezclar(listaOrdenadaArboles))
  }

  /**
   * Función que recibe un texto en forma de lista de caracteres
   * y retorna el árbol de Huffman asociado a ese texto
   * @param cars:List[Char]
   * @return ArbolH
   */
  def crearArbolDeHuffman(cars:List[Char]):ArbolH =
  {
    hastaQue(listaUnitaria, combinar)((listaDeHojasOrdenadas _ compose ocurrencias) (cars)).head
  }

  /**
   * Decodificando:
   * consiste en recibir un mensaje codificado como una lista de Bits y usar
   * el árbol de Huffman para encontrar el texto codificado.
   */

  /**
   * Vamos a suponer que los Bits los representamos con un entero
   */
  type Bit = Int

  /**
   * Función que recibe un árbol de Huffman y una lista de bits correspondiente
   * a la codificación de un mensaje con ese árbol,
   * y retorna la lista de caracteres correspondiente al mensaje decodificado
   * @param arbol:ArbolH
   * @param bits:List[Bit]
   * @return List[Char]
   */
  def decodificar(arbol:ArbolH, bits:List[Bit]):List[Char] = {
    @tailrec
    def auxDecodificar(arbol: ArbolH, bs: List[Bit]):(Char, List[Bit]) =
      (bs, arbol) match
      {
        case (xs, Hoja(c, _)) => (c, xs)
        case (Nil, _) => sys.error("ERROR: secuencia detenida en medio del arbol")
        case (x :: xs, Nodo(l, r, _, _)) => auxDecodificar(if (x == 0) l else r, xs)
        case _ => sys.error("Esto puede suceder")
      }

    if (bits.isEmpty) Nil
    else {
      val (c, remainder) = auxDecodificar(arbol, bits)
      c :: decodificar(arbol, remainder)
    }
  }

  /**
   * Codificar:
   * consiste en recibir un mensaje codificado como una lista de caracteres y usar
   * el árbol de Huffman para codificar el mensaje como una lista de Bits.
   */

  /**
   * Función que recibe un árbol de Huffman y luego una lista de caracteres correspondiente
   * al mensaje a codificar con ese árbol,
   * y retorna la lista de bits correspondiente al mensaje codificado.
   * @param arbol:ArbolH
   * @param mensaje: List[Char]
   * @return List[Bit]
   */
  def codificar(arbol:ArbolH)(mensaje: List[Char]): List[Bit]=
  {
    def auxCodificar(arbol: ArbolH, n: Char): List[Bit] =
    {
      require(cars(arbol).contains(n))
      arbol match {
        case Hoja(_, _) => Nil
        case Nodo(l, r, _, _) =>
          if (cars(l).contains(n)) 0 :: auxCodificar(l, n)
          else 1 :: auxCodificar(r, n)
      }
    }

    def codificarIter(arbol: ArbolH)(text: List[Char]): List[List[Bit]] =
      text match
      {
        case Nil => Nil
        case n :: na => auxCodificar(arbol, n) :: codificarIter(arbol)(na)
      }
    codificarIter(arbol)(mensaje).flatten
  }

  /**
   * Codificando usando tablas de códigos:
   * Utilizamos una tabla de códigos para resolver la ineficiencia al implementar el algoritmo anterior.
   * Se evidencia que por cada caracter a codificar hay que recorrer el árbol desde la raíz hasta una hoja donde se encuentre el carácter a codificar,
   * para saber los bits que se necesitan para codificarlo.
   * Una alternativa para resolver este problema es tener el código correspondiente a cada carácter en una tabla de códigos,
   * y cada que se necesite codificar ese carácter, extraer su código de la tabla y no recorriendo el árbol de Huffman.
   * Una manera sencilla de implementar esa tabla de códigos (no necesariamente la más eficiente) es con una lista de parejas (car, lista_Bits),
   * donde car es un carácter y lista_Bits es la lista de Bits que lo codifica según el árbol.
   */
  type TablaCodigos = List[(Char, List[Bit])]

  /**
   * Función currificada que recibe una tabla de códigos, y luego un caracter.
   * Retorna la lista de Bits correspondiente a ese caracter según la tabla.
   * @param tabla:TablaCodigos
   * @param car:Char
   * @return List[Bit]
   */
  def codigoEnBits(tabla:TablaCodigos)(car:Char):List[Bit] =
  {
    tabla find { case (c, _) => c == car } map { _._2 } getOrElse Nil
  }

  /**
   * Función que recibe dos tablas de códigos correspondientes a dos subárboles (izquierdo y derecho) de un árbol de Huffman
   * y retorna la tabla de códigos correspondiente al árbol del que hacen parte
   * @param a:TablaCodigos
   * @param b:TablaCodigos
   * @return TablaCodigos
   */
  def mezclarTablasDeCodigos(a:TablaCodigos, b:TablaCodigos): TablaCodigos = a union b


  val izquierda: TablaCodigos => TablaCodigos = ct  => BitP(0)(ct)
  val derecha: TablaCodigos => TablaCodigos = ct => BitP(1)(ct)
  val BitP: Bit => TablaCodigos => TablaCodigos = b => ct => ct map { case (c, bs) => (c, b :: bs) }

  /**
   * Función que recibe un árbol de Huffman,
   * y retorna la tabla de códigos correspondiente a ese árbol.
   * @param arbol:ArbolH
   * @return TablaCodigos
   */
  def convertir(arbol:ArbolH):TablaCodigos = arbol match
  {
    case Hoja(car, _) => List((car, Nil))
    case Nodo(izq, der, _, _) => mezclarTablasDeCodigos(izquierda(convertir(izq)), derecha(convertir(der)))
  }

  /**
   * Función currificada que recibe un árbol de Huffman,
   * luego una lista de caracteres correspondiente al mensaje a codificar con ese árbol,
   * y retorna la lista de bits correspondiente al mensaje codificado
   * @param arbol: ArbolH
   * @param texto: List[Char]
   * @return List[Bit]
   */
  def codificarRapido(arbol: ArbolH)(texto: List[Char]): List[Bit] = texto flatMap codigoEnBits(convertir(arbol))

  /**
   * función auxiliar que recibe un string y devuelve una lista de Char usando el método toList
   * @param cadena: String
   * @return List[Char]
   */
  def cadenaALista(cadena: String):List[Char] = cadena.toList

}
