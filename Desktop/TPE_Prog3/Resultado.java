import java.util.List;

public class Resultado {
    private List<Camion> camiones;
    private int pesoNoAsignado;
    private int cantidadEstadosGenerados;
    
    public Resultado(List<Camion> camiones, int pesoNoAsignado) {
        this.camiones = camiones;
        this.pesoNoAsignado = pesoNoAsignado;
        this.cantidadEstadosGenerados = 0;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }
    public int getPesoNoAsignado() {
        return pesoNoAsignado;
    }
    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }
    public void setPesoNoAsignado(int pesoNoAsignado) {
        this.pesoNoAsignado = pesoNoAsignado;
    }
    public int getCantidadEstadosGenerados() {
        return cantidadEstadosGenerados;
    }
    public void setCantidadEstadosGenerados(int cantidadEstadosGenerados) {
        this.cantidadEstadosGenerados = cantidadEstadosGenerados;
    }
}
