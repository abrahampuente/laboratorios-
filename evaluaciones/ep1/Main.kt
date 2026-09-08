import java.time.LocalDateTime

fun main(){
    val sistema = Local()

    while(true){
        println("\n--- BikeCity | Sistema de Arriendo ---")
        println("1. Ingresar bicicleta")
        println("2. Retirar bicicleta")
        println("3. Estado de los slots")
        println("4. Recaudación total")
        println("5. Historial de tickets")
        println("6. Salir")
        print("Seleccione una opción: ")

        when(readln().trim()){
            "1" -> {
                var codigo = ""
                while(true){
                    print("Ingrese el código de la bicicleta (Ej: BM01MC): ")
                    codigo = readln().uppercase().trim()
                    if(codigo.matches(Regex("^[A-Z]{2}[0-9]{2}[A-Z]{2}$"))){
                        break
                    } else {
                        println("Código inválido. Intente nuevamente.")
                    }
                }
                
                print("Ingrese el modelo de la bicicleta: ")
                val modelo = readln().trim()
                
                println("Ingrese el tipo de cliente (1. Turista | 2. Abonado | 3. Discapacitado): ")
                val cliente = when(readln().trim()){
                    "2" -> Clientes.ABONADO
                    "3" -> Clientes.DISCAPACITADO
                    else -> Clientes.TURISTA
                }

                println("Ingrese el tipo de bicicleta (1. Ciudad | 2. Montaña | 3. Eléctrica): ")
                val tipoBici = when(readln().trim()){
                    "2" -> BiciMontana(codigo, modelo, LocalDateTime.now(), cliente)
                    "3" -> {
                        print("¿La bicicleta tiene larga autonomía? (s/n): ")
                        val largaAutonomia = readln().trim().lowercase() == "s"
                        BiciElectrica(codigo, modelo, LocalDateTime.now(), cliente, largaAutonomia)
                    }
                    else -> BiciCiudad(codigo, modelo, LocalDateTime.now(), cliente)
                }

                sistema.entrada(tipoBici)
            }

            "2" -> {
                print("Ingrese el código de la bicicleta a retirar: ")
                val codigo = readln().uppercase().trim()

                print("Ingrese los minutos de uso: ")
                val minutos = readln().toIntOrNull() ?: 0

                sistema.salida(codigo, minutos)
            }

            "3" -> {
                println("\n--- ESTADO DE LOS SLOTS ---")
                sistema.slots.forEach { slot ->
                    val infoBici = if (slot.bicicleta != null) " Ocupado por: ${slot.bicicleta!!.codigo}" else ""
                    println("Slot #${slot.id.toString().padStart(2, '0')} | Estado: ${slot.estado.name.padEnd(12)} $infoBici")
                }
            }

            "4" -> {
                println("\n--- RECAUDACIÓN ---")
                println("Total recaudado hasta el momento: $${sistema.recaudacion.toInt()}")
            }

            "5" -> {
                sistema.mostrarHistorial()
            }

            "6" -> {
                println("Cerrando el sistema...")
                return 
            }

            else -> println("Opción inválida. Intente de nuevo.")
        }
    }
}