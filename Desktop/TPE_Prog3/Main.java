
public class Main {
    public static void main(String[] args) {
        /* 
        LectorPaquete lectorPaquetes = new LectorPaquete();
        LectorCamion lectorCamiones = new LectorCamion();

       
        List<Paquete> paquetes = lectorPaquetes.leerArchivo("Paquete.csv");
        List<Camion> camiones = lectorCamiones.leerArchivo("Camion.csv");

       
        System.out.println("Camiones cargados: " + camiones.size());
        for (Camion c : camiones) {
            System.out.println("Patente: " + c.getPatente() + " | Refrigerado: " + c.isEstaRefrigerado());
        }

        System.out.println("\nPaquetes cargados: " + paquetes.size());
        for (Paquete paq : paquetes) {
            System.out.println("Codigo: " + paq.getCodigoIdentificador() + " | Contiene Alimento: " + paq.isContieneAlimentos());
        }*/
        
            // 1. Definimos las rutas a tus archivos CSV
            String pathCamiones = "Camion.csv";
            String pathPaquetes = "Paquete.csv";
    
            System.out.println("Inicializando el sistema y leyendo archivos...");
            
            Servicios servicios = new Servicios(pathCamiones, pathPaquetes);

    
            System.out.println("\n========================================");
            System.out.println("   PROBANDO ALGORITMO GREEDY");
            System.out.println("========================================");
            
            servicios.solucionGreedy(); 
            

    }
}