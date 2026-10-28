package hilos;

import buffers.BuzonEntrada;

public class Usuario extends Thread{

    private int idUsuario;
    private int cantidadSolicitudes;
    private BuzonEntrada buzonEntr;

    public Usuario(int idUsuario, int cantidadSolicitudes, BuzonEntrada buzonEntr) {
        this.idUsuario = idUsuario;
        this.cantidadSolicitudes = cantidadSolicitudes;
        this.buzonEntr = buzonEntr;
    }
}
