fun main() {

    val eventManager = EventManager()

    while (true) {
        println()
        println("======================================")
        println(" CAMPUS COMMUNITY MANAGEMENT SYSTEM")
        println("======================================")
        println("1. Student Functions")
        println("2. Admin Functions")
        println("3. Exit")
        print("Enter your choice: ")

        when (readln()) {
            "1" -> println("Student functionality is not implemented.")
            "2" -> {
                val adminMenu = AdminMenu(eventManager)
                adminMenu.showMenu()
            }
            "3" -> {
                println("Goodbye!")
                return
            }
            else -> println("Invalid choice. Please try again.")
        }
    }
}