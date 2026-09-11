import java.time.LocalDate
import java.time.LocalTime

class EventManager {

    // --------------------------------
    // Data provided for the task
    // --------------------------------

    val reservations = mutableListOf(
        Reservation("R001", "S001", "E001", ReservationStatus.ACTIVE),
        Reservation("R002", "S002", "E001", ReservationStatus.ACTIVE),
        Reservation("R003", "S003", "E001", ReservationStatus.USER_CANCELLED),

        Reservation("R004", "S001", "E002", ReservationStatus.ACTIVE),
        Reservation("R005", "S002", "E002", ReservationStatus.ACTIVE),
        Reservation("R006", "S004", "E002", ReservationStatus.ACTIVE),
        Reservation("R007", "S005", "E002", ReservationStatus.ACTIVE),

        Reservation("R008", "S006", "E003", ReservationStatus.ACTIVE),

        Reservation("R009", "S001", "E004", ReservationStatus.COMPLETED),
        Reservation("R010", "S003", "E004", ReservationStatus.COMPLETED),

        Reservation("R011", "S004", "E005", ReservationStatus.COMPLETED),
        Reservation("R012", "S005", "E005", ReservationStatus.USER_CANCELLED),

        Reservation("R013", "S002", "E006", ReservationStatus.EVENT_CANCELLED),
        Reservation("R014", "S006", "E006", ReservationStatus.EVENT_CANCELLED)
    )

    // --------------------------
    // Event data
    // --------------------------

    val events = mutableListOf(
        Event(
            id = "E001",
            title = "Python Coding Workshop",
            category = "Academic",
            organiser = "Helen Moore",
            date = LocalDate.of(2026, 10, 14),
            time = LocalTime.of(14, 0),
            location = "Computing Lab B12",
            maxCapacity = 6,
            initialStatus = EventStatus.UPCOMING
        ),

        Event(
            id = "E002",
            title = "Five-a-Side Football",
            category = "Sports",
            organiser = "Alex Turner",
            date = LocalDate.of(2026, 10, 16),
            time = LocalTime.of(17, 0),
            location = "Sports Hall",
            maxCapacity = 4,
            initialStatus = EventStatus.UPCOMING
        ),

        Event(
            id = "E003",
            title = "Careers in Technology",
            category = "Careers",
            organiser = "Rachel Adams",
            date = LocalDate.of(2026, 10, 20),
            time = LocalTime.of(13, 0),
            location = "Lecture Hall 2",
            maxCapacity = 6,
            initialStatus = EventStatus.UPCOMING
        ),

        Event(
            id = "E004",
            title = "Student Wellbeing Workshop",
            category = "Wellbeing",
            organiser = "Sarah Lewis",
            date = LocalDate.of(2026, 10, 5),
            time = LocalTime.of(11, 0),
            location = "Room C05",
            maxCapacity = 5,
            initialStatus = EventStatus.COMPLETED
        ),

        Event(
            id = "E005",
            title = "Community Volunteering Day",
            category = "Volunteering",
            organiser = "James Wilson",
            date = LocalDate.of(2026, 10, 2),
            time = LocalTime.of(9, 0),
            location = "Community Hub",
            maxCapacity = 6,
            initialStatus = EventStatus.COMPLETED
        ),

        Event(
            id = "E006",
            title = "International Students Social",
            category = "Social",
            organiser = "Maria Costa",
            date = LocalDate.of(2026, 10, 18),
            time = LocalTime.of(18, 0),
            location = "Student Union",
            maxCapacity = 5,
            initialStatus = EventStatus.CANCELLED
        )
    )

    // --------------------------------
    // Reservation calculation
    // --------------------------------

    private fun getActiveReservationCount(eventId: String): Int {
        return reservations.count {
            it.eventId == eventId &&
                    it.status == ReservationStatus.ACTIVE
        }
    }

    // -------------------------
    // Event viewing
    // -------------------------

    fun viewAllEvents() {
        displayEvents(events)
    }

    fun viewUpcomingEvents() {
        val upcomingEvents = events.filter {
            val activeReservations = getActiveReservationCount(it.id)
            val status = it.getStatus(activeReservations)

            status == EventStatus.UPCOMING ||
                    status == EventStatus.FULL
        }

        displayEvents(upcomingEvents)
    }

    fun viewCompletedEvents() {
        val completedEvents = events.filter {
            val activeReservations = getActiveReservationCount(it.id)

            it.getStatus(activeReservations) == EventStatus.COMPLETED
        }

        displayEvents(completedEvents)
    }

    fun viewCancelledEvents() {
        val cancelledEvents = events.filter {
            val activeReservations = getActiveReservationCount(it.id)

            it.getStatus(activeReservations) == EventStatus.CANCELLED
        }

        displayEvents(cancelledEvents)
    }

    private fun displayEvents(eventList: List<Event>) {

        if (eventList.isEmpty()) {
            println("No events found.")
            return
        }

        for (event in eventList) {

            val activeReservations = getActiveReservationCount(event.id)

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
            println("Reservations: $activeReservations")
            println(
                "Remaining Capacity: " +
                        event.getRemainingCapacity(activeReservations)
            )
            println("Status: ${event.getStatus(activeReservations)}")
            println("--------------------------------------")
        }
    }

    // -------------------------
    // Adding event
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

        if (
            title.isBlank() ||
            category.isBlank() ||
            organiser.isBlank() ||
            location.isBlank()
        ) {
            println("Error: Text fields cannot be empty.")
            return
        }

        val date = try {
            LocalDate.parse(dateInput)
        } catch (e: Exception) {
            println("Error: Invalid date. Please use YYYY-MM-DD.")
            return
        }

        val time = try {
            LocalTime.parse(timeInput)
        } catch (e: Exception) {
            println("Error: Invalid time. Please use HH:MM.")
            return
        }

        val maxCapacity = capacityInput.toIntOrNull()

        if (maxCapacity == null || maxCapacity <= 0) {
            println("Error: Maximum capacity must be a number greater than 0.")
            return
        }

        val highestIdNumber = events.maxOfOrNull {
            it.id.removePrefix("E").toIntOrNull() ?: 0
        } ?: 0

        val newId = "E" + (highestIdNumber + 1)
            .toString()
            .padStart(3, '0')

        val newEvent = Event(
            id = newId,
            title = title,
            category = category,
            organiser = organiser,
            date = date,
            time = time,
            location = location,
            maxCapacity = maxCapacity,
            initialStatus = EventStatus.UPCOMING
        )

        events.add(newEvent)

        println()
        println("Community event added successfully.")
        println("New Event ID: ${newEvent.id}")
    }

    // -------------------------
    // Modifying event
    // -------------------------

    fun modifyEvent() {
        println()
        println("========== MODIFY COMMUNITY EVENT ==========")

        displayEvents(events)

        print("Enter the Event ID you want to modify: ")
        val eventId = readln().trim().uppercase()

        val event = events.find {
            it.id == eventId
        }

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

                val activeReservations =
                    getActiveReservationCount(event.id)

                if (newCapacity < activeReservations) {
                    println(
                        "Error: Capacity cannot be lower than the current " +
                                "number of active reservations " +
                                "($activeReservations)."
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

    // -------------------------
    // Event cancellation
    // -------------------------

    fun cancelEvent() {
        println()
        println("========== CANCEL COMMUNITY EVENT ==========")

        displayEvents(events)

        print("Enter the Event ID you want to cancel: ")
        val eventId = readln().trim().uppercase()

        val event = events.find {
            it.id == eventId
        }

        if (event == null) {
            println("Error: Event not found.")
            return
        }

        val activeReservations =
            getActiveReservationCount(event.id)

        when (event.getStatus(activeReservations)) {

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

                print(
                    "Are you sure you want to cancel " +
                            "'${event.title}'? (Y/N): "
                )

                val confirmation =
                    readln().trim().uppercase()

                if (confirmation != "Y") {
                    println("Cancellation stopped.")
                    return
                }

                event.initialStatus = EventStatus.CANCELLED

                var updatedReservations = 0

                for (reservation in reservations) {

                    if (
                        reservation.eventId == event.id &&
                        reservation.status == ReservationStatus.ACTIVE
                    ) {
                        reservation.status =
                            ReservationStatus.EVENT_CANCELLED

                        updatedReservations++
                    }
                }

                println()
                println("Event cancelled successfully.")
                println("Event ID: ${event.id}")
                println(
                    "Status: ${
                        event.getStatus(
                            getActiveReservationCount(event.id)
                        )
                    }"
                )
                println(
                    "Reservations updated: $updatedReservations"
                )
            }
        }
    }
}