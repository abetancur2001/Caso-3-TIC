package controladores;

public class ControladorProcesadores {

    private int[] procesadores;

    public ControladorProcesadores(int cantProcesadores) {
        this.procesadores = new int[cantProcesadores];
        java.util.Arrays.fill(procesadores, -1);

    }
}
