enum class ReservationStatus {
    ACTIVE,
    USER_CANCELLED,
    EVENT_CANCELLED
}

class Reservation(
    val id: Int,
    val studentName: String,
    val eventId: Int,
    var status: ReservationStatus = ReservationStatus.ACTIVE
)