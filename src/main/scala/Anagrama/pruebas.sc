import Anagrama._

// Pruebas lOcPal
lOcPal("tahcasa")
lOcPal("caminnnanajaja")
lOcPal("tahcasa")
lOcPal("vivir")
lOcPal("comunicado")

// Pruebas lOcFrase
val frase_1a = List("cosas", "como", "yo")
lOcFrase(frase_1a)

val frase_1= List("mocosos", "moco", "roca")
lOcFrase(frase_1)

val frase_2 = List("sos","cayo","roca")
lOcFrase(frase_2)

val frase_3 = List("moco", "sos", "ocasos")
lOcFrase(frase_3)

val frase_4 = List("sos", "yo", "y")
lOcFrase(frase_4)

val frase_5 = List("como", "cayo", "y")
lOcFrase(frase_5)

// Prueba combinaciones
val miL= List(('a', 2), ('b', 2))
combinaciones(miL)
combinaciones(lOcPal("abc"))
combinaciones(lOcPal("xyu"))
combinaciones(lOcPal("tyut"))
combinaciones(lOcPal("k"))
combinaciones(lOcPal("ok"))

// Pruebas complemento
val lOc1 = List(('c', 2), ('a', 1), ('o', 1))
val slOc1 = List(('c', 1))
assert(complemento(lOc1, slOc1) == List(('c',1), ('a',1), ('o',1)))
complemento(lOc1, slOc1)

val lOc2 = List(('x', 2), ('a', 1), ('t', 2))
val slOc2 = List(('a', 1))
assert(complemento(lOc2, slOc2) == List(('x',2), ('t',2)))
complemento(lOc2, slOc2)

val lOc3 = List(('c', 2), ('w', 2), ('y', 3))
val slOc3 = List(('c', 2))
assert(complemento(lOc3, slOc3) == List(('w',2), ('y',3)))
complemento(lOc3, slOc3)

val lOc4 = List(('q', 3), ('z', 3), ('y', 5))
val slOc4 = List(('q', 1))
assert(complemento(lOc4, slOc4) == List(('q',2), ('z',3), ('y',5)))
complemento(lOc4, slOc4)

val lOc5 = List(('j', 2), ('h', 1))
val slOc5 = List(('j', 1))
assert(complemento(lOc5, slOc5) == List(('j',1), ('h',1)))
complemento(lOc5, slOc5)
//otro ejemplo de complemento con una Palabra
complemento(lOcPal("ab"),lOcPal("ad"))
complemento(lOcPal("carros"),lOcPal("carro"))
complemento(lOcPal("tiene"),lOcPal(" "))
complemento(lOcPal("tiene"),lOcPal("tn"))
complemento(lOcPal("andresBorja"),lOcPal("Borja"))
complemento(lOcPal("nena"),lOcPal("ea"))

anagramasDePalabra("yuy")

// Pruebas anagramasDeFrase
anagramasDeFrase(frase_1)
anagramasDeFrase(frase_2)
anagramasDeFrase(frase_3)
anagramasDeFrase(frase_4)
anagramasDeFrase(frase_5)

// Prueba Final como sugiere el enunciado
lOcPal("ocasos")
combinaciones(lOcPal("ocasos"))
val frase_1= List("mocosos", "moco", "roca")
combinaciones(lOcFrase(frase_1))
anagramasDeFrase(frase_1)

lOcPal("yo")
combinaciones(lOcPal("yo"))
val frase_2 = List("sos","cayo","roca")
combinaciones(lOcFrase(frase_2))
anagramasDeFrase(frase_2)

lOcPal("cayo")
combinaciones(lOcPal("cayo"))
val frase_3 = List("moco", "sos", "ocasos")
combinaciones(lOcFrase(frase_3))
anagramasDeFrase(frase_3)

lOcPal("roca")
combinaciones(lOcPal("roca"))
val frase_4 = List("sos", "yo", "y")
combinaciones(lOcFrase(frase_4))
anagramasDeFrase(frase_4)

lOcPal("mocosos")
combinaciones(lOcPal("mocosos"))
val frase_5 = List("como", "cayo", "y")
combinaciones(lOcFrase(frase_5))
anagramasDeFrase(frase_5)


