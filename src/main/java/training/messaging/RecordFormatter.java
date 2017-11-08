package training.messaging;

import training.concepts.record.Record;

import org.json.simple.JSONObject;
import java.io.IOException;
import java.io.StringWriter;

public class RecordFormatter {

  public String generateJsonString(Record record) throws IOException {
    JSONObject obj = new JSONObject();

    obj.put("type", "Record");
    obj.put("title", record.getTitle());
    obj.put("artist", record.getArtist());
    obj.put("genre", record.getGenre());
    obj.put("format", record.getFormat());
    obj.put("customerEmail", record.getCustomer().getEmail());

    StringWriter out = new StringWriter();
    obj.writeJSONString(out);
    return out.toString();
  }

}
