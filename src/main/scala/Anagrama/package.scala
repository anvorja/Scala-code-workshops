import scala.language.postfixOps

/**
 * This package contains functions regarding the design of an anagram producer program.
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.0.0 date: 30/05/2022
 */
package object Anagrama {

  // Palabras de una frase por medio del tipo String:
  type Palabra = String

  // Suponemos que las frases contendrán solamente palabras de esta forma.
  type Frase = List[Palabra]

  /* lista de parejas (car, num) ordenada por car, donde num es un entero positivo que indica cuántas veces aparece el caracter
   car en la frase o en la palabra
   */
  type Occurrencias = List[(Char, Int)]

  /**
   * El diccionario de las palabras con respecto a las cuáles se harán los anagramas,se representarán por medio de una lista de palabras
   */
  val diccionario: List[Palabra] = List("cosas","como","yo", "y", "ocasos", "cayo", "mocosos", "roca", "moco", "sos")

  /**
   * En esta función usamos el método groupBy para tener una manera eficiente de
   * agrupar las palabras del diccionario por las que tienen la misma lista de ocurrencias
   * @param p: Palabra
   * @return Occurrencias
   */
  def lOcPal(p: Palabra): Occurrencias = p.toLowerCase.groupBy(ch => ch).mapValues(_.length).toList sorted


  /**
   * Esta función concatena todas las palabras de la frase en una sola palabra,
   * y usa  la función que calcula la lista de ocurrencias de una palabra.
   * @param f: Frase
   * @return Occurrencias
   */
  def lOcFrase(f: Frase): Occurrencias =
    f.flatMap(lOcPal).groupBy(_._1).map(x => (x._1, x._2.map(_._2).sum)).toList sorted


  /**
   * Para calcular los anagramas de una palabra hay que tener en cuenta una observación sencilla:
   * la lista de ocurrencias de un anagrama de una palabra es la misma lista de ocurrencias de la palabra
   */

  /**
   * Esta expresión agrupa las palabras del diccionario por sus listas de ocurrencias
   */
  lazy val diccionarioPorOcurrencias: Map[Occurrencias, List[Palabra]] =
  {
    diccionario.map(w => (lOcPal(w), w)).groupBy(_._1)
      .map(x => (x._1, x._2.map(_._2))) withDefaultValue List()
  }

  /**
   * En esta función nos dan una palabra y devuelve la lista de sus anagramas,
   * usando el valor diccionarioPorOcurrencias, definido anteriormente
   * @param palabra: Palabra
   * @return List [Palabra]
   */
  def anagramasDePalabra (palabra: Palabra) : List [Palabra] = diccionarioPorOcurrencias(lOcPal(palabra))

  /**
   * Para calcular los anagramas de una frase, y teniendo en cuenta la idea general descrita
   * inicialmente, requerimos de una función auxiliar que dada una lista de ocurrencias,
   * devuelva todas las posibles sublistas de ocurrencias de esa lista.
   * Si vemos una lista de ocurrencias, como el conjunto (con repeticiones) de caracteres que se usan en una frase o
   * una palabra, podemos ver una sublista como un subconjunto de ese conjunto. Visto así,
   * una sublista nos servirá en la toma de un subconjunto de caracteres de una frase o palabra y
   * hacer algo con ellos.
   */

  /**
   * Esta función recibe una lista de ocurrencias,
   * y devuelve la lista de todas las sublistas de ocurrencias de la lista original
   * @param lOcurrencias: Occurrencias
   * @return List[Occurrencias]
   */
  def combinaciones(lOcurrencias: Occurrencias): List[Occurrencias] = lOcurrencias match
  {
    case List() => List(List())
    case (m, n) :: tail =>
      (for {
        i <- 0 to n
        occ <- combinaciones(tail)
      } yield (m, i) :: occ).toList map limpiarCeros
  }

  /**
   * Función auxiliar para remover ceros
   * @param list: Occurrencias
   * @return List[(Char, Int)]
   */
  def limpiarCeros(list: Occurrencias):List[(Char, Int)] = list filter (_._2 != 0)

  /**
   * Con esta función, dada una lista de ocurrencias lOc y una sublista de esa lista slOc,
   * retorna la sublista de ocurrencias complementaria de slOc,
   * es decir la sublista de ocurrencias que tiene los caracteres de la lista lOc que no
   * están en slOc.
   * Esta función supone que slOc es una sublista de lOc,
   * es decir, que si un caracter se encuentra en slOc, él mismo debe estar también
   * en lOc con una frecuencia mayor o igual a la frecuencia que tenga en slOc
   * @param lOc: Occurrencias
   * @param slOc: Occurrencias
   * @return Occurrencias
   */
  def complemento(lOc: Occurrencias, slOc: Occurrencias): Occurrencias =
    limpiarCeros(lOc.map(z => (z._1, z._2 - slOc.find(_._1 == z._1).getOrElse(('_', 0))._2)))

  /**
   * Función que recibe una frase, y devuelve la lista de frases que son anagramas de la frase original.
   * @param sentence: Frase
   * @return List [Frase]
   */
  def anagramasDeFrase(sentence: Frase): List [Frase] =
  {
    def anagramasDeFrase(occurrencias: Occurrencias): List[Frase] =
      if (occurrencias.isEmpty) List(Nil)
      else for
      {
        combinaciones <- combinaciones(occurrencias)
        palabra <- diccionarioPorOcurrencias(combinaciones)
        others <- anagramasDeFrase(complemento(occurrencias, combinaciones))
      } yield palabra :: others
    anagramasDeFrase(lOcFrase(sentence))
  }

}
