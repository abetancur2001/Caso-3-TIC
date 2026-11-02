import buffers.BuzonEntrada;
import buffers.ZonaEspera;
import controladores.ControladorAlmacenamiento;
import controladores.ControladorMemoria;
import controladores.ControladorProcesadores;
import buffers.ContadorMVs;
import hilos.Gestor;
import hilos.Usuario;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Properties p = new Properties();
        p.load(new FileInputStream("config.txt"));
        int nu = Integer.parseInt(p.getProperty("nu"));
        int base = Integer.parseInt(p.getProperty("base"));
        int ng = Integer.parseInt(p.getProperty("ng"));
        int tam1 = Integer.parseInt(p.getProperty("tam1"));
        int tam2 = Integer.parseInt(p.getProperty("tam2"));
        int procesadores = Integer.parseInt(p.getProperty("procesadores"));
        int memoria = Integer.parseInt(p.getProperty("memoria"));
        int tamPagina = Integer.parseInt(p.getProperty("tamPagina"));
        int almacenamiento = Integer.parseInt(p.getProperty("almacenamiento"));
        int tamBloque = Integer.parseInt(p.getProperty("tamBloque"));

        int cantMVs = base * nu * (nu+1) / 2;
        ContadorMVs contadorMVs = new ContadorMVs(0, cantMVs);
        BuzonEntrada buzonEntrada = new BuzonEntrada(nu,tam1);
        ZonaEspera zonaEspera = new ZonaEspera(tam2, cantMVs);
        ControladorAlmacenamiento controladorAlmacenamiento = new ControladorAlmacenamiento(almacenamiento, tamBloque);
        ControladorMemoria controladorMemoria = new ControladorMemoria(memoria, tamPagina);
        ControladorProcesadores controladorProcesadores = new ControladorProcesadores(procesadores);

        Gestor[] gestores = new Gestor[ng];
        for (int i = 1; i <= ng; i++){
            gestores[i-1] = new Gestor("G-"+i,zonaEspera, buzonEntrada, controladorProcesadores, controladorMemoria, controladorAlmacenamiento, contadorMVs);
            gestores[i-1].start();
        }

        Usuario[] usuarios = new Usuario[nu];
        for (int j = 1; j <= nu; j++){
            usuarios[j-1] = new Usuario("U-"+j, base * j, buzonEntrada, procesadores, memoria, almacenamiento);
            usuarios[j-1].start();
        }

        contadorMVs.dormirMain();

        if (buzonEntrada.vacio()){
            if (zonaEspera.terminarZona()){
                if (controladorAlmacenamiento.terminarControladorAlm()){
                    if (controladorMemoria.terminarControladorMem()){
                        if (controladorProcesadores.terminarControladorPro()){
                            System.out.println("Programa Terminado");
                        }
                        else{
                            System.out.println("Error: los procesadores no quedaron libres");
                        }
                    }
                    else{
                        System.out.println("Error: la memoria no quedo libre");
                    }
                }
                else{
                    System.out.println("Error: el almacenamiento no quedo libre");
                }
            }
            else{
                System.out.println("Error: la zona de espera no quedo vacia");
            }
        }
        else{
            System.out.println("Error: el buzon de entrada no quedo vacio");
        }

    }
}
