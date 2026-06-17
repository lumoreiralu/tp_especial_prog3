import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Servicios {
    //Completar con las estructuras y métodos privados que se requieran.
    private HashMap<String, Paquete> paquetes;
    private List<Paquete> paquetesQueContienenAlimentos;
    private List<Paquete> paquetesQueNoContienenAlimentos;
    private TreeMap<Integer, List<Paquete>> paquetesPorPrioridad;
    private List<Camion> camiones;

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
        this.paquetesQueContienenAlimentos = new ArrayList<Paquete>();
        this.paquetesQueNoContienenAlimentos = new ArrayList<Paquete>();
        //para servicio 3
        this.paquetesPorPrioridad = new TreeMap<>();


        this.cargarPaquetes(pathPaquetes);

        LectorCamion lectorCamion = new LectorCamion();
        this.camiones = lectorCamion.leerArchivo(pathCamiones);
    }

    //Servicio 1: O(1) ya que el método get() de un HashMap tiene acceso directo constante.
    public Paquete servicio1(String codigoPaquete) {
        return paquetes.get(codigoPaquete);

     }

    //Servicio 2: O(1), ya que solo implica un if y el retorno de la referencia a una lista ya construida.
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        if(contieneAlimentos){
            return paquetesQueContienenAlimentos;
        }
        else{
            return paquetesQueNoContienenAlimentos;
        }
    }

    //Servicio 3: O(logP+K) (logaritmico + iteracion).
    //Donde P es la cantidad de prioridades distintas guardadas en el árbol 
    //(costo del subMap para ubicar el rango) y K es la cantidad total de paquetes que caen 
    // dentro de ese rango y deben ser copiados a la lista resultado.
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima){
        List<Paquete> resultado = new ArrayList<>();

        //subMap() devuelve un mapa que contiene las entradas con claves entre urgenciaMaxima y urgenciaMinima
        //se agregan las listas de paquetes de cada prioridad dentro del rango a la lista resultado
        for(List<Paquete> paquetes : paquetesPorPrioridad.subMap(urgenciaMinima, true, urgenciaMaxima, true).values()){
            resultado.addAll(paquetes);
        }
        return resultado;
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
                this.paquetesQueContienenAlimentos.add(p);
            }
            else{
                this.paquetesQueNoContienenAlimentos.add(p);
            }
            
            // Estructura para el servicio 3 (TreeMap)
            //hice un treemap con lista por si se repite la prioridad y no perder el anterior
            if (!this.paquetesPorPrioridad.containsKey(p.getNivelDeUrgencia())) {
                this.paquetesPorPrioridad.put(p.getNivelDeUrgencia(), new ArrayList<>());
            }
            this.paquetesPorPrioridad.get(p.getNivelDeUrgencia()).add(p);
        }
    }

    //la estrategia greedy es priorizar los paquetes de mayor peso, 
    //se ordenan los paquetes por peso descendente y se asignan a camiones refrigados
    //solo los que contienen alimentos y los que no van a 
    //a los camiones normales y luego si es necesario a los refrigerados. en cada iteracion tomamos el mejor 
    //paquete disponible. 

    public void solucionGreedy(){
        // unimos todos los paquetes
        List<Paquete> todosLosPaquetes = new ArrayList<>();
        todosLosPaquetes.addAll(paquetesQueContienenAlimentos);
        todosLosPaquetes.addAll(paquetesQueNoContienenAlimentos);
        
        //los ordenamos
        todosLosPaquetes.sort((p1, p2) -> Integer.compare(p2.getPeso(), p1.getPeso()));    
        
        int pesoNoAsignado = 0;
        int candidatosConsiderados = 0;
        for(Paquete p: todosLosPaquetes){
            candidatosConsiderados++;
            boolean asignado = false;
            if(p.isContieneAlimentos()){
                for(Camion c: camiones){
                    if(c.isEstaRefrigerado()&&p.getPeso()<=c.getCapacidadMaxima()){
                        c.setCapacidadMaxima(c.getCapacidadMaxima()-p.getPeso());
                        c.asignarPaquete(p);
                        asignado=true;
                        break;
                    }
                }
            } else {
                for(Camion c: camiones){
                    if(!c.isEstaRefrigerado()&&p.getPeso()<=c.getCapacidadMaxima()){
                        c.setCapacidadMaxima(c.getCapacidadMaxima()-p.getPeso());
                        c.asignarPaquete(p);
                        asignado=true;
                        break;
                    }
                }
            }
            if(!asignado){
                for(Camion c: camiones){
                    if(c.isEstaRefrigerado()&&p.getPeso()<=c.getCapacidadMaxima()){
                        c.setCapacidadMaxima(c.getCapacidadMaxima()-p.getPeso());
                        c.asignarPaquete(p);
                        asignado=true;
                        break;
                    }
                }
            }
            if(!asignado){
                pesoNoAsignado+=p.getPeso();
            }
        }
        System.out.println("Greedy Solución obtenida:");
        for(Camion c : camiones) {
            System.out.println(c.toString());
        }
        System.out.println("Peso no asignado: " + pesoNoAsignado + " kg.");
        System.out.println("Métrica para analizar el costo de la solución (cantidad de candidatos considerados): " + candidatosConsiderados);
    }     

    //la estrategia backtracking es probar todas las combinaciones posibles de asignación de paquetes a camiones.
    //se ordenan los paquetes por peso descendente y los que tengan alimentos solamente se asignan a camiones refrigerados.
    //si no se puede asignar un paquete a ningun camion, se suma su peso al peso no asignado y se pasa al siguiente paquete.
    //Resultado contiene la asignacion de paquetes a camiones junto su peso no asignado y la cantidad de estados generados al terminar el algoritmo.

    public void obtenerMejorSolucionBacktracking(){

        List<Paquete> listadoDePaquetes = new ArrayList<>();
        listadoDePaquetes.addAll(paquetesQueContienenAlimentos);
        listadoDePaquetes.addAll(paquetesQueNoContienenAlimentos);
        listadoDePaquetes.sort((p1, p2) -> Integer.compare(p2.getPeso(), p1.getPeso()));


        Resultado mejorSolucion = new Resultado(new ArrayList<>(), Integer.MAX_VALUE);
        Resultado resultadoFinal = solucionBacktracking(0, mejorSolucion, listadoDePaquetes, 0);

        System.out.println("Backtracking Solución obtenida:");
        for(Camion c : resultadoFinal.getCamiones()) {
            System.out.println(c.toString());
        }
        System.out.println("Peso no asignado: " + resultadoFinal.getPesoNoAsignado() + " kg.");
        System.out.println("Métrica para analizar el costo de la solución (cantidad de estados generados): " + resultadoFinal.getCantidadEstadosGenerados());
    }


    public Resultado solucionBacktracking(int index, Resultado mejorSolucion, List<Paquete> listadoDePaquetes, int pesoNoAsignado){
        mejorSolucion.setCantidadEstadosGenerados(mejorSolucion.getCantidadEstadosGenerados() + 1);

        if(index == listadoDePaquetes.size()){
            if(pesoNoAsignado < mejorSolucion.getPesoNoAsignado()){
                List<Camion> camionesCopia = new ArrayList<>();
                for(Camion c : camiones){
                    Camion cCopia = new Camion(c.getId(), c.getPatente(), c.isEstaRefrigerado(), c.getCapacidadMaxima());
                    for(Paquete p : c.getPaquetesAsignados()){
                        cCopia.asignarPaquete(p);
                    }
                    camionesCopia.add(cCopia);
                }
                mejorSolucion.setCamiones(camionesCopia);
                mejorSolucion.setPesoNoAsignado(pesoNoAsignado);
            }
            return mejorSolucion;
        }

        if(pesoNoAsignado >= mejorSolucion.getPesoNoAsignado()){
            return mejorSolucion;
        }

        Paquete paqueteActual = listadoDePaquetes.get(index);
        for(Camion c: camiones){
            if(paqueteActual.getPeso() <= c.getCapacidadMaxima()){
                if(paqueteActual.isContieneAlimentos()){
                    if(c.isEstaRefrigerado()){
                        c.asignarPaquete(paqueteActual);
                        c.setCapacidadMaxima(c.getCapacidadMaxima() - paqueteActual.getPeso());
                        solucionBacktracking(index+1, mejorSolucion, listadoDePaquetes, pesoNoAsignado);

                        c.eliminarUltimoPaqueteAsignado();
                        c.setCapacidadMaxima(c.getCapacidadMaxima() + paqueteActual.getPeso());
                    }
                }
                else{
                    c.asignarPaquete(paqueteActual);
                    c.setCapacidadMaxima(c.getCapacidadMaxima() - paqueteActual.getPeso());
                    solucionBacktracking(index+1, mejorSolucion, listadoDePaquetes, pesoNoAsignado);

                    c.eliminarUltimoPaqueteAsignado();
                    c.setCapacidadMaxima(c.getCapacidadMaxima() + paqueteActual.getPeso());
                }
            }
        }
        solucionBacktracking(index+1, mejorSolucion, listadoDePaquetes, pesoNoAsignado + paqueteActual.getPeso());
        return mejorSolucion;
    }
}