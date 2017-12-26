import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHTTPHandler implements Runnable {

    static final String HTML_START =
            "<html>" +
                    "<title>Mandelbrot Server</title>" +
                    "<body>";

    static final String HTML_END =
            "</body>" +
                    "</html>";

    private String computationsServerAddress="127.0.0.1";
    private int port = 9989;

    static final String HOME_PAGE_START ="";

    static final String HOME_PAGE_END ="";

    private Socket clientHTTP = null;

    public RequestHTTPHandler(Socket clientHTTP) {
        this.clientHTTP = clientHTTP;
    }

    public RequestHTTPHandler(Socket clientHTTP,String address,int port) {
        this.clientHTTP = clientHTTP;
        this.computationsServerAddress = address;
        this.port = port;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Logger.getGlobal().log(Level.SEVERE,"I am dead "+ Thread.currentThread().getId());

    }

    @Override
    public void run() {
        try {
            //TODO initialise HTTPRequest Object
            HttpRequest request = new HttpRequest(clientHTTP);
            request.init();
            switch (request.getMethod()){
                case "GET":
                    System.out.println("Do GET " + Thread.currentThread().getId());
                    doGet(request);
                    break;

                default:
                    sendResponse(400,request,false);
            }
            //clientHTTP.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpRequest request) throws UnsupportedEncodingException, FileNotFoundException {
        //Decode URL to known what response to send
        String target = URLDecoder.decode(request.getHttpQuery().split("\\?")[0].replaceFirst("/",""),"UTF-8");
        if (target.equals("") || (new File(target)).isFile()){
            //Case when the target is root directory or an existing file [200]
            Logger.getGlobal().log(Level.INFO,"Right target "+ Thread.currentThread().getId());
            //System.out.println("SUCCESS 200 ");
            sendResponse(200,request,true);
        }else {
            //otherwise send "File not found page" [404]
            Logger.getGlobal().log(Level.INFO,"Bad target (not found) "+ Thread.currentThread().getId() );
            sendResponse(404,request,false);
        }

    }
    private void sendResponse(int statusCode, HttpRequest request, boolean isFile) {
        //Separate parameters request from target file
        String[] queryParts = request.getHttpQuery().split("\\?");
//        System.out.println("Query before"+request.getHttpQuery());
//        System.out.println("Query parts"+queryParts[0]);
//        System.out.println(queryWithoutParams);
//        System.out.println("Query "+queryWithoutParams);
        String queryWithoutParams ;
        queryWithoutParams = (!queryParts[0].equals("/"))
                    ? queryParts[0].replaceFirst("/", ""): "ProjetPW.html";

        //Initialize variable represent header HTTP components
        String serverdetails = "Server: Java HTTPServer\r\n";
        String statusLine = "";
        String contentTypeLine = "";
        String contentLengthLine = "";
        FileInputStream fileInputStream;
        String header="";
        String responseBody = "";

        //Verify extension of file target and set right value to "Content-Type" in header http response
        if (!queryWithoutParams.endsWith(".htm") && !queryWithoutParams.endsWith(".html"))
            contentTypeLine = "Content-Type: \r\n";
        else contentTypeLine = "Content-Type: text/html" + "\r\n";

        //Choose the treatment from status code of header
        switch (statusCode){
            case 200:
                System.out.println("Sending HTTP/1.1 200 OK");
                statusLine = "HTTP/1.1 200 OK" + "\r\n";
                header=statusLine+serverdetails+contentTypeLine+contentLengthLine;
                try {
                    if (isFile) {

                        //When file target was an image with PNG format
                        if(queryWithoutParams.endsWith(".png")){
                            //Set contentType of image in the header
                            contentTypeLine = "image/*";
                            //Build header
                            header=statusLine+serverdetails+contentTypeLine+contentLengthLine;
                            //Get the parameters of computation from the "Referer" line header of HTTP request client
                            Map<String, List<String>> params = HttpRequest.getQueryParams(request.getHeaders().get("Referer"));
                            System.out.println("Treats image generation");
                            System.out.println("-------------------------------------------------------");
                            //Create serializer to commands computation and get the image raw data
                            MandelbrotSerializerProtocol serializer = new MandelbrotSerializerProtocol(
                                    MandelbrotServer.DEFAULT_HEIGHT * MandelbrotServer.DEFAULT_WIDTH *3 + 1024);
                            //Transform parameters to JSON format
                            JSONObject req = MandelbrotSerializerProtocol.paramsToRequest(params);
                            //Connect to Computation Server
                            Socket socket = new Socket(computationsServerAddress,port);
                            //Send a commands to ComputationServer
                            serializer.write(req.toString(),socket.getOutputStream());
                            //Receive Response
                            Logger.getGlobal().log(Level.INFO,"Wait response from server computation "+ Thread.currentThread().getId());
                            req = serializer.read(socket.getInputStream());

                            Logger.getGlobal().log(Level.INFO,"Receive response and send it ");
                            //Copy from serializer buffer result computation to "bytes"
                            System.out.println(serializer);
                            byte[] bytes = Arrays.copyOfRange(serializer.getBuffer(),serializer.getRawDataOffset(),serializer.getRawDataOffset()+serializer.getRawDataSize());
//                            WorkLauncher.printInfile(bytes,MandelbrotServer.DEFAULT_WIDTH,MandelbrotServer.DEFAULT_HEIGHT,"toSend");
                            //Consructs the socle of our images
                            //Build DataBuffer from Raw data of imageSet
                            //serializer.getBuffer(),serializer.getRawDataSize(),serializer.getRawDataOffset()
                            DataBuffer bufferD = new DataBufferByte(bytes,1024);

                            //3 bytes per pixel: red, green, blue
                            WritableRaster raster = Raster.createInterleavedRaster(bufferD,MandelbrotServer.DEFAULT_WIDTH, MandelbrotServer.DEFAULT_HEIGHT, 3 * MandelbrotServer.DEFAULT_WIDTH, 3, new int[] {0, 1, 2}, (Point)null);
                            //Define a Color model (RGB)
                            ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
                            //Build BufferImage
                            BufferedImage image = new BufferedImage(cm, raster, true, null);

                            //sendData(header+contentLengthLine,request);
                            System.out.println("Content data buffer "+serializer.getRawDataSize());
                            contentLengthLine = "Content-Length:\r\n"
                                    +"Connection: close\r\n"+"\r\n";

                            //Send header of HTTP request
                            sendData(header+contentLengthLine,request);
                            ImageIO.write(image, "png",request.outputStreamResponse());
                            //Send data in OutputStream in One time
                            //request.outputStreamResponse().flush();
                            //(new OutputStreamImageOutputStreamSpi()).createOutputStreamInstance(image).write(buffer.array());
                            //System.out.println("OutputStream Size \r\n"+(new FileInputStream("resultats.png")).available());
                            //Send data to client HTTP
                            //sendFile(fileInputStream,request.outputStreamResponse());
                            //request.outputStreamResponse().close();

                            clientHTTP.close();
                            clientHTTP = null;
                            System.out.println("-------------------------------------------------------");
                            return;
                        }
                        fileInputStream = new FileInputStream(queryWithoutParams);
                        contentLengthLine = "Content-Length: " + Integer.toString(fileInputStream.available()) + "\r\n"
                        +"Connection: close\r\n"+"\r\n";
                        sendData(header+contentLengthLine,request);
                        sendFile(fileInputStream,request.outputStreamResponse());
                    }else {
                        contentLengthLine = "Content-Length: 0 \r\n"+"Connection: close\r\n"+"\r\n";
                        sendData(header+contentLengthLine,request);
                    }
                    System.out.println("Request sended \r\n"+header+contentLengthLine);

                } catch (IOException e) {
                    e.printStackTrace();
                    sendResponse(404,request,false);
                }

                break;

            case 400:
                //Case not found file
                System.out.println("Sending HTTP/1.1 400 File not found");
                statusLine = "HTTP/1.1 400 Bad Request" + "\r\n";
                contentTypeLine = "Content-Type: text/html" + "\r\n";
                responseBody=HTML_START.concat("<H1> ERROR 400 </H1> </br> Bad Request").concat(HTML_END);
                contentLengthLine = "Content-Length: " + responseBody.length() + "\r\n"
                        +"Connection: close\r\n"+"\r\n";
                header=statusLine+serverdetails+contentTypeLine+contentLengthLine;
                try {
                    //Send header and close InputStream and OutputStream
                    sendData(header,request);
                    request.bufferClient().close();
                    request.outputStreamResponse().close();
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        request.bufferClient().close();
                        request.outputStreamResponse().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                    break;

            case 404:
                //When receive a bad HTTP request
                System.out.println("Sending HTTP/1.1 400 Bad Request");
                statusLine = "HTTP/1.1 404 Not Found" + "\r\n";
                contentTypeLine = "Content-Type: text/html" + "\r\n";
                responseBody = HTML_START.concat("<H1> ERROR 404 </H1> </br> File not found").concat(HTML_END);
                contentLengthLine = "Content-Length: " + responseBody.length() + "\r\n"
                        + "Connection: close\r\n" + "\r\n";
                header = statusLine + serverdetails + contentTypeLine + contentLengthLine+responseBody;
                try {
                    sendData(header, request);
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        request.outputStreamResponse().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
                break;
                }
    }

    private void sendData(String header, HttpRequest request) throws IOException {
        request.outputStreamResponse().writeBytes(header);
    }


    private void sendFile (FileInputStream fin, DataOutputStream out) throws IOException {
        byte[] buffer = new byte[1024] ;
        int bytesRead;

        while ((bytesRead = fin.read(buffer)) != -1 ) {
            out.write(buffer, 0, bytesRead);
        }
        fin.close();
        clientHTTP.close();
    }

    public static void main (String args[]) throws Exception {
//        String  line;
//        Scanner sc = new Scanner(new File("F:\\sample-RTdM2l5h9kAYbyQoos9UZyF8ENizJzOeECZtz79AySE\\input1.txt"));
//        int comp_Pipo=0;
//        line = sc.nextLine();
//        String[] array = line.split(" ");
//        comp_Pipo = Integer.parseInt(array[0]);
//        line = sc.nextLine();
//        array = line.replaceFirst("[|\\?|!|\\.|,]"," ").split(" ");
//        int[] nbrComp = new int[array.length];
//
//        int totalWords = 0;
//        for (int i = 1; i < array.length; i++) {
//            nbrComp[i] = Integer.parseInt(array[i]);
//            totalWords+=nbrComp[i];
//        }
//
//        int tmp = totalWords;
//        ArrayList<ArrayList<String>> matrix = new ArrayList<>(comp_Pipo);
//
//        for (int i = 1; i < matrix.size(); i++) {
//            matrix.add(new ArrayList<>());
//        }
//
//        while (sc.hasNext() && tmp>0) {
//            line = sc.nextLine();
//            int s = 0;
//            for (int i = 0; i < comp_Pipo; i++) {
//                s+=nbrComp[i];
//                if (tmp+s>= totalWords) {
//                    matrix.get(i).add(line);
//                }
//            }
//            tmp--;
//        }
//        line = sc.nextLine();
//        tmp = Integer.parseInt(line);
//        int resultat = 0;
//        while (sc.hasNext() && tmp>0) {
//            line = sc.nextLine();
//            array = line.split(" ");
//            boolean flag = true;
//            for (int i = 0; i < comp_Pipo; i++) {
//                flag&=matrix.get(i).contains(array[i]);
//            }
//            if (flag)
//                resultat++;
//            tmp--;
//        }
//	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
//        System.out.print(resultat);
//        sc =new Scanner(new File("F:\\sample-RTdM2l5h9kAYbyQoos9UZyF8ENizJzOeECZtz79AySE\\output1.txt"));
//        System.out.println("Resultat :"+(Integer.parseInt(sc.nextLine())== resultat));

        ServerSocket Server = new ServerSocket (8991, 100, InetAddress.getByName("127.0.0.1"));
        System.out.println ("TCPServer Waiting for client on port 8991");

        while(true) {
            Socket connected = Server.accept();
            (new Thread(new RequestHTTPHandler(connected))).start();
        }
    }
}
