import Huffman._

/**
 * This Worksheet contains proofs regarding Huffman tree building functions
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.0.0 date: 21/05/2022
 */

//Prueba hacerNodoArbolH
val arbol1 = hacerNodoArbolH(hacerNodoArbolH(Hoja('d',2), Hoja('s',7)), Hoja('w',2))
val arbol2 = hacerNodoArbolH(Hoja('x',5), Hoja('h',4))
val arbol3 = hacerNodoArbolH(Hoja('f',6), Hoja('g',9))
val arbol4 = hacerNodoArbolH(hacerNodoArbolH(Hoja('u',2), Hoja('t',7)), Hoja('i',2))
val arbol5 = hacerNodoArbolH(Hoja('g',6), Hoja('z',9))

// Prueba cadenaALista
val listaC1 = cadenaALista("xxxxxxyyyyrrr")
val listaC2 = cadenaALista("HuHumuHmuumm")
val listaC3 = cadenaALista("ititiWitWiiWWi")
val listaC4 = cadenaALista(" ")
val listaC5 = cadenaALista("papaaSapSaaSS")

// Pruebas ocurrencias
val listadoFrecuencia_1 = ocurrencias(listaC1)
val listadoFrecuencia_2 = ocurrencias(listaC2)
val listadoFrecuencia_3 = ocurrencias(listaC3)
val listadoFrecuencia_4 = ocurrencias(listaC4)
val listadoFrecuencia_5 = ocurrencias(listaC5)

// Pruebas listaDeHojasOrdenadas
val listaHoja_1 = listaDeHojasOrdenadas(listadoFrecuencia_1)
val listaHoja_2 = listaDeHojasOrdenadas(listadoFrecuencia_2)
val listaHoja_3 = listaDeHojasOrdenadas(listadoFrecuencia_3)
val listaHoja_4 = listaDeHojasOrdenadas(listadoFrecuencia_4)
val listaHoja_5 = listaDeHojasOrdenadas(listadoFrecuencia_5)

// Pruebas listaUnitaria
listaUnitaria(listaHoja_1)
listaUnitaria(listaHoja_2)
listaUnitaria(listaHoja_3)
listaUnitaria(listaHoja_4)
listaUnitaria(listaHoja_5)

// Pruebas combinar
combinar(listaHoja_1)
combinar(listaHoja_2)
combinar(listaHoja_3)
combinar(listaHoja_4)
combinar(listaHoja_5)

// Pruebas hastaQue
val until_1 = hastaQue(listaUnitaria,combinar)(listaHoja_1)
val until_2 = hastaQue(listaUnitaria,combinar)(listaHoja_2)
val until_3 = hastaQue(listaUnitaria,combinar)(listaHoja_3)
val until_4 = hastaQue(listaUnitaria,combinar)(listaHoja_4)
val until_5 = hastaQue(listaUnitaria,combinar)(listaHoja_5)

// Pruebas crearArbolDeHuffman
val arbol_1 = crearArbolDeHuffman(listaC1)
val arbol_2 = crearArbolDeHuffman(listaC2)
val arbol_3 = crearArbolDeHuffman(listaC3)
val arbol_4 = crearArbolDeHuffman(listaC4)
val arbol_5 = crearArbolDeHuffman(listaC5)

// Pruebas peso
peso(arbol_1)
assert(peso(arbol_1)==13)
peso(arbol_2)
assert(peso(arbol_2)==12)
peso(arbol_3)
assert(peso(arbol_3)==14)
peso(arbol_4)
assert(peso(arbol_4)==1)
peso(arbol_5)
assert(peso(arbol_5)==13)

// Pruebas convertir
val convertir_1 = convertir(arbol_1)
val convertir_2 = convertir(arbol_2)
val convertir_3 = convertir(arbol_3)
val convertir_4 = convertir(arbol_4)
val convertir_5 = convertir(arbol_5)

// Pruebas codigoEnBits
assert(codigoEnBits(convertir_1)('r') == List(1, 0))
codigoEnBits(convertir_1)('r')
assert(codigoEnBits(convertir_2)('m') == List(1, 1))
codigoEnBits(convertir_2)('m')
assert(codigoEnBits(convertir_3)('i') == List(1))
codigoEnBits(convertir_3)('i')
assert(codigoEnBits(convertir_4)(' ') == List())
codigoEnBits(convertir_4)(' ')
assert(codigoEnBits(convertir_5)('a') == List(0))
codigoEnBits(convertir_5)('a')

// Pruebas mezclaTablasDeCodigos
val tablaCodigos_1 = mezclarTablasDeCodigos(convertir_1, convertir_2)
val tablaCodigos_2 = mezclarTablasDeCodigos(convertir_2, convertir_3)
val tablaCodigos_3 = mezclarTablasDeCodigos(convertir_3, convertir_4)
val tablaCodigos_4 = mezclarTablasDeCodigos(convertir_4, convertir_5)
val tablaCodigos_5 = mezclarTablasDeCodigos(convertir_2, convertir_5)

// Pruebas codificarRapido
val codeRapido_1 = codificarRapido(arbol_1)(listaC1)
val codeRapido_2 = codificarRapido(arbol_2)(listaC2)
val codeRapido_3 = codificarRapido(arbol_3)(listaC3)
val codeRapido_4 = codificarRapido(arbol_4)(listaC4)
val codeRapido_5 = codificarRapido(arbol_5)(listaC5)

// Pruebas codificar
codificar(arbol_1)(listaC1)
codificar(arbol_2)(listaC2)
codificar(arbol_3)(listaC3)
codificar(arbol_4)(listaC4)
codificar(arbol_5)(listaC5)

// Pruebas decodificar
decodificar(arbol_1,codeRapido_1)
decodificar(arbol_2,codeRapido_2)
decodificar(arbol_3,codeRapido_3)
decodificar(arbol_4,codeRapido_4)
decodificar(arbol_5,codeRapido_5)