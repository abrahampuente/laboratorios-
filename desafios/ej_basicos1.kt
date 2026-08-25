// Ejercicio 2: Primer progama equivalente
fun saludar() {
    println("Hola DSY1105")
    println("Estoy aprendiendo Kotlin")
}

// Ejercicio 3: Variables básicas
fun variablesBasicas() {
    val nombreApp = "MiPrimeraApp"
    val version = 1
    val tamanioMB = 42.5
    val publicada = false

    println("Nombre: $nombreApp")
    println("Versión: $version")
    println("Tamaño: $tamanioMB MB")
    println("Publicada: $publicada")
}

// Ejercicio 4: val vs var
fun valVersusVar() {
    val nombreUsuario = "Ana" 
    var puntos = 10          
    puntos = 15

    println("Usuario: $nombreUsuario | Puntos: $puntos")
}

// Ejercicio 5: Decisión simple
fun decisionSimple(bateria: Int) {
    if (bateria < 20) {
        println("Batería baja")
    } else {
        println("Batería disponible")
    }
}

// Ejercicio 6: Función pequeña 
fun darBienvenida(nombre: String) = "Bienvenido/a, $nombre"

// Ejercicio 7: Nullable y Elvis
fun nullableYElvis() {
    var alias: String? = null
    println("Alias 1: ${alias ?: "Sin alias"}")

    alias = "coder01"
    println("Alias 2: ${alias ?: "Sin alias"}")
}

// Desafío breve
fun verificarEstadoPublicacion(pruebasSuperadas: Boolean) =
    if (pruebasSuperadas) "Lista para publicar" else "Requiere más pruebas"

fun main() {
    println("Ej 2:")
    saludar()

    println("\nEj 3:")
    variablesBasicas()

    println("\nEj 4:")
    valVersusVar()

    println("\nEj 5:")
    decisionSimple(35)

    println("\nEj 6:")
    println(darBienvenida("Ana"))

    println("\nEj 7:")
    nullableYElvis()

    println("\nDesafío Breve:")
    val appFicticia = "TechTask"
    val pruebasProntas = true
    println("App: $appFicticia -> Status: ${verificarEstadoPublicacion(pruebasProntas)}")
}