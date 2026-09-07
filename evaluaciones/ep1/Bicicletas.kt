import java.time.LocalDateTime

abstract class Bicicleta(
    val codigo: String,
    val modelo: String,
    val fecha: LocalDateTime,
    val cliente: String,
){
    abstract val tarifa: Double
    protected abstract fun Costobase(minutos: Int): Double
    fun Total(minutos: Int): Double{
        val costoBase = Costobase(minutos)

        if (costoBase <= 0) return 0.0

        val iva = costoBase * 0.19
        if (cliente == cliente.DISCAPACITADO) {
            return iva * 0.5
        } else {
            return iva
        }
    }
}

class BiciCiudad(
    codigo: String,
    modelo: String,
    fecha: LocalDateTime,
    cliente: String,
): Bicicleta(codigo, modelo, fecha, cliente) {
    override val tarifa: Double = 800.0

    override fun Costobase(minutos: Int): Double {
        val costo = (tarifa/60) * minutos
        if (cliente == cliente.ABONADO) {
            return costo * 0.8
        } else {
            return costo
        }
    }
}

class BiciMontana(
    codigo: String,
    modelo: String,
    fecha: LocalDateTime,
    cliente: String,
): Bicicleta(codigo, modelo, fecha, cliente) {
    override val tarifa: Double = 1500.0

    override fun Costobase(minutos: Int): Double {
        if (minutos < 20) return 0.0
        return (tarifa/60) * minutos
    }
}

class BiciElectrica(
    codigo: String,
    modelo: String,
    fecha: LocalDateTime,
    cliente: String,
    val largaAutonomia: Boolean
): Bicicleta(codigo, modelo, fecha, cliente) {
    override val tarifa: Double = 2200.0

    override fun Costobase(minutos: Int): Double {
        val costo = (tarifa/60) * minutos
        if (largaAutonomia == true) {
            return costo * 1.3
        } else {
            return costo
        }
    }
}