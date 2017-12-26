import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class CalculatorClient {

    public static final int DEFAULT_SIZE_BUFFER = 8000005;

    private Socket socket;
    private boolean flag;
    private int sizeBuffer;

    public CalculatorClient() {
    }

    public CalculatorClient(String address, int port, int sizeBuffer) {
        flag = true;
        this.sizeBuffer = sizeBuffer;
        try {
            socket = new Socket(address,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CalculatorClient(String address, int sizeBuffer) {
        flag = true;
        this.sizeBuffer = sizeBuffer;
        try {
            String[] array = address.split(":");
            socket = new Socket(InetAddress.getByName(array[0]),Integer.valueOf((array.length==2)?array[1]:""+80));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CalculatorClient run(){
        //TODO configure the terms of exchanges
        System.out.println("Size buffer before launch watchdog "+sizeBuffer);
        (new Thread(new QueriesWatchdog(this,this.sizeBuffer))).start();
        return this;
    }

    public Socket getSocket(){
        return socket;
    }

    public boolean isConnected() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    /**
    This function take an ByteBuffer empty and not initialised and put response of server commands
     @param serializer Object serialize the communications in Mandelbrot Protocole
     */
    public synchronized byte[] response(MandelbrotSerializerProtocol serializer) throws IOException {
        /*
        JSON struct input
        idClient:String;
        offset:Int;
        repeat:Int;
        Xmin:double;
        Xmax:double;
        Ymin:double;
        Ymax:double;
        width:int;
        height:int;
         */

        serializer.resetlimit();
        //Read the request
        JSONObject request = serializer.read(socket.getInputStream());
        //Choice treatment
        if (!request.isNull("OP") && request.getString("OP").equals("ConfigCom")){
            configCom(serializer);
            return serializer.getBuffer();
        }

        if (!request.isNull("OP") && request.getString("OP").equals("ControlVitality")){
            pingResponse(serializer);
            return serializer.getBuffer();
        }

        int width = request.optInt("width",10);
        int height = request.optInt("height",10);

        //Get number of iterations
        int iter = request.optInt("Iteration",20);
        //Calculate the step value of Abscissa between each pixel(width axes)
        double stepX =Math.abs(request.optDouble("Xmax") - request.optDouble("Xmin"))/request.optInt("width",10);
        //Calculate the step value of orderly between each pixel(height axes)
        double stepY =Math.abs(request.optDouble("Ymax") - request.optDouble("Ymin"))/request.optInt("height",10);
        //Initialise the Cartesian coordinates of most left and up point in the set
        double bornInfX = request.optDouble("Xmin");
        double bornInfY = request.optDouble("Ymax");
        System.out.println("Xmin value : "+ request.optDouble("Xmin") + "Ymax value : "+request.optDouble("Ymax"));
        //Initialise Data buffer
        int step = request.optInt("step",1);
        int offset = request.optInt("offset",0);
        byte[] bytes = new byte[width * (height/step)];
        bornInfY-=(offset*stepY);
        for (int i = 0; i < (height/step); i++) {
            //Reset at the most left value
            bornInfX = request.optDouble("Xmin");
            for (int j = 0; j < width;j++) {
                //Test and assignment of value which corresponds
                byte value = (byte) (testMandelbrot(bornInfX,bornInfY,iter) &0xff);
                //Final format is PNG RGB-color
                bytes[width*i + j] = value;
                //Increase value(start Xmin to Xmax)
                bornInfX+=stepX;
            }
            //Decrease value(start Ymax to Ymin)
            bornInfY-=(stepY*step);
        }
        System.out.println("bornInfX value : "+ bornInfX + " bornInfY value : "+bornInfY);

        //Put the result code SUCCESS to response
        request.put("resultCode",200);
        //Write response and data
        request.put("dataBytes",true);
        System.out.println("Content request  : "+request.toString());
        System.out.println("Bytes to send  : "+bytes.length);
        serializer.write(request.toString(),bytes);
        WorkLauncher.printInfile(WorkLauncher.translateToRGB(bytes,width,height/step),width,height/step,"resultforClient"+Thread.currentThread().getId());
        return bytes;
    }

    private void configCom(MandelbrotSerializerProtocol serializer){
        JSONObject data = serializer.getData().put("BufferSize",sizeBuffer).put("resultCode",200);
        serializer.write(data.toString());
    }

    private void pingResponse(MandelbrotSerializerProtocol serializer){
        JSONObject data = serializer.getData().put("Msg","PONG").put("resultCode",200);
        serializer.write(data.toString());
    }

    public static byte[] doComputaion(JSONObject request){
        //Get number of iterations
        int iter = request.optInt("Iteration",20);
        //Calculate the step value of Abscissa between each pixel(width axes)
        double stepX = Math.abs(request.optDouble("Xmax") - request.optDouble("Xmin"))/request.optInt("width",10);
        //Calculate the step value of orderly between each pixel(height axes)
        double stepY = Math.abs(request.optDouble("Ymax") - request.optDouble("Ymin"))/request.optInt("height",10);
        //Initialise the Cartesian coordinates of most left and up point in the set
        double bornInfX = request.optDouble("Xmin");
        double bornInfY = request.optDouble("Ymax");
        System.out.println("Xmin value : "+ request.optDouble("Xmin") + "Ymax value : "+request.optDouble("Ymax"));
        //Initialise Data buffer
        byte[] bytes = new byte[request.optInt("width",10) * request.optInt("height",10) * 3];
        for (int i = 0; i < request.optInt("height"); i++) {
            //Reset at the most left value
            bornInfX = request.optDouble("Xmin");
            for (int j = 0; j < request.optInt("width")*3;j+=3) {
                //Test and assignment of value which corresponds
                byte value = (byte) ((testMandelbrot(bornInfX,bornInfY,iter)-iter)%256);
                //Final format is PNG RGB-color
                bytes[request.optInt("width")*3*i + j] = value;
                bytes[request.optInt("width")*3*i + j + 1] = value;
                bytes[request.optInt("width")*3*i + j + 2] = value;
                //Increase value(start Xmin to Xmax)
                bornInfX+=stepX;
            }
            //Decrease value(start Ymax to Ymin)
            bornInfY-=stepY;
        }
        System.out.println("bornInfX value : "+ bornInfX + " bornInfY value : "+bornInfY);

        //Put the result code SUCCESS to response
        request.put("resultCode",200);
        //Write response and data
        request.put("dataBytes",true);
        System.out.println("Content request  : "+request.toString());
        System.out.println("Bytes to send  : "+bytes.length);
        WorkLauncher.printInfile(bytes,request.optInt("width",10),request.optInt("height"),"resultforServerByComputation");
        return bytes;
    }

    public static int testMandelbrot (double xc , double yc , int n) {
        double xz =0;
        double yz =0;
        int i =0;
        while ( xz *xz + yz *yz <= 2*2 && i < n ) {
            double txz =xz ;
            xz = xz *xz - yz *yz + xc ;
            yz = 2.0* txz *yz + yc ;
            i++;
        }
        return i ;
    }

    public void test(String st) throws IOException {
        System.out.println("Mandelbrot for zero : "+testMandelbrot(0,0,50));
        CalculatorClient client = new CalculatorClient();
        JSONObject data = new JSONObject();
        int width = 600;
        int height = 400;
        data.put("repeat",50);
        data.put("width",width);
        data.put("height",height);
        data.put("Xmin",-2);
        data.put("Xmax",1);
        data.put("Ymin",-1);
        data.put("Ymax",1);

        byte[] aByteArray = doComputaion(data);

        DataBuffer bufferD = new DataBufferByte(aByteArray, aByteArray.length);

        //3 bytes per pixel: red, green, blue
        WritableRaster raster = Raster.createInterleavedRaster(bufferD, width, height, 3 * width, 3, new int[] {0, 1, 2}, (Point)null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage image = new BufferedImage(cm, raster, true, null);

        try {
            ImageIO.write(image, "png", new File(st+".png"));
            //(new OutputStreamImageOutputStreamSpi()).createOutputStreamInstance(image).write(buffer.array());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        CalculatorClient client = new CalculatorClient(MandelbrotServer.SERVER_ADDRESS,MandelbrotServer.CALCULI_SERVER,CalculatorClient.DEFAULT_SIZE_BUFFER);
        //CalculatorClient client = new CalculatorClient("0.tcp.ngrok.io:14731",CalculatorClient.DEFAULT_SIZE_BUFFER);
        client.run();
    }

}
