package viii_beustchallenge

/**
 * Finds base 10 numbers whose digits don't repeat. Solution to Cedric's coding
 * challenge: http://beust.com/weblog/archives/000491.html

 * @author Bob Lee (crazybob@crazybob.org)
 */
public class JavaBeustSequence {

    public trait Listener {

        public fun hear(newValue: Long)
    }

    /** A digit, part of a doubly-linked set.  */
    class Digit
    /** Constructs digit with given value as well as all subsequent digits.  */
    (var previous: Digit?, val value: Int) {
        var next: Digit? = null

        {

            // Recursively construct subsequent digits.
            if (value < 9) next = Digit(this, value + 1)
        }

        /** Removes digit from the set.  */
        fun use() {
            if (previous != null) previous!!.next = next
            if (next != null) next!!.previous = previous
        }

        /** Puts digit back into the set.  */
        fun yield() {
            if (previous != null) previous!!.next = this
            if (next != null) next!!.previous = this
        }
    }

    companion object {

        /**
         * Finds all numbers in sequence up to max.

         * @param max maximum sequence value
         * *
         * @param listener hears search results
         */
        public fun findAll(max: Long, listener: Listener) {
            val zero = Digit(null, 0)
            val one = zero.next
            for (length in 1..10) {
                if (find(one, zero, length, 0, max, listener)) return
            }
        }

        /**
         * Called recursively for each digit from most to least significant.

         * @param start digit to start with, used to skip 0 for most significant digit
         * *
         * @param head of set
         * *
         * @param remaining digit count
         * *
         * @param value so far
         * *
         * @param max value
         * *
         * @param listener hears results
         * *
         * @return true if we reached max, false otherwise
         */
        private fun find(start: Digit, head: Digit, remaining: Int, value: Long, max: Long, listener: Listener): Boolean {
            run {
                var current: Digit? = start
                while (current != null) {
                    val newValue = value + current!!.value.toLong()
                    if (remaining == 1) {
                        if (newValue > max) return true
                        listener.hear(newValue)
                    } else {
                        current!!.use()
                        val newHead = if ((current == head)) head.next else head
                        if (find(newHead, newHead, remaining - 1, newValue * 10, max, listener))
                            return true
                        current!!.yield()
                    }
                    current = current!!.next
                }
            }
            return false
        }
    }


}