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

        long capacidadKiB = (long) capacidad * 1024 * 1024

        int cantBlocks = (int) capacidadKiB / tamBloque;

        if(cantBlocks > bloquesLibres){
            return false;
        }

        for (int i : bloques){
            if (i == -1){
                bloques[i] = id;
            }
        }

        this.bloquesLibres -= cantBlocks;

        return true;

    }

}
