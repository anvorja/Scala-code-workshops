import Matrices._

matrizAlAzar ( 2 , 2 )
matrizAlAzar ( 4 , 2 )
matrizAlAzar( 8 , 2 )

val m1 = matrizAlAzar ( 2,2 )

val m2 = matrizAlAzar ( 2,2 )

val l1 = m1.length
val l2=  m2.length

Vector.tabulate(l1,l2) ((a,b) => a )

Vector.tabulate(l1,l2) ((a,b) => b )

Vector.tabulate(l1,l2) ((a,b) => b+1 )

Vector.tabulate(l1,l2) ((a,b) => (a,b) )

val x = Vector.tabulate(2, 2)((a,b) => (a * a, b+1))