import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Scanner;

public class Descifrar {
    public static final String MENSAJE_CIFRADO = leerFichero() ;

    public static void main(String[] args) {

        try {
            // Tomamos la clave privada
            PrivateKey clavePrivada = KeysManager.getClavePrivada();

            Cipher cipher = Cipher.getInstance("RSA");

            // Desciframos con la clave privada
            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);

            byte[] mensajeCifrado = Base64.getDecoder().decode(MENSAJE_CIFRADO);

            // Se obtiene el mensaje descifrado
            byte[] mensaje = cipher.doFinal(mensajeCifrado);

            // Lo imprimimos por pantalla en Base64
            System.out.println(new String(mensaje));


        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo seleccionado no existe");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("No existe el padding seleccionado");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave introducida no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque utilizado no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding utilizado es erróneo");
            e.printStackTrace();
        }

    }


    public static String leerFichero() {

        BufferedReader br = null;

        String contenido = "";

        try {

            br = new BufferedReader(new FileReader("C:\\Users\\dperea\\Downloads\\David_Perea_Garcia_HibernateCrud\\Confidencialidad\\src\\FicheroConMensajeCifrado"));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {
                contenido = sc.nextLine();

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return contenido;
    }


}
