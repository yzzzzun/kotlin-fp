package ex

//fun fib(i:Int): Int {
//    tailrec fun go(offset: Int, a:Int, b:Int):Int = if(offset<i) go(offset+1,b,a+b) else a+b
//    return if(i<=1) i else go(2,0,1)
//}

internal tailrec fun _fib(curr: Int, limit: Int, pprev: Int, prev: Int): Int =
    if (curr == limit) pprev + prev else _fib(curr + 1, limit, prev, pprev + prev)

fun fib(i: Int): Int = when (i) {
    in 0..1 -> i
    else -> _fib(2, i, 0, 1)
}
