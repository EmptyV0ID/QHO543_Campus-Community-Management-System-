import java.time.LocalDate
import java.time.LocalTime

enum class EventStatus {
    UPCOMING,
    FULL,
    COMPLETED,
    CANCELLED
}

class Event(
    val id: String,
    var title: String,
    var category: String,
    var organiser: String,
    var date: LocalDate,
    var time: LocalTime,
    var location: String,
    var maxCapacity: Int,
    var initialStatus: EventStatus
) {
    fun getStatus(activeReservations: Int): EventStatus {
        return when {
            initialStatus == EventStatus.CANCELLED -> EventStatus.CANCELLED
            initialStatus == EventStatus.COMPLETED -> EventStatus.COMPLETED
            activeReservations >= maxCapacity -> EventStatus.FULL
            else -> EventStatus.UPCOMING
        }
    }

    fun getRemainingCapacity(activeReservations: Int): Int {
        return maxCapacity - activeReservations
    }
}