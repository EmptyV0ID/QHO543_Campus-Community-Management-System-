import java.time.LocalDate
import java.time.LocalTime

class EventManager {

    val events = mutableListOf(
        Event(
            1,
            "Welcome Party",
            "Social",
            "Student Union",
            LocalDate.of(2026, 10, 5),
            LocalTime.of(18, 0),
            "Main Hall",
            100,
            45
        ),
        Event(
            2,
            "Kotlin Workshop",
            "Academic",
            "Computing Society",
            LocalDate.of(2026, 10, 15),
            LocalTime.of(14, 0),
            "Computer Lab 2",
            30,
            18
        ),
        Event(
            3,
            "Five-a-Side Football",
            "Sports",
            "University Sports Club",
            LocalDate.of(2026, 11, 2),
            LocalTime.of(16, 30),
            "University Sports Ground",
            20,
            20
        ),
        Event(
            4,
            "Summer Charity Fair",
            "Charity",
            "Student Volunteers",
            LocalDate.of(2026, 7, 10),
            LocalTime.of(12, 0),
            "University Courtyard",
            150,
            87
        )
    )

    fun viewAllEvents() {
        displayEvents(events)
    }

    fun viewUpcomingEvents() {
        val upcomingEvents = events.filter {
            it.getStatus() == EventStatus.UPCOMING ||
                    it.getStatus() == EventStatus.FULL
        }

        displayEvents(upcomingEvents)
    }

    fun viewCompletedEvents() {
        val completedEvents = events.filter {
            it.getStatus() == EventStatus.COMPLETED
        }

        displayEvents(completedEvents)
    }

    fun viewCancelledEvents() {
        val cancelledEvents = events.filter {
            it.getStatus() == EventStatus.CANCELLED
        }

        displayEvents(cancelledEvents)
    }

    private fun displayEvents(eventList: List<Event>) {

        if (eventList.isEmpty()) {
            println("No events found.")
            return
        }

        for (event in eventList) {
            println()
            println("--------------------------------------")
            println("Event ID: ${event.id}")
            println("Title: ${event.title}")
            println("Category: ${event.category}")
            println("Organiser: ${event.organiser}")
            println("Date: ${event.date}")
            println("Time: ${event.time}")
            println("Location: ${event.location}")
            println("Maximum Capacity: ${event.maxCapacity}")
            println("Reservations: ${event.reservationCount}")
            println("Remaining Capacity: ${event.getRemainingCapacity()}")
            println("Status: ${event.getStatus()}")
            println("--------------------------------------")
        }
    }
}