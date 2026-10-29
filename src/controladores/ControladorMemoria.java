package controladores;

public class ControladorMemoria {

    private int[] paginas;
    private int tamPag;

    public ControladorMemoria(int memoria, int pag) {

        this.tamPag = pag;

        long memEnKiB = (long) memoria * 1024 * 1024;

        long cantPag = memEnKiB / pag;

        this.paginas = new int[(int) cantPag];
        java.util.Arrays.fill(paginas, -1);

    }
}
