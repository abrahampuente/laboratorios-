import java.time.LocalDateTime

fun main(){
    val scanner = Scanner(System.`in`)
    val sistema = local()

    while(true){
        println("---BikeCity | Sistema de Arriendo---")
        println("1. Ingresar bicicleta")
        println("2. Retirar bicicleta")
        println("3. Estado de los slots")
        println("4. Recaudación total")
        println("5. Salir")
        print("Seleccione una opción: ")

        when(readln().trim()){
            1 -> {
                var codigo = ""
                while(true){
                    print("Ingrese el código de la bicicleta: (2 letras - 2 números - 2 letras)")
                    codigo = readln().uppercase().trim()
            

        }

    }

}