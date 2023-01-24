

package object Kmedianas{

  import scala.annotation.tailrec
  import scala.collection.parallel.CollectionConverters._
  import scala.collection.parallel.{ParMap, ParSeq}
  import scala.collection.{Map, Seq, mutable}
  import scala.util.Random

  class Punto(val x: Double, val y: Double, val z: Double)
  {
    private def cuadrado(v: Double):Double = v*v

    def distanciaAlCuadrado(that: Punto): Double = cuadrado(that.x-x) + cuadrado(that.y-y) + cuadrado(that.z-z)

    private def round(v: Double): Double = (v * 100).toInt / 100.0

    override def toString = s"(${round ( x )} , ${round ( y )} , ${round ( z )})"
  }

  def hallarPuntoMasCercano (p : Punto , medianas : IterableOnce [ Punto ] ) : Punto = {
    val it = medianas.iterator
    assert(it.nonEmpty)
    var puntoMasCercano = it.next()
    var minDistancia = p.distanciaAlCuadrado(puntoMasCercano)
    while (it.hasNext
    ){
      val point = it.next()
      val distancia = p.distanciaAlCuadrado(point)
      if(distancia < minDistancia) {
        minDistancia = distancia
        puntoMasCercano = point
      }
    }

    puntoMasCercano
  }


  def clasificarSeq( puntos : Seq [ Punto ] , medianas : Seq [ Punto ] ) : Map[ Punto , Seq[ Punto ] ] =
  {
    puntos.groupBy(hallarPuntoMasCercano(_, medianas)) map {
      case (k, v) => (k, v)
    }
  }

  def clasificarPar( puntos : ParSeq [ Punto ] , medianas : ParSeq [ Punto ] ) : ParMap[ Punto , ParSeq[ Punto ] ] =
  {
    puntos.par.groupBy(hallarPuntoMasCercano(_, medianas)) map {
      case (k, v) => (k, v)
    }
  }


  def calculePromedioSeq (medianaVieja : Punto , puntos : Seq [ Punto ] ) : Punto = {
    if (puntos.isEmpty) medianaVieja
    else {
      var x = 0.0
      var y = 0.0
      var z = 0.0
      puntos.foreach { p =>
        x += p.x
        y += p.y
        z += p.z
      }
      new Punto(x / puntos.length, y / puntos.length, z / puntos.length)
    }
  }
  def calculePromedioPar(medianaVieja: Punto, puntos: ParSeq[Punto]): Punto = {

    if (puntos.isEmpty) medianaVieja
    else {
      var x = 0.0
      var y = 0.0
      var z = 0.0
      puntos.seq.foreach { p =>
        x += p.x
        y += p.y
        z += p.z
      }
      new Punto(x / puntos.length, y / puntos.length, z / puntos.length)

    }
  }

  def actualizarPar(classified: ParMap[Punto, ParSeq[Punto]], medianasViejas: ParSeq[Punto]): ParSeq[Punto] =
  {
    medianasViejas.par.map(mV => calculePromedioPar(mV, classified(mV)))
  }

  def actualizarSeq(classified: Map[Punto, Seq[Punto]], medianasViejas: Seq[Punto]): Seq[Punto] =
  {
    medianasViejas.map(mV => calculePromedioSeq(mV, classified(mV)))
  }

  def hayConvergencia(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto]): Boolean =
  {
    medianasViejas.zip(medianasNuevas).forall{ case (medianasV, medianasN) => eta >= medianasN.distanciaAlCuadrado(medianasV) }
  }

  def hayConvergenciaPar(eta: Double, oldMeans: ParSeq[Punto], newMeans: ParSeq[Punto]): Boolean = {
    oldMeans.zip(newMeans).par.forall{ case (medianasV, medianasN) => eta >= medianasN.distanciaAlCuadrado(medianasV) }
  }


  @tailrec
  final def kMedianasPar(puntos: ParSeq[Punto], medianas: ParSeq[Punto], eta: Double): ParSeq[Punto] = {
    val clasificado = clasificarPar(puntos, medianas)
    val nuevaMediana = actualizarPar(clasificado, medianas)

    if (!hayConvergenciaPar(eta, medianas, nuevaMediana)) kMedianasPar(puntos, nuevaMediana, eta) else nuevaMediana
  }

  @tailrec
  final def kMedianasSeq(puntos: Seq[Punto], medianas: Seq[Punto], eta: Double): Seq[Punto] = {
    val clasificado = clasificarSeq(puntos, medianas)
    val nuevaMediana = actualizarSeq(clasificado, medianas)

    if (!hayConvergencia(eta, medianas, nuevaMediana)) kMedianasSeq(puntos, nuevaMediana, eta) else nuevaMediana
  }

  def generarPuntosPar(k: Int, num: Int): ParSeq[Punto] = {
    val randx = new Random(1)
    val randy = new Random(3)
    val randz = new Random(5)
    (0 until num)
      .map({ i =>
        val x = ((i + 1) % k) * 1.0 / k + randx.nextDouble() * 0.5
        val y = ((i + 5) % k) * 1.0 / k + randy.nextDouble() * 0.5
        val z = ((i + 7) % k) * 1.0 / k + randz.nextDouble() * 0.5
        new Punto(x, y, z)
      }).to(mutable.ArrayBuffer).par
  }

  def generarPuntosSeq(k: Int, num: Int): Seq[Punto] = {
    val randx = new Random(1)
    val randy = new Random(3)
    val randz = new Random(5)
    (0 until num)
      .map({ i =>
        val x = ((i + 1) % k) * 1.0 / k + randx.nextDouble() * 0.5
        val y = ((i + 5) % k) * 1.0 / k + randy.nextDouble() * 0.5
        val z = ((i + 7) % k) * 1.0 / k + randz.nextDouble() * 0.5
        new Punto(x, y, z)
      }).to(mutable.ArrayBuffer)
  }

  def inicializarMedianasPar(k: Int, puntos: ParSeq[Punto]): ParSeq[Punto] = {
    val rand = new Random(7)
    (0 until k).map(_ => puntos(rand.nextInt(puntos.length))).to(mutable.ArrayBuffer).par
  }

  def inicializarMedianasSeq(k: Int, puntos: Seq[Punto]): Seq[Punto] = {
    val rand = new Random(7)
    (0 until k).map(_ => puntos(rand.nextInt(puntos.length))).to(mutable.ArrayBuffer)
  }

}