package ex

fun <P1, P2, RETURN> curry(block: (P1, P2) -> RETURN): (P1) -> ((P2) -> RETURN) = { p1 -> { p2 -> block(p1, p2) } }

fun <P1, P2, RETURN> ((P1, P2) -> RETURN).curry(p1: P1): (P2) -> RETURN = { p2 -> this(p1, p2) }

fun main() {
    val c1 = curry { a: Int, b: Int ->
        a + b
    }
    println("c1(2)(5) actual = ${c1(2)(5)} expected = 7")
    println("c1(1)(3) actual = ${c1(1)(3)} expected = 4")

    val block = { a: Int, b: Int ->
        a + b
    }
    block.curry(2)(5)
    block.curry(1)(3)

    println("block.curry(2)(5) actual = ${block.curry(2)(5)} expected = 7")
    println("block.curry(1)(3) actual = ${block.curry(1)(3)} expected = 4")
}
