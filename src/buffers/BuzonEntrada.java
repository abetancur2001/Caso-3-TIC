package buffers;

public class BuzonEntrada {

    private int capacidadBuzonEntrada;
    private int usuariosActivos;
    private Solicitud[] solicitudes;

    public BuzonEntrada(Solicitud[] solicitudes, int usuariosActivos, int capacidadBuzonEntrada) {
        this.solicitudes = solicitudes;
        this.usuariosActivos = usuariosActivos;
        this.capacidadBuzonEntrada = capacidadBuzonEntrada;
    }
}
