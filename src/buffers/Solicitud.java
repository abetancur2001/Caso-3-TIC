package buffers;

public class Solicitud {

    private String idSolicitud;
    private int procesadoresPedidos;
    private int memoriaPedida;
    private int almacenamientoPedido;

    public Solicitud(String idSolicitud, int procesadoresPedidos, int memoriaPedida, int almacenamientoPedido) {
        this.idSolicitud = idSolicitud;
        this.procesadoresPedidos = procesadoresPedidos;
        this.memoriaPedida = memoriaPedida;
        this.almacenamientoPedido = almacenamientoPedido;
    }
}
