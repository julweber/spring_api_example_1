package training.concepts.customer.operations;

import training.Application;
import training.concepts.OperationInterface;
import training.concepts.customer.Customer;
import training.concepts.customer.CustomerRepository;
import training.concepts.customer.representers.FullRepresenter;
import training.concepts.application.ErrorRepresenter;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


@Service
public class Create implements OperationInterface {

  @Autowired
  private CustomerRepository repository;

  public Map<String, Object> run(Map<String, Object> payload) {
    Application.logger.info("Running {} operation with payload: {}", this.getClass(), payload);
    Map params = (Map) payload.get("params");
    Customer model = (Customer) params.get("customer");

    if (model.getPassword() == null) {
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("NO_PASSWORD",
      "No password was provided!");
      payload.put("representer", rep);
      return payload;
    }

    if (!repository.findByEmail(model.getEmail()).isEmpty() ) {
      // TODO: add error response
      payload.put("httpStatus", HttpStatus.UNPROCESSABLE_ENTITY);
      payload.put("model", null);
      ErrorRepresenter rep = new ErrorRepresenter("EMAIL_TAKEN",
      "There is already a customer with the given email address!");
      payload.put("representer", rep);
      return payload;
    }

    model.setRank(0);
    repository.save(model);
    payload.put("httpStatus", HttpStatus.CREATED);
    payload.put("model", model);
    FullRepresenter rep = new FullRepresenter(model);
    payload.put("representer", rep);
    return payload;
  }
}
