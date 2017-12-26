import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;


public class MandelbrotServer extends Thread{

    public static final  int CALCULI_SERVER = 9989;
    public static final  int HTTP_SERVER = 80;
    public static final  int RECORD_COMPUTATIONS_SERVER = 1995;

    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    public static final String SERVER_ADDRESS = "127.0.0.1";

    //Socket for incoming connection
    private ServerSocket calculatorsSocket;
    private ServerSocket incomeSocket;
    private ServerSocket computationsRecorder;
    private ArrayList<RecorderCalculatesCustomers.CalculatesClientSpec> sockets ;



    public MandelbrotServer(int calculatorsPort, int httpPort, int computations) throws IOException {
        calculatorsSocket =  new ServerSocket (calculatorsPort, 100, InetAddress.getByName(SERVER_ADDRESS));
        computationsRecorder =  new ServerSocket(computations,100,InetAddress.getByName(MandelbrotServer.SERVER_ADDRESS));
        incomeSocket = new ServerSocket (httpPort, 100, InetAddress.getByName(SERVER_ADDRESS));
        sockets = new ArrayList<>();
    }

//    byte[] doJob(Map<String,List<String>> params){
//        int width = Integer.parseInt(params.getOrDefault("width", Collections.singletonList(String.valueOf(DEFAULT_WIDTH))).get(0));
//        int height = Integer.parseInt(params.getOrDefault("height", Collections.singletonList(String.valueOf(DEFAULT_HEIGHT))).get(0));
//        byte[] bytes = new byte[width * height * 3];
//        System.out.println("Initialized variables");
////        synchronized (sockets){
//            System.out.println("In critical section");
//            JSONObject data = new JSONObject();
//            int iter = Integer.parseInt(params.getOrDefault("Iteration",Collections.singletonList("50")).get(0));
//            double xMin = Double.parseDouble(params.getOrDefault("Xmin",Collections.singletonList("-2")).get(0));
//            double xMax = Double.parseDouble(params.getOrDefault("Xmax",Collections.singletonList("1")).get(0));
//            double yMin = Double.parseDouble(params.getOrDefault("Ymin",Collections.singletonList("-1")).get(0));
//            double yMax = Double.parseDouble(params.getOrDefault("Ymax",Collections.singletonList("1")).get(0));
//            double stepY = 1;
//
//            data.put("idClient","LocalClient");
//            data.put("OP","ComputationCommand");
//            data.put("offset",0);
//            data.put("Xmin",xMin);
//            data.put("Xmax",xMax);
//            data.put("Ymin",yMin);
//            data.put("Ymax",yMax);
//            data.put("width",width);
//            data.put("height",height);
//            data.put("Iteration",iter);
//
////        WorkLauncher.printInfile(CalculatorClient.doComputaion(data),width,height);
////        return CalculatorClient.doComputaion(data);
//            if (sockets.size()>0){
//                int pWidth = (width<=0)?sockets.size():width;
//                int pHeight =(height<=0)?1:height/sockets.size();
//
//                //double stepX =(Math.abs(xMax-xMin)>0)?Math.abs(xMax-xMin)/sockets.size():1;
//                stepY =(Math.abs(yMax-yMin)>0)?Math.abs(yMax-yMin)/sockets.size():1;
//                data.put("width",pWidth);
//                data.put("height",pHeight);
//
//                for (int i = 0; i < sockets.size(); i++) {
//                    data.put("idClient",sockets.get(i).getId());
//                    data.put("Ymax",yMax);
//                    yMax -= stepY;
//                    data.put("Ymin",yMax);
//                    yMax -= stepY;
//                    data.put("offset",pWidth * pHeight * i );
//                    (new Thread (new WorkLauncher(bytes,data,sockets.get(i)))).start();
//                }
//            }else {
//                System.out.println("JSON request " + data);
//                System.out.println("Create Computation Client");
//                CalculatorClient client = new CalculatorClient("192.168.5.1", CALCULI_SERVER, bytes.length + CalculatorClient.DEFAULT_SIZE_BUFFER);
//                RecorderCalculatesCustomers.CalculatesClientSpec clientSpec = new RecorderCalculatesCustomers.CalculatesClientSpec(client.getSocket());
//                clientSpec.setSizeBuffer(CalculatorClient.DEFAULT_SIZE_BUFFER);
//                client.run();
//                (
//                        new Thread
//                                (new WorkLauncher(
//                                        bytes,
//                                        data,
//                                        clientSpec
//                                        ))
//                ).start();
//                try {
//                    join();
//                    client.setFlag(false);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//            try {
//                join();
//                System.out.println("Thread Worker was dead");
//                cleanSocketsClosed();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                notifyAll();
//                cleanSocketsClosed();
//            }
////            notifyAll();
////        }
//        //TODO implement Image calculi
//        System.out.println("Return result");
//        return bytes;
//    }

    public void run() {
        (new RecorderCalculatesCustomers(calculatorsSocket,sockets)).start();
        (new ComputationsHandler(computationsRecorder,sockets)).start();
        (new HTTPHandler(incomeSocket)).start();
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void cleanSocketsClosed(){
        Iterator<RecorderCalculatesCustomers.CalculatesClientSpec> iterator = sockets.iterator();
        while (iterator.hasNext()){
            RecorderCalculatesCustomers.CalculatesClientSpec client = iterator.next();
            if (client.getSocket().isClosed())
                iterator.remove();

        }
    }



    public static void main(String[] args) throws IOException {
        System.out.println("Build server");
        MandelbrotServer server = new MandelbrotServer(CALCULI_SERVER,HTTP_SERVER,RECORD_COMPUTATIONS_SERVER);
        System.out.println("Start server");
        server.start();
    }
}
