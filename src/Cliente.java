import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {

    public final static int PUERTO = 42869;

    private static String ip = "192.168.20.35";

    public static void main(String[] args) {

        try {

            DatagramSocket socketUDP = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getByName(ip);
 

            byte[] buffer = new byte[1024];
            
            String mensaje = "Hola como estas? Que tal el clima por allá";

            buffer = mensaje.getBytes();

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, ipServidor, PUERTO);

            try {
                socketUDP.send(peticion);
            } catch (IOException e) {
                System.out.println("Error al enviar el mensaje");
            }

            System.out.println("Se envia el mensaje al servidor");

            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

            try {
                socketUDP.receive(respuesta);
            } catch (IOException e) {
                System.out.println("Error al recibir el mensaje");
            }

            System.out.println("Se recibe la respuesta del servidor"+ new String(respuesta.getData()));

            socketUDP.close();

        } catch (SocketException e) {
            System.out.println("Error al crear el socket");
        } catch (UnknownHostException e) {
            System.out.println("Error al crear el socket");
        }

    }
    
}
