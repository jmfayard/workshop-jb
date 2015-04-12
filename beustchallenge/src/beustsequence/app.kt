package beustsequence


class SequenceListener(max : Long) : JavaBeustSequence.Listener {
    val _max = max

    var found = 0
    override fun hear(newValue: Long) {
        found++
    }

    val _start = System.currentTimeMillis()
    fun duration() : Long =
            System.currentTimeMillis() - _start

    override fun toString() =
        "${found} results in ${duration()} miliseconds for max=$_max"
}

fun  javaimpl(max : Long) : String {
        val listener = SequenceListener(max)
        JavaBeustSequence.findAll(max, listener)
        return listener.toString()
}

fun kotlinImpl(max : Long) : String {
    val listener = SequenceListener(max)
    kotlinFindAll(max, listener)
    return listener.toString()
}

fun main(args: Array<String>) {

    println("""
        == Crazy Bob's Java implementation ==
        ${javaimpl(10000L)}
        ${javaimpl(Long.MAX_VALUE)}

        == Crazy Bob's Kotlin version ==
        // way too simple : in intellij, copy-pasting the code does most of the work for you
        ${kotlinImpl(10000L)}
        ${kotlinImpl(Long.MAX_VALUE)}

        """)

    /**** RESULT :

    == Crazy Bob's Java implementation ==
    5274 results in 2 miliseconds for max=10000
    8877690 results in 86 miliseconds for max=9223372036854775807

    == First Kotlin Implementation ==
    // in intellij, copy-pasting the code does most of the work for you
    5274 results in 2 miliseconds for max=10000
    8877690 results in 96 miliseconds for max=9223372036854775807

     */

}

