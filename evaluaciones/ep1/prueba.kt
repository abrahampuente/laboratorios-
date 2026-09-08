import java.time.LocalDateTime
enum class Clientes {
    TURISTA,
    ABONADO,
    DISCAPACITADO
}
enum class EstadoSlot {
    LIBRE,
    ARRENDADA,
    PROCESO,
    MANTENCION
}

abstract class Bicicleta(
    val codigo: String,
    val modelo: String,
    val fecha: LocalDateTime,
    val cliente: Clientes
){
    abstract val tarifa: Double
    protected abstract fun Costobase(minutos: Int): Double
    
    fun Total(minutos: Int): Double{
        val costoBase = Costobase(minutos)

        if (costoBase <= 0) return 0.0

        val iva = costoBase * 1.19
        return if (cliente == Clientes.DISCAPACITADO) {
            iva * 0.5
        } else {
            iva
        }
    }
}

class BiciCiudad(
    codigo: String,
    modelo: String,
    fecha: LocalDateTime,
    cliente: Clientes
): Bicicleta(codigo, modelo, fecha, cliente) {
    override val tarifa: Double = 800.0

    override fun Costobase(minutos: Int): Double {
        val costo = (tarifa / 60) * minutos
        return if (cliente == Clientes.ABONADO) {
            costo * 0.8
        } else {
            costo
        }
    }
}

class BiciMontana(
    codigo: String,
    modelo: String,
    fecha: LocalDateTime,
    cliente: Clientes
): Bicicleta(codigo, modelo, fecha, cliente) {
    override val tarifa: Double = 1500.0

    override fun Costobase(minutos: Int): Double {
        if (minutos < 20) return 0.0
        return (tarifa / 60) * minutos
    }
}

class BiciElectrica(
    codigo: String,
    modelo: String,
    fecha: LocalDateTime,
    cliente: Clientes,
    val largaAutonomia: Boolean
): Bicicleta(codigo, modelo, fecha, cliente) {
    override val tarifa: Double = 2200.0

    override fun Costobase(minutos: Int): Double {
        val costo = (tarifa / 60) * minutos
        return if (largaAutonomia) {
            costo * 1.3
        } else {
            costo
        }
    }
}

data class Registro(
    val ticket: Int,
    val bicicleta: Bicicleta,
    val minutos: Int,
    val total: Double
)

class Local {
    val slots = List(10) { index -> Slot(index + 1) }

    private val historial = mutableListOf<Registro>()
    var recaudacion: Double = 0.0
        private set

    private var ticketCont: Int = 1

    fun entrada(bicicleta: Bicicleta){
        val slotLibre = slots.find { it.estado == EstadoSlot.LIBRE }
        if (slotLibre == null) {
            println("No hay slots disponibles.")
            return
        }
        slotLibre.proceso("Registrando entrada del codigo ${bicicleta.codigo}")
        slotLibre.arrendar(bicicleta)
        println("Bicicleta ${bicicleta.codigo} ingresada en el slot ${slotLibre.id}")
    }

    fun salida(codigo: String, minutos: Int){
        val slotOcupado = slots.find { it.bicicleta?.codigo == codigo }
        if (slotOcupado == null) {
            println("No se encontró la bicicleta con código $codigo")
            return
        }

        val bicicleta = slotOcupado.bicicleta!!
        slotOcupado.proceso("Calculando tarifa para la bicicleta ${bicicleta.codigo}")
        val total = bicicleta.Total(minutos)
        if (total <= 0 && minutos > 20) {
            println("No se puede calcular la tarifa para la bicicleta ${bicicleta.codigo}")
        }

        val registro = Registro(ticketCont, bicicleta, minutos, total)
        historial.add(registro)
        recaudacion += total
        ticketCont++

        slotOcupado.liberar()
        println("Bicicleta ${bicicleta.codigo} retirada del slot ${slotOcupado.id}. Total a pagar: $${total.toInt()}")
    }
    
    fun mostrarHistorial(){
        if (historial.isEmpty()) {
            println("No se han realizado arriendos el día de hoy.")
            return
        }
        
        historial.forEach { reg ->
            println("Ticket #${reg.ticket.toString().padStart(4, '0')} | Bici: ${reg.bicicleta.codigo} (${reg.bicicleta.modelo}) | Tipo: ${reg.bicicleta::class.simpleName} | Cliente: ${reg.bicicleta.cliente} | Minutos: ${reg.minutos} min | Total: $${reg.total.toInt()}")
        }
    }
}

class Slot(val id: Int){
    var estado: EstadoSlot = EstadoSlot.LIBRE
      private set

    var bicicleta: Bicicleta? = null
      private set

    var motivo: String? = null
      private set

    fun arrendar(bicicleta: Bicicleta){
        this.bicicleta = bicicleta
        this.estado = EstadoSlot.ARRENDADA
        this.motivo = null
    }

    fun liberar(){
        this.bicicleta = null
        this.estado = EstadoSlot.LIBRE
        this.motivo = null
    }

    fun proceso(motivo: String){
        this.estado = EstadoSlot.PROCESO
        this.motivo = motivo
    }

    fun mantencion(motivo: String){
        this.estado = EstadoSlot.MANTENCION
        this.motivo = motivo
    }
}

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