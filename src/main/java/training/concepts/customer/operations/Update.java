package training.concepts.customer.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.customer.Customer;
import training.concepts.customer.CustomerRepository;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class Update implements OperationInterface {

  @Autowired
  private CustomerRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    // fetch an individual customer by ID
    Map params = (Map) payload.get("params");
    Long customerId = (Long) params.get("customerId");
    Customer inputCustomer = (Customer) params.get("customer");
    Application.logger.info("Input Customer for update: {}", inputCustomer);

    Customer model = repository.findOne(customerId);

    Application.logger.info("Found Customer: {}", model);

    // return early if customer with id was not found
    if (model == null) {
      payload.put("httpStatus", HttpStatus.NOT_FOUND);
      payload.put("model", null);
      return payload;
    }

    if (inputCustomer.getFirstName() != null) {
      model.setFirstName(inputCustomer.getFirstName());
    }
    if (inputCustomer.getLastName() != null) {
      model.setLastName(inputCustomer.getLastName());
    }
    if (inputCustomer.getEmail() != null) {
      model.setEmail(inputCustomer.getEmail());
    }

    repository.save(model);

    payload.put("httpStatus", HttpStatus.OK);
    payload.put("model", model);
    return payload;
  }
}
