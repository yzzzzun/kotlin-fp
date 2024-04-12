package ex

fun <P1, P2, RETURN> uncurry(block: (P1) -> (P2) -> RETURN): (P1, P2) -> RETURN =
    { p1: P1, p2: P2 ->
        block(p1)(p2)
    }

fun <P1, RESULT, RETURN> compose(result: (RESULT) -> RETURN, block: (P1) -> RESULT): (P1) -> RETURN = { p1: P1 ->
    result(block(p1))
}

//infix fun <P, RESULT, RETURN> ((P) -> RESULT).compose(block: (RESULT) -> RETURN): (P) -> RETURN =
//    { p -> block(this(p)) }

operator fun <P, RESULT, RETURN> ((P) -> RESULT).plus(block: (RESULT) -> RETURN): (P) -> RETURN =
    { p -> block(this(p)) }

fun main() {
    val f1 = compose({ it: Int -> it * 2 }, { it: Int -> it + 5 })
    val f2 = { it: Int -> it + 5 } + { it * 2 }
    println(f1(2))
    println(f2(2))
}
