//package cl.duoc.dsy1105.lab;

public class Laboratorio01Java {

    public static void main(String[] args) {
        String modelo = "Galaxy A55";
        int bateria = 90;
        boolean modoAhorro = false;
        String propietario = null;

        System.out.println("=== TELÉFONO ===");
        System.out.println("Modelo: " + modelo);
        System.out.println("Batería: " + bateria + "%");
        System.out.println("Estado: " + obtenerEstadoBateria(bateria));
        System.out.println("Ahorro de energía: " + modoAhorro);

        if (propietario == null) {
            System.out.println("Propietario: Sin propietario");
        }

    }

    public static String obtenerEstadoBateria(int bateria) {
        if (bateria < 20) {
            return "Batería baja";
        } else if (bateria <= 79) {
            return "Batería suficiente";
        } else {
            return "Batería alta";
        }
    }
}