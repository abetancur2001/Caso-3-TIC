package controladores;

public class ControladorProcesadores {

    private int[] procesadores;
    private int procesadoresLibres;

    public ControladorProcesadores(int cantProcesadores) {
        this.procesadores = new int[cantProcesadores];
        java.util.Arrays.fill(procesadores, -1);

        this.procesadoresLibres = procesadores.length;
    }

    public synchronized boolean pedirPro (int proc, int id){

        if (proc > procesadoresLibres){
            return false;
        }

        int i = 0;
        int k = 0;

        while(i < proc){
            if (procesadores[k] == -1){
                procesadores[k] = id;
                i++;
            }

            k ++;
        }

        this.procesadoresLibres -= proc;

        return true;

    }

    public boolean terminarControladorPro(){
        for (int i = 0; i < procesadores.length; i++){
            if (procesadores[i] != -1){
                return false;
            }
        }

        if (procesadoresLibres != procesadores.length){
            return false;
        }

        return true;
    }

    public synchronized void liberarPro (int id){

        int i = 0;

        while (i < procesadores.length){
            if (procesadores[i] == id){
                procesadores[i] = -1;
                this.procesadoresLibres ++;
            }

            i++;

        }

    }



}
