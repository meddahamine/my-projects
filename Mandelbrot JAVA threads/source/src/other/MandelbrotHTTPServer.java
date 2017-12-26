package other;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;


public class MandelbrotHTTPServer  {

    private static int indexReq = 0;

    private HttpServer server;

    public MandelbrotHTTPServer(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port),0);
            server.createContext("/",new HTTPHandlerSimple());
            server.setExecutor(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        server.start();
    }

    public static void main(String[] argv) throws IOException {
//        // création de la socket
//        int port = 6989;
//        ByteBuffer byteBuffer= new ByteBuffer(1024);
//        ServerSocket serverSocket = new ServerSocket(port);
//        System.err.println("Serveur lancé sur le port : " + port);
//
//        // repeatedly wait for connections, and process
//        while (true) {
//
//            // on reste bloqué sur l'attente d'une demande client
//            Socket clientSocket = serverSocket.accept();
//            System.err.println("Nouveau client connecté");
//
//            // on ouvre un flux de converation
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(clientSocket.getInputStream())
//            );
//            PrintWriter out = new PrintWriter(
//                    new BufferedWriter(
//                            new OutputStreamWriter(clientSocket.getOutputStream())),
//                    true);
//
//            // chaque fois qu'une donnée est lue sur le réseau on la renvoi sur le flux d'écriture.
//            // la donnée lue est donc retournée exactement au même client.
//            String s;
//            out.write("HTTP/1.0 200 OK\r\n");
//            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
//            out.write("Server: Apache/0.8.4\r\n");
//            out.write("Content-Type: text/html\r\n");
//            out.write("Content-Length: 59\r\n");
//            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
//            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
//            out.write("\r\n");
//            out.write("<TITLE>Exemple</TITLE>");
//            out.write("<P>Ceci est une page d'exemple.</P>");
//            byte[] bodyResponses = new byte[(new FileInputStream("../ProjetPW.html")).available()];
//            FileInputStream fileInputStream =new FileInputStream("../ProjetPW.html");
//            clientSocket.getOutputStream().write(bodyResponses);
////            fileInputStream.read(bodyResponses);
////
////            while ((s = fileInputStream.read()) != -1) {
////                System.out.println(s);
////                out.write("<TITLE>Exemple</TITLE>");
////                out.write("<P>Ceci est une page d'exemple.</P>");
////                out.write(s);
////
////            }
//
//            // on ferme les flux.
//            System.err.println("Connexion avec le client terminée");
//            out.close();
//            in.close();
//            clientSocket.close();
//        }


        (new MandelbrotHTTPServer(8000)).start();
    }

    public static class HTTPHandlerSimple implements HttpHandler{

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            indexReq++;
            debugTransaction(httpExchange);

//            handleTextPlain(httpExchange);
            switch (httpExchange.getRequestHeaders().get("Accept").get(0).split(",")[0]){
                case "text/html":
                    System.out.println("HTML response");
//                    handleImageWebP(httpExchange);
                    handleTextPlain(httpExchange);
                    break;

                case "image/webp":
                    System.out.println("IMAGE response");
                    handleImageWebP(httpExchange);
                    break;
            }

        }

        private void debugTransaction(HttpExchange httpExchange){
            System.out.println(indexReq);
            System.out.println("Headers number :"+httpExchange.getRequestHeaders().keySet().size());
            System.out.println("Keys:");
            httpExchange.getRequestHeaders().keySet().forEach(System.out::println);
            System.out.println("Values:");
            httpExchange.getRequestHeaders().values().forEach(System.out::println);
            System.out.println("First value of Accept:");
            System.out.println(httpExchange.getRequestHeaders().get("Accept").get(0));
        }
        private void handleTextPlain(HttpExchange httpExchange) throws IOException{
            int responseOK = 200;
            byte[] bodyResponses = new byte[(new FileInputStream("ProjetPW.html")).available()];
            System.out.println("Body length "+bodyResponses.length);
            (new FileInputStream("ProjetPW.html")).read(bodyResponses);
            httpExchange.sendResponseHeaders(responseOK,bodyResponses.length);
            OutputStream HttpResponseBody = httpExchange.getResponseBody();
            HttpResponseBody.write(bodyResponses);
            HttpResponseBody.close();
        }

        private void handleImageWebP(HttpExchange httpExchange) throws IOException{
            int responseOK = 200;
            //TODO generation of image
            byte[] bodyResponses = new byte[(new FileInputStream("mandelbrotset.png")).available()];
            (new FileInputStream("mandelbrotset.png")).read(bodyResponses);
            httpExchange.sendResponseHeaders(responseOK,bodyResponses.length);
            OutputStream HttpResponseBody = httpExchange.getResponseBody();
            HttpResponseBody.write(bodyResponses);
            HttpResponseBody.close();
        }
    }
}