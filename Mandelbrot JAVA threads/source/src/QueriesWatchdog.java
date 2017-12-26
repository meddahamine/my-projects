import java.io.IOException;


public class QueriesWatchdog implements Runnable {

    private CalculatorClient client;
    private MandelbrotSerializerProtocol serializer;

    public QueriesWatchdog(CalculatorClient client, int sizeBuffer) {
        this.client = client;
        this.serializer = new MandelbrotSerializerProtocol(sizeBuffer);
    }


    @Override
    public void run() {
        while (client.isConnected()){
                try {
                    System.out.println("Client wait request");
//                    //TODO read request
//                    //TODO calculate image
                    client.response(serializer);
                    System.out.println("My serializer before sends to server \n"+serializer);
                    serializer.write(client.getSocket().getOutputStream());
                    System.out.println("Client do response");
                    //TODO send image
                }catch(IOException e){
                    e.printStackTrace();
                    client.setFlag(false);
                }
        }
    }
}
