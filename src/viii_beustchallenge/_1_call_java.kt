package viii_beustchallenge

import syntax.operatorOverloading.test
import kotlin.properties.Delegates

/**
 * Created by jmfayard on 03/05/15.
 */

/**
 * First step: we simply use the fact that Kotlin is interoperable with java
 *    and call diretly `JavaBeustSequence.java`
 *
 *  We only need to implement interface Listener with this one function
 *      public fun hear(newValue:Long)
 */
class MyListener : JavaBeustSequence.Listener {
    val startedAt : Long    = System.currentTimeMillis()
    var latestAt  : Long    = System.currentTimeMillis()
    var found = 0

    override fun hear(newValue: Long) {
        found++
        latestAt = System.currentTimeMillis()
    }
}

fun main(args: Array<String>) {
    val listener = MyListener()
    JavaBeustSequence.findAll(Long.MAX_VALUE, listener)

    println("""
    == _1_ Call Crazy Bob's Java implementation ==
    ${listener.found} integer found in ${listener.latestAt - listener.startedAt} miliseconds
    """)

    /**** RESULT :
    == _1_ Call Crazy Bob's Java implementation ==
    8877690 integer found in -83 miliseconds


     */

}
