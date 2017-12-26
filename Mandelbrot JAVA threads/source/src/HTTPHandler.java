import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HTTPHandler extends Thread {

    private ServerSocket socket;
    private ExecutorService service;

    public HTTPHandler(ServerSocket socket) {
        this.socket = socket;
        this.service = Executors.newFixedThreadPool(2);
    }

    public HTTPHandler(int port) throws IOException {
        this.socket = new ServerSocket(port,100);
        this.service = Executors.newFixedThreadPool(2);
    }

    public HTTPHandler() throws IOException {
        this.socket = new ServerSocket(80,100);
        this.service = Executors.newFixedThreadPool(2);
    }

    @Override
    public void run() {
        while (true){
            try {
                service.execute(new RequestHTTPHandler(
                        socket.accept(),
                        MandelbrotServer.SERVER_ADDRESS,
                        MandelbrotServer.RECORD_COMPUTATIONS_SERVER));
                System.out.println("State of previous request "+service.isTerminated());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }
}
