import java.time.LocalDateTime

data class Registro(
    val ticket: Int,
    val bicicleta: Bicicleta,
    val minutos: Int,
    val total: Double
)

class local{
    val slots = List(10) { index -> Slot(index+1)}

    private val historial = mutableListOf<Registro>()
    var recaudacion: Double = 0.0
        private set

    private var ticketCont: Int = 1

    fun entrada(bicicleta: Bicicleta){
        val slotLibre = slots.find { it.estado == EstadoSlot.LIBRE }
        if (slotLibre == null) {
            println("No hay slots disponibles")
            return
        }
        slotLibre.Proceso("Registrando entrada del codigo ${bicicleta.codigo}")
        slotLibre.Ocupado(bicicleta)
        println("Bicicleta ${bicicleta.codigo} ingresada en el slot ${slotLibre.id}")
    }

    fun salida(codigo: String, minutos: Int){
        val slotOcupado = slots.find { it.bicicleta?.codigo == codigo }
        if (slotOcupado == null) {
            println("No se encontró la bicicleta con código $codigo")
            return
        }

        val bicicleta = slotOcupado.bicicleta!!
        slotOcupado.Proceso("Calculando tarifa para la bicicleta ${bicicleta.codigo}")
        val total = bicicleta.Total(minutos)
        if (total <= 0 && minutos > 20) {
            println("No se puede calcular la tarifa para la bicicleta ${bicicleta.codigo}")
        }

        val registro = Registro(ticketCont, bicicleta, minutos, total)
        historial.add(registro)
        recaudacion += total
        ticketCont++

        slotOcupado.Liberar()
        println("Bicicleta ${bicicleta.codigo} retirada del slot ${slotOcupado.id}. Total a pagar: $total")
}
