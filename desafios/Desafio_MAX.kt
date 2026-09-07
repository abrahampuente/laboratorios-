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
    val arriendo = Arriendo()
    var opcion: Int
    do {
        Menu()
        print("Ingrese una opción: ")
        opcion = readlnOrNull()?.toIntOrNull()?: 0

        when (opcion) {
            1 -> arriendo.Registrar()
            2 -> arriendo.Buscar()
            3 -> arriendo.Arrendar()
            4 -> arriendo.Devolver()
            5 -> arriendo.ListaDisponibles()
            6 -> arriendo.ResumenArriendos()
            7 -> arriendo.Mantenimiento()
            8 -> println("Saliendo del programa...")
            else -> println("Ingrese una opción válida (1-8)")
        } 
    }while (opcion != 8)

}

data class Bicicleta(
    val id: Int,
    val tipo: String,
    val tarifa: Double,
    var disponible: String = "s",
    var mantenimiento: Int 
)

class Arriendo{
    private val bicicletas = mutableListOf<Bicicleta>()
    var ingresosTotales: Double = 0.0
    var arriendosTotales: Int = 0

    init{
        bicicletas.add(Bicicleta(1, "eléctrica", 10000.0, "s", 8))
        bicicletas.add(Bicicleta(2, "montaña", 8000.0, "n", 6))
        bicicletas.add(Bicicleta(3, "ruta", 12000.0, "s", 9))
        bicicletas.add(Bicicleta(4, "urbana", 5000.0, "s", 7))
        bicicletas.add(Bicicleta(5, "eléctrica", 10000.0, "s", 4))
    }

    fun Registrar() {
      println("Ingrese el ID de la bicicleta: ")
      val id = readlnOrNull()?.toIntOrNull()?: return println("Porfavor ingrese un ID válido")
      if (bicicletas.any { it.id == id }) {
        return println("El ID ya existe. Por favor ingrese un ID único.")
      }
      println("Ingrese el tipo de bicicleta (eléctrica, montaña, ruta, urbana): ")
      val tipo = readlnOrNull() ?: return println("Porfavor ingrese un tipo válido")
      if (tipo !in listOf("eléctrica", "montaña", "ruta", "urbana")) {
        return println("Tipo de bicicleta no válido. Por favor ingrese uno de los siguientes: eléctrica, montaña, ruta, urbana.")
      }
      println("Ingrese la tarifa por hora: ")
      val tarifa = readlnOrNull()?.toDoubleOrNull()?: return println("Porfavor ingrese una tarifa válida")
      if (tarifa <= 0) {
        return println("La tarifa no puede ser negativa. Por favor ingrese una tarifa válida.")
      }
      println("Ingrese disponibilidad de la bicicleta (s/n): ")
      val disponible = readlnOrNull()?.lowercase().takeIf { it == "s" || it == "n" } ?: return println("Porfavor ingrese una disponibilidad válida (s/n)")
      println("Ingrese el estado de mantenimiento de la bicicleta (0-10): ")
      val mantenimiento = readlnOrNull()?.toIntOrNull()?: return println("Porfavor ingrese un estado de mantenimiento válido")
      bicicletas.add(Bicicleta(id, tipo, tarifa, disponible, mantenimiento))
      println("Bicicleta registrada con éxito!")
      if (mantenimiento < 5) {
        println("La bicicleta necesita mantenimiento antes de ser arrendada.")
      }
    }

    fun Buscar() {
        println("Ingrese el ID de la bicicleta a buscar: ")
        val id = readlnOrNull()?.toIntOrNull() ?: return println("Porfavor ingrese un ID válido")
        val bicicleta = bicicletas.find { it.id == id }
        if (bicicleta != null) {
            println("Bicicleta encontrada: $bicicleta")
        } else {
            println("No se encontró una bicicleta con el ID proporcionado.")
    }
    }

    fun Arrendar() {
        print("Ingrese el ID de la bicicleta a arrendar: ")
        val id = readlnOrNull()?.toIntOrNull() ?: return println("Porfavor ingrese un ID válido")
        val bicicleta = bicicletas.find { it.id == id }
        if (bicicleta == null) {
            println("No se encontró una bicicleta con el ID proporcionado.")
            return
        }
        if (bicicleta.disponible == "n") {
            println("La bicicleta no está disponible para arrendar.")
            return
        }
        if (bicicleta.mantenimiento < 5) {
            println("La bicicleta necesita mantenimiento antes de ser arrendada.")
            return
        }
        print("Ingrese la cantidad de horas a arrendar: ")
        val horas = readlnOrNull()?.toIntOrNull() ?: return println("Porfavor ingrese una cantidad de horas válida")
        if (horas <= 0) {
            println("La cantidad de horas debe ser mayor a cero.")
            return
        }
        val total = bicicleta.tarifa * horas
        ingresosTotales += total
        println("Bicicleta arrendada con éxito! El costo es: $total")
        arriendosTotales++
        bicicleta.disponible = "n"
        }

    fun Devolver() {
        print("Ingrese el ID de la bicicleta a devolver: ")
        val id = readlnOrNull()?.toIntOrNull() ?: return println("Porfavor ingrese un ID válido")
        val bicicleta = bicicletas.find { it.id == id }
        if (bicicleta == null) {
            println("No se encontró una bicicleta con el ID proporcionado.")
            return
        }
        if (bicicleta.disponible == "s") {
            println("La bicicleta ya está disponible, no se puede devolver.")
            return
        }
        bicicleta.disponible = "s"
        println("Bicicleta devuelta con éxito!")
    }    
    
    fun ListaDisponibles() {
        val disponibles = bicicletas.filter { it.disponible == "s" }
        if (disponibles.isEmpty()) {
            println("No hay bicicletas disponibles en este momento.")
        } else {
            println("Bicicletas disponibles:")
            disponibles.forEach { println(it) }
        }
    }

    fun ResumenArriendos() {
        println("Resumen de arriendos:")
        println("Total de ingresos: $ingresosTotales")
        println("Total de arriendos realizados: $arriendosTotales")
    }
    
    fun Mantenimiento() {
        println("Bicicletas que necesitan mantenimiento:")
        val necesitanMantenimiento = bicicletas.filter { it.mantenimiento < 5 } 
        necesitanMantenimiento.forEach { println(it) }
    }
}
