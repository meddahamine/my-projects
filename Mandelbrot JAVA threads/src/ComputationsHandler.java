import org.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ComputationsHandler extends Thread{

    public static final int LIMIT_WAIT = 10000000;

    private ServerSocket socket;
    private ArrayList<RecorderCalculatesCustomers.CalculatesClientSpec> sockets;
    private ExecutorService threads ;
    private MandelbrotSerializerProtocol serializer;

    public ComputationsHandler(ServerSocket socketWorks, ArrayList<RecorderCalculatesCustomers.CalculatesClientSpec> sockets) {
        this.socket =  socketWorks;
        this.sockets = sockets;
        this.serializer = new MandelbrotSerializerProtocol(
                MandelbrotServer.DEFAULT_HEIGHT * MandelbrotServer.DEFAULT_WIDTH * 3 + 1024);
        this.threads = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket newSocket = socket.accept();
                System.out.println("New Computations commands");
                byte[] bytes = doJob(serializer.read(newSocket.getInputStream()));
                System.out.println("Bytes generated : " + bytes.length);
                serializer.getData().put("dataBytes",true);
                serializer.write(serializer.getData().toString(), bytes, newSocket.getOutputStream());
                WorkLauncher.printInfile(bytes,MandelbrotServer.DEFAULT_WIDTH,MandelbrotServer.DEFAULT_HEIGHT,"ComputationsHandler");
                System.out.println("Bytes sends");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] doJob(JSONObject params) throws InterruptedException, IOException {
        int width = params.optInt("width", MandelbrotServer.DEFAULT_WIDTH);
        int height = params.optInt("height", MandelbrotServer.DEFAULT_HEIGHT);
        byte[] bytes;
        System.out.println("Initialized variables");
        synchronized (sockets){
            System.out.println("In critical section");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

            JSONObject data = new JSONObject();
            int iter = params.optInt("Iteration",50);
            double xMin = params.optDouble("Xmin",-2);
            double xMax = params.optDouble("Xmax",1);
            double yMin = params.optDouble("Ymin",-1);
            double yMax = params.optDouble("Ymax",1);
            double stepY = 1;

            data.put("idClient","LocalClient");
            data.put("OP","ComputationCommand");
            data.put("offset",0);
            data.put("Xmin",xMin);
            data.put("Xmax",xMax);
            data.put("Ymin",yMin);
            data.put("Ymax",yMax);
            data.put("width",width);
            data.put("height",height);
            data.put("Iteration",iter);


//            WorkLauncher.printInfile(CalculatorClient.doComputaion(data),width,height);
//            return CalculatorClient.doComputaion(data);
            System.out.println("Clients before cleaning operation : "+sockets.size());
            ArrayList<RecorderCalculatesCustomers.CalculatesClientSpec> array = new ArrayList<>();
            sockets.stream().filter(RecorderCalculatesCustomers.CalculatesClientSpec::isClosed).forEach(array::add);
            sockets.removeAll(array);
            int participants = (sockets.size()%2==0 || sockets.size()==1)?sockets.size():sockets.size()-1;
            AtomicInteger atomicInteger = new AtomicInteger(participants);
            System.out.println("Clients after cleaning operation : "+sockets.size());
            if (sockets.size()>0){
//                int pWidth = (width<=0)?participants:width;
//                int pHeight =(height<=0)?1:height/participants;

                //double stepX =(Math.abs(xMax-xMin)>0)?Math.abs(xMax-xMin)/sockets.size():1;
//                stepY =(Math.abs(yMax-yMin)>0)?Math.abs(yMax-yMin)/participants:1;
//                data.put("width",width);
//                data.put("height",height);
//                    data.put("Ymax",yMax);
//                    yMax -= stepY;
//                    data.put("Ymin",yMax);
//                    yMax -= stepY;

                bytes = new byte[height*width];
                for (int i = 0; i < participants; i++) {

                    data.put("idClient",sockets.get(i).getId());
                    data.put("step",participants);
                    data.put("offset",i );
                    threads.execute(new WorkLauncher(bytes,data.toString(),sockets.get(i),atomicInteger));
                }
            }else {
                return CalculatorClient.doComputaion(data);
            }

            int compt = 0;
            while (atomicInteger.get()>0 ) compt++;
//            && compt< LIMIT_WAIT
//            if (compt == LIMIT_WAIT){
//                Logger.getGlobal().log(Level.SEVERE,"Too time for compute a result");
//                bytes = new byte[width*height*3];
//                return bytes;
//            }
            WorkLauncher.printInfile(WorkLauncher.translateToRGB(bytes,MandelbrotServer.DEFAULT_WIDTH,MandelbrotServer.DEFAULT_HEIGHT),MandelbrotServer.DEFAULT_WIDTH,MandelbrotServer.DEFAULT_HEIGHT,"CHDo");
        }

            System.out.println("Return result");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        return WorkLauncher.translateToRGB(bytes,MandelbrotServer.DEFAULT_WIDTH,MandelbrotServer.DEFAULT_HEIGHT);
    }

    public static byte[] translateToRGB(byte[][] matrix){
        byte[] result = new byte[matrix.length*matrix[0].length];
        int index =0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[index] = matrix[j][i];
                index++;
            }
        }
        return result;
    }

}
