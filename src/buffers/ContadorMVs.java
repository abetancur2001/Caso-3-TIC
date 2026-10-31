package buffers;

public class ContadorMVs {

    private int idMVs;
    private int contadorMVs;

    public ContadorMVs(int idMVs, int contadorMVs) {
        this.idMVs = idMVs;
        this.contadorMVs = contadorMVs;
    }

    public synchronized int getIdMVs() {

        int contadorTemp = idMVs;

        this.idMVs++;

        return contadorTemp;
    }

    public synchronized void quitarMV(){
        notify();
        this.contadorMVs --;
    }

    public synchronized int getContadorMVs() {
        return contadorMVs;
    }

    public synchronized void dormirMain() throws InterruptedException {
        while(contadorMVs != 0){
            wait();
        }
    }

}
