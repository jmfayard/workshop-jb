package ii_conventions

import i_introduction._0_Hello_World.Hello._00_Start
import syntax.qualifiedThis.labelsForExtensionFunctionLiterals

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : B {
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
    fun maxDayOfTheMonth() : Int {
        return when(month) {
            2                       ->  28
            1, 3, 5, 7, 8, 10, 12   ->  31
            4, 6, 9, 11             -> 30
            else                    -> -1
        }
    }
    //fun copy(year: Int = this.year, month: Int = this.month, dayOfMonth: Int = this.dayOfMonth)  = MyDate(year, month, dayOfMonth)

    fun nextDay() : MyDate {
        if (dayOfMonth >= maxDayOfTheMonth())
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

enum class TimeInterval {
    DAY
    WEEK
    YEAR
}
/*
        Make class DateRange implement Iterable<MyDate>.
        Use object expression to implement Iterator<MyDate>.
        Use an utility function 'MyDate.nextDay()'.
 */
class DateRange(public val start: MyDate, public val end: MyDate) : Iterable<MyDate> {
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
