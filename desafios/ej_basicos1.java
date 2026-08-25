package desafios;
public class ej_basicos1 {

    // Ejercicio 2: Primer programa
    public static void saludar() {
        System.out.println("Hola DSY1105");
        System.out.println("Estoy aprendiendo Kotlin");
    }

    // Ejercicio 3: Variables básicas
    public static void variablesBasicas() {
        String nombreApp = "MiPrimeraApp";
        int version = 1;
        double tamanioMB = 42.5;
        boolean publicada = false;

        System.out.println("Nombre: " + nombreApp);
        System.out.println("Versión: " + version);
        System.out.println("Tamaño: " + tamanioMB + " MB");
        System.out.println("Publicada: " + publicada);
    }

    // Ejercicio 5: Decisión simple
    public static void decisionSimple(int bateria) {
        if (bateria < 20) {
            System.out.println("Batería baja");
        } else {
            System.out.println("Batería disponible");
        }
    }

    // Ejercicio 6: Función pequeña
    public static String darBienvenida(String nombre) {
        return "Bienvenido/a, " + nombre;
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 2 ===");
        saludar();

        System.out.println("\n=== Ejercicio 3 ===");
        variablesBasicas();

        System.out.println("\n=== Ejercicio 5 ===");
        decisionSimple(35);

        System.out.println("\n=== Ejercicio 6 ===");
        System.out.println(darBienvenida("Ana"));
    }
}