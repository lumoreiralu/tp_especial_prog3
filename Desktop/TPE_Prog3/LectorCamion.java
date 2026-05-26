public class LectorCamion extends LectorCSV<Camion> {

    @Override
    protected Camion mapearObjeto(String[] campos) {
        // campos[0] = id
        // campos[1] = patente
        // campos[2] = estaRefrigerado (1 o 0)
        // campos[3] = capacidad
        
        int id = Integer.parseInt(campos[0].trim());
        String patente = campos[1].trim();
        boolean estaRefrigerado = campos[2].trim().equals("1"); 
        int capacidadMaxima = Integer.parseInt(campos[3].trim());

        return new Camion(id, patente, estaRefrigerado, capacidadMaxima);
    }
}