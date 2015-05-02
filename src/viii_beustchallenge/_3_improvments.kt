package viii_beustchallenge._3_

import viii_beustchallenge._1_.MyListener


public trait Listener {
    public fun hear(newValue: Long)
}

/** A digit, part of a doubly-linked set.  */
/** Constructs digit with given value as well as all subsequent digits.  */
class Digit (var previous: Digit?, val value: Int) {
    var next: Digit?

    {   /** this is the constructor. It builds subsequents digits recursively **/

        next = null
        if (value < 9) next = Digit(this, value + 1)
    }

    /** Removes digit from the set.  */
    fun use() {
        // the Elvis Operator works for assignments too!
        previous?.next = next
        next?.previous = previous
    }

    /** Puts digit back into the set.  */
    fun yield() {
        previous?.next = this
        next?.previous = this
    }
}

public class _3_BeustSequence(val max : Long) : MyListener() {

    /**
     * Finds all numbers in sequence up to max.

     * @param max maximum sequence value
     * @param listener hears search results
     */
    public fun findAll() {
        val zero = Digit(null, 0)
        for (length in 1..10) {
            if (find(zero, length, 0)) return
        }
    }

    /**
     * Called recursively for each digit from most to least significant.

     * @param head of set
     * @param remaining digit count
     * @param value so far
     * @param max value
     * @return true if we reached max, false otherwise
     */
    private fun find(head: Digit?, remaining: Int, value: Long): Boolean {
        // we change a bit the loop because Kotlin can only do Null Safety Checks
        // where b is immutable (i.e. a local val or a member val which has a backing field and is not overridable),
        // because otherwise it might happen that b changes to null after the check.

        var firstLoop = true
        var next : Digit? = null
        while (true) {
            val current : Digit?
            if (firstLoop && value == 0L) {
                // avoid trailing zeroes without the need for the start parameter
                current = head?.next
            } else if (firstLoop) {
                current = head
            } else {
                current = next
            }

            if (current == null) {
                return false

            } else if (remaining == 1) {
                val newValue = value + current.value
                if (newValue > max) return true
                hear(newValue)

            } else {
                val nextValue = 10*(value + current.value)
                current.use()
                val newHead = if ((current == head)) head.next else head
                if (find(newHead, remaining - 1, nextValue))
                    return true
                current.yield()
            }
            firstLoop = false
            next = current.next
        }

    }

}

fun main(args: Array<String>) {
    val solution = _3_BeustSequence(Long.MAX_VALUE)
    solution.findAll()

    println("""
    == _3_ Kotlin adaptation of Crazy Bob's algorithm  ==
    ${solution.found} integer found in ${solution.latestAt - solution.startedAt} miliseconds
    """)

    /**** RESULT :
    == _3_ Kotlin adaptation of Crazy Bob's algorithm  ==
    8877690 integer found in 463 miliseconds


     */

}