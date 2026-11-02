package buffers;

public class ZonaEspera {

    private int capacidadZonaEspera;
    private int MVsPendientes;
    private int turno;

    public ZonaEspera(int capacidadZonaEspera, int turno) {
        this.capacidadZonaEspera = capacidadZonaEspera;
        this.turno = turno;
    }

    public synchronized boolean superaUmbral(){
        return MVsPendientes >= capacidadZonaEspera;
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized void notificarMVs(){
        this.turno ++;
        notifyAll();
    }

    public synchronized void dormirGestor() throws InterruptedException {

        while(MVsPendientes != 0){
            wait();
        }

    }

    public synchronized void marcarPendiente(){
        this.MVsPendientes ++;
    }

    public synchronized void disminuirMVsPendientes(){

        this.MVsPendientes--;
        if (MVsPendientes == 0){
            notifyAll();
        }

    }

    public boolean terminarZona(){
        return this.MVsPendientes == 0;
    }

    public synchronized void esperar(int turno1) throws InterruptedException {
        while(turno1 == turno) {
            wait();
        }

    }
}
