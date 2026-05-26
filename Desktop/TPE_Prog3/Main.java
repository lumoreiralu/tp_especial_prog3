import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        LectorPaquete lectorPaquetes = new LectorPaquete();
        LectorCamion lectorCamiones = new LectorCamion();

       
        List<Paquete> paquetes = lectorPaquetes.leerArchivo("Paquete.csv");
        List<Camion> camiones = lectorCamiones.leerArchivo("Camion.csv");

       
        System.out.println("Camiones cargados: " + camiones.size());
        for (Camion c : camiones) {
            System.out.println("Patente: " + c.getPatente() + " | Refrigerado: " + c.isEstaRefrigerado());
        }

        System.out.println("\nPaquetes cargados: " + paquetes.size());
    }
}