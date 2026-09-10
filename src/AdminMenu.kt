class AdminMenu(private val eventManager: EventManager) {

    fun showMenu() {
        while (true) {
            println()
            println("======================================")
            println("        ADMIN EVENT MANAGEMENT")
            println("======================================")
            println("1. View Community Events")
            println("2. Add Community Event")
            println("3. Modify Community Event")
            println("4. Cancel Community Event")
            println("5. Return to Main Menu")
            print("Enter your choice: ")

            when (readln()) {
                "1" -> showViewEventsMenu()
                "2" -> eventManager.addEvent()
                "3" -> eventManager.modifyEvent()
                "4" -> println("Cancel Event - Coming soon")
                "5" -> return
                else -> println("Invalid choice. Please try again.")
            }
        }
    }

    private fun showViewEventsMenu() {
        println()
        println("========== VIEW EVENTS ==========")
        println("1. View All Events")
        println("2. View Upcoming Events")
        println("3. View Completed Events")
        println("4. View Cancelled Events")
        println("5. Return to Admin Menu")
        print("Enter your choice: ")

        when (readln()) {
            "1" -> eventManager.viewAllEvents()
            "2" -> eventManager.viewUpcomingEvents()
            "3" -> eventManager.viewCompletedEvents()
            "4" -> eventManager.viewCancelledEvents()
            "5" -> return
            else -> println("Invalid choice.")
        }
    }
}