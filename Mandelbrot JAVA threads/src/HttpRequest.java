import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.*;



/**
 * Object encapsulate all parameters of interactions with HTTP client
 */
public class HttpRequest {

    private Socket connectedClient = null;
    private BufferedReader inFromClient = null;
    private DataOutputStream outToClient = null;
    private HashMap<String,String> headers;
    private String headerLine;

    public HttpRequest(Socket connectedClient) {
        this.connectedClient = connectedClient;
        headers = new HashMap<>();
    }

    /**
     * Use this method to read incoming stream.
     * @return BufferedReader from HTTP client to read was coming
     * @throws IOException
     */
    public BufferedReader bufferClient() throws IOException {
        return inFromClient;
    }

    /**
     * Use this method to send response.
     * @return DataOutputStream of socket HTTP client
     * @throws IOException
     */
    public DataOutputStream outputStreamResponse() throws IOException {
        return outToClient;
    }

    /**
     * You must call this method before any any interaction with this object
     * @throws IOException
     */
    public void init() throws IOException {
        inFromClient = new BufferedReader(new InputStreamReader (connectedClient.getInputStream()));
        outToClient = new DataOutputStream(connectedClient.getOutputStream());

        String requestString = inFromClient.readLine();
        System.out.println(requestString);
        headerLine = requestString;

//        StringTokenizer tokenizer = new StringTokenizer(headerLine);
//        String httpMethod = tokenizer.nextToken();
//        String httpQueryString = tokenizer.nextToken();
        System.out.println("Result : \n");
        String tmp ="";
        ArrayList<String> array = new ArrayList<>();
        initializeHeadersFirstLine(requestString);
        while (inFromClient.ready())
        {
            // Read the HTTP complete HTTP Query
            // System.out.println(requestString);
            tmp =inFromClient.readLine();
            System.out.println(tmp+"----_");
            if(tmp.contains(":"))
                array.add(tmp);
        }
        if (!array.isEmpty()) {
            array.stream().skip(1).forEach(this::addHeader);
        }
        System.out.println(headers);

        //while (bufferClient().ready())
        //    s = s+(bufferClient().readLine());

//        System.out.println("Result : \n"+s);
        //String[] stringTokenizer = s.split("\n\r");
        //initializeHeadersFirstLine(stringTokenizer[0]);

//        for (String element:stringTokenizer){
//            addHeader(element);
//        }

    }

    /**
     * Initialise only the first line of HTTP header request
     * You can access to there component by calling methods
     *  getMethod</br>
     * getHttpQuery</br>
     * getProtocol</br>
     * @param headerLine first line of header
     */
    private void initializeHeadersFirstLine(String headerLine){
        if (headerLine != null) {
            StringTokenizer header = new StringTokenizer(headerLine);
            if (header.countTokens() == 3) {
                headers.put("Method", header.nextToken());
                headers.put("HttpQuery", header.nextToken());
                headers.put("Protocol", header.nextToken());
                return;
            }
        }
                headers.put("Method", "UNKNOWN");
                headers.put("HttpQuery", "UNKNOWN");
                headers.put("Protocol", "UNKNOWN");
    }

    public String getMethod(){
        return headers.get("Method");
    }

    public String getHttpQuery(){
        return headers.get("HttpQuery");
    }

    public String getProtocol(){return headers.get("Protocol");}

    public String getHeaderLine() {
        return headerLine;
    }

    /**
     *
     * @param line One line of header HTTP request
     */
    private void addHeader(String line){
        String[] header = line.split(": ");
        headers.put(header[0],header[1]);
    }


    public HashMap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        String outPut = "";
        headers.forEach((k,v)->outPut.concat(k.concat(":").concat(v).concat("\n")));
        return outPut;
    }

    /**
     This function return an Map of pairs String Key and List of values
     @param url HTTP Query header
     */
    public static Map<String, List<String>> getQueryParams(String url) throws UnsupportedEncodingException {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        String[] urlParts = url.split("\\?");
        //return Empty map because the URL have not parameters
        if (urlParts.length < 2) {
            return params;
        }

        //get the part of query contains parameters
        String query = urlParts[1];
        //Separate all pairs of (key,value)
        for (String param : query.split("&")) {
            //Separate key from value
            String[] pair = param.split("=");
            String key = URLDecoder.decode(pair[0], "UTF-8");
            String value = "";
            //Check if xe have value for this key
            if (pair.length > 1) {
                value = URLDecoder.decode(pair[1], "UTF-8");
            }

            // skip ?& and &&
            if ("".equals(key) && pair.length == 1) {
                continue;
            }

            //
            List<String> values = params.get(key);
            if (values == null) {
                values = new ArrayList<String>();
                params.put(key, values);
            }
            values.add(value);
        }

        return params;
    }
}
