package object Canicas {

  type Frasco = (Int, Int)
  type Distr = List[Frasco]


  def canicasPosiblesFrasco(f: Int, c: Int): List[Frasco] = {
    (for {
      j <- 0 to c
    } yield (f, j)).toList

  }

  /**
   *
   * @param n numero de frascos
   * @param c numero de canicas
   * @return
   */
  def canicasPorFrasco(n: Int, c: Int): List[Distr] = {
    (for {
      i <- 1 to n
      l <- canicasPosiblesFrasco(i, c) :: Nil
    } yield l).toList
  }

  /**
   *
   * @param valIniciales canicas por frasco
   * @param comb         combinacion realizada
   * @return
   */
  def aux(valIniciales: List[Distr], comb: List[Distr]): List[Distr] = {
    if (valIniciales.isEmpty) comb else {
      val combinacion = (for {
        xs <- comb
        ys <- valIniciales.head
      } yield xs ++ List(ys))
      aux(valIniciales drop 1, combinacion)
    }
  }

  /**
   *
   * @param lc canicasPorFrasco
   * @return
   */
  def mezclarLCanicas(lc: List[Distr]): List[Distr] = {
    if (lc.isEmpty) List()
    else if (lc.length == 1) lc
    else {
      val combinacion = {
        for {
          x <- lc.head
          m <- lc(1)
        } yield List(x, m)
      }
      aux(lc drop 2, combinacion)
    }
  }


  /**
   *
   * @param m numero total de canicas
   * @param n numero de frascos
   * @param c maximas canicas por frasco
   * @return
   */
  def distribucion(m: Int, n: Int, c: Int): List[Distr] = {
    val valores1 = canicasPorFrasco(n, c)

    if (valores1.length == 1) {
      for {
        p <- valores1
        u <- p
        if u._2 == c} yield List(u)
    }
    else {
      val miListaPosible = mezclarLCanicas(valores1)
      for {
        x <- miListaPosible
        tupla = x.unzip

        if (tupla._2.sum == m)
      } yield x


    }
  }

  def agrupaciones (m: Int ):List [List [Int]] ={
    (for {
      f<-1 to m/2
      x<-distribucion(m,f,m+1-f).map(f=>f.map(a=>a._2).toSet)
    } yield x).toSet.filter(conjunto=>conjunto.sum== m).map(conj=>conj.toList.sortWith((a, b)=>a<b)).toList.filterNot(conj=>conj.contains(0)).sortBy(conj=>conj.length)
  }
}