package training.concepts.customer.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.customer.Customer;
import training.concepts.customer.CustomerRepository;
import training.concepts.application.ErrorRepresenter;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class Delete implements OperationInterface {

  @Autowired
  private CustomerRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    // fetch an individual customer by ID
    Map params = (Map) payload.get("params");
    Long customerId = (Long) params.get("customerId");

    Customer model = repository.findOne(customerId);
    Application.logger.info("Found customer: {}", model);

    if (model == null) {
      Application.logger.info("Could not find customer with id: {}", customerId);
      ErrorRepresenter errorRepresenter = new ErrorRepresenter("NOT_FOUND",
        String.format("The custmer with id: %d could not be found!", customerId));
      payload.put("httpStatus", HttpStatus.NOT_FOUND);
      payload.put("representer", errorRepresenter);
      payload.put("model", null);
      return payload;
    } else {
      repository.delete(model);
      Application.logger.info(String.format("Deleted customer!"));
      payload.put("httpStatus", HttpStatus.NO_CONTENT);
      payload.put("model", null);
      payload.put("representer", null);
      return payload;
    }

  }
}
