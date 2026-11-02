package hilos;

import buffers.ContadorMVs;
import buffers.ZonaEspera;
import controladores.ControladorAlmacenamiento;
import controladores.ControladorMemoria;
import controladores.ControladorProcesadores;

import java.util.concurrent.ThreadLocalRandom;

public class MV extends Thread{

    private int numeroMV;
    private String idMV;
    private int procesadoresMV;
    private int memoriaMV;
    private int almacenamientoMV;
    private ZonaEspera zonaEspera;
    private ControladorProcesadores controladorPro;
    private ControladorMemoria controladorMem;
    private ControladorAlmacenamiento controladorAlm;
    private ContadorMVs contadorMVs;

    public MV(int numeroMV, int procesadoresMV, int memoriaMV, int almacenamientoMV, ZonaEspera zonaEspera, ControladorProcesadores controladorPro, ControladorMemoria controladorMem, ControladorAlmacenamiento controladorAlm, ContadorMVs contadorMVs) {
        this.numeroMV = numeroMV;
        this.idMV = "MV-" + numeroMV;
        this.procesadoresMV = procesadoresMV;
        this.memoriaMV = memoriaMV;
        this.almacenamientoMV = almacenamientoMV;
        this.zonaEspera = zonaEspera;
        this.controladorPro = controladorPro;
        this.controladorMem = controladorMem;
        this.controladorAlm = controladorAlm;
        this.contadorMVs = contadorMVs;
    }

    @Override
    public void run(){

        zonaEspera.marcarPendiente();
        System.out.println("["+idMV+"]: Solicitando recursos");

        while (true) {

            int i = zonaEspera.getTurno();

            if (controladorPro.pedirPro(procesadoresMV, numeroMV)) {
                    if (controladorMem.pedirMem(memoriaMV, numeroMV )){
                        if (controladorAlm.pedirAlm(almacenamientoMV, numeroMV)){

                            zonaEspera.disminuirMVsPendientes();

                            System.out.println("["+idMV+"]: Recursos solicitados suficientes, ejecutando");

                            int tiempoVida = ThreadLocalRandom.current().nextInt(4000, 40001);

                            try {
                                sleep(tiempoVida);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            System.out.println("["+idMV+"]: Ejecución exitosa, liberando recursos");


                            controladorPro.liberarPro(numeroMV);
                            controladorMem.liberarMem(numeroMV);
                            controladorAlm.liberarAlm(numeroMV);

                            contadorMVs.quitarMV();
                            zonaEspera.notificarMVs();

                            break;
                        }
                        else{
                            controladorPro.liberarPro(numeroMV);
                            controladorMem.liberarMem(numeroMV);
                            try {
                                System.out.println("["+idMV+"]: Almacenamiento insuficiente, liberando procesadores y memoria, a dormir");
                                zonaEspera.esperar(i);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    else{
                        controladorPro.liberarPro(numeroMV);
                        try {
                            System.out.println("["+idMV+"]: Memoria insuficiente, liberando procesadores, a dormir");
                            zonaEspera.esperar(i);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            }
            else {
                try {
                    System.out.println("["+idMV+"]: Procesadores insuficientes, a dormir");
                    zonaEspera.esperar(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
