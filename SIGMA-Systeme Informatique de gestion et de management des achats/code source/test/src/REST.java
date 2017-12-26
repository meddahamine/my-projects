import java.io.StringReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.http.HTTPBinding;

@WebServiceProvider
@ServiceMode(value = Service.Mode.PAYLOAD)
public class REST implements Provider<Source> {
    public Source invoke(Source source) {
        String replyElement = new String("<p>hello world</p>");

        StreamSource reply = new StreamSource(new StringReader(replyElement));
        return reply;
    }
    public static void main(String args[]) {
        Endpoint e = Endpoint.create(HTTPBinding.HTTP_BINDING, new REST());
        e.publish("http://127.0.0.1:8084/hello/world");
    }
}