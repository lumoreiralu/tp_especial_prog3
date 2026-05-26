import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class LectorCSV<T> {

    public List<T> leerArchivo(String ruta) {
        List<T> listaResultado = new ArrayList<>();
        Path path = Paths.get(ruta);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            

            String primeraLinea = br.readLine();
            if (primeraLinea == null) {
                return listaResultado;
            }
            
            int cantidadEsperada = Integer.parseInt(primeraLinea.trim());
            System.out.println("El archivo " + ruta + " indica que contiene " + cantidadEsperada + " registros.");

            
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                // Separamos los 4 elementos
                String[] campos = linea.split(";");


                T objeto = mapearObjeto(campos);
                listaResultado.add(objeto);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico: " + e.getMessage());
        }

        return listaResultado;
    }

    protected abstract T mapearObjeto(String[] campos);
}