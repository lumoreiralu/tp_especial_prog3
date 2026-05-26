import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Servicios {
    //Completar con las estructuras y métodos privados que se requieran.
    private HashMap<String, Paquete> paquetes;
    private List<Paquete> paquetesQueContienenAlimento;
    private TreeMap<Integer, List<Paquete>> paquetesPorPrioridad;

    //Expresar la complejidad temporal del constructor.

    // Complejidad Temporal: O(N log N), donde N es la cantidad de paquetes en el archivo.
    // Porque se recorren las N líneas del archivo CSV. Las inserciones en el HashMap 
    // y en el ArrayList son de tiempo constante O(1), pero la inserción en el TreeMap 
    // toma tiempo logarítmico O(log N) para mantener el orden por prioridad,
    // buscar e insertar en el árbol cuesta O(logN) y agregar al final del ArrayList cuesta O(1)
    public Servicios(String pathCamiones, String pathPaquetes)
    {
        //para servicio 1
        this.paquetes = new HashMap<>();
        //para servicio 2
        this.paquetesQueContienenAlimento = new ArrayList<Paquete>();
        //para servicio 3
        this.paquetesPorPrioridad = new TreeMap<>();


        this.cargarPaquetes(pathPaquetes);
    }

    //Expresar la complejidad temporal del servicio 1.
    public Paquete servicio1(String codigoPaquete) {
        return paquetes.get(codigoPaquete);

     }

    //Expresar la complejidad temporal del servicio 2.
    public List<Paquete> servicio2(boolean contieneAlimentos) {
    }

    //Expresar la complejidad temporal del servicio 3.
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima){
    }

    private void cargarPaquetes(String pathPaquetes) {
        LectorPaquete lector = new LectorPaquete();
        
        //el lector procesa el archivo y devuelve la lista de paquetes 
        List<Paquete> paquetesLeidos = lector.leerArchivo(pathPaquetes); 
        
        // recorremos la lista del lector y llenamos las estructuras de servicios
        for (Paquete p : paquetesLeidos) {
            
            // Estructura para el servicio 1 (HashMap)
            this.paquetes.put(p.getCodigoIdentificador(), p);
            
            // Estructura para el servicio 2 (List)
            if (p.isContieneAlimentos()) {
                this.paquetesQueContienenAlimento.add(p);
            }
            
            // Estructura para el servicio 3 (TreeMap)

            //hice un treemap con lista por si se repite la prioridad y no perder el anterior
            if (!this.paquetesPorPrioridad.containsKey(p.getNivelDeUrgencia())) {
                this.paquetesPorPrioridad.put(p.getNivelDeUrgencia(), new ArrayList<>());
            }
            this.paquetesPorPrioridad.get(p.getNivelDeUrgencia()).add(p);
        }
    }
}