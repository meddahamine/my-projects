import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class MandelbrotSerializerProtocol {

    private ByteBuffer byteBuffer;
    private int rawDataOffset;
    private int rawDataSize;
    private byte[] buffer;
    private JSONObject data;
    private int position;
    private int limit;

    public MandelbrotSerializerProtocol(int capacity) {
        this.buffer = new byte[capacity];
        byteBuffer = ByteBuffer.allocate(capacity);
        this.position = 0;
        this.limit = 0;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public JSONObject read(){
        System.out.println("read op begin  "+Thread.currentThread().getId());
        reset();
        //Read length of request body
        int length = getInt();
        System.out.println("length header"+length);
        //Decode the request
        byteBuffer.clear();
        //byteBuffer.position(0);
        byteBuffer.put(buffer,position,length);
        //byteBuffer.limit(length);
        byteBuffer.flip();
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = cs.decode(byteBuffer);
        byteBuffer.limit(cb.limit());
        System.out.println("Charset length "+ cb.array().length);
        System.out.println("Charset "+ Arrays.toString(cb.array()));
        //Construct JSON of request
        data = new JSONObject(cb.toString());
        position+=length;
        //Treat case when we have data
        if (!data.isNull("dataBytes") && data.getBoolean("dataBytes")){
            rawDataSize = getInt();
            rawDataOffset = getPosition();
        }
        return data;
    }

    public JSONObject read(InputStream in) throws IOException {
        reset();
        //Read from the InputStream of socket
        int result ;

        result = in.read();
        if (result==-1)
            throw new IOException("Forget client connection");

        while (in.available() > 0) {
            putByte((byte) result);
            result = in.read();
        }
        System.out.println("Bytes receives "+limit);
        return read();
    }

    public JSONObject getData() {
        return data;
    }

    public int getPosition() {
        return position;
    }

    public int getRawDataOffset() {
        return rawDataOffset;
    }

    public int getRawDataSize() {
        return rawDataSize;
    }

    private void putByte(byte b){
        buffer[position] = b;
        setPosition(++position);
    }

    public byte get(){
        return buffer[position++];
    }

    private byte getByte(){
        return buffer[position++];
    }

    private void putInt(int i){
        putByte((byte)((i>>24)&0xff));
        putByte((byte)((i>>16)&0xff));
        putByte((byte)((i>>8)&0xff));
        putByte((byte)(i&0xff));
    }

    private int getInt(){
        return ((getByte()&0xff)<<24) + ((getByte()&0xff)<<16) + ((getByte()&0xff)<<8) + (getByte()&0xff);
    }

    private void put(byte[] array){
        for (int i = 0; i < array.length && position < buffer.length; i++) {
            putByte(array[i]);
        }
    }

    private void get(byte[] array){
        int temp = 0;
        for (int i = 0; i < array.length && position < buffer.length; i++) {
            array[i] = getByte();
            temp = i;
        }
        setPosition(position+temp);
    }

    private void setPosition(int newPosition){
        position = newPosition;
        if (newPosition > limit){
            limit = newPosition;
        }
    }

    public byte get(int i){
        return buffer[i];
    }

    public void write(String st){
        byteBuffer.clear();
        reset();
        Charset cs = Charset.forName("UTF-8");
        putInt(cs.encode(st).array().length);
        put(cs.encode(st).array());
    }

    public void write(String st, byte[] bytes){
        write(st);
        putInt(bytes.length);
        position-=4;
        System.out.println("RawDataSize value after put ==> "+getInt());
        rawDataOffset = getPosition()+1;
        put(bytes);
        rawDataSize = bytes.length;
    }

    public void write(String st, byte[] bytes, OutputStream out) throws IOException {
        byteBuffer.clear();
        write(st,bytes);
        System.out.println("Limit (3) "+limit);
        out.write(buffer,0,limit+1);
    }

    public void write(OutputStream out) throws IOException {
        reset();
        //System.out.println(toString());
        System.out.println("Limit (1)"+limit);
        out.write(buffer,0,limit+1);
        out.flush();
    }

    public void write(String st, OutputStream out) throws IOException {
        reset();
        write(st);
        out.write(buffer,0, limit+1);
    }


    private void reset(){
        position = 0;
    }

    public void readData(){ position = rawDataOffset;}

    public void resetlimit(){
        position = 0;
        limit = 0;
    }

    public static JSONObject paramsToRequest(Map<String,List<String>> params){
        //Create JSONObject encapsulate request
        JSONObject request = new JSONObject();

        //Get from parameters all what we need to build request
        int width = Integer.parseInt(params.getOrDefault("width", Collections.singletonList(String.valueOf(MandelbrotServer.DEFAULT_WIDTH))).get(0));
        int height = Integer.parseInt(params.getOrDefault("height", Collections.singletonList(String.valueOf(MandelbrotServer.DEFAULT_HEIGHT))).get(0));
        int iter = Integer.parseInt(params.getOrDefault("Iteration",Collections.singletonList("50")).get(0));
        double xMin = Double.parseDouble(params.getOrDefault("Xmin",Collections.singletonList("-2")).get(0));
        double xMax = Double.parseDouble(params.getOrDefault("Xmax",Collections.singletonList("1")).get(0));
        double yMin = Double.parseDouble(params.getOrDefault("Ymin",Collections.singletonList("-1")).get(0));
        double yMax = Double.parseDouble(params.getOrDefault("Ymax",Collections.singletonList("1")).get(0));

        //Build request
        request.put("idClient","LocalClient");
        request.put("OP","ComputationCommand");
        request.put("offset",0);
        request.put("Xmin",xMin);
        request.put("Xmax",xMax);
        request.put("Ymin",yMin);
        request.put("Ymax",yMax);
        request.put("width",width);
        request.put("height",height);
        request.put("Iteration",iter);

        return request;
    }

    @Override
    public String toString() {
        return "MandelbrotSerializerProtocol{\n" +
                "byteBuffer=" + byteBuffer +
                ",\n rawDataOffset=" + rawDataOffset +
                ", rawDataSize=" + rawDataSize +
                ", buffer=" + buffer.length +
                ", data=" + data +
                ", position=" + position +
                ", limit=" + limit +
                "\n}";
    }
}
