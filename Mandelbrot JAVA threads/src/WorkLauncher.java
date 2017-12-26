import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WorkLauncher implements Runnable {

    private JSONObject data;
    private RecorderCalculatesCustomers.CalculatesClientSpec client;
    private byte[] bytes;
    private OutputStream out;
    private InputStream in;
    private AtomicInteger look;

    public WorkLauncher(byte[] bytes, String data, RecorderCalculatesCustomers.CalculatesClientSpec client, AtomicInteger look) {
        this.data = new JSONObject(data);
        this.client = client;
        try {
            this.out = client.getSocket().getOutputStream();
            this.in = client.getSocket().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bytes = bytes;
        this.look = look;
        System.out.println("Create thread worker");
    }

    @Override
    public void run() {
        Logger.getGlobal().log(Level.INFO,"Launch request command to computation client");
        MandelbrotSerializerProtocol serializer = new MandelbrotSerializerProtocol(client.getSizeBuffer());
        try {
            System.out.println("****************************"+Thread.currentThread().getId()+"*******************************");
            serializer.write(data.toString(),out);
            JSONObject data = serializer.read(in);
            int width = data.getInt("width");
            int height = data.getInt("height");
            int offset = data.getInt("offset");
            int step = data.getInt("step");
            System.out.println("Read JSON response");
            if (data.getInt("resultCode") == 200) {
//                int pos = serializer.getRawDataOffset();
//                System.out.println("My serializer in deserializing op "+serializer);
//                for (int i = 0; i < height * (width-1)  ; i++) {
//                    serializer.r
//                    bytes[offset + 3*i] = serializer.get(pos+i);
//                    bytes[offset + 3*i + 1] = serializer.get(pos+i);
//                    bytes[offset + 3*i + 2] = serializer.get(pos+i);
//                }
                serializer.readData();
                for (int i = offset; i < height; i+=step) {
                    for (int j = 0; j < width; j++) {
                        bytes[width*i+j] = serializer.get();
                    }
                }
//                bytes = Arrays.copyOfRange(
//                        serializer.getBuffer(),
//                        serializer.getRawDataOffset(),
//                        serializer.getRawDataOffset()+serializer.getRawDataSize());
            }
            System.out.println("result code : " +data.getInt("resultCode"));
            Logger.getGlobal().log(Level.INFO,"Data received "+ Thread.currentThread().getId());
            System.out.println("****************************"+Thread.currentThread().getId()+"*******************************");
            look.decrementAndGet();
            look=null;
            client=null;
            out=null;
            in = null;
            bytes=null;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            look.decrementAndGet();
        }
    }

    public static byte[] translateToRGB(byte[] src,int width, int height){

        byte[] dst = new byte[width*height*3];
        for (int i = 0; i < height * (width-1) ;i++) {
            dst[3*i] = (byte) (src[i]*10);
            dst[3*i+1] = (byte) (src[i]*30);
            dst[3*i+2] = (byte) (src[i]*15);
        }

        return dst;
    }

    public static void printInfile(byte[] bytes,int width, int height, String name){
        DataBuffer bufferD = new DataBufferByte(bytes, bytes.length);

        //3 bytes per pixel: red, green, blue
        WritableRaster raster = Raster.createInterleavedRaster(bufferD, width, height, 3 * width, 3, new int[] {0, 1, 2}, (Point)null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage image = new BufferedImage(cm, raster, true, null);

        try {
            ImageIO.write(image, "png", new File(name+".png"));
            //(new OutputStreamImageOutputStreamSpi()).createOutputStreamInstance(image).write(buffer.array());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
