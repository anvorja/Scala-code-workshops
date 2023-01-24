import Newton._

//Pruebas Profe
val expr1= Suma(Atomo( 'x' ) , Numero ( 2 ) )  //  (x+2.0)
val expr2= Prod (Atomo( 'x' ) , Atomo( 'x' ) ) //  (x∗x)
val expr3= Suma( expr1 , Expo ( expr2 , Numero ( 5 ) ) ) //  ((x+2.0)+((x∗x)ˆ5.0))
val expr4= Logaritmo (Atomo( 'x' ) ) //  (lg(x))
val expr5= Prod ( Div ( expr1 , expr2 ) , Resta ( expr3 , expr4 ) ) // (((x+2.0)/(x∗x))∗(((x+2.0)+((x∗x)ˆ5.0))−(log(x))))
val expr6= Expo (Atomo( 'x' ) , Numero ( 3 ) ) // ( xˆ3.0)
val expr7= Logaritmo(Expo(Suma(Numero(25),Numero(10)),Numero(4)))  // (log(((25+10)^4)))
val expr8= Expo(Atomo('x'),Resta(Numero(6),Numero(2))) // (x^(6-2))
val expr9= Expo(Div(Numero(4),Numero(6)),Atomo('x')) // ((4/6)^x)
val expr10= Prod(Atomo('x'),Suma(Numero(7),Expo(Atomo('x'),Numero(2)))) // (x*(7+(x^2)))
val expr11= Resta(expr9,Prod(Numero(2),Atomo('x'))) // (((4/6)^x)-(2*x))

//Pruebas Propias
mostrar(Numero(16))
mostrar(Atomo ('x'))
mostrar(Suma(Atomo( 'x' ) , Numero ( 2 ) ))

mostrar(expr1)
mostrar(expr2)
mostrar(expr3)
mostrar(expr4)
mostrar(expr5)
mostrar(expr6)
mostrar(expr7)
mostrar(expr8)
mostrar(expr9)
mostrar(expr10)
mostrar(expr11)