import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {

    public final static int PUERTO = 42869;

    public static void main(String[] args) throws Exception {
        
        byte[] buffer = new byte[1024];

        try{ 

            System.out.println("Inicio del servidor UDP");
            DatagramSocket socket = new DatagramSocket(PUERTO);

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socket.receive(peticion);
            System.out.println("Se recibe un mensaje de conexión");

            int puertoCliente = peticion.getPort();
            InetAddress IpCliente = peticion.getAddress();

            String mensaje = new String(peticion.getData());
            mensaje = "Recibido el mensaje del cliente: " + mensaje;
            buffer = mensaje.getBytes();
                        
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, IpCliente, puertoCliente);

            socket.send(respuesta);

            System.out.println("Se manda el mensaje de confirmación. (NO es TCP, no buscamos hacer Acknowledge)");

            socket.setDatagramSocketImplFactory(null);

            // while(true){
            // }

        } catch (SocketException ex) {
            System.out.println("Error al crear el socket");
        }
    }
}
