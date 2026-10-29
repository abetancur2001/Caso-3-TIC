package hilos;

import buffers.BuzonEntrada;

public class Usuario extends Thread{

    private String idUsuario;
    private int cantidadSolicitudes;
    private BuzonEntrada buzonEntr;

    public Usuario(String idUsuario, int cantidadSolicitudes, BuzonEntrada buzonEntr) {
        this.idUsuario = idUsuario;
        this.cantidadSolicitudes = cantidadSolicitudes;
        this.buzonEntr = buzonEntr;
    }
}
