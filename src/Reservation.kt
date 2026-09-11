enum class ReservationStatus {
    ACTIVE,
    USER_CANCELLED,
    EVENT_CANCELLED,
    COMPLETED
}

class Reservation(
    val id: String,
    val studentId: String,
    val eventId: String,
    var status: ReservationStatus
)