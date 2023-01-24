import ConjuntosF._

// Pruebas para función pertenece
pertenece(9, (x : Int) => x < 0)
pertenece(-9, (x : Int) => 2*x - 5 == 12)
pertenece(0, (x : Int) => 2*x - 5 == 12)
pertenece(9, (x : Int) => 2*x - 5 == 13)
pertenece(9, (x : Int) => x/2 - 5 == 0)
pertenece(10, (x : Int) => x/2 - 5 == 0)
pertenece(11, (x : Int) => x/2 - 5 == 0)
pertenece(-9, (x : Int) => x > 0)
pertenece(-9, (x : Int) => x < 0)

def conjComoCadena (s: Conjunto): String =
{
  val xs = for (i <-(-100) to 100 if pertenece(i, s)) yield i
  xs.mkString("{", ",", "}")
}

//conjuntos adicionales para otras pruebas potenciales
val sa = conjuntoUnitario(-5)
val sb = conjuntoUnitario(-67)
val sc = conjuntoUnitario(-16)
val sd = conjuntoUnitario(8)
val se = conjuntoUnitario(12)
val sf = conjuntoUnitario(34)
val sg = conjuntoUnitario(-11)
val setSuperU = union(union(union(union(union(union(sg,sf),se),sd),sc),sb),sa)
conjComoCadena(setSuperU)

val setVacio = conjuntoVacio(3)
conjComoCadena(setVacio)

//llamaremos al universal objeto de estudio numeros enteros de -100 a 100
val setUniversal = conjuntoUviversal(3)
conjComoCadena(setUniversal)
val vacioUuniversal = union(setUniversal,setVacio)
conjComoCadena(vacioUuniversal)
val vacioIntUniversal = intersect(setUniversal,setVacio)
conjComoCadena(vacioIntUniversal)
//otra forma de definir mi Universal objeto de estudio numeros enteros de -100 a 100
val setUniversal2 = (x : Int) => x <= 0 || x > 0
conjComoCadena(setUniversal2)

//PRUEBAS DEL TALLER
// Prueba de conjuntoUnitario
val s1 = conjuntoUnitario(1)
conjComoCadena(s1)
conjComoCadena(s1).length-2
assert(conjComoCadena(s1).length-2 == 1, "Cardinalidad 1")

val s2 = conjuntoUnitario(2)
conjComoCadena(s2)
conjComoCadena(s2).length-2
assert(conjComoCadena(s2).length-2 == 1, "Cardinalidad 1")

val s3 = conjuntoUnitario(3)
conjComoCadena(s3)
conjComoCadena(s3).length-2
assert(conjComoCadena(s3).length-2 == 1, "Cardinalidad 1")

// Prueba de union
val s4 = union(s1, s2)
conjComoCadena(s4)
val s4_s4 = union(s4,s4)
conjComoCadena(s4_s4) //Idempotencia
val s5 = union(s1, s3)
conjComoCadena(s5)
val s5_s5 = union(s5,s5)
conjComoCadena(s5_s5) //Idempotencia
val s6 = union(s2, s3)
conjComoCadena(s6)
val s6_s6 = union(s6,s6)
conjComoCadena(s6_s6) //Idempotencia

val s4Us5 = conjComoCadena(union(s4, s5))
val s5Us4 = conjComoCadena(union(s5, s4))
assert(s4Us5 == s5Us4, "Cumplen Asociativa")

// Prueba de uniones
assert(pertenece (1 , s4) , "Union 1 pertenece s4")
assert (pertenece (2 , s4) , "Union 2 pertenece s4" )
assert(!pertenece (3 , s4) , "Union 3 no pertenece s4")
assert (pertenece (1 , s5) , "Union 1 pertenece s5")
assert(pertenece(3, s5) , "Union 2 pertenece s5")
assert(!pertenece(2 , s5) , "Union 3 no pertenece s5")
assert(pertenece (2 , s6) , "Union 2 pertenece s6")
assert (pertenece (3 , s6) , "Union 3 pertenece s6")
assert(!pertenece (1 , s6) , "Union 1 no pertenece s6")



// Prueba de intersecciones
val s7 = intersect( s4 , s5 )
conjComoCadena(s7)
val s8 = intersect( s4 , s6 )
conjComoCadena(s8)
val s9 = intersect( s5 , s6 )
conjComoCadena(s9)

// Idempotencia para intersect
val s4Ints4 = intersect(s4, s4)
conjComoCadena(s4)
conjComoCadena(s4Ints4)
val s5Ints5 = intersect(s5, s5)
conjComoCadena(s5)
conjComoCadena(s5Ints5)
val s6Ints6 = intersect(s6, s6)
conjComoCadena(s6)
conjComoCadena(s6Ints6)

// Intersección y Conmutatividad
val s4Ints5 = conjComoCadena(intersect(s4, s5))
val s5Ints4 = conjComoCadena(intersect(s5, s4))
assert(s4Ints5 == s5Ints4, "Cumplen Conmutatividad")

//Nota el conjunto vacío recibe cualquier entero para funcionar
// y retorna un conjunto sin elementos
// Propiedad absorbente
val s6IntVacio = intersect(s6, conjuntoVacio(1))
conjComoCadena(s6IntVacio)
val s7IntVacio = intersect(s7, conjuntoVacio(5))
conjComoCadena(s7IntVacio)


// Prueba de intersecciones
assert(pertenece (1 , s7) , "1 pertenece s7")
assert (!pertenece (2 , s7) , "2 no pertenece s7")
assert(!pertenece (3 , s7) , "3 no pertenece s7")
assert (pertenece (2 , s8) , "2 pertenece s8")
assert(!pertenece(1 , s8) , "1 no pertenece s8")
assert(!pertenece(3 , s8) , "3 no pertenece s8")
assert(pertenece (3 , s9) , "3 pertenece s9")
assert (!pertenece (1 , s9) , "1 no pertenece s9")
assert(!pertenece (2 , s9) , "2 no pertenece s9")


// Pruebas Diferencia entre conjuntos
val s10 = dif(s4, s5)
conjComoCadena(s10)
val s11 = dif(s4, s6)
conjComoCadena(s10)
val s12 = dif(s8, s7)
conjComoCadena(s12)

// A − ∅ = A
val s6minusVacio = dif(s6, conjuntoVacio(8))
conjComoCadena(s6minusVacio)
conjComoCadena(s6)
val s5minusVacio = dif(s5, conjuntoVacio(3))
conjComoCadena(s5minusVacio)
conjComoCadena(s5)
val s4minusVacio = dif(s4, conjuntoVacio(9))
conjComoCadena(s4minusVacio)
conjComoCadena(s4)

// ∅ − A = ∅
val vacioMinusS6 = dif(conjuntoVacio(8), s6)
conjComoCadena(vacioMinusS6)
conjComoCadena(conjuntoVacio(9))
val vacioMinusS5 = dif(conjuntoVacio(3), s5)
conjComoCadena(vacioMinusS5)
conjComoCadena(conjuntoVacio(-5))
val vacioMinusS4 = dif(conjuntoVacio(9), s4)
conjComoCadena(vacioMinusS4)
conjComoCadena(conjuntoVacio(-2))


// Prueba de función filtrar
val s10a = filtrar ( union(s4, s5 ), (x : Int ) => (x%2) == 0)
conjComoCadena(s10a )
assert (!pertenece(1 , s10a ) , "1 no pertenece s10a")
assert (!pertenece(3 , s10a ) , "3 no pertenece s10a")
assert (pertenece(2 , s10a ) , "2 pertenece s10a")
assert (!pertenece( 40, s10a ) , "40 no pertenece s10a")
assert (!pertenece( 41, s10a ) , "41 no pertenece s10a")

val unSuperConjunto = filtrar ( union(setSuperU, s5 ), (x : Int ) => (x%3) == 0)
conjComoCadena(unSuperConjunto)
assert (pertenece(3 , unSuperConjunto ) , "3 no pertenece unSuperConjunto")
assert (pertenece(12 , unSuperConjunto ) , "12 no pertenece unSuperConjunto")
assert (!pertenece(21 , unSuperConjunto ) , "21 no pertenece unSuperConjunto")


//Prueba de forall y exists
assert(!forall(union( s4 , s5 ) , (x : Int ) => (x%2) == 0) , "1 y 3 no son pares " )
assert(!forall(setSuperU , (x : Int ) => x < 0), "No todos los elementos son negativos")
assert(!forall(setSuperU , (x : Int ) => x > 0), "No todos los elementos son positivos")
assert(forall(s5,(x : Int ) => (x%2) == 1) , "1 y 3 son impares" )
assert(!forall(union( s6 , s8 ) , (x : Int ) => (x%2) == 0) , "1 y 3 no son pares " )
assert(forall(s5,(x : Int ) => (x%2) == 1) , "1 y 3 son impares" )

assert(exists(union( s4 , s5 ) , (x : Int ) => (x%2) == 0) , "2 es par" )
assert(exists(setSuperU, (x : Int ) => x< 0), "Hay al menos un número negativo" )
assert(exists(setSuperU, (x : Int ) => x> 0), "Hay al menos un número positivo" )
assert(exists(union( s4 , s5 ) , (x : Int ) => (x%2) == 0) , "2 es par" )
assert(!exists(setSuperU, (x : Int ) => x == 0) , "No existe un cero" )



// Prueba Map
val s11a = mapear( union (s4 , s5) , (x : Int ) => x*x)
conjComoCadena (s11a)
assert(!pertenece(2 , s11a ) , "2 no pertenece s11 " )
assert(!pertenece(3 , s11a ) , "3 no pertenece s11 " )
assert(!pertenece(5 , s11a ) , "5 no pertenece s11 " )
assert(!pertenece(6 , s11a ) , "6 no pertenece s11 " )
assert(!pertenece(7 , s11a ) , "7 no pertenece s11 " )
assert(!pertenece(8 , s11a ) , "8 no pertenece s11 " )
assert(pertenece(1 , s11a ) , "1 pertenece s11 " )
assert(pertenece(4 , s11a ) , "4 pertenece s11 " )
assert(pertenece(9 , s11a) , "9 pertenece s11 " )


