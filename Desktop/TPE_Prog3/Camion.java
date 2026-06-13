import java.util.ArrayList;
import java.util.List;

public class Camion{
    private int id;
    private String patente;
    private boolean estaRefrigerado;
    private int capacidadMaxima;
    private List<Paquete> paquetesAsignados;

    public Camion(int id, String patente, boolean estaRefrigerado, int capacidadMaxima) {
        this.id = id;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidadMaxima = capacidadMaxima;
        this.paquetesAsignados = new ArrayList<>();
    }

    

    public Camion() {
    }


    public int getId() {
        return id;
    }

    public void asignarPaquete(Paquete paquete){
        this.paquetesAsignados.add(paquete);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isEstaRefrigerado() {
        return estaRefrigerado;
    }

    public void setEstaRefrigerado(boolean estaRefrigerado) {
        this.estaRefrigerado = estaRefrigerado;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    public String toString(){
        String lista = "El camion: " + this.getId()+ ", con capacidad libre restante: " +this.getCapacidadMaxima() + "Kg, lleva los paquetes: " ; 
        for(Paquete p : paquetesAsignados){
            lista+= p.getCodigoIdentificador() + ", peso: " + p.getPeso() + "Kg.";
        }
        return lista;
    }
    
}