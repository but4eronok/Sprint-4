package ru.sber.generics

//import com.sun.org.apache.xpath.internal.operations.Bool
//import java.util.*

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return p1 == p2
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private var stack = ArrayDeque<T>()
    fun push(element: T) = stack.addFirst(element)
    fun pop() = stack.removeFirst()
    fun isEmpty(): Boolean = stack.isEmpty()
    fun peek() = stack.first()
    fun search(element: T): T? {
        return stack.find { it == element }
    }
}