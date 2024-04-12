package ex

//내부적으로 예외를 발생시킨다 -> 부수효과
inline val <T> List<T>.head: T get() = first()
inline val <T> List<T>.tail: List<T> get() = drop(1)
//
//fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
//    tailrec fun go(x: A, xs: List<A>): Boolean =
//        if (xs.isEmpty()) true
//        else if (!order(x, xs.head)) false
//        else go(xs.head, xs.tail)
//
//    return if (aa.isEmpty()) true else go(aa.head, aa.tail)
//
//}

internal tailrec fun <T> _isSorted(head: T, tail: List<T>, check: T.(T) -> Boolean): Boolean {
    val tailHead = tail.head
    val tailTail = tail.tail

    return when {
        !check(head, tailHead) -> false
        tailTail.isEmpty() -> true
        else -> _isSorted(tailHead, tailTail, check)
    }
}

fun <T> List<T>.isSorted(check: T.(T) -> Boolean): Boolean = when (size) {
    in 0..1 -> true
    else -> _isSorted(head, tail, check)
}

fun main() {
    val list1 = listOf<Int>(1, 2)
    val sorted = list1.isSorted { i -> this < i }
    println("sorted = ${sorted}")

//    val sorted = isSorted(list1) { a, b -> a < b }
//    println("sorted = ${sorted}")
}
