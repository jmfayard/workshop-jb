package beustsequence;

/**
 * Finds base 10 numbers whose digits don't repeat. Solution to Cedric's coding
 * challenge: http://beust.com/weblog/archives/000491.html
 *
 * @author Bob Lee (crazybob@crazybob.org)
 */
public class JavaBeustSequence {
    
    public interface Listener {

        void hear(long newValue);
    }

    /**
     * Finds all numbers in sequence up to max.
     *
     * @param max maximum sequence value
     * @param listener hears search results
     */
    public static void findAll(long max, Listener listener) {
        Digit zero = new Digit(null, 0);
        Digit one = zero.next;
        for (int length = 1; length <= 10; length++) {
            if (find(one, zero, length, 0, max, listener)) return;
        }
    }

    /**
     * Called recursively for each digit from most to least significant.
     *
     * @param start digit to start with, used to skip 0 for most significant digit
     * @param head of set
     * @param remaining digit count
     * @param value so far
     * @param max value
     * @param listener hears results
     * @return true if we reached max, false otherwise
     */
    private static boolean find(Digit start, Digit head, int remaining,
                                long value, long max, Listener listener) {
        for (Digit current = start; current != null; current = current.next) {
            long newValue = value + current.value;
            if (remaining == 1) {
                if (newValue > max) return true;
                listener.hear(newValue);
            } else {
                current.use();
                Digit newHead = (current == head) ? head.next : head;
                if (find(newHead, newHead, remaining - 1, newValue * 10, max, listener))
                    return true;
                current.yield();
            }
        }
        return false;
    }

    /** A digit, part of a doubly-linked set. */
    static class Digit {

        final int value;
        Digit previous;
        Digit next;

        /** Constructs digit with given value as well as all subsequent digits. */
        Digit(Digit previous, int value) {
            this.value = value;
            this.previous = previous;

            // Recursively construct subsequent digits.
            if (value < 9) next = new Digit(this, value + 1);
        }

        /** Removes digit from the set. */
        void use() {
            if (previous != null) previous.next = next;
            if (next != null) next.previous = previous;
        }

        /** Puts digit back into the set. */
        void yield() {
            if (previous != null) previous.next = this;
            if (next != null) next.previous = this;
        }
    }


}