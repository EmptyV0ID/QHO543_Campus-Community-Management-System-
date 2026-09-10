import java.time.LocalDate
import java.time.LocalTime

enum class EventStatus {
    UPCOMING,
    FULL,
    COMPLETED,
    CANCELLED
}

class Event(
    val id: Int,
    var title: String,
    var category: String,
    var organiser: String,
    var date: LocalDate,
    var time: LocalTime,
    var location: String,
    var maxCapacity: Int,
    var reservationCount: Int = 0,
    var cancelled: Boolean = false
) {
    fun getRemainingCapacity(): Int {
        return maxCapacity - reservationCount
    }

    fun getStatus(): EventStatus {
        return when {
            cancelled -> EventStatus.CANCELLED
            date.isBefore(LocalDate.now()) -> EventStatus.COMPLETED
            getRemainingCapacity() <= 0 -> EventStatus.FULL
            else -> EventStatus.UPCOMING
        }
    }
}