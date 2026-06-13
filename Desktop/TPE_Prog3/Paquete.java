
public class Paquete{
   private int id; 
   private String codigoIdentificador;
   private int peso;
   private boolean contieneAlimentos;
   private int nivelDeUrgencia;

   public Paquete(int id, String codigoIdentificador, int peso, boolean contieneAlimentos, int nivelDeUrgencia) {
    this.id = id;
    this.codigoIdentificador = codigoIdentificador;
    this.peso = peso;
    this.contieneAlimentos = contieneAlimentos;
    this.nivelDeUrgencia = nivelDeUrgencia;
   }

   

   public Paquete() {
}



   public int getId() {
    return id;
   }


   public void setId(int id) {
    this.id = id;
   }


   public String getCodigoIdentificador() {
    return codigoIdentificador;
   }


   public void setCodigoIdentificador(String codigoIdentificador) {
    this.codigoIdentificador = codigoIdentificador;
   }


   public int getPeso() {
    return peso;
   }


   public void setPeso(int peso) {
    this.peso = peso;
   }


   public boolean isContieneAlimentos() {
    return contieneAlimentos;
   }


   public void setContieneAlimentos(boolean contieneAlimentos) {
    this.contieneAlimentos = contieneAlimentos;
   }


   public int getNivelDeUrgencia() {
    return nivelDeUrgencia;
   }


   public void setNivelDeUrgencia(int nivelDeUrgencia) {
    this.nivelDeUrgencia = nivelDeUrgencia;
   }
   
   
}
