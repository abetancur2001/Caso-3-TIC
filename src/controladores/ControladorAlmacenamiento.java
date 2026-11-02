package controladores;

public class ControladorAlmacenamiento {

    private int tamBloque;
    private int[] bloques;
    private int bloquesLibres;

    public ControladorAlmacenamiento(int alm, int block) {

        this.tamBloque = block;

        long almEnKiB = (long)alm * 1024*1024;

        long cantBlo  = almEnKiB / block;

        this.bloques = new int[(int) cantBlo];
        java.util.Arrays.fill(bloques, -1);
        this.bloquesLibres = bloques.length;

    }

    public synchronized boolean pedirAlm(int capacidad, int id){

        long capacidadKiB = (long) capacidad * 1024 * 1024;

        long cantBlocks = Math.round((double)capacidadKiB / tamBloque);

        if(cantBlocks > bloquesLibres){
            return false;
        }

        int i = 0;
        int j = 0;
        while (j < cantBlocks){
            if (bloques[i] == -1){
                bloques[i] = id;
                j++;
            }
            i++;

        }

        this.bloquesLibres -= cantBlocks;

        return true;

    }

    public boolean terminarControladorAlm(){
        for (int i = 0; i < bloques.length; i++){
            if (bloques[i] != -1){
                return false;
            }
        }

        if (bloquesLibres != bloques.length ){
            return false;
        }


        return true;
    }

    public synchronized void liberarAlm (int id){

        int i = 0;

        while (i<bloques.length){
            if (bloques[i] == id){
                bloques[i] = -1;

                this.bloquesLibres ++;

            }

            i++;

        }

    }

}
