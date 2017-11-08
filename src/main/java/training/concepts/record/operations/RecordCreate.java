package training.concepts.record.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.record.Record;
import training.concepts.record.RecordRepository;
import training.concepts.record.representers.FullRepresenter;
import training.concepts.customer.CustomerRepository;
import training.concepts.customer.Customer;
import training.concepts.application.ErrorRepresenter;
import training.concepts.record.json.RecordJson;

// add client class and dependent exceptions
import training.clients.MessageRestClient;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import training.messaging.MessageSender;

@Service
public class RecordCreate implements OperationInterface {

  @Autowired
  private RecordRepository repository;

  @Autowired
  private CustomerRepository customerRepository;

  // add rest client to send messages to the messages REST api
  private MessageRestClient restClient;

  @Autowired
  private MessageSender amqpClient;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    Map params = (Map) payload.get("params");
    RecordJson json = (RecordJson) params.get("record");


    Application.logger.info("Received record json object: {}", json);

    if (json.getTitle() == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("NO_TITLE",
      "No title was provided!");
      payload.put("representer", rep);
      return payload;
    }

    if (json.getArtist() == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("NO_ARTIST",
      "No artist was provided!");
      payload.put("representer", rep);
      return payload;
    }

    if (json.getCustomerId() == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("NO_CUSTOMER",
      "No customerId was provided!");
      payload.put("representer", rep);
      return payload;
    }


    Customer customer = customerRepository.findOne(json.getCustomerId());
    if (customer == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("NO_CUSTOMER",
      String.format("No Customer with id: %d was found within the database!", json.getCustomerId()));
      payload.put("representer", rep);
      return payload;
    }

    Record model = new Record(json.getTitle());
    model.setCustomer(customer);
    model.setArtist(json.getArtist());
    model.setGenre(json.getGenre());
    model.setFormat(json.getFormat());

    repository.save(model);

    sendMessageViaQueue(model);
    payload.put("httpStatus", HttpStatus.CREATED);
    payload.put("model", model);
    FullRepresenter rep = new FullRepresenter(model);
    payload.put("representer", rep);
    return payload;
  }

  // request the messages API to send a message to the customer's email with record information
  protected void sendMessageViaRest(Record record) {
    try {
      restClient = new MessageRestClient();
      restClient.postMessage(record.getCustomer().getEmail(),
        String.format("New Record added: %s", record.getTitle()),
        String.format("Successfully added record: %s to your library!", record.toString()));
    } catch (ClientProtocolException ex) {
      Application.logger.info("Sending message to Messages API failed! Exception: {}", ex.toString());
    } catch (IOException ex) {
      Application.logger.info("Sending message to Messages API failed! Exception: {}", ex.toString());
    }
  }

  protected void sendMessageViaQueue(Record record) {
    amqpClient.sendMessage(record.getCustomer().getEmail(),
      String.format("New Record added: %s", record.getTitle()),
      String.format("Successfully added record: %s to your library!", record.toString()));
  }
}
