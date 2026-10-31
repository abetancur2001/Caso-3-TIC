package hilos;
import java.util.concurrent.ThreadLocalRandom;
import buffers.BuzonEntrada;
import buffers.Solicitud;

public class Usuario extends Thread{

    private String idUsuario;
    private int cantidadSolicitudes;
    private BuzonEntrada buzonEntr;
    private int procesadoresTotal;
    private int memoriaTotal;
    private int almacenamientoTotal;

    public Usuario(String idUsuario, int cantidadSolicitudes, BuzonEntrada buzonEntr, int procesadoresTotal, int memoriaTotal, int almacenamientoTotal) {
        this.idUsuario = idUsuario;
        this.cantidadSolicitudes = cantidadSolicitudes;
        this.buzonEntr = buzonEntr;
        this.procesadoresTotal = procesadoresTotal;
        this.memoriaTotal = memoriaTotal;
        this.almacenamientoTotal = almacenamientoTotal;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= cantidadSolicitudes) {

            int procesadoresPedidos = ThreadLocalRandom.current().nextInt(1, (int) Math.round(procesadoresTotal*(0.75)) + 1 );
            int memoriaPedida = ThreadLocalRandom.current().nextInt(1, (int) Math.round(memoriaTotal*(0.75)) + 1 );
            int memoriaPedidaRound = Integer.highestOneBit(memoriaPedida);
            int almacenamientoPedido = ThreadLocalRandom.current().nextInt(1, (int) Math.round(almacenamientoTotal*(0.75)) + 1 );
            int modAlm = almacenamientoPedido % 2;
            int almacenamientoRound = almacenamientoPedido - modAlm;

            if (almacenamientoRound == 0){
                almacenamientoRound = 2;
            }

            Solicitud solicitud = new Solicitud(idUsuario+" - S" + i,procesadoresPedidos, memoriaPedidaRound, almacenamientoRound);

            buzonEntr.addSolicitud(solicitud);

            i++;
        }

        buzonEntr.terminarUsuario();

    }
}
