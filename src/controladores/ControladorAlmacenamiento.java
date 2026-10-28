package controladores;

public class ControladorAlmacenamiento {

    private int tamBloque;
    private int[] bloques;

    public ControladorAlmacenamiento(int alm, int block) {

        this.tamBloque = block;

        long almEnKiB = (long)alm * 1024*1024;

        long cantBlo  = almEnKiB / block;

        this.bloques = new int[(int) cantBlo];
        java.util.Arrays.fill(bloques, -1);
    }
}
