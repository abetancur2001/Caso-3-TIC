package controladores;

public class ControladorMemoria {

    private int[] paginas;
    private int tamPag;
    private int paginasLibres;

    public ControladorMemoria(int memoria, int pag) {

        this.tamPag = pag;

        long memEnKiB = (long) memoria * 1024 * 1024;

        long cantPag = memEnKiB / pag;

        this.paginas = new int[(int) cantPag];
        java.util.Arrays.fill(paginas, -1);

        this.paginasLibres = paginas.length;

    }

    public synchronized boolean pedirMem(int memoria, int id){

        long memoriaKiB = (long) memoria * 1024 * 1024;
        long cantPag = Math.round((double)memoriaKiB / tamPag);

        if(cantPag > paginasLibres){
            return false;
        }

        int i = 0;
        int k = 0;

        while (i < cantPag){

            if (paginas[k] == -1){
                paginas[k] = id;
                i++;
            }

            k++;
        }

        this.paginasLibres -= cantPag;

        return true;
    }

    public boolean terminarControladorMem(){
        for (int i = 0; i < paginas.length; i++){
            if (paginas[i] != -1){
                return false;
            }
        }

        if (paginasLibres != paginas.length){
            return false;
        }

        return true;
    }

    public synchronized void liberarMem(int id){

        int i = 0;

        while(i < paginas.length){
            if (paginas[i] == id){
                paginas[i] = -1;
                this.paginasLibres ++;
            }

            i++;
        }

    }

}
