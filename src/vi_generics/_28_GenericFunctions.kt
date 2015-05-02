package vi_generics.generics

import java.util.ArrayList
import java.util.HashSet
import util.TODO
import kotlin.properties.Delegates

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

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    //    task28()
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
}
fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun <T> List<T>.partitionTo(dest1: List<T>, dest2: List<T>, predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
    return Pair(
            dest1.plus(this.filter(predicate)),
            dest2.plus(this.filterNot(predicate))
    )
}

fun <T> Set<T>.partitionTo(dest1: Set<T>, dest2: Set<T>, predicate: (T) -> Boolean): Pair<Set<T>, Set<T>> {
    return Pair(
            dest1.plus(this.filter(predicate)).toSet(),
            dest2.plus(this.filterNot(predicate)).toSet()
    )
}



public class Ensemble<T>(c: Collection<T>) : ArrayList<T>(c), Set<T> {

}
fun <T> Collection<T>.toEnsemble() : Ensemble<T> = Ensemble<T>(this)
fun <T> partitionTo2(c1: Collection<T>, c2: Collection<T>, predicate: (T) -> Boolean ): Pair<Ensemble<T>, Ensemble<T>> {
    val res1 = c1.filter { predicate(it) }.toEnsemble()
    val res2 = c1.filterNot { predicate(it) }.toEnsemble()
    return Pair(res1, res2)
}


