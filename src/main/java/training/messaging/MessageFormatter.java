package training.messaging;

import org.json.simple.JSONObject;
import java.io.IOException;
import java.io.StringWriter;

public class MessageFormatter {

  public String generateJsonString(String recipient, String header, String body) throws IOException {
    JSONObject obj = new JSONObject();

    obj.put("type", "Message");
    obj.put("recipient", recipient);
    obj.put("header", header);
    obj.put("body", body);

    StringWriter out = new StringWriter();
    obj.writeJSONString(out);
    return out.toString();
  }

}
