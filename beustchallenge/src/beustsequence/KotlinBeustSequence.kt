package beustsequence

/** A digit, part of a doubly-linked set. */
class Digit(var previous : Digit?, var value : Int) {

    /**  constructs digit with given value as well as all subsequent digits.  **/
    var next : Digit? = if (value < 9)  Digit(this, value + 1)   else    null


    /** Removes digit from the set.  */
    fun use() {
        previous?.next = next
        next?.previous = previous
    }

    /** Puts digit back into the set.  */
    fun yield() {
        previous?.next = this
        next?.previous = this
    }

}

/**
 * Finds all numbers in sequence up to max.

 * @param max maximum sequence value
 * @param listener hears search results
 */
public fun kotlinFindAll(max: Long, listener: JavaBeustSequence.Listener) {
    val zero = Digit(null, 0)
    val one = zero.next
    for (length in 1..10) {
        if (kotlinFind(one, zero, length, 0, max, listener)) return
    }
}

/**
 * Called recursively for each digit from most to least significant.

 * @param start digit to start with, used to skip 0 for most significant digit
 * @param head of set
 * @param remaining digit count
 * @param value so far
 * @param max value
 * @param listener hears results
 *
 * @return true if we reached max, false otherwise
 */
private fun kotlinFind(start: Digit?, head: Digit?, remaining: Int, value: Long, max: Long, listener: JavaBeustSequence.Listener): Boolean {
    run {
        var current: Digit? = start
        while (current != null) {
            val newValue = value + current!!.value.toLong()
            if (remaining == 1) {
                if (newValue > max) return true
                listener.hear(newValue)
            } else {
                current!!.use()
                val newHead = if ((current == head)) head!!.next else head
                if (kotlinFind(newHead, newHead, remaining - 1, newValue * 10, max, listener))
                    return true
                current!!.yield()
            }
            current = current!!.next
        }
    }
    return false
}

