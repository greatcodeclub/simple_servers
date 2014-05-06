import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server {

    public static void main(String[] args) throws Exception {

        int port = 3000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server listening on port " + port);

        while (true) {

            Socket clientSocket = serverSocket.accept();
            System.err.println("New connection");

            PrintWriter    out = new PrintWriter(clientSocket.getOutputStream(), true); 
            BufferedReader in  = new BufferedReader( 
                                    new InputStreamReader(clientSocket.getInputStream())); 

            String s;
            while ((s = in.readLine()) != null) {
                System.out.println("Received " + s);
                out.println("Bye!");
            }

            out.close();
            in.close();
            clientSocket.close();
        }
    }
}