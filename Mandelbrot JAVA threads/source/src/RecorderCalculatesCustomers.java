import org.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RecorderCalculatesCustomers extends Thread{

    private ServerSocket socket;
    private ArrayList<CalculatesClientSpec> sockets;

    public RecorderCalculatesCustomers(ServerSocket socketCalculi,ArrayList<CalculatesClientSpec> sockets) {
        this.socket = socketCalculi;
        this.sockets = sockets;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("waiting Client(Recorder)");
                Socket ns = socket.accept();
                cleanSocketsClosed(sockets);
                synchronized (sockets) {
                    System.out.println("Register Client");
                    sockets.add(specClient(ns));
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private CalculatesClientSpec specClient(Socket socket){
        JSONObject data = new JSONObject();
        CalculatesClientSpec clientSpec = new CalculatesClientSpec(socket);
        data.put("idClient",clientSpec.getId());
        data.put("OP","ConfigCom");
        MandelbrotSerializerProtocol serializer = new MandelbrotSerializerProtocol(1024);
        System.out.println("Data content of ConfigComm");
        System.out.println(data.toString());
        try {
            serializer.write(data.toString(),socket.getOutputStream());
            System.out.println("Serializer state :\n"+serializer);
            data = serializer.read(socket.getInputStream());
            if (data.getInt("resultCode") == 200){
                clientSpec.setSizeBuffer(data.getInt("BufferSize"));
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return clientSpec;
    }

    public static void cleanSocketsClosed(ArrayList<CalculatesClientSpec> sockets) throws IOException {
        synchronized (sockets) {
            Iterator<CalculatesClientSpec> iterator = sockets.iterator();
            System.out.println("Cleaning closed socket ");
            while (iterator.hasNext()) {
                RecorderCalculatesCustomers.CalculatesClientSpec client = iterator.next();
                if (client.getSocket().isClosed()) {
                    client.disconnect();
                    iterator.remove();
                    System.out.println("Socket " + System.identityHashCode(client.getSocket()));
                }

            }
        }
    }

    public static class CalculatesClientSpec{

        private int sizeBuffer;
        private boolean free;
        private Socket socket;
        private String id;

        public CalculatesClientSpec(Socket socket) {
            this.socket = socket;
            this.free = true;
            this.sizeBuffer = CalculatorClient.DEFAULT_SIZE_BUFFER;
            this.id = String.valueOf(System.identityHashCode(this));
        }

        public int getSizeBuffer() {
            return sizeBuffer;
        }

        public void setSizeBuffer(int sizeBuffer) {
            this.sizeBuffer = sizeBuffer;
        }

        public boolean isFree() {
            return free;
        }

        public void disconnect() throws IOException {
            this.free = false;
            socket.close();
        }

        public boolean isClosed(){
            MandelbrotSerializerProtocol serializer = new MandelbrotSerializerProtocol(1024);
            JSONObject data = new JSONObject();
            data.put("idClient",getId());
            data.put("OP","ControlVitality");
            data.put("Msg","PING");
            try{
                serializer.write(data.toString(),socket.getOutputStream());
                data = serializer.read(socket.getInputStream());
                if (data.isNull("Msg") && !data.getString("Msg").equals("PONG"))
                    throw new IOException();
                return false;
            }catch (IOException e){
                Logger.getGlobal().log(Level.SEVERE,"Forget connection with client [id:"+getId()+"]");
                return true;
            }
        }
        public Socket getSocket() {
            return socket;
        }

        public String getId() {
            return id;
        }
    }
}
