fun Menu() {
    println("----CENTRO DE ARRIENDO----")
    println("1. Registrar bicicleta")
    println("2. Buscar bicicleta")
    println("3. Arrendar bicicleta")
    println("4. Devolver bicicleta")
    println("5. Lista de bicicletas disponibles")
    println("6. Resumen de arriendos")
    println("7. Mantenimiento de bicicletas")
    println("8. Salir")
}

fun main() {
    var opcion: Int
    do {
        Menu()
        print("Ingrese una opción: ")
        opcion = readlnOrNull()?.toIntOrNull()?: 0

        when (opcion) {
            1 -> println("Registrando bicicleta...")
            2 -> println("Buscando bicicleta...")
            3 -> println("Arrendando bicicleta...")
            4 -> println("Devolviendo bicicleta...")
            5 -> println("Mostrando lista de bicicletas disponibles...")
            6 -> println("Mostrando resumen de arriendos...")
            7 -> println("Realizando mantenimiento de bicicletas...")
            8 -> println("Saliendo del programa...")
            else -> println("Ingrese una opción válida (1-8)")
        } 
    }while (opcion != 8)

}

data class Bicicleta(
    val id: Int,
    val tipo: String,
    val tarifa: Double,
    var disponible: Boolean = true,
    var mantenimiento: Int 
)