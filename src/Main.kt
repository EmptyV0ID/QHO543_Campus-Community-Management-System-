fun main() {

    val eventManager = EventManager()

    while (true) {
        println()
        println("======================================")
        println(" CAMPUS COMMUNITY MANAGEMENT SYSTEM")
        println("======================================")
        println("1. Student Functions - Member A")
        println("2. Admin Functions - Member B")
        println("3. Additional Functions - Member C")
        println("4. Exit")
        print("Enter your choice: ")

        when (readln()) {
            "1" -> println("Student functionality is not implemented.")

            "2" -> {
                val adminMenu = AdminMenu(eventManager)
                adminMenu.showMenu()
            }

            "3" -> {
                println()
                println("Member C functionality is optional and not implemented.")
            }

            "4" -> {
                println("Goodbye!")
                return
            }

            else -> println("Invalid choice. Please try again.")
        }
    }
}