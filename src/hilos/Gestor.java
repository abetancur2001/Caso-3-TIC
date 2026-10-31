package hilos;

import buffers.BuzonEntrada;
import buffers.ContadorMVs;
import buffers.Solicitud;
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

    @Override
    public void run(){

        while(buzonAsignado.getUsuariosActivos() != 0 || !buzonAsignado.vacio()){

            if (zonaAsignada.superaUmbral()){

            }

            Solicitud soli = buzonAsignado.retirarSolicitud();

            if (soli != null){
                MV mv = new MV(contadorMVs.getIdMVs(), soli.getProcesadoresPedidos(), soli.getMemoriaPedida(), soli.getAlmacenamientoPedido(),zonaAsignada, controladorProcesadores, controladorMemoria, controladorAlmacenamiento);
                mv.start();
            }
            else{
                Thread.yield();
            }

        }

    }

}
