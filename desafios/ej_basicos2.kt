// Ejercicio 1: Datos personales simples
fun ejercicio1() {
    val nombre = "Carlos"
    val edad = 22
    val carrera = "Analista Programador"
    val promedio = 5.8

    println("Nombre: $nombre, Edad: $edad, Carrera: $carrera, Promedio: $promedio")
}

// Ejercicio 2: Operadores
fun ejercicio2(n1: Int, n2: Int) {
    println("Suma: ${n1 + n2}")
    println("Resta: ${n1 - n2}")
    println("Multiplicación: ${n1 * n2}")
    println("División: ${n1 / n2}")
    println("¿Primero es mayor que el segundo?: ${n1 > n2}")
    println("¿Ambos son positivos?: ${n1 > 0 && n2 > 0}")
}

// Ejercicio 3: Condicionales
fun ejercicio3(nota: Double) {
    if (nota >= 4.0) {
        println("Nota $nota: Aprobado")
    } else {
        println("Nota $nota: Reprobado")
    }
}

// Ejercicio 4: when
fun ejercicio4(dia: Int) {
    val nombreDia = when (dia) {
        1 -> "Lunes"
        2 -> "Martes"
        3 -> "Miércoles"
        4 -> "Jueves"
        5 -> "Viernes"
        6 -> "Sábado"
        7 -> "Domingo"
        else -> "Día inválido"
    }
    println("Día $dia: $nombreDia")
}

// Ejercicio 5: Ciclo for
fun ejercicio5() {
    println("Números del 1 al 10:")
    for (i in 1..10) {
        print("$i ")
    }
    println("\nSólo números pares:")
    for (i in 1..10) {
        if (i % 2 == 0) print("$i ")
    }
    println()
}

// Ejercicio 6: while
fun ejercicio6() {
    var contador = 5
    while (contador >= 1) {
        println(contador)
        contador--
    }
    println("¡Inicio!")
}

// Ejercicio 7: Función
fun calcularDescuento(precio: Double, porcentaje: Double): Double {
    return precio - (precio * (porcentaje / 100))
}

// Ejercicio 8: Null safety
fun ejercicio8() {
    var correo: String? = null
    println("Correo 1: ${correo ?: "Correo no registrado"}")

    correo = "usuario@example.com"
    println("Correo 2: ${correo ?: "Correo no registrado"}")
}

// Ejercicio 9: Primera List
fun ejercicio9() {
    val lenguajes = listOf("Kotlin", "Java", "Python", "C++", "JavaScript")
    println("Lista completa: $lenguajes")
    println("Primer elemento: ${lenguajes.first()}")
    println("Cantidad de elementos: ${lenguajes.size}")
    println("Recorrido con for:")
    for (lenguaje in lenguajes) {
        println("- $lenguaje")
    }
}

// Ejercicio 10: MutableList
fun ejercicio10() {
    val tareas = mutableListOf("Estudiar Kotlin", "Hacer ejercicio", "Comprar pan")
    tareas.add("Lavar la ropa")
    tareas.remove("Hacer ejercicio")
    
    println("Tareas pendientes:")
    for (tarea in tareas) {
        println("- $tarea")
    }
}

// Ejercicio 11: forEach
fun ejercicio11() {
    val nombres = listOf("Ana", "Pedro", "Sofía", "Lucas")

    println("Con forEach:")
    nombres.forEach { println("- $it") }

    println("\nCon for:")
    for (nombre in nombres) {
        println("- $nombre")
    }
}

// Ejercicio 12: filter, map y count
fun ejercicio12() {
    val numeros = listOf(12, 5, 18, 3, 21, 10)

    val mayoresOIgualesA10 = numeros.filter { it >= 10 }
    val multiplicadosPorDos = numeros.map { it * 2 }
    val cantidadMayoresA15 = numeros.count { it > 15 }

    println("Originales: $numeros")
    println("Mayores o iguales a 10: $mayoresOIgualesA10")
    println("Multiplicados por 2: $multiplicadosPorDos")
    println("Cantidad de números mayores a 15: $cantidadMayoresA15")
}

fun main() {
    println("Ej 1:")
    ejercicio1()

    println("\nEj 2:")
    ejercicio2(15, 5)

    println("\nEj 3:")
    ejercicio3(5.3)
    ejercicio3(3.9)
    ejercicio3(4.0)

    println("\nEj 4:")
    ejercicio4(3)
    ejercicio4(9)

    println("\nEj 5:")
    ejercicio5()

    println("\nEj 6:")
    ejercicio6()

    println("\nEj 7:")
    val precioFinal = calcularDescuento(10000.0, 20.0)
    println("Precio final a pagar: $$precioFinal")

    println("\nEj 8:")
    ejercicio8()

    println("\nEj 9:")
    ejercicio9()

    println("\nEj 10:")
    ejercicio10()

    println("\nEj 11:")
    ejercicio11()

    println("\nEj 12:")
    ejercicio12()
}