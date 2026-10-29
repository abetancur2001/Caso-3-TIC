package hilos;

import buffers.BuzonEntrada;
import buffers.ContadorMVs;
import buffers.ZonaEspera;
import controladores.ControladorAlmacenamiento;
import controladores.ControladorMemoria;
import controladores.ControladorProcesadores;

public class Gestor extends Thread{

    private String idGestor;
    private ZonaEspera zonaAsignada;
    private BuzonEntrada buzonAsignado;
    private ControladorProcesadores controladorProcesadores;
    private ControladorMemoria controladorMemoria;
    private ControladorAlmacenamiento controladorAlmacenamiento;
    private ContadorMVs contadorMVs;

    public Gestor(String idGestor, ZonaEspera zonaAsignada, BuzonEntrada buzonAsignado, ControladorProcesadores controladorProcesadores, ControladorMemoria controladorMemoria, ControladorAlmacenamiento controladorAlmacenamiento, ContadorMVs contadorMVs) {
        this.idGestor = idGestor;
        this.zonaAsignada = zonaAsignada;
        this.buzonAsignado = buzonAsignado;
        this.controladorProcesadores = controladorProcesadores;
        this.controladorMemoria = controladorMemoria;
        this.controladorAlmacenamiento = controladorAlmacenamiento;
        this.contadorMVs = contadorMVs;
    }
}
