package buffers;

import java.util.ArrayList;

public class BuzonEntrada {

    private int capacidadBuzonEntrada;
    private int usuariosActivos;
    private ArrayList<Solicitud> solicitudes;

    public BuzonEntrada(int usuariosActivos, int capacidadBuzonEntrada) {
        this.solicitudes = new ArrayList<>();
        this.usuariosActivos = usuariosActivos;
        this.capacidadBuzonEntrada = capacidadBuzonEntrada;
    }
}
