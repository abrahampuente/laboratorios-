// Ejercicio 1: Cerrar colecciones
fun main() {
    val aplicaciones = mutableListOf("Git", "Github", "Visual Studio Code", "IntelliJ IDEA")
    aplicaciones.add("Android Studio")
    println("Aplicaciones instaladas: $aplicaciones \n")

    for (aplicacion in aplicaciones) {
        println("- $aplicacion")
    }

    println("\nRecorrido con forEach:")
    aplicaciones.forEach { aplicacion ->
        println("- $aplicacion")
    }

    val nombrelargo = aplicaciones.filter { it.length > 6 }
    val mayuscula = aplicaciones.map { it.uppercase() }
    val appsConA = aplicaciones.count { it.startsWith("A") }

    println("\nAplicaciones con nombre largo:")
    nombrelargo.forEach { println("- $it") }
    println("\nAplicaciones en mayúsculas:")
    mayuscula.forEach { println("- $it") }
    println("\nAplicaciones que comienzan con 'A': $appsConA")
}

// Ejercicio 2: Primera Clase

class Producto(val nombre: String, val precio: Double) {
    fun mostrarResumen() {
        println("Producto: $nombre \nPrecio: $$precio")
    }
}

fun main() {
    val producto1 = Producto("Tarjeta Gráfica", 1200.0)
    val producto2 = Producto("Memoria RAM", 700.0)

    producto1.mostrarResumen()
    producto2.mostrarResumen()
}

// Ejercicio 3: Estado y Comportamiento

class contador {
    var cuenta: Double = 0.0
    fun incrementar(valor: Double) {
        cuenta += valor
    }
}

fun main() {
    var aumento = contador()
    aumento.incrementar(5.0)
    println("Cuenta: ${aumento.cuenta}")
    aumento.incrementar(10.5)
    println("Cuenta: ${aumento.cuenta}")
    aumento.incrementar(15.5)
    println("Cuenta: ${aumento.cuenta}")
}

// Ejercicio 4: Encapsulamiento

class CuentaPuntos {
    var puntos: Int = 0
    private set

    fun sumarPuntos(valor: Int) {
        if (valor > 0) {
            puntos += valor
        } else {
            println("No se pueden agregar puntos negativos.")
        }
    }

    fun reiniciar(): Int {
        puntos = 0
        return puntos
    }
}

fun main() {
    val cuenta = CuentaPuntos()
    println("Puntos iniciales: ${cuenta.puntos}")
    cuenta.sumarPuntos(10)
    println("Puntos acumulados: ${cuenta.puntos}")
    cuenta.sumarPuntos(5)
    println("Puntos acumulados: ${cuenta.puntos}")
    cuenta.sumarPuntos(-3) 
    println("Puntos acumulados: ${cuenta.puntos}")
    cuenta.reiniciar()
    println("Puntos después de reiniciar: ${cuenta.puntos}") 
}

// Ejercicio 5: Herencia simple

abstract class notificacion(val destinatario: String) {
    abstract fun enviar()
}

class Email(destinatario: String) : notificacion(destinatario) {
    override fun enviar() {
        println("Email enviado a $destinatario")
    }
}
class Push(destinatario: String) : notificacion(destinatario) {
    override fun enviar() {
        println("Push enviado a $destinatario")
    }
}

fun main() {
    val email = Email("user@example.com")
    val push = Push("Dispositivo personal")

    email.enviar()
    push.enviar()
// Ejercicio 6: Polimorfismo 
    val notificaciones: List<notificacion> = listOf(
       Email("as.puente.a@gmail.com"),
       Push("Dispositivo laboral"),
    )
    for (n in notificaciones) {
    n.enviar()
    }
}

// Ejercicio 7: Validación preventiva

fun calcularPromedio(total: Double, cantidad: Int): Double {
    if (cantidad > 0) {
        return total / cantidad
    }
    throw IllegalArgumentException("La cantidad debe ser mayor que cero.")
}

fun main() {
    var total = 100.0
    var cantidad = 10
    val promedio = calcularPromedio(total, cantidad)
    println("Promedio: $promedio")
}

// Ejercicio 8: Try/catch especifico

fun strtoint(str: String): Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        println("Error: El texto '$str' no se puede convertir a entero.")
        return 0
    }
}

fun main() {
    var texto = "123a"
    var resultado = strtoint(texto)
    println("Resultado: $resultado")
    texto = "1234"
    resultado = strtoint(texto)
    println("\nResultado: $resultado")
}   

// Ejercicio 9: data class

data class Usuario(val id: Int, val nombre: String, val correo: String)