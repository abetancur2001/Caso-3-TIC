package hilos;

import buffers.BuzonEntrada;
import buffers.ZonaEspera;

public class Gestor extends Thread{

    private int idGestor;
    private ZonaEspera zonaAsignada;
    private BuzonEntrada buzonAsignado;

    public Gestor(int idGestor, ZonaEspera zonaAsignada, BuzonEntrada buzonAsignado) {
        this.idGestor = idGestor;
        this.zonaAsignada = zonaAsignada;
        this.buzonAsignado = buzonAsignado;
    }
}
