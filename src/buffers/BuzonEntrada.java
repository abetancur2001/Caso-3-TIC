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

    public synchronized void addSolicitud(Solicitud solicitud){

        while (solicitudes.size() == capacidadBuzonEntrada) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        solicitudes.add(solicitud);

    }

    public synchronized Solicitud retirarSolicitud(){

        if (solicitudes.isEmpty()){
            return null;
        }

        notify();

        return solicitudes.remove(0);

    }

    public synchronized boolean vacio(){
        return solicitudes.size() == 0;
    }

    public synchronized void terminarUsuario(){
        usuariosActivos--;
    }


    public int getCapacidadBuzonEntrada() {
        return capacidadBuzonEntrada;
    }

    public void setCapacidadBuzonEntrada(int capacidadBuzonEntrada) {
        this.capacidadBuzonEntrada = capacidadBuzonEntrada;
    }

    public synchronized int getUsuariosActivos() {
        return usuariosActivos;
    }

    public void setUsuariosActivos(int usuariosActivos) {
        this.usuariosActivos = usuariosActivos;
    }

    public synchronized ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
