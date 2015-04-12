package ii_conventions

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

}

enum class TimeInterval {
    DAY
    WEEK
    YEAR
}

class DateRange(public val start: MyDate, public val end: MyDate)
