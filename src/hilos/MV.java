package hilos;

import buffers.ZonaEspera;
import controladores.ControladorAlmacenamiento;
import controladores.ControladorMemoria;
import controladores.ControladorProcesadores;

public class MV extends Thread{

    private int idMV;
    private int procesadoresMV;
    private int memoriaMV;
    private int almacenamientoMV;
    private ZonaEspera zonaEspera;
    private ControladorProcesadores controladorPro;
    private ControladorMemoria controladorMem;
    private ControladorAlmacenamiento controladorAlm;

    public MV(int idMV, int procesadoresMV, int memoriaMV, int almacenamientoMV, ZonaEspera zonaEspera, ControladorProcesadores controladorPro, ControladorMemoria controladorMem, ControladorAlmacenamiento controladorAlm) {
        this.idMV = idMV;
        this.procesadoresMV = procesadoresMV;
        this.memoriaMV = memoriaMV;
        this.almacenamientoMV = almacenamientoMV;
        this.zonaEspera = zonaEspera;
        this.controladorPro = controladorPro;
        this.controladorMem = controladorMem;
        this.controladorAlm = controladorAlm;
    }
}
