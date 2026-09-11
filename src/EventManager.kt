import java.time.LocalDate
import java.time.LocalTime

class EventManager {

    val reservations = mutableListOf(
        Reservation(
            1,
            "Alice Brown",
            1
        ),
        Reservation(
            2,
            "David Green",
            1
        ),
        Reservation(
            3,
            "Sarah Jones",
            2
        )
    )

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

    // -------------------------
    // TASK 4 - VIEW EVENTS
    // -------------------------

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

    // -------------------------
    // TASK 5 - ADD EVENT
    // -------------------------

    fun addEvent() {
        println()
        println("========== ADD COMMUNITY EVENT ==========")

        print("Enter event title: ")
        val title = readln().trim()

        print("Enter category: ")
        val category = readln().trim()

        print("Enter organiser: ")
        val organiser = readln().trim()

        print("Enter date (YYYY-MM-DD): ")
        val dateInput = readln().trim()

        print("Enter time (HH:MM): ")
        val timeInput = readln().trim()

        print("Enter location: ")
        val location = readln().trim()

        print("Enter maximum capacity: ")
        val capacityInput = readln().trim()

        // Check that text fields are not empty
        if (
            title.isBlank() ||
            category.isBlank() ||
            organiser.isBlank() ||
            location.isBlank()
        ) {
            println("Error: Text fields cannot be empty.")
            return
        }

        // Convert and validate the date
        val date = try {
            LocalDate.parse(dateInput)
        } catch (e: Exception) {
            println("Error: Invalid date. Please use YYYY-MM-DD.")
            return
        }

        // Convert and validate the time
        val time = try {
            LocalTime.parse(timeInput)
        } catch (e: Exception) {
            println("Error: Invalid time. Please use HH:MM.")
            return
        }

        // Convert and validate maximum capacity
        val maxCapacity = capacityInput.toIntOrNull()

        if (maxCapacity == null || maxCapacity <= 0) {
            println("Error: Maximum capacity must be a number greater than 0.")
            return
        }

        // Generate the next available event ID
        val newId = if (events.isEmpty()) {
            1
        } else {
            events.maxOf { it.id } + 1
        }

        // Create the new Event object
        val newEvent = Event(
            id = newId,
            title = title,
            category = category,
            organiser = organiser,
            date = date,
            time = time,
            location = location,
            maxCapacity = maxCapacity
        )

        // Store the new event
        events.add(newEvent)

        println()
        println("Community event added successfully.")
        println("New Event ID: ${newEvent.id}")
    }

    fun modifyEvent() {
        println()
        println("========== MODIFY COMMUNITY EVENT ==========")

        // Show the administrator the available events first
        displayEvents(events)

        print("Enter the Event ID you want to modify: ")
        val eventId = readln().toIntOrNull()

        if (eventId == null) {
            println("Error: Please enter a valid Event ID.")
            return
        }

        val event = events.find { it.id == eventId }

        if (event == null) {
            println("Error: Event not found.")
            return
        }

        println()
        println("Selected Event: ${event.title}")
        println()
        println("1. Change Title")
        println("2. Change Category")
        println("3. Change Organiser")
        println("4. Change Date")
        println("5. Change Time")
        println("6. Change Location")
        println("7. Change Maximum Capacity")
        println("8. Cancel Modification")
        print("Enter your choice: ")

        when (readln()) {

            "1" -> {
                print("Enter new title: ")
                val newTitle = readln().trim()

                if (newTitle.isBlank()) {
                    println("Error: Title cannot be empty.")
                    return
                }

                event.title = newTitle
            }

            "2" -> {
                print("Enter new category: ")
                val newCategory = readln().trim()

                if (newCategory.isBlank()) {
                    println("Error: Category cannot be empty.")
                    return
                }

                event.category = newCategory
            }

            "3" -> {
                print("Enter new organiser: ")
                val newOrganiser = readln().trim()

                if (newOrganiser.isBlank()) {
                    println("Error: Organiser cannot be empty.")
                    return
                }

                event.organiser = newOrganiser
            }

            "4" -> {
                print("Enter new date (YYYY-MM-DD): ")
                val dateInput = readln().trim()

                val newDate = try {
                    LocalDate.parse(dateInput)
                } catch (e: Exception) {
                    println("Error: Invalid date. Please use YYYY-MM-DD.")
                    return
                }

                event.date = newDate
            }

            "5" -> {
                print("Enter new time (HH:MM): ")
                val timeInput = readln().trim()

                val newTime = try {
                    LocalTime.parse(timeInput)
                } catch (e: Exception) {
                    println("Error: Invalid time. Please use HH:MM.")
                    return
                }

                event.time = newTime
            }

            "6" -> {
                print("Enter new location: ")
                val newLocation = readln().trim()

                if (newLocation.isBlank()) {
                    println("Error: Location cannot be empty.")
                    return
                }

                event.location = newLocation
            }

            "7" -> {
                print("Enter new maximum capacity: ")
                val newCapacity = readln().toIntOrNull()

                if (newCapacity == null || newCapacity <= 0) {
                    println("Error: Capacity must be a number greater than 0.")
                    return
                }

                if (newCapacity < event.reservationCount) {
                    println(
                        "Error: Capacity cannot be lower than the current " +
                                "number of reservations (${event.reservationCount})."
                    )
                    return
                }

                event.maxCapacity = newCapacity
            }

            "8" -> {
                println("Modification cancelled.")
                return
            }

            else -> {
                println("Error: Invalid choice.")
                return
            }
        }

        println()
        println("Event updated successfully.")
    }

    fun cancelEvent() {
        println()
        println("========== CANCEL COMMUNITY EVENT ==========")

        displayEvents(events)

        print("Enter the Event ID you want to cancel: ")
        val eventId = readln().toIntOrNull()

        if (eventId == null) {
            println("Error: Please enter a valid Event ID.")
            return
        }

        val event = events.find { it.id == eventId }

        if (event == null) {
            println("Error: Event not found.")
            return
        }

        when (event.getStatus()) {

            EventStatus.CANCELLED -> {
                println("Error: This event is already cancelled.")
                return
            }

            EventStatus.COMPLETED -> {
                println("Error: Completed events cannot be cancelled.")
                return
            }

            EventStatus.UPCOMING,
            EventStatus.FULL -> {
                print("Are you sure you want to cancel '${event.title}'? (Y/N): ")

                val confirmation = readln().trim().uppercase()

                if (confirmation != "Y") {
                    println("Cancellation stopped.")
                    return
                }

                event.cancelled = true

                // Update active reservations for this event
                var updatedReservations = 0

                for (reservation in reservations) {
                    if (
                        reservation.eventId == event.id &&
                        reservation.status == ReservationStatus.ACTIVE
                    ) {
                        reservation.status = ReservationStatus.EVENT_CANCELLED
                        updatedReservations++
                    }
                }

                println()
                println("Event cancelled successfully.")
                println("Event ID: ${event.id}")
                println("Status: ${event.getStatus()}")
                println("Reservations updated: $updatedReservations")
            }
        }
    }
}