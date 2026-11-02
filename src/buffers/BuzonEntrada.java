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

    public synchronized void addSolicitud(Solicitud solicitud, String idUsuario){

        while (solicitudes.size() == capacidadBuzonEntrada) {
            try {
                System.out.println("["+idUsuario+"]: Buzón lleno, a dormir");
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

    public synchronized int getUsuariosActivos() {
        return usuariosActivos;
    }

}
