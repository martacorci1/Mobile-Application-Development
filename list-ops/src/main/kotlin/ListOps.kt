fun <T> List<T>.customAppend(list: List<T>): List<T> {
    var ret : List<T> = this
    list.forEach {
        ret = ret.plus(it)
    }
    return ret
}

fun List<Any>.customConcat(): List<Any> {
    var result : List<Any> = emptyList()
    for (element in this) {
        result = if(element is List<*>) {
            result.customAppend((element as List<Any>).customConcat())
        } else {
            result.plus(element)
        }
    }
    return result
}

fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    var ret : List<T> = emptyList();
    this.forEach{
        if(predicate(it)) {
            ret = ret.plus(it)
        }
    }
    return ret
}

val List<Any>.customSize: Int get() = this.lastIndex + 1

fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    var ret : List<U> = emptyList()
    this.forEach {
        ret = ret.plus(transform(it))
    }
    return ret
}

fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    var ret = initial
    this.forEach {
        ret = f(ret, it)
    }
    return ret
}

fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    var ret = initial
    this.customReverse().forEach {
        ret = f(it, ret)
    }
    return ret
}

fun <T> List<T>.customReverse(): List<T> {
    var size : Int = this.lastIndex
    var ret : List<T> = emptyList()
    for (n in size downTo 0) {
        ret = ret.plus(this[n])
    }
    return ret
}
