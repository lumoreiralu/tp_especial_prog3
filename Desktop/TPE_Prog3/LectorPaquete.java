public class LectorPaquete extends LectorCSV<Paquete> {

    @Override
    protected Paquete mapearObjeto(String[] campos) {
        // campos[0] = id
        // campos[1] = codigoIdentificador
        // campos[2] = peso
        // campos[3] = contieneAlimentos (1 = true, 0 = false)
        // campos[4] = nivelDeUrgencia
        
        int id = Integer.parseInt(campos[0].trim());
        String codigo = campos[1].trim();
        int peso = Integer.parseInt(campos[2].trim());
        boolean contieneAlimentos = campos[3].trim().equals("1");
        int nivelUrgencia = Integer.parseInt(campos[4].trim());

        return new Paquete(id, codigo, peso, contieneAlimentos, nivelUrgencia);
    }
}