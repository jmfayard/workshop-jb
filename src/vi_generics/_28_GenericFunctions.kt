package vi_generics.generics

import java.util.ArrayList
import java.util.HashSet
import util.TODO

fun task28() = TODO(
    """
        Task28.
        Add function 'partitionTo' that splits collection in two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is the function 'partition()' in the standard library that always returns two lists (newly created).
        You shall write a function that splits the collection in two given collections as arguments.
        The signature of the function 'toCollection()' from standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

public trait ListAndSet<T> : List<T>, Set<T>

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
//    task28()
    return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
//    task28()
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}

fun <T> partitionTo(hashSet1: Collection<T>, hashSet2: Collection<T>, predicate: (T) -> Boolean ): Pair<ListAndSet<T>, ListAndSet<T>> {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
}

//fun <T> partitionTo(first: Collection<T>, second: Collection<*>, predicate: (T) -> Boolean): Pair<List<T>, List<T>> {


//public inline fun <T> partitionTo(c1, Collection 2, predicate: (T) -> Boolean): Pair<List<T>, List<T>> {

