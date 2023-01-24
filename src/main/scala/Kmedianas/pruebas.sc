import Kmedianas._
import org.scalameter._

val standardConfig = config (
  Key.exec.minWarmupRuns := 20,
  Key.exec.minWarmupRuns := 40,
  Key.exec.benchRuns := 25,
  Key.verbose := false
) withWarmer(Warmer.Default())

// Numero de puntos
val numPuntos1 = 100
val numPuntos2 = 18000
val numPuntos3 = 24000
val numPuntos4 = 100000
val numPuntos5 = 800000

// Valores eta
val eta1 = 0.01
val eta2 = 0.001

// Valores k
val k1 = 2
val k2 = 4
val k3 = 8
val k4 = 16
val k5 = 32
val k6 = 64

// generarPuntosSeq
val puntosSeq1 = generarPuntosSeq(k1, numPuntos1)
val puntosSeq2 = generarPuntosSeq(k2, numPuntos2)
val puntosSeq3 = generarPuntosSeq(k3, numPuntos3)
val puntosSeq4 = generarPuntosSeq(k4, numPuntos4)
val puntosSeq5 = generarPuntosSeq(k5, numPuntos5)
val puntosSeq6 = generarPuntosSeq(k6, numPuntos5)

// generarPuntosPar
val puntosPar1 = generarPuntosPar(k1, numPuntos1)
val puntosPar2 = generarPuntosPar(k2, numPuntos2)
val puntosPar3 = generarPuntosPar(k3, numPuntos3)
val puntosPar4 = generarPuntosPar(k4, numPuntos4)
val puntosPar5 = generarPuntosPar(k5, numPuntos5)
val puntosPar6 = generarPuntosPar(k6, numPuntos5)

// inicializarMedianasSeq
val medianasSeq1 = inicializarMedianasSeq(k1, puntosSeq1)
val medianasSeq2 = inicializarMedianasSeq(k2, puntosSeq2)
val medianasSeq3 = inicializarMedianasSeq(k3, puntosSeq3)
val medianasSeq4 = inicializarMedianasSeq(k4, puntosSeq4)
val medianasSeq5 = inicializarMedianasSeq(k5, puntosSeq5)
val medianasSeq6 = inicializarMedianasSeq(k6, puntosSeq6)

// inicializarMedianasPar
val medianasPar1 = inicializarMedianasPar(k1, puntosPar1)
val medianasPar2 = inicializarMedianasPar(k2, puntosPar2)
val medianasPar3 = inicializarMedianasPar(k3, puntosPar3)
val medianasPar4 = inicializarMedianasPar(k4, puntosPar4)
val medianasPar5 = inicializarMedianasPar(k5, puntosPar5)
val medianasPar6 = inicializarMedianasPar(k6, puntosPar6)

// Pruebas clasificarSeq
val clasificarSeq1 = clasificarSeq(puntosSeq1, medianasSeq1)
val clasificarSeq2 = clasificarSeq(puntosSeq2, medianasSeq2)
val clasificarSeq3 = clasificarSeq(puntosSeq3, medianasSeq3)
val clasificarSeq4 = clasificarSeq(puntosSeq4, medianasSeq4)
val clasificarSeq5 = clasificarSeq(puntosSeq5, medianasSeq5)
val clasificarSeq6 = clasificarSeq(puntosSeq6, medianasSeq6)

// Pruebas clasificarPar
val clasificarPar1 = clasificarPar(puntosPar1, medianasPar1)
val clasificarPar2 = clasificarPar(puntosPar2, medianasPar2)
val clasificarPar3 = clasificarPar(puntosPar3, medianasPar3)
val clasificarPar4 = clasificarPar(puntosPar4, medianasPar4)
val clasificarPar5 = clasificarPar(puntosPar5, medianasPar5)
val clasificarPar6 = clasificarPar(puntosPar6, medianasPar6)

// Pruebas actualizarSeq
val actualizarSeq1 = actualizarSeq(clasificarSeq1, medianasSeq1)
val actualizarSeq2 = actualizarSeq(clasificarSeq2, medianasSeq2)
val actualizarSeq3 = actualizarSeq(clasificarSeq3, medianasSeq3)
val actualizarSeq4 = actualizarSeq(clasificarSeq4, medianasSeq4)
val actualizarSeq5 = actualizarSeq(clasificarSeq5, medianasSeq5)
val actualizarSeq6 = actualizarSeq(clasificarSeq6, medianasSeq6)

// Pruebas actualizarPar
val actualizarPar1 = actualizarPar(clasificarPar1, medianasPar1)
val actualizarPar2 = actualizarPar(clasificarPar2, medianasPar2)
val actualizarPar3 = actualizarPar(clasificarPar3, medianasPar3)
val actualizarPar4 = actualizarPar(clasificarPar4, medianasPar4)
val actualizarPar5 = actualizarPar(clasificarPar5, medianasPar5)
val actualizarPar6 = actualizarPar(clasificarPar6, medianasPar6)

// Pruebas hayConvergenciaSeq
val convergenciaSeq1 = hayConvergencia(eta1, medianasSeq1, actualizarSeq1)
val convergenciaSeq2 = hayConvergencia(eta2, medianasSeq2, actualizarSeq2)
val convergenciaSeq3 = hayConvergencia(eta1, medianasSeq3, actualizarSeq3)
val convergenciaSeq4 = hayConvergencia(eta2, medianasSeq4, actualizarSeq4)
val convergenciaSeq5 = hayConvergencia(eta1, medianasSeq5, actualizarSeq5)
val convergenciaSeq6 = hayConvergencia(eta2, medianasSeq6, actualizarSeq6)

// Pruebas hayConvergenciaPar
val convergenciaPar1 = hayConvergenciaPar(eta1, medianasPar1, actualizarPar1)
val convergenciaPar2 = hayConvergenciaPar(eta2, medianasPar2, actualizarPar2)
val convergenciaPar3 = hayConvergenciaPar(eta1, medianasPar3, actualizarPar3)
val convergenciaPar4 = hayConvergenciaPar(eta2, medianasPar4, actualizarPar4)
val convergenciaPar5 = hayConvergenciaPar(eta1, medianasPar5, actualizarPar5)
val convergenciaPar6 = hayConvergenciaPar(eta2, medianasPar6, actualizarPar6)

// Pruebas kMedianasSeq
val kMedianasSeq1 = kMedianasSeq(puntosSeq1, medianasSeq1, eta1)
val kMedianasSeq2 = kMedianasSeq(puntosSeq2, medianasSeq2, eta2)
val kMedianasSeq3 = kMedianasSeq(puntosSeq3, medianasSeq3, eta1)
val kMedianasSeq4 = kMedianasSeq(puntosSeq4, medianasSeq4, eta2)
val kMedianasSeq5 = kMedianasSeq(puntosSeq5, medianasSeq5, eta1)
val kMedianasSeq6 = kMedianasSeq(puntosSeq6, medianasSeq6, eta2)

// Pruebas kMedianasPar
val kMedianasPar1 = kMedianasPar(puntosPar1, medianasPar1, eta1)
val kMedianasPar2 = kMedianasPar(puntosPar2, medianasPar2, eta2)
val kMedianasPar3 = kMedianasPar(puntosPar3, medianasPar3, eta1)
val kMedianasPar4 = kMedianasPar(puntosPar4, medianasPar4, eta2)
val kMedianasPar5 = kMedianasPar(puntosPar5, medianasPar5, eta1)
val kMedianasPar6 = kMedianasPar(puntosPar6, medianasPar6, eta2)

println("---------------Tiempo kMedianasSeq--------------")

// Pruebas desempeño kMedianasSeq
val tiempoSeq1 = standardConfig measure {
  kMedianasSeq(puntosSeq1, medianasSeq1, eta1)
}

val tiempoSeq2 = standardConfig measure {
  kMedianasSeq(puntosSeq2, medianasSeq2, eta2)
}

val tiempoSeq3 = standardConfig measure {
  kMedianasSeq(puntosSeq3, medianasSeq3, eta1)
}

val tiempoSeq4 = standardConfig measure {
  kMedianasSeq(puntosSeq4, medianasSeq4, eta2)
}

val tiempoSeq5 = standardConfig measure {
  kMedianasSeq(puntosSeq5, medianasSeq5, eta1)
}

val tiempoSeq6 = standardConfig measure {
  kMedianasSeq(puntosSeq6, medianasSeq6, eta2)
}
println("---------------Tiempo kMedianasPar--------------")

// Pruebas desempeño kMedianasPar
val tiempoPar1 = standardConfig measure {
  kMedianasPar(puntosPar1, medianasPar1, eta1)
}

val tiempoPar2 = standardConfig measure {
  kMedianasPar(puntosPar2, medianasPar2, eta2)
}

val tiempoPar3 = standardConfig measure {
  kMedianasPar(puntosPar3, medianasPar3, eta1)
}

val tiempoPar4 = standardConfig measure {
  kMedianasPar(puntosPar4, medianasPar4, eta2)
}

val tiempoPar5 = standardConfig measure {
  kMedianasPar(puntosPar5, medianasPar5, eta1)
}

val tiempoPar6 = standardConfig measure {
  kMedianasPar(puntosPar6, medianasPar6, eta2)
}