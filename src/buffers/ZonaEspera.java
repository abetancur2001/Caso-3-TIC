package buffers;

public class ZonaEspera {

    private int capacidadZonaEspera;
    private int MVsDormidas;
    private int turno;

    public ZonaEspera(int capacidadZonaEspera, int turno) {
        this.capacidadZonaEspera = capacidadZonaEspera;
        this.turno = turno;
    }

    public synchronized boolean superaUmbral(){
        return MVsDormidas >= capacidadZonaEspera;
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized void esperar(int turno1) throws InterruptedException {
        while(turno1 == turno){
            wait();
        }
    }
}
