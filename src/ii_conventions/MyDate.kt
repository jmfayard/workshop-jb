package ii_conventions

import i_introduction._0_Hello_World.Hello._00_Start
import syntax.enums.Day
import syntax.qualifiedThis.labelsForExtensionFunctionLiterals

/**
Use classes MyDate and TimeInterval.
Use a utility function MyDate.addTimeIntervals.
Uncomment the commented line and make it compile.

(1). Add an extension function 'plus()' to MyDate, taking TimeInterval as an argument.
(2). Support adding several time intervals to a date. Add an extra class.
If any problems, see ii_conventions/_15_Tips.kt.
 */
enum class TimeInterval  {
    DAY
    WEEK
    YEAR
}

// Assert.assertEquals(MyDate(2016, 1, 20), task15_2(MyDate(2014, 0, 25)))
//return today + YEAR * 2 + WEEK * 3 + DAY * 5
// 25+26 = 51

data class MyDate(var year: Int, var month: Int, var dayOfMonth: Int) : B, kotlin.Comparable<MyDate>  {
    init {
        while (dayOfMonth > daysThisMonth()) {
            dayOfMonth -= daysThisMonth()
            month++
        }
        while (month > 12) {
            month = month-12
            year = year++
        }
    }

    fun times(a: TimeInterval): MyDate {
        throw UnsupportedOperationException()
    }
    fun plus(a: TimeInterval): MyDate {
        return when(a) {
                TimeInterval.DAY  ->  copy(dayOfMonth = dayOfMonth+1)
                TimeInterval.WEEK ->  copy(dayOfMonth = dayOfMonth+7)
                TimeInterval.YEAR ->  copy(year = year +1)
                else ->  this
            }
    }
    fun plus(a : RepeatedTimeInterval): MyDate {
        if (a.ti == TimeInterval.YEAR)
            return copy(year = year + a.n)
        else {
            val days = if (a.ti == TimeInterval.DAY)  a.n else 7*a.n
            return copy(dayOfMonth = dayOfMonth + days)

        }

    }

    override fun compareTo(other: MyDate): Int = compareTo(other as B)

    override fun compareTo(other: B): Int {
        if (other !is MyDate) {
            throw ClassCastException()
        } else {
            var diffYear = year - other.year
            var diffMonth = month - other.month
            var diffDays = dayOfMonth - other.dayOfMonth
            if (diffYear != 0)
                return diffYear
            else if (diffMonth != 0)
                return diffMonth
            else if (diffDays != 0)
                return diffDays
            else
                return 0
        }
    }
    fun daysThisMonth() : Int {
        return when(month+1) {
            2                       ->  28
            1, 3, 5, 7, 8, 10, 12   ->  31
            4, 6, 9, 11             -> 30
            else                    -> -1
        }
    }
    //fun copy(year: Int = this.year, month: Int = this.month, dayOfMonth: Int = this.dayOfMonth)  = MyDate(year, month, dayOfMonth)

    fun nextDay() : MyDate {
        if (dayOfMonth >= daysThisMonth())
            if (month == 12)
                return copy(year = year+1, month = 1, dayOfMonth = 1)
            else
                return copy(month = month+1, dayOfMonth = 1)
        else
                return copy(dayOfMonth = dayOfMonth+1)
    }

    fun rangeTo(endDate: MyDate): DateRange {
        return  DateRange(this, endDate)
    }

}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

fun TimeInterval.times(i: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(ti = this, n = i)
}



/*
        Make class DateRange implement Iterable<MyDate>.
        Use object expression to implement Iterator<MyDate>.
        Use an utility function 'MyDate.nextDay()'.
 */
class DateRange(public override val start: MyDate, public override val end: MyDate) : kotlin.Range<MyDate>, Iterable<MyDate> {
    override fun contains(item: MyDate): Boolean {
        return (item >= start) && (item < end)
    }

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var current : MyDate = start
            override fun hasNext(): Boolean {
                return current <= end
            }

            override fun next(): MyDate {
                val result = current
                current = current.nextDay()
                return result
            }

        }
    }
}
