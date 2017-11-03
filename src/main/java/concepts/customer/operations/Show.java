package training.concepts.customer.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.customer.Customer;
import training.concepts.customer.CustomerRepository;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;


@Service
public class Show implements OperationInterface {

  @Autowired
  private CustomerRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    // fetch an individual customer by ID
    Map params = (Map) payload.get("params");
    Long customerId = (Long) params.get("customerId");

    Customer model = repository.findOne(customerId);

    Application.logger.info("Found Customer: {}", model.toString());

    payload.put("httpStatus", HttpStatus.OK);
    payload.put("model", model);
    payload.put("json", "{}");
    return payload;
  }
}
