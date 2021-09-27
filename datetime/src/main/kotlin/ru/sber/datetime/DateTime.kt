package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val zoneid = ZoneId.getAvailableZoneIds()
        .filter { TimeZone.getTimeZone(it).rawOffset % 3600000 != 0 }
        .toSet()
    return zoneid
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastDays = mutableListOf<String>()
    val years = Month.values()
    for (months in years)
        lastDays.add(YearMonth.of(year, months).atEndOfMonth().dayOfWeek.toString())
    return lastDays
}


// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var sum = 0
    val years = Month.values()
    for (months in years) {
        if (DayOfWeek.FRIDAY == LocalDate.of(year, months, 13).dayOfWeek) {
            sum++
        }
    }
    return sum
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return DateTimeFormatter.ofPattern("dd MMM yyy, HH:mm", Locale.US).format(dateTime)
}



