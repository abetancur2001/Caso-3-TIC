import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws IOException {

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










    }
}
